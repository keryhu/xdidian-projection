package com.xdidian.keryhu.pcgateway.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xdidian.keryhu.pcgateway.domain.FrontUrl;
import com.xdidian.keryhu.pcgateway.service.AssetManifestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
