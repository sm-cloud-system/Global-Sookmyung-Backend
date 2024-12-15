package com.sookmyung.global.domain.bookmark.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.bookmark.entity.*;
import com.sookmyung.global.domain.bookmark.repository.*;
import com.sookmyung.global.domain.like.entity.*;
import com.sookmyung.global.domain.like.repository.*;
import com.sookmyung.global.domain.like.service.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;
import com.sookmyung.global.domain.post.entity.*;
import com.sookmyung.global.domain.post.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {
  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final BookmarkRepository bookmarkRepository;

  @Transactional
  @Override
  public Long createBookmark(final Long memberId, final Long postId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    Bookmark bookmark = Bookmark.builder().member(member).post(post).build();
    bookmarkRepository.save(bookmark);
    return bookmark.getId();
  }

  @Transactional
  @Override
  public boolean deleteBookmark(Long memberId, Long postId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    bookmarkRepository.deleteByMemberAndPost(member, post);
    return true;
  }
}
