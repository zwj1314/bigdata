package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class ExtCproductServiceImpl implements ExtCproductService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(ExtCproduct entity) {
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增

        }
        baseDao.saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection<ExtCproduct> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(ExtCproduct.class, id);
        }
    }
}
