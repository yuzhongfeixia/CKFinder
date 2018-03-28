package com.afis.jx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
@Component
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomPasswordEncoder customPasswordEncoder;

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**","/ckfinder_demo.htm").permitAll();
		// http.csrf().disable().authorizeRequests().antMatchers("/css/**",
		// "/js/**", "/img/**").permitAll().anyRequest()
		// .hasAuthority(CoreConstants.OperatortType.ADMIN).and().formLogin().loginPage("/login.htm")
		// .defaultSuccessUrl("/index.htm").successHandler(successHandler).loginProcessingUrl("/login").permitAll()
		// .and().logout().deleteCookies(CookieConstants.WEB_USERNAME).permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
	}
}
