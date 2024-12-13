package com.sookmyung.global.domain.member.service;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.sookmyung.global.domain.member.entity.*;
import com.sookmyung.global.domain.member.repository.*;
import com.sookmyung.global.domain.nation.entity.*;
import com.sookmyung.global.domain.nation.repository.*;
import com.sookmyung.global.domain.university.entity.*;
import com.sookmyung.global.domain.university.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class InternationalStudentServiceImpl implements InternationalStudentService {
  private final NationRepository nationRepository;
  private final UniversityRepository universityRepository;
  private final InternationalStudentRepository internationalStudentRepository;

  @Transactional
  @Override
  public void createInternationalStudent(Member member, String nationality, String homeUniversity) {
    Nation nation = nationRepository.findByNameOrThrow(nationality);
    University university = universityRepository.findByNameOrThrow(homeUniversity);
    InternationalStudent internationalStudent =
        InternationalStudent.builder()
            .member(member)
            .nationality(nation)
            .homeUniversity(university)
            .build();
    internationalStudentRepository.save(internationalStudent);
  }
}
