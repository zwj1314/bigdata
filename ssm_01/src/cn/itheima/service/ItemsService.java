package cn.itheima.service;

import cn.itheima.pojo.Items;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 */
public interface ItemsService {

    public List<Items> list();

    public Items findItemsById(Integer id);

    public void updateItems(Items items);
}
