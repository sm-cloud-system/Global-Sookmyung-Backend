package com.sookmyung.global.domain.member.controller;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;

public interface MemberApi {
  ResponseEntity<ResponseTemplate<?>> validateNickname(String name);

  ResponseEntity<ResponseTemplate<?>> getProfile(Long memberId);

  ResponseEntity<ResponseTemplate<?>> getMemberPosts(Long memberId);

  ResponseEntity<ResponseTemplate<?>> getMemberBookmarkedPosts(Long memberId);
}
