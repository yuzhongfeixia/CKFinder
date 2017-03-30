package com.afis.jx.security;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			if (encodedPassword.equals(MD5Encoder.encode(rawPassword.toString().getBytes()))) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
