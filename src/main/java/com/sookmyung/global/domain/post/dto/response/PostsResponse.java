package com.sookmyung.global.domain.post.dto.response;

import java.time.format.*;

import com.sookmyung.global.domain.post.entity.*;

public record PostsResponse(
    Long postId,
    String title,
    int commentCount,
    String createdAt,
    String authorName,
    int likeCount) {
  public static PostsResponse of(Post post, int commentCount, int likeCount) {
    return new PostsResponse(
        post.getId(),
        post.getTitle(),
        commentCount,
        post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
        post.getAuthor().getNickname(),
        likeCount);
  }
}
