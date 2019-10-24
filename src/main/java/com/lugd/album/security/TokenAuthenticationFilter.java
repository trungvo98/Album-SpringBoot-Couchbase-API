package com.lugd.album.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.JsonObject;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	@Value("${auth.header}")
	private String AUTH_HEADER;

	@Value("${auth.expires_in}")
	private String EXPIRES_IN;

	@Value("${auth.secret}")
    private String SECRET;
	
	@Autowired
	TokenHelper tokenHelper;

	private String getToken(HttpServletRequest request) {

		String authHeader = request.getHeader(AUTH_HEADER);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}

		return null;
	}
	
	private String getTmpTime(HttpServletRequest request) {

		String authHeader = request.getHeader(EXPIRES_IN);
		if (authHeader != null) {
			return authHeader;
		}

		return null;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.info("=========Client Request========" + request.getRemoteHost() +" :: " + request.getRequestURI() + " :: " + request.getHeader("User-Agent"));
		RequestMatcher v2 = new AntPathRequestMatcher("/v2/**");
		RequestMatcher ui = new AntPathRequestMatcher("/swagger-ui.html");
		RequestMatcher web = new AntPathRequestMatcher("/webjars/**");
		RequestMatcher upload = new AntPathRequestMatcher("/img/**");
		RequestMatcher swg = new AntPathRequestMatcher("/swagger-resources/**");
		RequestMatcher ignored = new OrRequestMatcher(v2, ui, web, swg, upload);

		if (!ignored.matches(request)) {
			Integer error = null;
			String authToken = getToken(request);
			String tmptime = getTmpTime(request);
			authToken = "32143545";
			tmptime = "321435345";
			if (authToken != null && tmptime != null) {
				long tmp = Long.parseLong(tmptime);
				TokenHelper tokenHelper = new TokenHelper();
				boolean checked = tokenHelper.checkToken(authToken, tmp, SECRET);
				//disable for test
				if(checked) {
					error = HttpServletResponse.SC_UNAUTHORIZED;
				}
			} else {
				error = HttpServletResponse.SC_BAD_REQUEST;
			}
			if (error != null) {
				String msg = "";
				switch (error) {
					case HttpServletResponse.SC_NOT_FOUND:
						msg = "404 Page not found";
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						break;
					case HttpServletResponse.SC_UNAUTHORIZED:
						msg = "401 Authorization Required";
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						break;
					default:
						msg = "400 Bad request";
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						break;
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				JsonObject object = new JsonObject();
				object.addProperty("error", 1);
				object.addProperty("msg", msg);
				LOGGER.debug(" :: " + request.getRequestURI() + " :: " + object.toString());
				response.getWriter().write(object.toString());
				return;
			}
		}
		SecurityContextHolder.getContext().setAuthentication(new AnonAuthentication(true));
		chain.doFilter(request, response);

	}

}
