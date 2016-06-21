/**
 * @Description : please enter the description
 * @date : 16/6/21 上午10:11
 * @author : keryHu keryhu@hotmail.com
 */
import { NotFoundComponent } from './not-found.component';

export const NotFoundRoutes=[
  {
    path: '404',
    component: NotFoundComponent
  },
  //如果找不到页面,那么就是404错误
  {
    path: '**',
    redirectTo: '/404',
    terminal: true
  }


];

