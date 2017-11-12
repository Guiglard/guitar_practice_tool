package mylogger;

import java.lang.System.*;

public class MyLoggerFinder extends LoggerFinder {

  @Override
  public Logger getLogger(String name, Module module) {
    return new MyLogger();
  }
}