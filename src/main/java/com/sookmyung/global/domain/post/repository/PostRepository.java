package com.sookmyung.global.domain.post.repository;

import static com.sookmyung.global.common.code.fail.PostExceptionCode.NOT_FOUND_POST;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.post.entity.*;

public interface PostRepository extends JpaRepository<Post, Long> {
  default Post findByIdOrThrow(Long postId) {
    return findById(postId).orElseThrow(() -> new PostException(NOT_FOUND_POST));
  }
}
