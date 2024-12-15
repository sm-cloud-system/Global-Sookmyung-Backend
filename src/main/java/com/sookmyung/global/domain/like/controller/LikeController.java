package com.sookmyung.global.domain.like.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;

import lombok.*;

@RequestMapping("/likes/posts")
@RestController
@RequiredArgsConstructor
public class LikeController implements LikeApi {

  @PostMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> createLike(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    return null;
  }

  @DeleteMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> deleteLike(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    return null;
  }
}
