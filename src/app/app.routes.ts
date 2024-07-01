import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NotasComponent } from './notas/notas.component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'notas/:userId', component: NotasComponent },
  { path: 'sections', component: NotasComponent }

];
