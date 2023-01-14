package io.study.deneb;

import io.study.deneb.model.User;
import io.study.deneb.repository.UserRepository;
import io.study.deneb.security.RsaKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
    return args -> {
      userRepository.save(new User("deneb", encoder.encode("1234"), "ROLE_USER"));
    };
  }

}
