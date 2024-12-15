package com.sookmyung.global.domain.post.controller;

import static com.sookmyung.global.common.code.success.PostSuccessCode.SUCCESS_CREATE_POST;
import static com.sookmyung.global.common.code.success.PostSuccessCode.SUCCESS_GET_POST;
import static com.sookmyung.global.common.code.success.PostSuccessCode.SUCCESS_GET_POST_BY_SEARCHING;

import java.net.*;
import java.util.*;

import jakarta.validation.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.post.dto.request.*;
import com.sookmyung.global.domain.post.dto.response.*;
import com.sookmyung.global.domain.post.service.*;

import lombok.*;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {
  private static final String POST_PATH = "/posts/";
  private final PostService postService;

  @PostMapping
  @Override
  public ResponseEntity<ResponseTemplate> createPost(
      @AuthMember Long memberId, @RequestBody @Valid CreatePostRequest request) {
    Long postId = postService.createPost(memberId, request);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path(POST_PATH)
            .path("{id}")
            .buildAndExpand(postId)
            .toUri();
    HttpHeaders headers = new HttpHeaders();

    headers.setLocation(location);
    return ResponseUtil.success(SUCCESS_CREATE_POST, headers);
  }

  @GetMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate<?>> getPost(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    PostResponse response = postService.getPost(memberId, postId);
    return ResponseUtil.success(SUCCESS_GET_POST, response);
  }

  @GetMapping("/search")
  @Override
  public ResponseEntity<ResponseTemplate<?>> getPostsBySearching(
      @RequestParam("searchWord") String searchWord) {
    List<PostsBySearchingResponse> response = postService.getPostsBySearching(searchWord);
    return ResponseUtil.success(SUCCESS_GET_POST_BY_SEARCHING, response);
  }

  @GetMapping
  @Override
  public ResponseEntity<ResponseTemplate<?>> getPosts(@RequestParam("postType") PostType postType) {
    return null;
  }
}
