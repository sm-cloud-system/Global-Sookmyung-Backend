package com.sookmyung.global.domain.post.dto.response;

import java.time.format.*;

import com.sookmyung.global.domain.post.entity.*;

public record PostsBySearchingResponse(
    Long postId,
    String postTypeName,
    String title,
    int commentCount,
    String createdAt,
    String authorName,
    int likeCount) {
  public static PostsBySearchingResponse of(Post post, int commentCount, int likeCount) {
    return new PostsBySearchingResponse(
        post.getId(),
        post.getPostType().getBoard(),
        post.getTitle(),
        commentCount,
        post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
        post.getAuthor().getNickname(),
        likeCount);
  }
}
