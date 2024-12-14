package com.sookmyung.global.domain.auth.service;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.service.*;
import com.sookmyung.global.external.jwt.provider.*;
import com.sookmyung.global.external.jwt.service.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthMemberServiceImpl implements AuthMemberService {
  private final MemberService memberService;
  private final InternationalStudentService internationalStudentService;
  private final JwtService jwtService;

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
    Member member = memberService.findMember(request);
    String memberId = member.getId().toString();
    List<String> roles = List.of(member.getRole().name());
    IssueTokenResponse response = jwtService.issueToken(memberId, roles);
    return response;
  }
}
