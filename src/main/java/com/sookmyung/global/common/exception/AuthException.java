package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

public class AuthException extends CustomException {
  public AuthException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
