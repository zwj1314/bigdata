package cn.itcast.service;

import cn.itcast.dao.CustomerMapper;
import cn.itcast.dao.DictMapper;
import cn.itcast.pojo.BaseDict;
import cn.itcast.pojo.Customer;
import cn.itcast.pojo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/2
 * @description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<BaseDict> findDictByCode(String code) {
        List<BaseDict> list = dictMapper.findDictByCode(code);
        return list;
    }

    @Override
    public List<Customer> findCustomerByVo(QueryVo vo) {
        List<Customer> list = customerMapper.findCustomerByVo(vo);
        return list;
    }

    @Override
    public Integer findCustomerByVoCount(QueryVo vo) {
        Integer count = customerMapper.findCustomerByVoCount(vo);
        return count;
    }

    @Override
    public Customer findCustomerById(Long id) {
        Customer customer = customerMapper.findCustomerById(id);
        return customer;
    }

    @Override
    public void updateCustomerById(Customer customer) {
        customerMapper.updateCustomerById(customer);
    }

    @Override
    public void delCustomerById(Long id) {
        customerMapper.delCustomerById(id);
    }
}
