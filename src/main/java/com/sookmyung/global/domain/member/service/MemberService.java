package com.sookmyung.global.domain.member.service;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.member.entity.*;

public interface MemberService {
  Member createMember(SignUpRequest request);
}
