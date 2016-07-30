/**
 * @Description : 客户公司  admin 拥有的权限,在这个客户公司产品内,这个权限最高
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {roleEnum} from "../../model/roleEnum";


@Injectable()
export class CompanyAdminGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    const roles=[roleEnum.ROLE_COMPANY_ADMIN,roleEnum.ROLE_XDIDIAN_ADMIN,roleEnum.ROLE_XDIDIAN_SERVICE];

    if (this.auth.hasAnyRole(roles)) {
      return true;
    }
    this.router.navigate(['accessDenied']);
    return false;

  }

}
