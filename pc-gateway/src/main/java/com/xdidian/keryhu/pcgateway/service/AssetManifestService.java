package com.xdidian.keryhu.pcgateway.service;

import com.xdidian.keryhu.pcgateway.domain.AssetManifest;

public interface AssetManifestService {
    AssetManifest fetchAssetManifest();

    void invalidateCache();
}
