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

  logs: Log[] = [];

  constructor(private readonly httpClient: HttpClient,
              private readonly route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params.id;

    this.httpClient.get(`/be/logview_name/${id}`, {responseType: 'text'}).subscribe(response => this.travellog = response);
    this.httpClient.get<Log[]>(`/be/logview/${id}`).subscribe(response => {
      this.logs = response;
      if (this.logs.length > 0) {
        this.selectedLog = this.logs[0];
      }
    });
  }

  onLogClick(log: Log) {
    console.log(log);
    this.selectedLog = log;
  }

  createdString(log: Log) {
    return format(log.created * 1000, 'yyyy-MM-dd HH:mm');
  }

}
