package com.sookmyung.global.domain.member.service;

import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.domain.auth.dto.request.*;
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
    String encodedPassword = passwordEncoder.encode(request.password());
    return memberRepository.findByEmailAndPasswordOrThrow(request.email(), encodedPassword);
  }
}
