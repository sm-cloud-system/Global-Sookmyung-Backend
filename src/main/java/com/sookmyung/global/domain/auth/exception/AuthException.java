package com.sookmyung.global.domain.auth.exception;

import com.sookmyung.global.common.code.*;
import com.sookmyung.global.common.exception.*;

public class AuthException extends CustomException {
  public AuthException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
