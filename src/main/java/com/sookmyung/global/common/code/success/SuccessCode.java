package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

public interface SuccessCode {
  HttpStatus status();

  String message();
}
