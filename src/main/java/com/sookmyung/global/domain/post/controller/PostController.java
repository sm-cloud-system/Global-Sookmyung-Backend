package com.sookmyung.global.domain.post.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.domain.post.dto.*;

import lombok.*;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

  @PostMapping
  @Override
  public ResponseEntity<ResponseTemplate> createPost(
      @AuthMember Long memberId, CreatePostRequest request) {
    return null;
  }
}
