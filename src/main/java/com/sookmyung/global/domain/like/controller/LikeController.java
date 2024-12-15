package com.sookmyung.global.domain.like.controller;

import static com.sookmyung.global.common.code.success.LikeSuccessCode.SUCCESS_CREATE_LIKE;
import static com.sookmyung.global.common.code.success.LikeSuccessCode.SUCCESS_DELETE_LIKE;

import java.net.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.like.service.*;

import lombok.*;

@RequestMapping("/likes/posts")
@RestController
@RequiredArgsConstructor
public class LikeController implements LikeApi {
  private static final String LIKE_PATH = "/likes/";
  private final LikeService likeService;

  @PostMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> createLike(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    Long likeId = likeService.createLike(memberId, postId);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path(LIKE_PATH)
            .path("{id}")
            .buildAndExpand(likeId)
            .toUri();
    HttpHeaders headers = new HttpHeaders();

    headers.setLocation(location);
    return ResponseUtil.success(SUCCESS_CREATE_LIKE, headers);
  }

  @DeleteMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> deleteLike(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    likeService.deleteLike(memberId, postId);
    return ResponseUtil.success(SUCCESS_DELETE_LIKE);
  }
}
