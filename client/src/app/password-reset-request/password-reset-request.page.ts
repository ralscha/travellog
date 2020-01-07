import {Component} from '@angular/core';
import {NavController} from '@ionic/angular';
import {AuthService} from '../service/auth.service';
import {MessagesService} from '../service/messages.service';

@Component({
  selector: 'app-password-reset-request',
  templateUrl: './password-reset-request.page.html',
  styleUrls: ['./password-reset-request.page.scss'],
})
export class PasswordResetRequestPage {

  resetSent = false;

  constructor(private readonly navCtrl: NavController,
              private readonly authService: AuthService,
              private readonly messagesService: MessagesService) {
  }

  async resetRequest(email: string) {

    const loading = await this.messagesService.showLoading('Sending email');

    this.authService.resetPasswordRequest(email)
      .subscribe(async () => {
        await loading.dismiss();
        await this.messagesService.showSuccessToast('Email successfully sent');
        this.resetSent = true;
      }, () => {
        loading.dismiss();
        this.messagesService.showErrorToast('Sending email failed');
      });

  }


}
