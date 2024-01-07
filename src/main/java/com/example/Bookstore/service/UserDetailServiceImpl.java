package com.example.Bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository Urepo;

	public UserDetailServiceImpl(UserRepository userRepository) {
		this.Urepo = userRepository;
	}

	@Override
	//Etsii käyttäjän repositorista käyttäjänimellä, lataa sen ja palauttaa sen.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User currentUser = Urepo.findByUsername(username);
		if(!(currentUser == null)) {
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
				AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
		} else {
			throw new UsernameNotFoundException(username);
			

	}}

}
