/**
 * @Description : 判断 点击"重新发送",倒计时是否结束。
 * @date : 16/7/19 上午8:06
 * @author : keryHu keryhu@hotmail.com
 */


export default function ClickNotExpired():boolean {
  const m=localStorage.getItem('num');

  //如果m存在,且值大于0
  if(m&&+m>0){
    return true;
  }
  else {
    return false;
  }

}
