package com.sookmyung.global.domain.bookmark.controller;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;

public interface BookmarkApi {
  ResponseEntity<ResponseTemplate> createBookmark(Long memberId, Long postId);

  ResponseEntity<ResponseTemplate> deleteBookmark(Long memberId, Long postId);
}
