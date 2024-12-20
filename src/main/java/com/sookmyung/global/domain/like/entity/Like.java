package com.sookmyung.global.domain.like.entity;

import jakarta.persistence.*;

import com.sookmyung.global.common.base.*;
import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

import lombok.*;

@Entity
@Table(
    name = "Likes",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "post_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @Builder
  private Like(Member member, Post post) {
    this.member = member;
    this.post = post;
  }
}
