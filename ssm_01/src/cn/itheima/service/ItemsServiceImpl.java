package cn.itheima.service;

import cn.itheima.dao.ItemsMapper;
import cn.itheima.pojo.Items;
import cn.itheima.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> list() {
        //如果不需要任何查询条件，直接将example对象new出来就行了
        ItemsExample example = new ItemsExample();
        List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public Items findItemsById(Integer id) {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public void updateItems(Items items) {
        itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }
}
