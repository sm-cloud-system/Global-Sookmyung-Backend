package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

public class PostException extends CustomException {
  public PostException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
