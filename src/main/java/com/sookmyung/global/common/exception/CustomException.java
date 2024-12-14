package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

import lombok.*;

@Getter
public class CustomException extends RuntimeException {
  private final ExceptionCode exceptionCode;

  public CustomException(ExceptionCode exceptionCode) {
    super(exceptionCode.message());
    this.exceptionCode = exceptionCode;
  }

  public int getHttpStatus() {
    return exceptionCode.status().value();
  }
}
