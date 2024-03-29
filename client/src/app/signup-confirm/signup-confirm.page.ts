import {Component, OnInit} from '@angular/core';
import {AuthService} from '../service/auth.service';
import {MessagesService} from '../service/messages.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-signup-confirm',
  templateUrl: './signup-confirm.page.html',
  styleUrls: ['./signup-confirm.page.scss'],
})
export class SignupConfirmPage implements OnInit {

  success!: boolean;

  constructor(private readonly authService: AuthService,
              private readonly route: ActivatedRoute,
              private readonly messagesService: MessagesService) {
  }

  async ngOnInit(): Promise<void> {
    const token = this.route.snapshot.paramMap.get('token');

    if (!token) {
      this.success = false;
      return;
    }

    const loading = await this.messagesService.showLoading('Processing Confirmation');

    this.authService.confirmSignup(token)
      .subscribe(async (success) => {
        await loading.dismiss();
        this.success = success;
      }, () => {
        loading.dismiss();
        this.success = false;
      });
  }

}
