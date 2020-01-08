import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Log} from './log';
import format from 'date-fns/format';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent implements OnInit {

  travellog: string;

  zoom = 8;
  lat = 51.673858;
  lng = 7.815982;

  selectedLog: Log = null;

  logs: Log[] = [
    {
      created: new Date().getTime() / 1000,
      lat: 51.673858,
      lng: 7.815982,
      location: 'Ko Samui',
      report: 'the report A'
    },
    {
      created: new Date().getTime() / 1000,
      lat: 51.67386,
      lng: 7.81599,
      location: 'Phuket',
      report: 'the report B'
    },
    {
      created: new Date().getTime() / 1000,
      lat: 51.67387,
      lng: 7.815999,
      location: 'Bangkok',
      report: 'the report C'
    }
  ];

  constructor(private readonly httpClient: HttpClient,
              private readonly route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params.id;
    console.log(id);
    this.travellog = 'test';
    this.selectedLog = this.logs[0];
  }

  onLogClick(log: Log) {
    this.selectedLog = log;
  }

  createdString(log: Log) {
    return format(log.created * 1000, 'yyyy-MM-dd HH:mm');
  }

}
