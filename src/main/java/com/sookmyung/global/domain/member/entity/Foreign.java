package com.sookmyung.global.domain.member.entity;

import jakarta.persistence.*;

import com.sookmyung.global.domain.nation.entity.*;

public class Foreign {
  @Id private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id", nullable = false)
  University homeUniversity;
}
