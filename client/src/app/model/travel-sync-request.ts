import {Travel} from './travel';

export interface TravelSyncRequest {
  inserted: Travel[];
  updated: Travel[];
  removed: number[];
  gets: number[];
}
