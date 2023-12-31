package com.ethfundwave.ethfundwave.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter{

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		      String jwt = parseJwt(request);
		      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
		        String username = jwtUtils.getUserNameFromJwtToken(jwt);

		        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
		            userDetails.getAuthorities());
		        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        SecurityContextHolder.getContext().setAuthentication(authentication);
		      }
		    } catch (Exception e) {
		      logger.error("Cannot set user authentication: {}", e);
		    }

		    filterChain.doFilter(request, response);
	}

	
}
