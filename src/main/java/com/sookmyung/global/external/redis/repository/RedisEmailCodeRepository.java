package com.sookmyung.global.external.redis.repository;

import static com.sookmyung.global.domain.auth.exception.AuthExceptionCode.NOT_FOUND_EMAIL;

import java.util.*;

import org.springframework.data.repository.*;

import com.sookmyung.global.domain.auth.exception.*;
import com.sookmyung.global.external.redis.dto.*;

public interface RedisEmailCodeRepository extends CrudRepository<EmailCodeDTO, String> {
  Optional<EmailCodeDTO> findByEmail(final String email);

  default EmailCodeDTO findByEmailOrThrow(final String email) {
    return findByEmail(email).orElseThrow(() -> new AuthException(NOT_FOUND_EMAIL));
  }
}
