package com.sookmyung.global.common.code;

import org.springframework.http.*;

public interface ExceptionCode {
  HttpStatus status();

  String message();
}
