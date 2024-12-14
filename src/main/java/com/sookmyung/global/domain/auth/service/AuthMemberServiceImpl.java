package com.sookmyung.global.domain.auth.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.service.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthMemberServiceImpl implements AuthMemberService {
  private final MemberService memberService;
  private final InternationalStudentService internationalStudentService;

  @Transactional
  @Override
  public void SignUp(SignUpRequest request) {
    Member member = memberService.createMember(request);
    if (request.isInternational()) {
      internationalStudentService.createInternationalStudent(
          member, request.nationalityName(), request.homeUniversityName());
    }
  }

  @Override
  public IssueTokenResponse login(AuthRequest request) {
    return null;
  }
}
