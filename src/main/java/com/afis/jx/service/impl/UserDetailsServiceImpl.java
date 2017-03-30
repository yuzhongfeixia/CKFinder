package com.afis.jx.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.afis.jx.security.CustomUserDetails;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.debug("[登录] username = {}", username);

		// OperatorExample example = new OperatorExample();
		// example.createCriteria().andLoginNameEqualTo(username);
		// List<Operator> list = operatorMapper.selectByExample(example);
		// if (list != null && !list.isEmpty()) {
		// Operator opr = list.get(0);
		//
		// final Collection<SimpleGrantedAuthority> authorities = new
		// ArrayList<>();
		// authorities.add(new SimpleGrantedAuthority(opr.getType()));// 设置角色
		// // return new
		// // org.springframework.security.core.userdetails.User(username,
		// // opr.getPassword(), authorities);
		// logger.debug("用户{}---角色{}", username, opr.getType());
		// return new CustomUserDetails(username, opr.getPassword(), null,
		// authorities);
		// } else {
		// throw new UsernameNotFoundException(username + " cannot be found");
		// }
		return null;
	}
}
