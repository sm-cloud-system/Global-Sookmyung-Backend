package com.sookmyung.global.domain.member.service;

import com.sookmyung.global.domain.member.entity.*;

public interface InternationalStudentService {
  void createInternationalStudent(Member member, String nationality, String homeUniversity);
}
