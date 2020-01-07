import {Travel} from './travel';

export interface TravelSyncResponse {
  gets: Travel[];
  updated: { [key: string]: number };
  inserted: { [key: string]: { id: number, ts: number } };
  removed: number[];
}
