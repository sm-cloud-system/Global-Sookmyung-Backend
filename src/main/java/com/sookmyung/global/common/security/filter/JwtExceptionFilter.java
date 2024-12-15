package com.sookmyung.global.common.security.filter;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import com.fasterxml.jackson.databind.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.common.util.*;

import lombok.*;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (AuthException e) {
      ResponseUtil.generateErrorResponse(response, e);
    }
  }
}
