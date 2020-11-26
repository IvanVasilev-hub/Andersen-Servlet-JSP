package com.example.entity;

public class User {

  private Integer id;
  private String email;
  private String password;
  private Role role;

  public User() {
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    role = Role.GUEST;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(id).append(" ")
        .append(email).append(" ")
        .append(password).append(" ")
        .append(role);
    return sb.toString();
  }
}
