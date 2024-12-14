package com.sookmyung.global.domain.nation.repository;

import static com.sookmyung.global.common.code.fail.NationExceptionCode.NOT_FOND_NATION;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.nation.entity.*;

public interface NationRepository extends JpaRepository<Nation, Long> {
  Optional<Nation> findByName(String name);

  default Nation findByNameOrThrow(String name) {
    return findByName(name).orElseThrow(() -> new NationException(NOT_FOND_NATION));
  }
}
