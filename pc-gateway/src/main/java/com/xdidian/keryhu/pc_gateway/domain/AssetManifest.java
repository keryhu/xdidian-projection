package com.xdidian.keryhu.pc_gateway.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



/**
 * @Description : pc 前台angular2 配置信息的class
 * @date : 2016年6月18日 下午9:10:25
 * @author : keryHu keryhu@hotmail.com
 */
@Component
public class AssetManifest implements Serializable {

  private static final long serialVersionUID = 7608704396065800832L;
  private final Map<String, String> manifest;

  public AssetManifest() {
    manifest = new HashMap<>();
  }

  private AssetManifest(Map<String, String> manifest) {
    this.manifest = manifest;
  }

  public static AssetManifest newInstance(String jsonStr) throws IOException {
    TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {};
    Map<String, String> manifest = new ObjectMapper().readValue(jsonStr, typeRef);
    return new AssetManifest(manifest);
  }

  public String get(String key) {
    return Optional.ofNullable(manifest.get(key)).orElse(key);
  }
}
