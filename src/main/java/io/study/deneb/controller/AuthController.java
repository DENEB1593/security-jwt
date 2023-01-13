package io.study.deneb.controller;

import io.study.deneb.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final TokenService tokenService;

  public AuthController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping(path = "token")
  public String token(Authentication authentication) {
    log.debug("token is requested by user : {}", authentication.getName());
    String token = tokenService.generate(authentication);
    log.debug("token generated : {}", token);
    return token;
  }

}
