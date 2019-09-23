/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.persistence.dao;

import com.belatrixsf.refactoringexercies.persistence.db.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a data access object class to manage the insertions and queries of
 * a database table.
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class LogsDao {

  private final static int COUNT = 1;
  private final Connection cnn;

  public LogsDao(Connection cnn) {
    this.cnn = cnn;
  }

  /**
   * Register a new row into the logs table to create a new log message
   *
   * @param query
   * @throws java.sql.SQLException
   */
  public void insertNewLog(String query) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = cnn.prepareStatement(query);
      statement.executeUpdate();
    } finally {
      DbConnection.close(statement);
    }

  }

  /**
   * Retrieve the count of existent rows into the logs table
   *
   * @return int number with the registered rows count
   * @throws java.sql.SQLException
   */
  public int getCountLogs() throws SQLException {
    int logs = 0;
    PreparedStatement statement = null;
    try {
      statement = cnn.prepareStatement("select count(*) from logs");
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        logs = rs.getInt(COUNT);
      }
    } finally {
      DbConnection.close(statement);
    }
    return logs;
  }
}
