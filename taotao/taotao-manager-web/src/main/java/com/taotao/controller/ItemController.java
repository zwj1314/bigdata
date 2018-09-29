package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/11
 * @description:商品管理Controller
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
}
