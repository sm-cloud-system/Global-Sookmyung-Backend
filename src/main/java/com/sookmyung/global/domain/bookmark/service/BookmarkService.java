package com.sookmyung.global.domain.bookmark.service;

public interface BookmarkService {
  Long createBookmark(Long memberId, Long postId);

  boolean deleteBookmark(Long memberId, Long postId);
}
