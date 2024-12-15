package com.sookmyung.global.domain.comment.service;

import com.sookmyung.global.domain.comment.dto.request.*;

public interface CommentService {
  Long createComment(Long memberId, Long postId, CreateCommentRequest request);
}
