package com.xdidian.keryhu.personal_menu.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xdidian.keryhu.personal_menu.domain.Menu;


public interface MenuRepository extends MongoRepository<Menu, String> {
  
  public Optional<Menu> findByUserId(String userId);

}
