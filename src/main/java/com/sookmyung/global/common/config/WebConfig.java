package com.sookmyung.global.common.config;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.config.annotation.*;

import com.sookmyung.global.common.security.*;

import lombok.*;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new AuthMemberArgumentResolver());
  }
}
