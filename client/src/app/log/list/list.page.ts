import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {LogService} from '../../service/log.service';
import {Log} from '../../model/log';
import {TravelService} from '../../service/travel.service';

@Component({
  selector: 'app-log-list',
  templateUrl: './list.page.html',
  styleUrls: ['./list.page.scss'],
})
export class LogListPage implements OnInit {

  logs$: Observable<Log[]>;
  selectedTravel: string;

  constructor(private readonly logService: LogService, private readonly travelService: TravelService) {
  }

  async ngOnInit() {
    this.selectedTravel = await this.travelService.getDefaultTravelName();
    this.logs$ = this.logService.getObservable();
    this.logService.requestSync();
  }

  refresh(event) {
    this.logService.requestSync()
      .finally(() => event.target.complete());
  }

}
