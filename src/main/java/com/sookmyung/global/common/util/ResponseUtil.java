package com.sookmyung.global.common.util;

import java.io.*;

import jakarta.servlet.http.*;

import org.springframework.http.*;

import com.fasterxml.jackson.databind.*;
import com.sookmyung.global.common.code.fail.*;
import com.sookmyung.global.common.code.success.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.common.response.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseUtil {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String UTF_8 = "UTF-8";

  public static void generateErrorResponse(
      final HttpServletResponse response, final CustomException exception) throws IOException {
    String bodyValue =
        MAPPER.writeValueAsString(ResponseTemplate.error(exception.getExceptionCode()));

    response.setStatus(exception.getExceptionCode().status().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(UTF_8);
    response.getWriter().write(bodyValue);
  }

  public static <T> ResponseEntity<ResponseTemplate<?>> success(SuccessCode code, T data) {
    return ResponseEntity.status(code.status()).body(ResponseTemplate.success(code, data));
  }

  public static ResponseEntity<ResponseTemplate> success(SuccessCode code) {
    return ResponseEntity.status(code.status()).body(ResponseTemplate.success(code));
  }

  public static <T> ResponseEntity<ResponseTemplate<?>> success(
      SuccessCode code, HttpHeaders headers, T data) {
    return ResponseEntity.status(code.status())
        .headers(headers)
        .body(ResponseTemplate.success(code, data));
  }

  public static ResponseEntity error(ExceptionCode code) {
    return ResponseEntity.status(code.status()).body(ResponseTemplate.error(code));
  }
}
