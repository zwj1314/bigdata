package com.taotao.service;

import com.taotao.pojo.TbItem;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/11
 * @description:
 */
public interface ItemService {

    TbItem getItemById(Long itemId);
}
