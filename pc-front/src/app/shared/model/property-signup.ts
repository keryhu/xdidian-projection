/**
 * @Description : 物业公司注册提交的model class
 * @date : 16/6/22 上午10:55
 * @author : keryHu keryhu@hotmail.com
 */

export class PropertySignup{
  constructor(
    public companyName : string,  //公司名字
    public email? : string,
    public phone? : string,
    public password? : string,
    public directName? : string    //负责人名字



  ){}
}

