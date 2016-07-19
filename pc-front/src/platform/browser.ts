/**
 * @Description : please enter the description
 * @date : 16/7/16 下午1:41
 * @author : keryHu keryhu@hotmail.com
 */


export * from './browser-providers';

import { PROVIDERS } from './browser-providers';

export const PLATFORM_PROVIDERS = [
  ...PROVIDERS,
];
