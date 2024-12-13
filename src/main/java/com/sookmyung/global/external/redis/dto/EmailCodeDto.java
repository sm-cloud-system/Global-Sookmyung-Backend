package com.sookmyung.global.external.redis.dto;

import org.springframework.data.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.index.*;

import lombok.*;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 600)
public class EmailCodeDto {
  @Id @Indexed private String email;

  private String code;

  @Builder
  private EmailCodeDto(String email, String code) {
    this.email = email;
    this.code = code;
  }
}
