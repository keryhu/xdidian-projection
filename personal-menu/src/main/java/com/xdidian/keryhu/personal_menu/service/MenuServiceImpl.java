package com.xdidian.keryhu.personal_menu.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.personal_menu.client.UserClient;
import com.xdidian.keryhu.personal_menu.domain.MenuDto;
import com.xdidian.keryhu.personal_menu.domain.MenuType;
import com.xdidian.keryhu.personal_menu.repository.MenuRepository;
import com.xdidian.keryhu.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Component("menuService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {

  private final UserClient userClient;
  private final MenuRepository repository;

  @Override
  public List<MenuDto> getMenu(String userId) {
    // TODO Auto-generated method stub
    // 当前用户的权限。
    Collection<String> authorities = SecurityUtils.getAuthorities();
    boolean isInCompany = userClient.isInComopany(userId);
    boolean defaultMenu = repository.findByUserId(userId).map(e -> e.isDefaultMenus()).orElse(true);
    List<MenuDto> dto = new ArrayList<MenuDto>();
    // 如果未加入任何公司，那么就返回这3个。
    if (!isInCompany) {

      dto.add(new MenuDto(MenuType.NEW_COMPANY.getName(), MenuType.NEW_COMPANY.getUrl()));
      dto.add(new MenuDto(MenuType.JOIN_COMPANY.getName(), MenuType.JOIN_COMPANY.getUrl()));
      dto.add(new MenuDto(MenuType.PERSONAL_INFO.getName(), MenuType.PERSONAL_INFO.getUrl()));

      return dto;
    }
    // 如果用户的权限 是 公司的 管理员，则含有所有的 默认菜单，，不是
    else if (authorities.contains("ROLE_COMPANY_ADMIN")) {
      for (MenuType m : MenuType.values()) {
        dto.add(new MenuDto(m.getName(), m.getUrl()));
      }
      return dto;

      // 当用户的菜单是默认菜单，
    } else if (defaultMenu) {

      dto.add(new MenuDto(MenuType.HOME.getName(), MenuType.HOME.getUrl()));
      dto.add(new MenuDto(MenuType.COMPANY_INFO.getName(), MenuType.COMPANY_INFO.getUrl()));
      dto.add(new MenuDto(MenuType.CAREER_PLANNING.getName(), MenuType.CAREER_PLANNING.getUrl()));
      dto.add(new MenuDto(MenuType.PERFORMANCE_APPRAISAL.getName(),
          MenuType.PERFORMANCE_APPRAISAL.getUrl()));
      dto.add(
          new MenuDto(MenuType.ATTENDANCE_SALARY.getName(), MenuType.ATTENDANCE_SALARY.getUrl()));
      dto.add(
          new MenuDto(MenuType.RELEASE_MANAGEMENT.getName(), MenuType.RELEASE_MANAGEMENT.getUrl()));
      dto.add(new MenuDto(MenuType.REPORT_TRAINING.getName(), MenuType.REPORT_TRAINING.getUrl()));
      dto.add(new MenuDto(MenuType.INNOVATION_SUGGESTIONS.getName(),
          MenuType.INNOVATION_SUGGESTIONS.getUrl()));
      dto.add(new MenuDto(MenuType.PERSONAL_INFO.getName(), MenuType.PERSONAL_INFO.getUrl()));

      return dto;
    }

    // 自定义菜单。
    else if (!defaultMenu) {
      List<MenuType> t = repository.findByUserId(userId).map(e -> e.getMenuTypes()).get();
      
      return  t.stream().map(e->new MenuDto(e.getName(),e.getUrl()))
      .collect(Collectors.toList());
    }
    return null;
  }

}
