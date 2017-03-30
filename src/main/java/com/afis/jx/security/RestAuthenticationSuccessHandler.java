package com.afis.jx.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// Cookies cookies = Cookies.initFromServlet(request, response);
		// cookies.set(CookieConstants.WEB_USERNAME,
		// SessionUtil.getOperatorName());
		super.onAuthenticationSuccess(request, response, authentication);

	}
}
