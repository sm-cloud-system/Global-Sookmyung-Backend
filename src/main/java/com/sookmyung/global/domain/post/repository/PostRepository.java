package com.sookmyung.global.domain.post.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.post.entity.*;

public interface PostRepository extends JpaRepository<Post, Long> {}
