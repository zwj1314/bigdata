package com.taotao.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/11
 * @description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }
}
