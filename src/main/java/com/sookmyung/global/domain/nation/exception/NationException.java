package com.sookmyung.global.domain.nation.exception;

import com.sookmyung.global.common.code.*;
import com.sookmyung.global.common.exception.*;

public class NationException extends CustomException {
  public NationException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
