/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.business;

import com.belatrixsf.refactoringexercies.persistence.dao.LogsDao;
import com.belatrixsf.refactoringexercies.persistence.db.DbConnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class NewJobLoggerTest {

  private Connection cnn;
  private final static int NEW_COUNT = 3;
  private final static int COUNT_INDIVIDUAL = 1;

  /**
   * Establish the configuration of the custom logger from an archive of
   * properties.
   */
  @BeforeClass
  public static void setUpClass() {
    InputStream inputStream = NewJobLogger.class.getClassLoader()
      .getResourceAsStream("log_data.properties");
    try {
      LogManager.getLogManager().readConfiguration(inputStream);
      assertNotNull(inputStream);
    } catch (IOException | SecurityException e) {
      fail("Data properties cannot be available.");
    }
  }

  @Before
  public void setUp() {
    cnn = DbConnection.getConnection();
    assertNotNull(cnn);
  }

  /**
   * Test of logMessage method, of class NewJobLogger.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testLogMessage() throws Exception {
    LogsDao logsDao = new LogsDao(cnn);
    int logs = logsDao.getCountLogs();
    NewJobLogger.logMessage("Test of message Info.", Level.INFO);
    NewJobLogger.logMessage("Test of message Warning.", Level.WARNING);
    NewJobLogger.logMessage("Test of message Severe.", Level.SEVERE);
    assertEquals(logs + NEW_COUNT, logsDao.getCountLogs());
    DbConnection.close(cnn);
  }

  /**
   * Test of logMessage method, of class NewJobLogger, only sending an info log.
   *
   * @throws Exception
   */
  @Test
  public void testInfoLogMessage() throws Exception {
    LogsDao logsDao = new LogsDao(cnn);
    int logs = logsDao.getCountLogs();
    NewJobLogger.logMessage("Test of message Info.", Level.INFO);
    assertEquals(logs + COUNT_INDIVIDUAL, logsDao.getCountLogs());
    DbConnection.close(cnn);
  }

  /**
   * Test of logMessage method, of class NewJobLogger, only sending a warning
   * log.
   *
   * @throws Exception
   */
  @Test
  public void testWarningLogMessage() throws Exception {
    LogsDao logsDao = new LogsDao(cnn);
    int logs = logsDao.getCountLogs();
    NewJobLogger.logMessage("Test of message Warning.", Level.WARNING);
    assertEquals(logs + COUNT_INDIVIDUAL, logsDao.getCountLogs());
    DbConnection.close(cnn);
  }

  /**
   * Test of logMessage method, of class NewJobLogger, only sending a severe
   * log.
   *
   * @throws Exception
   */
  @Test
  public void testSevereLogMessage() throws Exception {
    LogsDao logsDao = new LogsDao(cnn);
    int logs = logsDao.getCountLogs();
    NewJobLogger.logMessage("Test of message Severe.", Level.SEVERE);
    assertEquals(logs + COUNT_INDIVIDUAL, logsDao.getCountLogs());
    DbConnection.close(cnn);
  }

}
