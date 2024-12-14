package com.sookmyung.global.domain.member.controller;

import static com.sookmyung.global.common.code.success.MemberSuccessCode.SUCCESS_VALIDATE_NICKNAME;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.member.dto.response.*;
import com.sookmyung.global.domain.member.service.*;

import lombok.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
  private final MemberService memberService;

  @GetMapping("/validate-nickname")
  @Override
  public ResponseEntity<ResponseTemplate<?>> validateNickname(
      @RequestParam("nickname") final String nickname) {
    ValidateNicknameResponse response = memberService.validateNickname(nickname);
    return ResponseUtil.success(SUCCESS_VALIDATE_NICKNAME, response);
  }
}
