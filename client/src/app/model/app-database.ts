import Dexie from 'dexie';
import {Injectable} from '@angular/core';
import {ClientError} from './client-error';
import {Travel} from './travel';

@Injectable({
  providedIn: 'root'
})
export class AppDatabase extends Dexie {
  authenticationToken: Dexie.Table<string, number>;
  invalidAuthenticationTokens: Dexie.Table<string, number>;
  travel: Dexie.Table<Travel, number>;
  errors: Dexie.Table<ClientError, string>;

  constructor() {
    super('TravelLogDatabase');
    this.version(1).stores({
      authenticationToken: '++',
      invalidAuthenticationTokens: '++',
      travel: 'id,ts',
      errors: '++id'
    });
  }
}
