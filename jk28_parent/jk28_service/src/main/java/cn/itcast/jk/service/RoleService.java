package cn.itcast.jk.service;

import cn.itcast.jk.domain.Role;
import cn.itcast.jk.utils.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public interface RoleService {
    //查询所有，带条件查询
    public List<Role> find(String hql, Class<Role> entityClass, Object[] params);

    //获取一条记录
    public Role get(Class<Role> entityClass, Serializable id);

    //分页查询，将数据封装到一个page分页工具类对象
    public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params);

    //新增和修改保存
    public void saveOrUpdate(Role entity);

    //批量新增和修改保存
    public void saveOrUpdateAll(Collection<Role> entitys);

    //单条删除，按id
    public void deleteById(Class<Role> entityClass, Serializable id);

    //批量删除
    public void delete(Class<Role> entityClass, Serializable[] ids);
}
