/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.persistence.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.LogManager;

/**
 *
 * @author lord_nightmare
 */
public final class DbConnection {

  /**
   * Give a connection to the database using the values of properties info
   *
   * @return An open connection to make sentences to the database
   */
  public static Connection getConnection() {
    Connection cnn = null;
    try {
      LogManager logManager = LogManager.getLogManager();
      String className = DbConnection.class.getName();
      String dbUrl = logManager.getProperty(className + ".url");
      String dbUser = logManager.getProperty(className + ".user");
      String dbPass = logManager.getProperty(className + ".password");
      Class.forName("org.postgresql.Driver");
      cnn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
      cnn.setAutoCommit(true);
      return cnn;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return cnn;
  }

  /**
   *
   * @param cnn the connection with the database
   */
  public static void close(Connection cnn) {
    close(cnn, null);
  }

  /**
   *
   * @param ps the prepared statement with the SQL instruction
   */
  public static void close(PreparedStatement ps) {
    close(null, ps);
  }

  /**
   * Close a parametrized connection or prepared statement after used to send a
   * query to the database
   *
   * @param cnn the connection with the database, the connection can be null
   * @param ps the prepared statement with the SQL instruction, the prepared
   * statement can be null
   */
  private static void close(Connection cnn, PreparedStatement ps) {
    try {
      if (ps != null) {
        ps.close();
      }
      if (cnn != null) {
        cnn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
