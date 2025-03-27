package com.gabriel.SchoolManagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetails userDetails;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetails userDetails) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetails = userDetails;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String email = null;
		String token = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			//remove "Bearer " from the header to get just the token itself
			token = authorizationHeader.substring(7);
			try {
				email = jwtTokenProvider.extractEmail(token);
			} catch (Exception e) {
				logger.error("Invalid JWT token: {}", e);
			}
		}
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetails.loadUserByUsername(email);

			if (jwtTokenProvider.validateToken(token, userDetails.getUsername())) {
				// Extract roles from JWTg
				String role = jwtTokenProvider.extractRole(token);

				//getting the list of authorized roles since doesn't accept just one
				List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(

						userDetails, null, authorities);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
