package com.sookmyung.global.domain.comment.controller;

import static com.sookmyung.global.common.code.success.CommentSuccessCode.SUCCESS_CREATE_COMMENT;

import java.net.*;

import jakarta.validation.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.comment.dto.request.*;
import com.sookmyung.global.domain.comment.service.*;

import lombok.*;

@RequestMapping("/comments")
@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {
  private static final String COMMENT_PATH = "/comments/";
  private final CommentService commentService;

  @PostMapping("/posts/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> createComment(
      @AuthMember Long memberId,
      @PathVariable("postId") Long postId,
      @RequestBody @Valid CreateCommentRequest request) {
    Long commentId = commentService.createComment(memberId, postId, request);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path(COMMENT_PATH)
            .path("{id}")
            .buildAndExpand(commentId)
            .toUri();
    HttpHeaders headers = new HttpHeaders();

    headers.setLocation(location);
    return ResponseUtil.success(SUCCESS_CREATE_COMMENT, headers);
  }
}
