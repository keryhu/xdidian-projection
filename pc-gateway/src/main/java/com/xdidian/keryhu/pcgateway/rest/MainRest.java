package com.xdidian.keryhu.pcgateway.rest;


import com.xdidian.keryhu.pcgateway.domain.FrontUrl;
import com.xdidian.keryhu.pcgateway.service.AssetManifestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : 首页rest配置
 * Date : 2016年06月18日 上午10:46
 * Author : keryHu keryhu@hotmail.com
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(FrontUrl.class)
public class MainRest {

    private final FrontUrl frontUrl;

    private final AssetManifestService assetManifestService;


    @RequestMapping({"/**"})
    public String index(Model model) {
        model.addAttribute("assetHost", frontUrl.getAssetHost());
        model.addAttribute("manifest", assetManifestService.fetchAssetManifest());
        return "index";
    }


}
