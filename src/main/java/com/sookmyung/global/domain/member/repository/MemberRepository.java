package com.sookmyung.global.domain.member.repository;

import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_AUTH_REQUEST;
import static com.sookmyung.global.common.code.fail.MemberExceptionCode.NOT_FOUND_MEMBER;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.member.entity.*;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);

  default Member findByEmailOrThrow(String email) {
    return findByEmail(email).orElseThrow(() -> new AuthException(INVALID_AUTH_REQUEST));
  }

  Optional<Member> findByNickname(String nickname);

  default Member findByIdOrThrow(Long memberId) {
    return findById(memberId).orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
  }
}
