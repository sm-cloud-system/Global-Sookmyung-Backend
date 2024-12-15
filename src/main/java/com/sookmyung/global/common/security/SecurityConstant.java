package com.sookmyung.global.common.security;

public final class SecurityConstant {
  public static final String[] AUTH_WHITELIST = {
    "/", "/error", "/favicon.ico",
    "/auth/issue/code", "/auth/verify/email", "/auth/login",
    "/member/validate-nickname"
  };
  public static final String[] AUTH_WHITELIST_WILDCARD = {
    "/webjars/**", "/css/**", "/images/**", "/js/**", "/h2-console/**",
  };
  public static final String[] AUTH_WHITELIST_FOR_GUEST = {"/auth/sign-up"};
}
