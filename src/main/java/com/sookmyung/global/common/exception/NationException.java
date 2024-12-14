package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

public class NationException extends CustomException {
  public NationException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
