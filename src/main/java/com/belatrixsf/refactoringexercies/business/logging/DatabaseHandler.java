/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.business.logging;

import com.belatrixsf.refactoringexercies.persistence.dao.LogsDao;
import com.belatrixsf.refactoringexercies.persistence.db.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Represents the Handler that saves the logs of the application as records of a
 * table of a specific database engine.
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class DatabaseHandler extends Handler {

  private Connection cnn;

  public DatabaseHandler() {
    this.configure();
  }

  /**
   * Establish the format of the record to send into the database.
   */
  private void configure() {
    DatabaseFormatter databaseFormatter = new DatabaseFormatter();
    setFormatter(databaseFormatter);
    this.cnn = DbConnection.getConnection();
  }

  /**
   * Publish de log record as a new row into a database table.
   *
   * @param record log to insertion into the database table.
   */
  @Override
  public void publish(LogRecord record) {
    if (!isLoggable(record)) {
      return;
    }
    this.saveLogRecord(record);
  }

  @Override
  public void flush() {
  }

  @Override
  public void close() throws SecurityException {
    DbConnection.close(cnn);
  }

  /**
   * Call a new Data Access Object to manage the new log into a database table
   *
   * @param record log to insertion into the database table.
   */
  private void saveLogRecord(LogRecord record) {
    try {
      String query = getFormatter().format(record);
      new LogsDao(cnn).insertNewLog(query);
    } catch (SQLException e) {
      reportError(
        "The SQL execution has been failed", 
        e, 
        ErrorManager.FORMAT_FAILURE
      );
    }
  }

}
