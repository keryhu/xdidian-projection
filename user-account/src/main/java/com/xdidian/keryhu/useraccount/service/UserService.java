package com.xdidian.keryhu.useraccount.service;

import java.util.Optional;

import com.xdidian.keryhu.useraccount.domain.User;




public interface UserService {
	
	public Optional<User> findByIdentity(String identity);

	
	public User save(User user);
	

}
