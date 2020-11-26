package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
  private static final String DRIVER = "org.sqlite.JDBC";
  private static final String URL = "jdbc:sqlite:login.db";

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName(DRIVER);
    return DriverManager.getConnection(URL);
  }
}
