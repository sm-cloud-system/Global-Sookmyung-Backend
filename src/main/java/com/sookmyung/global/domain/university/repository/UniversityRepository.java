package com.sookmyung.global.domain.university.repository;

import static com.sookmyung.global.common.code.fail.UniversityExceptionCode.NOT_FOND_UNIVERSITY;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.university.entity.*;

public interface UniversityRepository extends JpaRepository<University, Long> {
  Optional<University> findByName(String name);

  default University findByNameOrThrow(String name) {
    return findByName(name).orElseThrow(() -> new NationException(NOT_FOND_UNIVERSITY));
  }
}
