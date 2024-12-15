package com.sookmyung.global.domain.like.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.like.entity.*;
import com.sookmyung.global.domain.like.repository.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;
import com.sookmyung.global.domain.post.entity.*;
import com.sookmyung.global.domain.post.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeServiceImpl implements LikeService {
  private final MemberRepository memberRepository;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;

  @Transactional
  @Override
  public Long createLike(final Long memberId, final Long postId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    Like like = Like.builder().member(member).post(post).build();
    likeRepository.save(like);
    return like.getId();
  }

  @Transactional
  @Override
  public boolean deleteLike(Long memberId, Long postId) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    likeRepository.deleteByMemberAndPost(member, post);
    return true;
  }
}
