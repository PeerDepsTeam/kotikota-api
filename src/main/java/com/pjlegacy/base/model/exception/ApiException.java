package com.pjlegacy.base.model.exception;

public class ApiException extends RuntimeException{
  private ExceptionType type;

  public ApiException(ExceptionType type, String message){
    super(message);
    this.type = type;
  }

  public enum ExceptionType {
    SERVER_EXCEPTION, CLIENT_EXCEPTION
  }
}
