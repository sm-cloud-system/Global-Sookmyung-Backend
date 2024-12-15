package com.sookmyung.global.domain.like.service;

public interface LikeService {
  Long createLike(Long memberId, Long postId);

  boolean deleteLike(Long memberId, Long postId);
}
