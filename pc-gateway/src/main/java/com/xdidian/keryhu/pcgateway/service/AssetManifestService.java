package com.xdidian.keryhu.pcgateway.service;

import com.xdidian.keryhu.pcgateway.domain.AssetManifest;

/**
 * Description : 关于前台angular2获取的接口
 * Date : 2016年06月18日 上午10:54
 * Author : keryHu keryhu@hotmail.com
 */
public interface AssetManifestService {
    AssetManifest fetchAssetManifest();

    void invalidateCache();
}
