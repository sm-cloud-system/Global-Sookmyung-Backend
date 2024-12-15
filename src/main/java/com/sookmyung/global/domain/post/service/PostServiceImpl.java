package com.sookmyung.global.domain.post.service;

import org.springframework.stereotype.*;

import com.sookmyung.global.domain.post.dto.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  @Override
  public Long createPost(Long memberId, CreatePostRequest request) {
    return null;
  }
}
