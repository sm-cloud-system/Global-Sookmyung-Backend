package com.sookmyung.global.domain.like.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.like.entity.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

public interface LikeRepository extends JpaRepository<Like, Long> {
  void deleteByMemberAndPost(Member member, Post post);
}
