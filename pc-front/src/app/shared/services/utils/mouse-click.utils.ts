/**
 * @Description : please enter the description
 * @date : 16/8/2 下午8:25
 * @author : keryHu keryhu@hotmail.com
 */


export  function isLeftClicked(e:MouseEvent):boolean {
  return e.button===MouseButtons.Left;
}


export  function isRightClick(e:MouseEvent):boolean {
  return e.button===MouseButtons.Right;
}

export function isEscapsePressed(e:KeyboardEvent):boolean {
  return e.keyCode===Keys.Escape;
}

enum Keys {
  Escape = 27
}

enum MouseButtons{
  Left=0,
  Right=2
}
