package com.sookmyung.global.domain.comment.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.comment.entity.*;
import com.sookmyung.global.domain.post.entity.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  int countByPost(Post post);

  List<Comment> findAllByPost(Post post);
}
