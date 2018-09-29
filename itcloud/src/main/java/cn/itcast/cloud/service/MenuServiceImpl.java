package cn.itcast.cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.cloud.dao.MenuDao;
import cn.itcast.cloud.domain.Menu;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
    private MenuDao menuDao;
	
	public List<Menu> findPrimaryMenu() {
		return menuDao.findPrimaryMenu();
	}

	public List<Menu> findSecondaryMenu(Long parentId) {
		return menuDao.findByParentId(parentId);
	}
}
