package com.lugd.album.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lugd.album.security.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenAuthenticationFilter jwtAuthenticationTokenFilter() throws Exception {
		return new TokenAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// custom JWT based security filter
		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class);	
		httpSecurity.cors().and();
		httpSecurity
			.authorizeRequests()
				// any request others authenticated
				.anyRequest().authenticated()
			// execute exception
			.and().exceptionHandling().accessDeniedPage("/error")
			.and().csrf().disable();
			

	}
		
	@Override
	public void configure( WebSecurity web ) throws Exception {
	    web.ignoring().antMatchers( HttpMethod.OPTIONS, "/**" );
	}

}
