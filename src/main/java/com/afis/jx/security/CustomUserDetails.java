package com.afis.jx.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{

	private static final long serialVersionUID = 6692042085657025960L;
	private String memberId;
	
	public CustomUserDetails(String username, String password, String memberId, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}
}
