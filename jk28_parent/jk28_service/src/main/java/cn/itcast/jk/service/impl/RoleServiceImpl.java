package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class RoleServiceImpl implements RoleService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public Role get(Class<Role> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(Role entity) {
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增

        }
        baseDao.saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection<Role> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<Role> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<Role> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(Role.class, id);
        }
    }
}
