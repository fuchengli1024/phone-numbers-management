package com.codetest.fucheng.phonenumbers.exception;

import java.util.List;

public class NotFoundException extends RuntimeException
{

  private int statusCode;


  public NotFoundException(String message)
  {
    super(message);
  }

  public NotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public NotFoundException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
