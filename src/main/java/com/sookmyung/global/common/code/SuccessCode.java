package com.sookmyung.global.common.code;

import org.springframework.http.*;

public interface SuccessCode {
  HttpStatus status();

  String message();
}
