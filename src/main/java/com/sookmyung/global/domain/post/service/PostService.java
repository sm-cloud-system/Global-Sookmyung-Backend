package com.sookmyung.global.domain.post.service;

import java.util.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.domain.post.dto.request.*;
import com.sookmyung.global.domain.post.dto.response.*;

public interface PostService {
  Long createPost(Long memberId, CreatePostRequest request);

  PostResponse getPost(Long memberId, Long postId);

  List<PostsBySearchingResponse> getPostsBySearching(String searchWord);

  List<PostsResponse> getPosts(PostType postType);

  List<PostsResponse> getMemberPosts(Long memberId);
}
