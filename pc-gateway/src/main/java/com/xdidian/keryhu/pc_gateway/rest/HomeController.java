package com.xdidian.keryhu.pc_gateway.rest;


import com.xdidian.keryhu.pc_gateway.domain.FrontUrl;
import com.xdidian.keryhu.pc_gateway.service.AssetManifestService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Description : 首页rest配置
 * @date : 2016年6月18日 下午9:11:14
 * @author : keryHu keryhu@hotmail.com
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableConfigurationProperties(FrontUrl.class)
public class HomeController {

    private final FrontUrl frontUrl;

    private final AssetManifestService assetManifestService;


    @RequestMapping({"/**"})
    public String index(Model model) {
        model.addAttribute("assetHost", frontUrl.getAssetHost());
        model.addAttribute("manifest", assetManifestService.fetchAssetManifest());
        return "index";
    }


}
