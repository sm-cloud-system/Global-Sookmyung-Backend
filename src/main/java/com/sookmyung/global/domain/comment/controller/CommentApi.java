package com.sookmyung.global.domain.comment.controller;

import jakarta.validation.*;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.domain.comment.dto.request.*;

public interface CommentApi {
  ResponseEntity<ResponseTemplate> createComment(
      Long memberId, Long postId, @Valid CreateCommentRequest request);
}
