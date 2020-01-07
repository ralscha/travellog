import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {IonicModule} from '@ionic/angular';
import {LogEditPage} from './edit.page';

const routes: Routes = [
  {
    path: ':id',
    component: LogEditPage
  },
  {
    path: '',
    component: LogEditPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [LogEditPage]
})
export class LogEditPageModule {
}
