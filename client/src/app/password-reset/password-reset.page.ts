import {Component, OnInit} from '@angular/core';
import {AuthService} from '../service/auth.service';
import {ActivatedRoute} from '@angular/router';
import {MessagesService} from '../service/messages.service';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.page.html',
  styleUrls: ['./password-reset.page.scss'],
})
export class PasswordResetPage implements OnInit {

  success: boolean = null;
  resetToken: string;
  submitError: string = null;

  constructor(private readonly authService: AuthService,
              private readonly route: ActivatedRoute,
              private readonly messagesService: MessagesService) {
  }

  async ngOnInit() {
    this.resetToken = this.route.snapshot.paramMap.get('token');

    if (!this.resetToken) {
      this.success = false;
    }
  }

  async reset(password: string) {
    const loading = await this.messagesService.showLoading('Changing Password');

    this.authService.resetPassword(this.resetToken, password)
      .subscribe(async (response) => {
        await loading.dismiss();
        this.submitError = null;
        if (!response) {
          await this.messagesService.showSuccessToast('Password successfully changed');
          this.success = true;
        } else if (response === 'WEAK_PASSWORD') {
          this.submitError = 'weakPassword';
          await this.messagesService.showErrorToast('Weak password');
        } else if (response === 'INVALID') {
          this.success = false;
          await this.messagesService.showErrorToast('Password change failed');
        }
      }, () => {
        this.success = false;
        loading.dismiss();
        this.messagesService.showErrorToast('Password change failed');
      });

  }

}
