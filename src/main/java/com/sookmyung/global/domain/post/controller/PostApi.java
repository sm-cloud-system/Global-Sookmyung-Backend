package com.sookmyung.global.domain.post.controller;

import jakarta.validation.*;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.domain.post.dto.*;

public interface PostApi {
  ResponseEntity<ResponseTemplate> createPost(Long memberId, @Valid CreatePostRequest request);
}
