package com.example.demo.userDetailsImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;

@Component
public class UserDetailsServiceIml implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<User> optionalUser = userDao.findByUserName(username);

		if (optionalUser.isPresent()) {

			User user = optionalUser.get();
			
//			System.out.println(user);
			
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			return customUserDetails;
//			return (UserDetails) User.builder().userId(user.getUserId()).userName(user.getUserName())
//					.email(user.getEmail()).password(user.getPassword()).role(user.getRole()).build();
		} else {
			
			throw new UsernameNotFoundException("userr not found"+username);
		}
	}

}
