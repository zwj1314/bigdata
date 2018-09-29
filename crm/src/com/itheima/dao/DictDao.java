package com.itheima.dao;

import com.itheima.domain.Dict;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public interface DictDao {
    List<Dict> findByCode(String dict_type_code);
}
