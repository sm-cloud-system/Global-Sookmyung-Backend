package com.sookmyung.global.domain.member.repository;

import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_AUTH_REQUEST;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.member.entity.*;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmailAndPassword(String email, String password);

  default Member findByEmailAndPasswordOrThrow(String email, String password) {
    return findByEmailAndPassword(email, password)
        .orElseThrow(() -> new AuthException(INVALID_AUTH_REQUEST));
  }
}
