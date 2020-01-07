import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppDatabase} from '../model/app-database';
import {Log} from '../model/log';
import {SyncService} from './sync.service';

@Injectable({
  providedIn: 'root'
})
export class LogService extends SyncService<Log> {

  constructor(httpClient: HttpClient, appDatabase: AppDatabase) {
    super(httpClient, appDatabase);
  }

  protected changed(oldEntry: Log, newEntry: Log): boolean {
    return oldEntry.location !== newEntry.location
      || oldEntry.report !== newEntry.report
      || oldEntry.created !== newEntry.created
      || oldEntry.lat !== newEntry.lat
      || oldEntry.lng !== newEntry.lng;
  }

  protected getTableName(): string {
    return 'log';
  }

  protected getUrlPrefix(): string {
    return 'log';
  }

}
