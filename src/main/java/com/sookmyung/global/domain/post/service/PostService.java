package com.sookmyung.global.domain.post.service;

import com.sookmyung.global.domain.post.dto.*;

public interface PostService {
  Long createPost(Long memberId, CreatePostRequest request);
}
