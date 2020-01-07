import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {IonicModule} from '@ionic/angular';
import {AuthGuard} from '../../service/auth.guard';
import {LogListPage} from './list.page';

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
    loadChildren: () => import('../edit/edit.module').then(m => m.LogEditPageModule),
    canActivate: [AuthGuard],
    data: {role: 'USER', offline: true}
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
  declarations: [LogListPage]
})
export class LogListPageModule {
}
