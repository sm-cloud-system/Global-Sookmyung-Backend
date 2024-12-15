package com.sookmyung.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@EnableJpaAuditing
public class GlobalApplication {

  public static void main(String[] args) {
    SpringApplication.run(GlobalApplication.class, args);
  }
}
