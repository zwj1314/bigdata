package com.itheima.dao;

import com.itheima.domain.Dict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:持久层
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

    @Override
    public List<Dict> findByCode(String dict_type_code) {
        return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
    }
}
