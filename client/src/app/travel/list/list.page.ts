import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Travel} from '../../model/travel';
import {TravelService} from '../../service/travel.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.page.html',
  styleUrls: ['./list.page.scss'],
})
export class ListPage implements OnInit {

  travels$: Observable<Travel[]>;

  constructor(private readonly travelService: TravelService) {
  }

  ngOnInit() {
    this.travels$ = this.travelService.getTravels();
    this.travelService.requestSync();
  }

  refresh(event) {
    this.travelService.requestSync()
      .finally(() => event.target.complete());
  }

}
