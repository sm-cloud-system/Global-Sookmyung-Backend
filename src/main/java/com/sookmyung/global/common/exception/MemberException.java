package com.sookmyung.global.common.exception;

import com.sookmyung.global.common.code.fail.*;

public class MemberException extends CustomException {
  public MemberException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
