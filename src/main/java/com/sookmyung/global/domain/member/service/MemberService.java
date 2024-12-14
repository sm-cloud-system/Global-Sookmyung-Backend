package com.sookmyung.global.domain.member.service;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.member.dto.response.*;
import com.sookmyung.global.domain.member.entity.*;

public interface MemberService {
  Member createMember(SignUpRequest request);

  Member findMember(AuthRequest request);

  ValidateNicknameResponse validateNickname(String nickname);
}
