package com.xdidian.keryhu.pc_gateway.rest;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xdidian.keryhu.pc_gateway.domain.RefreshToken;
import com.xdidian.keryhu.pc_gateway.repository.RefreshTokenRepository;
import com.xdidian.keryhu.pc_gateway.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j

public class RefreshTokenController {

  private final RefreshTokenRepository repository;
  private final RefreshTokenService tokenService;
  private final int reFreshTokenValiditySeconds = 130;

  @GetMapping("/api/getRefreshToken")
  public ResponseEntity<Map<String, String>> getRefreshToken(Principal principal,
      @RequestParam("loginName") final String loginName) {
    log.info("从服务器下载新的refreshToken");
    Map<String, String> map = new HashMap<String, String>();
    String token = repository.findByLoginName(loginName).map(e -> e.getRefreshToken()).orElse("");
    map.put("refreshToken", token);
    return ResponseEntity.ok().body(map);
  }

  /**
   * 当loginName已经存在的情况下，局部更新，否则新建
   * @param token
   * @param principal
   */
  
  @PostMapping("/api/storeRefreshToken")
  public void storeRefreshToken(@RequestBody final RefreshToken token, Principal principal) {
    log.info("store refreshToken 0-- {} ",token);
    tokenService.validate(token);
   
    boolean m=repository.findByLoginName(token.getLoginName()).isPresent();
    repository.findByLoginName(token.getLoginName()).ifPresent(e->{
      e.setExpiredTime(LocalDateTime.now().plusSeconds(reFreshTokenValiditySeconds));
      e.setRefreshToken(token.getRefreshToken());
      log.info("store refreshToken exist store-- {} ",token);
      repository.save(e);
    });
    
    if(!m){
      token.setExpiredTime(LocalDateTime.now().plusSeconds(reFreshTokenValiditySeconds));
      log.info("store refreshToken new store-- {} ",token);
      repository.save(token);
    }
 
  }

}
