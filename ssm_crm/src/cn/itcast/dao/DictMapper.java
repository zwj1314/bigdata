package cn.itcast.dao;

import cn.itcast.pojo.BaseDict;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/2
 * @description:
 */
public interface DictMapper {

    public List<BaseDict> findDictByCode(String code);
}
