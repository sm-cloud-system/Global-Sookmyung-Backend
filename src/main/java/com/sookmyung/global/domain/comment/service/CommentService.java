package com.sookmyung.global.domain.comment.service;

import java.util.*;

import com.sookmyung.global.domain.comment.dto.request.*;
import com.sookmyung.global.domain.comment.dto.response.*;

public interface CommentService {
  Long createComment(Long memberId, Long postId, CreateCommentRequest request);

  List<CommentResponse> getComments(Long memberId, Long postId);
}
