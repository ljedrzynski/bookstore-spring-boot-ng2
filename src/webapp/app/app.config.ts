import {Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {NewComponent} from "./book/new/new.component";
export const routerConfig: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'about',
    component: NewComponent
  },
  {
    path: 'courses',
    component: ListCom
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];/**
 * Created by lukasz on 08.01.2017.
 */
