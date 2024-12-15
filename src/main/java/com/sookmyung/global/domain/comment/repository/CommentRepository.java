package com.sookmyung.global.domain.comment.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.comment.entity.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
