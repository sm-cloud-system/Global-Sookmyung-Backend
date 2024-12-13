package com.sookmyung.global.common.enums;

public enum Role {
  /**
   * @note: GUEST 이메일 인증을 성공한 유저
   * @note: SOOKMYUNG_STUDENT 숙명여자대학교 재학생
   * @note: INTERNATIONAL_STUDENT 유학생
   */
  GUEST,
  SOOKMYUNG_STUDENT,
  INTERNATIONAL_STUDENT;

  public static Role checkStudentRole(boolean isInternational) {
    if (isInternational) return Role.INTERNATIONAL_STUDENT;
    return Role.SOOKMYUNG_STUDENT;
  }
}
