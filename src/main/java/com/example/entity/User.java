package com.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public boolean addRole(Role role) {
    return roles.add(role);
  }

  public boolean removeRole(Role role) {
    return roles.remove(role);
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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public boolean containsRole(String roleName) {
    long count = roles.stream()
        .filter(role -> role.getRole().toLowerCase().equals(roleName.toLowerCase()))
        .count();
    return count > 0;
  }

  @Override
  public String toString() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (!email.equals(user.email)) return false;
    return password.equals(user.password);
  }

  @Override
  public int hashCode() {
    int result = email.hashCode();
    result = 31 * result + password.hashCode();
    return result;
  }
}
