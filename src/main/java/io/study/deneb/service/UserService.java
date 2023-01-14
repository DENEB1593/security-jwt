package io.study.deneb.service;

import io.study.deneb.model.SecurityUser;
import io.study.deneb.model.User;
import io.study.deneb.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
      .findByUsername(username)
      .map(SecurityUser::new)
      .orElseThrow(() -> new UsernameNotFoundException("user not found [username] : " + username));
  }

}
