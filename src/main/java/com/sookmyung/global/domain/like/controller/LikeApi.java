package com.sookmyung.global.domain.like.controller;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;

public interface LikeApi {
  ResponseEntity<ResponseTemplate> createLike(Long memberId, Long postId);

  ResponseEntity<ResponseTemplate> deleteLike(Long memberId, Long postId);
}
