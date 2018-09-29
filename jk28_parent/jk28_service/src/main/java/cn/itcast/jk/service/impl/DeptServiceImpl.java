package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
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
public class DeptServiceImpl implements DeptService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public Dept get(Class<Dept> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(Dept entity) {
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增
            entity.setState(1);//1启用 0停用 默认为启用
        }
        baseDao.saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection<Dept> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<Dept> entityClass, Serializable id) {
        //递归删除(有哪些字部门的父部门id为传入进来的id值)
        String hql = "from Dept where parent.id=?";
        List<Dept> list = baseDao.find(hql, Dept.class, new Object[]{id});//查询当前部门下的子部门列表
        if (list != null && list.size() > 0) {//说明当前部门下有子部门
            for (Dept dept : list){
                deleteById(Dept.class, dept.getId());//递归调用
            }
        }
        baseDao.deleteById(entityClass, id);//删除父部门
    }

    @Override
    public void delete(Class<Dept> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(Dept.class, id);
        }
    }
}
