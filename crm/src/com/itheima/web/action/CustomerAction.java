package com.itheima.web.action;

import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:客户的控制层
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    //实现保存客户的功能，接收用户传入的客户参数，并保存，不要忘记手动new。模型驱动
    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    //提供service的成员属性，提供set方法
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    保存客户的方法
     */
    public String add(){
        /*System.out.println("web层，保存客户");
        System.out.println(customer);
*/
        //struts2中的action方法调用spring中的service方法，采用传统的方式。
        /*WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        CustomerService cs = (CustomerService) context.getBean("customerService");
        cs.save(customer);*/

        customerService.save(customer);

        return "save";
    }

    //属性驱动的方式
    //当前页，默认值就是1
    private Integer pageCode = 1;
    public void setPageCode(Integer pageCode){
        if (pageCode == null){
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }

    //每页显示的数据的条数
    private Integer pageSize = 2;
    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    /*
    实现分页查询的功能
     */
    public String findByPage(){
        //调用service业务层
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        //拼接查询的条件
        String cust_name = customer.getCust_name();
        if (cust_name != null && !cust_name.trim().isEmpty()){
            //说明客户的名称输入值了
            criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
        }

        //拼接客户的级别
        Dict level = customer.getLevel();
        if (level != null && !level.getDict_id().trim().isEmpty()){
            //选择了某个级别
            criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
        }

        //拼接客户的来源
        Dict soure = customer.getSource();
        if (soure != null && !soure.getDict_id().trim().isEmpty()){
            //选择了某个级别
            criteria.add(Restrictions.eq("source.dict_id", soure.getDict_id()));
        }

        //查询
        PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);
        //压栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        //栈顶是map<"page",page对象>
        vs.set("page", page);
        return "page";
    }
    /*
    初始化到添加的页面
     */
    public String initAddUI(){

        return "initAddUI";
    }


}
