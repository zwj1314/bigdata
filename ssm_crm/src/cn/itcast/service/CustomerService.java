package cn.itcast.service;

import cn.itcast.pojo.BaseDict;
import cn.itcast.pojo.Customer;
import cn.itcast.pojo.QueryVo;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/2
 * @description:
 */
public interface CustomerService {

    public List<BaseDict> findDictByCode(String code);

    public List<Customer> findCustomerByVo(QueryVo vo);
    public Integer findCustomerByVoCount(QueryVo vo);

    public Customer findCustomerById(Long id);

    public void updateCustomerById(Customer customer);

    public void delCustomerById(Long id);
}
