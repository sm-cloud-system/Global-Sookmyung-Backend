package com.sookmyung.global.common.security;

import java.util.*;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;

public class MemberAuthentication extends UsernamePasswordAuthenticationToken {

  public MemberAuthentication(
      Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
