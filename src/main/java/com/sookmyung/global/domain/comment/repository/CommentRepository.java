package com.sookmyung.global.domain.comment.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.comment.entity.*;
import com.sookmyung.global.domain.post.entity.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  int countByPost(Post post);
}
