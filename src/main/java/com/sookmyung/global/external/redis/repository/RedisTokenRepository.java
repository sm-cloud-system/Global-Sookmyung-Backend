package com.sookmyung.global.external.redis.repository;

import static com.sookmyung.global.domain.auth.code.AuthExceptionCode.UNAUTHORIZED_TOKEN;

import java.util.*;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import com.sookmyung.global.domain.auth.code.*;
import com.sookmyung.global.domain.auth.exception.*;
import com.sookmyung.global.external.redis.dto.*;

@Repository
public interface RedisTokenRepository extends CrudRepository<RefreshTokenDto, String> {
  Optional<RefreshTokenDto> findByMemberId(final String memberId);

  default RefreshTokenDto findByMemberIdOrElseThrowException(final String memberId) {
    return findByMemberId(memberId).orElseThrow(() -> new AuthException(UNAUTHORIZED_TOKEN));
  }
}