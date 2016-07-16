/**
 * @Description : please enter the description
 * @date : 16/6/23 下午7:52
 * @author : keryHu keryhu@hotmail.com
 */
import {FormControl} from '@angular/forms';


export class StringFormatValidator {
  static email(control:FormControl) {

    if (!control.value) {
      return null;
    }
    return StringFormatValidator.emailOfBoolean(control.value) ? null : {emailFormat: true};


  }

  static phone(control:FormControl) {
    if (!control.value) {
      return null;
    }
    return StringFormatValidator.phoneOfBoolean(control.value) ? null : {phoneFormat: true};
  }

  //用户名的匹配,由email或者phone组成,否则报错。
  static emailOrPhone(control:FormControl) {
    if (!control.value) {
      return null;
    }

    //如果既不是email格式,也不是phone格式。那么报错
    const neitherEmailNorPhone = !StringFormatValidator.emailOfBoolean(control.value) &&
      !StringFormatValidator.phoneOfBoolean(control.value);

    if(neitherEmailNorPhone){
      return {emailOrPhone: true};
    }
    else {
      return null;
    }


  }



  // 密码必须是6-20位
  static passwordInSize(control:FormControl){
    if (!control.value) {
      return null;
    }
    const notInSize=control.value.length<6||control.value.length>20;

    return notInSize?{shouldInSize:true}:null;

  }

  //密码必须包含两种组合
  static passwordContainsTwoTypes(control:FormControl){
    const notInSize=control.value.length<6||control.value.length>20;
    if (!control.value||notInSize) {
      return null;
    }
    return StringFormatValidator.passwordOfBoolean(control.value)?null:{containTwoTypes:true};

  }

  static emailOfBoolean(email:string):boolean {
    const EMAIL_REG = /^\w[-.\w]*\@[-a-zA-Z0-9]+(\.[-a-zA-Z0-9]+)*\.(com|cn|net|edu|info|xyz|wang|org|top|ren|club|pub|rocks|band|market|software|social|lawyer|engineer|me|tv|cc|co|biz|mobi|name|asia)$/i;
    const result = EMAIL_REG.test(email);
    return result;
  }

  static phoneOfBoolean(phone:string):boolean {
    const PHONE_REG = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    const result = PHONE_REG.test(phone);
    return result;
  }

  //6-20位包含字母,数字,特殊字符任两种。
  static passwordOfBoolean(password:string):boolean {
    const PASSWORD_REG = /^(?=.*\d)(?=.*[A-Za-z]).{6,20}|(?=.*\d)(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}|(?=.*[A-Za-z])(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}|(?=.*\d)(?=.*[A-Za-z])(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}$/;
    const result = PASSWORD_REG.test(password);
    return result;
  }

  //包含中文或字母的2-20个,可以包含 括号
  static companyNameOfBoolean(name:string):boolean {
    const NAME_REG = /^([\u4e00-\u9fa5\(\)（）]{2,20}|[a-zA-Z\.\s\(\)]{2,20})$/;
    const result = NAME_REG.test(name);
    return result;
  }

  //姓名匹配,只能由2-20个字母或中文,不能有数字和括号。
  static peopleNameOfBoolean(name:string):boolean {
    const PEOPLE_NAME = /^([\u4e00-\u9fa5]{2,20}|[a-zA-Z\.\s]{2,20})$/;
    const result = PEOPLE_NAME.test(name);
    return result;
  }

}

