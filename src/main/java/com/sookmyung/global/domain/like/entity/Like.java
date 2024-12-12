package com.sookmyung.global.domain.like.entity;

import jakarta.persistence.*;

import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.post.entity.*;

import lombok.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "post_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;
}
