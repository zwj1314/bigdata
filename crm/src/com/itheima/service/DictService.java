package com.itheima.service;

import com.itheima.domain.Dict;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public interface DictService {
    List<Dict> findByCode(String dict_type_code);
}
