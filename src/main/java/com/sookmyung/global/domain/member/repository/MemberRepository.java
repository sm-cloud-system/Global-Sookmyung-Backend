package com.sookmyung.global.domain.member.repository;

import org.springframework.data.jpa.repository.*;

import com.sookmyung.global.domain.member.entity.*;

public interface MemberRepository extends JpaRepository<Member, Long> {}
