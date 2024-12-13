package com.sookmyung.global.domain.university.exception;

import com.sookmyung.global.common.code.*;
import com.sookmyung.global.common.exception.*;

public class UniversityException extends CustomException {
  public UniversityException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
