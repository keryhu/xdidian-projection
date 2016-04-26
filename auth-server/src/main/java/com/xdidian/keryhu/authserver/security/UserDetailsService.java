package com.xdidian.keryhu.authserver.security;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xdidian.keryhu.domain.AuthUser;

import lombok.RequiredArgsConstructor;

import com.xdidian.keryhu.authserver.service.UserServiceImpl;


@Component("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    
    private  final UserServiceImpl userService;
    

    @Override
	@Transactional
	/**
	* <p>Title: loadUserByUsername</p>
	* <p>Description: 根据用户名，查询数据库系统后台(user-account),是否存在此用户</p>
	* @param username 包含uuid，email phone 的任何一种
	* @return  当前用户的spring security details
	* @throws UsernameNotFoundException 当前用户不存在
	* @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
    	
    return	Optional.ofNullable(userService.findByIdentity(username))
                    .map(a->new org.springframework.security.core.userdetails.User(a.getId(),
				         a.getPassword(), true, true, true, true, 
				         getAuthorities(a)))
                    .orElseThrow(()->new UsernameNotFoundException("用户－"+username+"－非会员用户，请先注册。"));
    	
    	
}
    
    /**
    * @Title: getAuthorities
    * @Description: TODO(获取用户的权限)
    * @param @param 需要被获取的用户class
    * @param @return    返回用户当前权限
    * @return Collection<? extends GrantedAuthority>    返回类型
    * @throws
     */
private Collection<? extends GrantedAuthority>  getAuthorities (AuthUser user){
		
		return user.getRoles().stream()
	               .map(e->new SimpleGrantedAuthority(e.name()))
	               .collect(Collectors.toList());
			}
}
