package com.sookmyung.global.domain.post.dto.response;

import com.sookmyung.global.domain.post.entity.*;

public record PostResponse(
    Boolean isMyPost,
    Long postId,
    String authorName,
    String title,
    String content,
    Boolean isLiked,
    int likeCount,
    Boolean isBookmarked,
    int bookmarkCount) {
  public static PostResponse of(
      boolean isMyPost,
      Post post,
      boolean isLiked,
      int likeCount,
      boolean isBookmarked,
      int bookmarkCount) {
    return new PostResponse(
        isMyPost,
        post.getId(),
        post.getAuthor().getNickname(),
        post.getTitle(),
        post.getContent(),
        isLiked,
        likeCount,
        isBookmarked,
        bookmarkCount);
  }
}
