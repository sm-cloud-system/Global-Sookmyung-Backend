package com.sookmyung.global.domain.member.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;

import lombok.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {

  @GetMapping("/validate-nickname")
  @Override
  public ResponseEntity<ResponseTemplate<?>> validateNickname(
      @RequestParam("name") final String name) {
    return null;
  }
}
