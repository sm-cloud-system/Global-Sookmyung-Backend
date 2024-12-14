package com.sookmyung.global.common.code.fail;

import org.springframework.http.*;

public interface ExceptionCode {
  HttpStatus status();

  String message();
}
