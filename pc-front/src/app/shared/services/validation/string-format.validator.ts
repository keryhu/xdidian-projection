/**
 * @Description : please enter the description
 * @date : 16/6/23 下午7:52
 * @author : keryHu keryhu@hotmail.com
 */
import {FormControl} from '@angular/forms';


export class StringFormatValidator{
  static email(control:FormControl) {

    if (!control.value) {
      return null;
    }
    return StringFormatValidator.emailOfBoolean(control.value) ? null : {emailFormat: true};


  }

  static emailOfBoolean(email:string):boolean {
    const EMAIL_REG = /^\w[-.\w]*\@[-a-zA-Z0-9]+(\.[-a-zA-Z0-9]+)*\.(com|cn|net|edu|info|xyz|wang|org|top|ren|club|pub|rocks|band|market|software|social|lawyer|engineer|me|tv|cc|co|biz|mobi|name|asia)$/gi;
    const result = EMAIL_REG.test(email);
    return result;
  }
}

