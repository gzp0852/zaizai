package com.shuaibi.zaizaiuser.service.impl;

import com.shuaibi.zaizaicommons.entity.user.Menu;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import com.shuaibi.zaizaiuser.entity.MenuDto;
import com.shuaibi.zaizaiuser.mapper.IMenuMapper;
import com.shuaibi.zaizaiuser.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gzp
 * @date 2018/12/6 16:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuMapper iMenuMapper;

	@Override
	public Result queryMenu() {
		List<Menu> menus = iMenuMapper.queryMenu();
		List<MenuDto> menuDtos = new ArrayList<>();
		getMenu(menus, menuDtos);
		return ResultUtils.success(menuDtos);
	}

	public void getMenu(List<Menu> menus, List<MenuDto> menuDtos) {
		for (Menu menu : menus) {
			if (menu.getFatherId() == 0) {
				if (menu != null) {
					MenuDto menuDto = new MenuDto();

					menuDto.setId(menu.getId());
					menuDto.setName(menu.getName());
					menuDto.setDesc(menu.getDesc());
					menuDto.setLevel(menu.getLevel());
					menuDto.setOrder(menu.getOrder());
					menuDto.setUrl(menu.getUrl());
					getChildMenu(menuDto, menus);
					menuDtos.add(menuDto);
				}
			}
		}
	}

	public void getChildMenu(MenuDto menuDto, List<Menu> menus) {
		List<MenuDto> menuDtos = new ArrayList<>();
		MenuDto sonMenuDto;
		for (Menu menu : menus) {
			if (menu != null) {
				if (menuDto.getId() == menu.getFatherId()) {
					sonMenuDto = new MenuDto();
					sonMenuDto.setId(menu.getId());
					sonMenuDto.setName(menu.getName());
					sonMenuDto.setDesc(menu.getDesc());
					sonMenuDto.setLevel(menu.getLevel());
					sonMenuDto.setOrder(menu.getOrder());
					sonMenuDto.setUrl(menu.getUrl());
					menuDtos.add(sonMenuDto);
					getChildMenu(sonMenuDto, menus);
				}
			}
		}
		menuDto.setChild(menuDtos);
	}
}
