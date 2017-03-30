package com.afis.jx.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		// QueryResult queryResult = new QueryResult();
		// queryResult.setMessageType(MessageType.LOGIN);
		// queryResult.setResponseCode(CoreConstants.FAIL_RESPONSE_CODE);
		// queryResult.setResponseDesc("用户名或密码错误");
		//
		// String json = JsonUtil.toJson(queryResult);
		// response.getWriter().write(json);
		// response.getWriter().flush();
	}

}
