package com.sookmyung.global.domain.comment.entity;

import jakarta.persistence.*;

import com.sookmyung.global.common.base.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Column(nullable = false)
  private String content;

  @Builder
  private Comment(Member member, Post post, String content) {
    this.member = member;
    this.post = post;
    this.content = content;
  }
}
