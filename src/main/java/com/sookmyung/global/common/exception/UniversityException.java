package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

public class UniversityException extends CustomException {
  public UniversityException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
