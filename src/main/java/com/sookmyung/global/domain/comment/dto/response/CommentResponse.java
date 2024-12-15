package com.sookmyung.global.domain.comment.dto.response;

import java.time.format.*;

import com.sookmyung.global.domain.comment.entity.*;

public record CommentResponse(
    Long commentId, String author, String content, String createdAt, Boolean isMyComment) {
  public static CommentResponse of(Comment comment, Boolean isMyComment) {
    return new CommentResponse(
        comment.getId(),
        comment.getMember().getNickname(),
        comment.getContent(),
        comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
        isMyComment);
  }
}
