package com.sookmyung.global.domain.member.service;

import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_AUTH_REQUEST;

import java.util.*;

import javax.naming.*;

import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.member.dto.response.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public Member createMember(SignUpRequest request) {
    Role role = Role.checkStudentRole(request.isInternational());
    String encodedPassword = passwordEncoder.encode(request.password());
    Member newMember =
        Member.builder()
            .email(request.email())
            .password(encodedPassword)
            .role(role)
            .firstName(request.firstName())
            .lastName(request.lastName())
            .birthDate(request.birthDate())
            .nickname(request.nickname())
            .build();
    memberRepository.save(newMember);
    return newMember;
  }

  public Member findMember(AuthRequest request) {
    Member member = memberRepository.findByEmailOrThrow(request.email());
    boolean isNotEqualsPassword =
        !passwordEncoder.matches(request.password(), member.getPassword());

    if (isNotEqualsPassword) {
      throw new AuthException(INVALID_AUTH_REQUEST);
    }
    return member;
  }

  public ValidateNicknameResponse validateNickname(final String nickname) {
    Optional<Member> member = memberRepository.findByNickname(nickname);

    if (member.isPresent()) {
      return ValidateNicknameResponse.of(false);
    }
    return ValidateNicknameResponse.of(true);
  }

  public ProfileResponse getProfile(final Long memberId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    return ProfileResponse.of(member.getNickname());
  }
}
