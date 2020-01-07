import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MessagesService} from '../../service/messages.service';
import {AlertController} from '@ionic/angular';
import {NgForm} from '@angular/forms';
import {TravelService} from '../../service/travel.service';
import {Travel} from '../../model/travel';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.page.html',
  styleUrls: ['./edit.page.scss'],
})
export class EditPage implements OnInit {

  selectedTravel: Travel;

  constructor(private readonly route: ActivatedRoute,
              private readonly router: Router,
              private readonly messagesService: MessagesService,
              private readonly alertController: AlertController,
              private readonly travelService: TravelService) {
  }

  async ngOnInit() {
    const travelIdString = this.route.snapshot.paramMap.get('id');
    if (travelIdString) {
      this.selectedTravel = await this.travelService.getTravel(parseInt(travelIdString, 10));
    } else {
      this.selectedTravel = {
        id: null,
        name: null,
        ts: 0
      };
    }
  }

  async deleteTravel() {
    if (this.selectedTravel) {
      const alert = await this.alertController.create({
        header: 'Delete Travel',
        message: 'Do you really want to delete this entry?</strong>',
        buttons: [
          {
            text: 'Cancel',
            role: 'cancel'
          }, {
            text: 'Delete Travel',
            handler: async () => this.reallyDeleteTravel()
          }
        ]
      });
      await alert.present();
    }
  }

  async save(form: NgForm) {
    this.selectedTravel.name = form.value.name;
    await this.travelService.save(this.selectedTravel);
    await this.messagesService.showSuccessToast('Travel successfully saved', 1000);
    await this.router.navigate(['/travel']);
  }

  private async reallyDeleteTravel() {
    await this.travelService.delete(this.selectedTravel);
    await this.router.navigate(['/travel']);
  }
}
