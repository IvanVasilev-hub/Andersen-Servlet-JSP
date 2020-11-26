package com.example.dao;

import com.example.entity.User;
import com.example.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.entity.Role.GUEST;
import static com.example.entity.Role.valueOf;

public class UserDao {

  public void create(User user) {
    try (Connection conn = JdbcUtil.getConnection()) {
      PreparedStatement st = conn.prepareStatement(
          "Insert into users (email, password, role) VALUES (?, ?, ?)");
      st.setString(1, user.getEmail());
      st.setString(2, user.getPassword());
      st.setString(3, String.valueOf(GUEST));
      st.execute();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public User read(int id) {
    User user = null;
    try (Connection conn = JdbcUtil.getConnection()) {
      PreparedStatement st = conn.prepareStatement(
          "SELECT id, email, password, role FROM users WHERE id = ?");
      st.setInt(1, id);
      ResultSet set = st.executeQuery();
      while (set.next()) {
        user = new User();
        user.setId(set.getInt(1));
        user.setEmail(set.getString(2));
        user.setPassword(set.getString(3));
        user.setRole(valueOf(set.getString(4)));
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return user;
  }

  public User read(String email) {
    User user = null;
    try (Connection conn = JdbcUtil.getConnection()) {
      PreparedStatement st = conn.prepareStatement(
          "SELECT id, email, password, role FROM users WHERE email = ?");
      st.setString(1, email);
      ResultSet set = st.executeQuery();
      while (set.next()) {
        user = new User();
        user.setId(set.getInt(1));
        user.setEmail(set.getString(2));
        user.setPassword(set.getString(3));
        user.setRole(valueOf(set.getString(4)));
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return user;
  }

  public User read(User user) {
    return read(user.getEmail());
  }

  public boolean isExists(User user) {
    return read(user.getEmail()) != null;
  }
}
