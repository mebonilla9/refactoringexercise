/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belatrixsf.refactoringexercies.business.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * Representation of a SimpleFormatter subclass to make a restructured data of a
 * log message into a String acceptable format.
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class DatabaseFormatter extends SimpleFormatter {

  /**
   * Define the structure and string format of row recorded into the database
   * table
   */
  private final static String FORMAT
    = "insert into logs (date_log, date_string, level, message) "
    + "values  (now(), '%1$tF %1$tT', '%4$-7s' ,'%5$s')";

  /**
   * Establish the date of the log record is produced by the application
   */
  private final Date logDate = new Date();

  /**
   * Prepare the log format to send a new row into a database table
   *
   * @param record contains the info of log to record in the database table
   * @return String format established for the data to sent a database table
   */
  @Override
  public synchronized String format(LogRecord record) {
    this.logDate.setTime(record.getMillis());
    String classMethodName = "";
    if (record.getSourceClassName() != null) {
      classMethodName += record.getSourceClassName();
      if (record.getSourceMethodName() != null) {
        classMethodName += " " + record.getSourceMethodName();
      }
    } else {
      classMethodName += record.getLoggerName();
    }
    String formatedMessage = formatMessage(record);
    String stackTraceLog = "";
    if (record.getThrown() != null) {
      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter, true);
      printWriter.println();
      record.getThrown().printStackTrace(printWriter);
      printWriter.close();
      stackTraceLog += stringWriter.toString();
    }
    return String.format(FORMAT, new Object[]{
      this.logDate, classMethodName, record.getLoggerName(),
      record.getLevel().getLocalizedName(), formatedMessage, stackTraceLog
    });
  }

}
