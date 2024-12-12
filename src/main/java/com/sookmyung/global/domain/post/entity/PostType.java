package com.sookmyung.global.domain.post.entity;

import lombok.*;

@RequiredArgsConstructor
public enum PostType {
  ALL_STUDENT("All Students"),
  INTERNATIONAL_STUDENT("International Students");

  private final String name;
}
