package cn.itcast.jk.service.impl;

import cn.itcast.jk.common.SysConstant;
import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Encrypt;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
public class UserServiceImpl implements UserService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    private SimpleMailMessage mailMessage;
    private JavaMailSender mailSender;

    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public List<User> find(String hql, Class<User> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public User get(Class<User> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(final User entity) {
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增
            String id = UUID.randomUUID().toString();
            entity.setId(id);
            entity.getUserinfo().setId(id);

            //补充shiro添加后的bug
            entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));

            baseDao.saveOrUpdate(entity);//记录保存

            //再开启一个新的线程完成邮件发送的功能，为了不影响员工的新增，即使邮件发送失败，员工记录照样可以保存
            /*Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MailUtil.sendMessage(entity.getUserinfo().getEmail(), "新员工入职系统账户通知", "欢迎您加入本集团，您的用户名："+entity.getUserName()+",初始密码为："+SysConstant.DEFAULT_PASS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();*/

            //spring集成javaMail
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mailMessage.setTo(entity.getUserinfo().getEmail());
                        mailMessage.setSubject("新员工入职系统账户通知");
                        mailMessage.setText("欢迎您加入本集团，您的用户名：" + entity.getUserName() + ",初始密码为：" + SysConstant.DEFAULT_PASS);
                        mailSender.send(mailMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        } else {
            //修改
            baseDao.saveOrUpdate(entity);
        }


    }

    @Override
    public void saveOrUpdateAll(Collection<User> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<User> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<User> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(User.class, id);
        }
    }
}
