package com.sookmyung.global.domain.post.service;

import com.sookmyung.global.domain.post.dto.request.*;
import com.sookmyung.global.domain.post.dto.response.*;

public interface PostService {
  Long createPost(Long memberId, CreatePostRequest request);

  PostResponse getPost(Long memberId, Long postId);
}
