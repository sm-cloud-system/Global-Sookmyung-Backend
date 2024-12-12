package com.sookmyung.global.external.redis.dto;

import org.springframework.data.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.index.*;

import lombok.*;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 600)
public class EmailCodeDTO {
  @Id @Indexed private String email;

  private String code;
}
