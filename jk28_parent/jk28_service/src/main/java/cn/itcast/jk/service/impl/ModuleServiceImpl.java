package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Module;
import cn.itcast.jk.service.ModuleService;
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
public class ModuleServiceImpl implements ModuleService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public Module get(Class<Module> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(Module entity) {
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增

        }
        baseDao.saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection<Module> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<Module> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<Module> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(Module.class, id);
        }
    }
}
