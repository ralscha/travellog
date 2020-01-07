import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppDatabase} from '../model/app-database';
import {BehaviorSubject, Observable} from 'rxjs';
import {Travel} from '../model/travel';
import {TravelSyncRequest} from '../model/travel-sync-request';
import {TravelSyncResponse} from '../model/travel-sync-response';

@Injectable({
  providedIn: 'root'
})
export class TravelService {

  private readonly travelsSubject = new BehaviorSubject<Travel[]>([]);
  private readonly travels$ = this.travelsSubject.asObservable();

  constructor(private readonly httpClient: HttpClient,
              private readonly appDatabase: AppDatabase) {
  }

  private static changed(oldTravel: Travel, newTravel: Travel) {
    return oldTravel.name !== newTravel.name;
  }

  getTravels(): Observable<Travel[]> {
    this.updateSubject();
    return this.travels$;
  }

  getTravel(id: number): Promise<Travel> {
    return this.appDatabase.travel.get(id);
  }

  async delete(travel: Travel) {
    travel.ts = -1;
    await this.appDatabase.travel.put(travel);
    this.requestSync().catch(e => console.log(e));
  }

  async save(travel: Travel) {
    if (!travel.id) {
      travel.id = await this.getNextNewId();
      travel.ts = Math.floor(Date.now() / 1000);
      await this.appDatabase.travel.add(travel);
      this.requestSync().catch(e => console.log(e));
    } else {
      const oldTravel = await this.appDatabase.travel.get(travel.id);
      if (TravelService.changed(oldTravel, travel)) {
        travel.ts = Math.floor(Date.now() / 1000);
        await this.appDatabase.travel.put(travel);
        this.requestSync().catch(e => console.log(e));
      }
    }
  }

  async requestSync() {
    this.updateSubject();

    const syncViewObject = await this.httpClient.get<{ [key: string]: number }>('/be/travel_syncview').toPromise();

    const syncView = new Map<number, number>();
    Object.entries(syncViewObject).forEach(kv => syncView.set(parseInt(kv[0], 10), kv[1]));

    const syncRequest: TravelSyncRequest = {
      inserted: [],
      updated: [],
      removed: [],
      gets: []
    };

    const deleteLocal = [];

    await this.appDatabase.travel.toCollection().each(travel => {
      const serverTimestamp = syncView.get(travel.id);
      if (serverTimestamp) {
        if (travel.ts === -1) {
          syncRequest.removed.push(travel.id);
        } else if (travel.ts > serverTimestamp) {
          syncRequest.updated.push(travel);
        } else if (travel.ts < serverTimestamp) {
          syncRequest.gets.push(travel.id);
        }
        syncView.delete(travel.id);
      } else {
        // not on the server, either insert or delete locally
        if (travel.id < 0) {
          syncRequest.inserted.push(travel);
        } else {
          deleteLocal.push(travel.id);
        }
      }
    });

    // all these ids are not in our local database, fetch them
    syncView.forEach((value, key) => syncRequest.gets.push(key));

    // delete local travel
    let deleted = false;
    for (const id of deleteLocal) {
      await this.appDatabase.travel.delete(id);
      deleted = true;
    }

    // if no changes end sync
    if (syncRequest.inserted.length === 0
      && syncRequest.updated.length === 0
      && syncRequest.removed.length === 0
      && syncRequest.gets.length === 0) {
      if (deleted) {
        this.updateSubject();
      }
      return Promise.resolve();
    }

    // send sync request to the server
    const syncResponse = await this.httpClient.post<TravelSyncResponse>('/be/travel_sync',
      syncRequest).toPromise();

    await this.appDatabase.transaction('rw', this.appDatabase.travel, async () => {
      if (syncResponse.gets && syncResponse.gets.length > 0) {
        await this.appDatabase.travel.bulkPut(syncResponse.gets);
      }
      if (syncResponse.inserted) {
        Object.entries(syncResponse.inserted).forEach(
          async (kv) => {
            const oldId = parseInt(kv[0], 10);
            const travelFromDb = await this.appDatabase.travel.get(oldId);
            travelFromDb.id = kv[1].id;
            travelFromDb.ts = kv[1].ts;
            await this.appDatabase.travel.delete(oldId);
            await this.appDatabase.travel.add(travelFromDb);
          });
      }
      if (syncResponse.updated) {
        Object.entries(syncResponse.updated).forEach(
          async (kv) => await this.appDatabase.travel.update(parseInt(kv[0], 10), {ts: kv[1]}));
      }
      if (syncResponse.removed) {
        syncResponse.removed.forEach(async (id) => await this.appDatabase.travel.delete(id));
      }
    });

    this.updateSubject();
    return Promise.resolve();
  }

  private updateSubject() {
    this.appDatabase.travel.where('ts').notEqual(-1).toArray().then(travel => {
      this.travelsSubject.next(travel);
    });
  }

  private async getNextNewId() {
    const first = await this.appDatabase.travel.toCollection().first();
    if (first) {
      if (first.id > 0) {
        return -1;
      } else {
        return first.id - 1;
      }
    }
    return -1;
  }

}
