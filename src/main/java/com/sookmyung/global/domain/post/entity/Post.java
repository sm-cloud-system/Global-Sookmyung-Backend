package com.sookmyung.global.domain.post.entity;

import jakarta.persistence.*;

import com.sookmyung.global.common.base.*;
import com.sookmyung.global.domain.member.entity.*;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private PostType postType;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private Member author;
}
