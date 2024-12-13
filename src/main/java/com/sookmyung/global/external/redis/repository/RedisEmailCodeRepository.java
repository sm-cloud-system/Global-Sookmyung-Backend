package com.sookmyung.global.external.redis.repository;

import static com.sookmyung.global.domain.auth.code.AuthExceptionCode.NOT_FOUND_EMAIL;

import java.util.*;

import org.springframework.data.repository.*;

import com.sookmyung.global.domain.auth.code.*;
import com.sookmyung.global.domain.auth.exception.*;
import com.sookmyung.global.external.redis.dto.*;

public interface RedisEmailCodeRepository extends CrudRepository<EmailCodeDto, String> {
  Optional<EmailCodeDto> findByEmail(final String email);

  default EmailCodeDto findByEmailOrThrow(final String email) {
    return findByEmail(email).orElseThrow(() -> new AuthException(NOT_FOUND_EMAIL));
  }
}
