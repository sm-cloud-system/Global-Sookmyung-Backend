package com.sookmyung.global.domain.bookmark.controller;

import static com.sookmyung.global.common.code.success.BookmarkSuccessCode.SUCCESS_CREATE_BOOKMARK;
import static com.sookmyung.global.common.code.success.BookmarkSuccessCode.SUCCESS_DELETE_BOOKMARK;

import java.net.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.security.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.bookmark.service.*;

import lombok.*;

@RequestMapping("/bookmarks/posts")
@RestController
@RequiredArgsConstructor
public class BookmarkController implements BookmarkApi {
  private static final String BOOKMARK_PATH = "/bookmarks/";
  private final BookmarkService bookmarkService;

  @PostMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> createBookmark(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    Long bookmarkId = bookmarkService.createBookmark(memberId, postId);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path(BOOKMARK_PATH)
            .path("{id}")
            .buildAndExpand(bookmarkId)
            .toUri();
    HttpHeaders headers = new HttpHeaders();

    headers.setLocation(location);
    return ResponseUtil.success(SUCCESS_CREATE_BOOKMARK, headers);
  }

  @DeleteMapping("/{postId}")
  @Override
  public ResponseEntity<ResponseTemplate> deleteBookmark(
      @AuthMember Long memberId, @PathVariable("postId") Long postId) {
    bookmarkService.deleteBookmark(memberId, postId);
    return ResponseUtil.success(SUCCESS_DELETE_BOOKMARK);
  }
}
