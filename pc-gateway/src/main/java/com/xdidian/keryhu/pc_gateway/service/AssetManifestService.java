package com.xdidian.keryhu.pc_gateway.service;

import com.xdidian.keryhu.pc_gateway.domain.AssetManifest;


/**
 * @Description : 关于前台angular2获取的接口
 * @date : 2016年6月18日 下午9:12:55
 * @author : keryHu keryhu@hotmail.com
 */
public interface AssetManifestService {
  AssetManifest fetchAssetManifest();

  void invalidateCache();
}
