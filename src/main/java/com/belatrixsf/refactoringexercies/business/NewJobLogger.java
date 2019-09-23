/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Represents the object of management the logs into the application.
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public final class NewJobLogger {

  private static final Logger LOG = Logger.getLogger(
    NewJobLogger.class.getName()
  );

  /**
   * Establish the configuration of the custom logger from an archive of
   * properties.
   */
  static {
    InputStream inputStream = NewJobLogger.class.getClassLoader()
      .getResourceAsStream("log_data.properties");
    try {
      LogManager.getLogManager().readConfiguration(inputStream);
    } catch (IOException | SecurityException e) {
      LOG.log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Create a new log into the application, produced by an error, warning or
   * info to the administrator of the application.
   *
   * @param messageLog message text to the log.
   * @param level level of the log.
   * @throws Exception
   */
  public static void logMessage(String messageLog, Level level) throws Exception {
    String cleanLog = messageLog.trim();
    if (cleanLog == null || cleanLog.isEmpty()) {
      return;
    }
    if (level == null) {
      throw new Exception("Error or Warning o Message level must be specified.");
    }
    LOG.log(level, cleanLog);
  }

}
