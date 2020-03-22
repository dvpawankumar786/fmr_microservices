package com.security.jwt.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.security.jwt.dao.UserDao;
import com.security.jwt.model.User;
import com.security.jwt.model.UserDto;
import com.security.jwt.service.UserService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		System.out.println("dddddddd"+user.getCustid());

		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}



	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
    public User save(UserDto user,int custid) {
	    User newUser = new User();
	    System.out.println("andoom"+ThreadLocalRandom.current().nextLong(200));
	    newUser.setId(ThreadLocalRandom.current().nextLong(200));
	    newUser.setCustid(String.valueOf(custid));
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(658745L);
        return userDao.save(newUser);
    }

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
