package com.sookmyung.global.domain.bookmark.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.bookmark.entity.*;
import com.sookmyung.global.domain.like.entity.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
  void deleteByMemberAndPost(Member member, Post post);

  boolean existsLikeByMemberAndPost(Member member, Post post);

  int countByPost(Post post);
}
