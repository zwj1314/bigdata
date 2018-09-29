package cn.itheima.vo;

import cn.itheima.pojo.Items;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 */
public class QueryVo {
    //商品对象
    private Items items;
    //用户对象
    //订单对象

    //批量删除
    private Integer[] ids;

    //批量修改
    private List<Items> itemsList;



    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }
}
