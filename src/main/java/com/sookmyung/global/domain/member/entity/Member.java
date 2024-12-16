package com.sookmyung.global.domain.member.entity;

import java.time.*;

import jakarta.persistence.*;

import com.sookmyung.global.common.enums.*;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  Role role;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Builder
  private Member(
      String email,
      String password,
      Role role,
      String firstName,
      String lastName,
      LocalDate birthDate,
      String nickname) {
    this.email = email;
    this.password = password;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.nickname = nickname;
  }
}
