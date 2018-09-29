package com.itheima.service;

import com.itheima.dao.DictDao;
import com.itheima.domain.Dict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
@Transactional
public class DictServiceImpl implements DictService {

    private DictDao dictDao;

    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    /*
    根据客户类别编码来查询字段
     */
    @Override
    public List<Dict> findByCode(String dict_type_code) {
        return dictDao.findByCode(dict_type_code);
    }
}
