package com.sookmyung.global.domain.comment.dto.response;

import java.time.format.*;

import com.sookmyung.global.domain.comment.entity.*;

public record CommentsResponse(
    Long commentId, String author, String content, String createdAt, Boolean isMyComment) {
  public static CommentsResponse of(Comment comment, Boolean isMyComment) {
    return new CommentsResponse(
        comment.getId(),
        comment.getMember().getNickname(),
        comment.getContent(),
        comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
        isMyComment);
  }
}
