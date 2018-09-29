package com.itheima.dao;

import com.itheima.domain.CstLinkmanEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:
 */
public class LinkmanDao {

    //实现保存联系人
    public void save(CstLinkmanEntity man) {

        Session session = HibernateUtils.getCurrentSession();

        session.save(man);

    }
}
