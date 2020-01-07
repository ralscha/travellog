import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MessagesService} from '../../service/messages.service';
import {AlertController} from '@ionic/angular';
import {NgForm} from '@angular/forms';
import {Log} from '../../model/log';
import {LogService} from '../../service/log.service';
import {TravelService} from '../../service/travel.service';

@Component({
  selector: 'app-log-edit',
  templateUrl: './edit.page.html',
  styleUrls: ['./edit.page.scss'],
})
export class LogEditPage implements OnInit {

  selectedLog: Log;

  constructor(private readonly route: ActivatedRoute,
              private readonly router: Router,
              private readonly messagesService: MessagesService,
              private readonly alertController: AlertController,
              private readonly logService: LogService,
              private readonly travelService: TravelService) {
  }

  async ngOnInit() {
    const logIdString = this.route.snapshot.paramMap.get('id');
    if (logIdString) {
      this.selectedLog = await this.logService.getEntry(parseInt(logIdString, 10));
    } else {
      this.selectedLog = {
        id: null,
        created: Math.floor(Date.now() / 1000),
        location: null,
        report: null,
        lat: null,
        lng: null,
        travelId: await this.travelService.getDefaultTravelId(),
        ts: 0
      };
    }
  }

  async deleteLog() {
    if (this.selectedLog) {
      const alert = await this.alertController.create({
        header: 'Delete Log',
        message: 'Do you really want to delete this entry?</strong>',
        buttons: [
          {
            text: 'Cancel',
            role: 'cancel'
          }, {
            text: 'Delete Log',
            handler: async () => this.reallyDeleteLog()
          }
        ]
      });
      await alert.present();
    }
  }

  async save(form: NgForm) {
    this.selectedLog.location = form.value.location;
    this.selectedLog.report = form.value.report;
    this.selectedLog.lat = form.value.lat;
    this.selectedLog.lng = form.value.lng;
    await this.logService.save(this.selectedLog);
    await this.messagesService.showSuccessToast('Travel successfully saved', 1000);
    await this.router.navigate(['/log']);
  }

  private async reallyDeleteLog() {
    await this.logService.delete(this.selectedLog);
    await this.router.navigate(['/log']);
  }
}
