package com.anjaneya.spring.boot.demo.jwt;

import com.anjaneya.spring.boot.demo.constants.ApiPathConstants;
import com.anjaneya.spring.boot.demo.utils.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.text.html.FormSubmitEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;
	private UserDetailsService userDetailsService;

	private static final Set<String> FILTER_PATHS =
			new HashSet<>(Arrays.asList(
					ApiPathConstants.USER_API, ApiPathConstants.USER_API + "/login"
			));

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI();
		String method = request.getMethod();
		return FormSubmitEvent.MethodType.POST.name().equals(method) && FILTER_PATHS.contains(path);
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AtomicBoolean isException = new AtomicBoolean(false);

		// get JWT token from http request
		String token = getTokenFromRequest(request);

		if (StringUtils.hasText(token)) {

			try {
				// get username from token
				String username = jwtTokenProvider.getUsername(token);

				// load the user associated with token
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(userDetails);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			} catch (Exception e) {
				ResponseUtils.setResponse(response, e.getMessage(), isException);
			}
		}

		if (!isException.get()) {
			filterChain.doFilter(request, response);
		}
	}

	private String getTokenFromRequest(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return null;
	}
}
