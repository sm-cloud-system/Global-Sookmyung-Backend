package com.sookmyung.global.domain.post.repository;

import static com.sookmyung.global.common.code.fail.PostExceptionCode.NOT_FOUND_POST;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

public interface PostRepository extends JpaRepository<Post, Long> {
  default Post findByIdOrThrow(Long postId) {
    return findById(postId).orElseThrow(() -> new PostException(NOT_FOUND_POST));
  }

  @Query(
      "select p from Post p where p.title like %:searchWord% or p.content like %:searchWord% order by p.createdAt desc")
  List<Post> findAllBySearchWord(@Param("searchWord") String searchWord);

  List<Post> findAllByPostTypeOrderByCreatedAtDesc(PostType postType);

  List<Post> findAllByAuthorOrderByCreatedAtDesc(Member member);
}
