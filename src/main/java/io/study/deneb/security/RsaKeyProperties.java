package io.study.deneb.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
  // 생성자 내 RSApublickey를 지정하면
  // Spring genericServiceConverter가 properties내 pem 값을 읽어 RSAPublicKey로 변환하는
  // RSAConverter를 호출한다.
}
