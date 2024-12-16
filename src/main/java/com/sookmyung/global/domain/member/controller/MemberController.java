package com.sookmyung.global.domain.member.controller;

import static com.sookmyung.global.common.code.success.MemberSuccessCode.SUCCESS_GET_MEMBER_BOOKMARKED_POSTS;
import static com.sookmyung.global.common.code.success.MemberSuccessCode.SUCCESS_GET_MEMBER_POSTS;
import static com.sookmyung.global.common.code.success.MemberSuccessCode.SUCCESS_GET_PROFILE;
import static com.sookmyung.global.common.code.success.MemberSuccessCode.SUCCESS_VALIDATE_NICKNAME;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.member.dto.response.*;
import com.sookmyung.global.domain.member.service.*;
import com.sookmyung.global.domain.post.dto.response.*;
import com.sookmyung.global.domain.post.service.*;

import lombok.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
  private final MemberService memberService;
  private final PostService postService;

  @GetMapping("/validate-nickname")
  @Override
  public ResponseEntity<ResponseTemplate<?>> validateNickname(
      @RequestParam("nickname") final String nickname) {
    ValidateNicknameResponse response = memberService.validateNickname(nickname);
    return ResponseUtil.success(SUCCESS_VALIDATE_NICKNAME, response);
  }

  @GetMapping("/profile")
  @Override
  public ResponseEntity<ResponseTemplate<?>> getProfile(@AuthMember final Long memberId) {
    ProfileResponse response = memberService.getProfile(memberId);
    return ResponseUtil.success(SUCCESS_GET_PROFILE, response);
  }

  @GetMapping("/posts")
  @Override
  public ResponseEntity<ResponseTemplate<?>> getMemberPosts(@AuthMember final Long memberId) {
    List<PostsResponse> response = postService.getMemberPosts(memberId);
    return ResponseUtil.success(SUCCESS_GET_MEMBER_POSTS, response);
  }

  @GetMapping("/bookmark/posts")
  @Override
  public ResponseEntity<ResponseTemplate<?>> getMemberBookmarkedPosts(
      @AuthMember final Long memberId) {
    List<PostsResponse> response = postService.getMemberBookmarkedPosts(memberId);
    return ResponseUtil.success(SUCCESS_GET_MEMBER_BOOKMARKED_POSTS, response);
  }
}
