package io.study.deneb.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "users")
public class User {

  @Id @GeneratedValue
  private Long id;

  @Column(length = 100, nullable = false)
  private String username;

  @Column(length = 100, nullable = false)
  private String password;

  @Column(nullable = false)
  private String roles;

  public User() {
  }

  public User(String username, String password, String roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
      .append("id", id)
      .append("username", username)
      .append("password", "[protected]")
      .append("roles", roles)
      .toString();
  }
}
