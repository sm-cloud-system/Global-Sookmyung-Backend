package com.sookmyung.global.external.redis.dto;

import org.springframework.data.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.index.*;

import lombok.*;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 604800016)
public class RefreshTokenDto {
  @Id @Indexed private String memberId;

  private String refreshToken;

  public void updateRefreshToken(final String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Builder
  private RefreshTokenDto(final String memberId, final String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }
}
