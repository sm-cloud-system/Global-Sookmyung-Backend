package com.sookmyung.global.domain.member.entity;

import jakarta.persistence.*;

import com.sookmyung.global.domain.nation.entity.*;
import com.sookmyung.global.domain.university.entity.*;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InternationalStudent {
  @Id private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nation_id", nullable = false)
  private Nation nationality;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id", nullable = false)
  private University homeUniversity;

  @Builder
  private InternationalStudent(Member member, Nation nationality, University homeUniversity) {
    this.member = member;
    this.nationality = nationality;
    this.homeUniversity = homeUniversity;
  }
}
