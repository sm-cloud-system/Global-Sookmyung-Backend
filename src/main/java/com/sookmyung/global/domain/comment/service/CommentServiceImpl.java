package com.sookmyung.global.domain.comment.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.comment.dto.request.*;
import com.sookmyung.global.domain.comment.entity.*;
import com.sookmyung.global.domain.comment.repository.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;
import com.sookmyung.global.domain.post.entity.*;
import com.sookmyung.global.domain.post.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;
  private final PostRepository postRepository;

  @Transactional
  @Override
  public Long createComment(final Long memberId, final Long postId, CreateCommentRequest request) {
    Member member = memberRepository.findByIdOrThrow(memberId);
    Post post = postRepository.findByIdOrThrow(postId);
    Comment comment =
        Comment.builder().member(member).post(post).content(request.content()).build();
    commentRepository.save(comment);
    return comment.getId();
  }
}