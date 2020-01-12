package com.security.jwt.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.jwt.model.CustomGrantedAuthority;
import com.security.jwt.model.CustomUserDetails;
import com.security.jwt.model.User;
import com.security.jwt.persistance.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	private final Map<String, UserDetails> users = new HashMap<>();
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user != null) {
			CustomUserDetails customUserDetails = new CustomUserDetails();
			customUserDetails.setUserName(user.getUserName());
			byte[] valueDecoded=null;
			MessageDigest digest;
			valueDecoded = Base64.decodeBase64(user.getPassword());
			System.out.println("Decoded value is " + new String(valueDecoded));
			
			customUserDetails.setPassword(new String(valueDecoded));
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			String userAuth=user.getUserAuthorities();
			StringTokenizer st=new StringTokenizer(userAuth,",");
			while(st.hasMoreTokens())
			{
				authorities.add(new CustomGrantedAuthority(st.nextToken().toString()));
			}
			customUserDetails.setGrantedAuthorities(authorities);
			return customUserDetails;
		}
		throw new UsernameNotFoundException(username);
	}
	
}