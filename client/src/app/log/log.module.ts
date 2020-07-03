import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {IonicModule} from '@ionic/angular';
import {LogListPage} from './list/list.page';
import {LogEditPage} from './edit/edit.page';
import {LogPhotoPage} from './photo/photo.page';
import {AuthGuard} from '../service/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: LogListPage,
    canActivate: [AuthGuard],
    data: {role: 'USER', offline: true},
    pathMatch: 'full'
  },
  {
    path: 'edit',
    component: LogEditPage,
    canActivate: [AuthGuard],
    data: {role: 'USER', offline: true}
  },
  {
    path: 'edit/:id',
    component: LogEditPage,
    canActivate: [AuthGuard],
    data: {role: 'USER', offline: true}
  },
  {
    path: 'photo/:id',
    component: LogPhotoPage,
    canActivate: [AuthGuard],
    data: {role: 'USER', offline: false}
  },
  {
    path: '**',
    redirectTo: '/log'
  },
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [LogListPage, LogEditPage, LogPhotoPage]
})
export class LogModule {
}
