package cn.itcast.jk.action.cargo;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {

    private ContractProduct model = new ContractProduct();

    @Override
    public ContractProduct getModel() {
        return model;
    }

    //分页查询
    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    //注入ContractProductService和DeptService
    private ContractProductService contractProductService;

    public void setContractProductService(ContractProductService contractProductService) {
        this.contractProductService = contractProductService;
    }

    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    private FactoryService factoryService;

    public void setFactoryService(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = contractProductService.findPage("from ContractProduct", page, ContractProduct.class, null);
        //设置分页的url
        page.setUrl("contractProductAction_list");

        //将page对象压入栈顶
        super.push(page);
        return "list";
    }

    /*
    查看
    id=
    model对象
        id属性：
     */
    public String toview() {
        //1.调用业务方法，根据id得到对象
        ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());

        //2.放入栈顶
        super.push(contractProduct);

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate() {
        //1.调用业务方法，查询出生产货物的厂家
        String hql = "from Factory where ctype='货物' and state=1";
        List<Factory> factoryList = factoryService.find(hql, Factory.class, null);

        super.put("factoryList", factoryList);
        //2.查询出当前购销合同下的货物列表
        contractProductService.findPage("from ContractProduct where contract.id=?", page, ContractProduct.class, new String[]{model.getContract().getId()});
        //设置page的url
        page.setUrl("contractProductAction_tocreate");
        super.push(page);
        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="contractProductName" Value=""/>
     * model对象接收
     */
    public String insert() {
        //1.调用业务方法实现保存
        contractProductService.saveOrUpdate(model);

        //跳页面
        return tocreate();
    }

    /**
     * 进入修改页面
     */
    public String toupdate() {
        //1.根据选中的id得到一个对象
        ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());
        //2.将对象放入值栈中
        super.push(contractProduct);
        //
        String hql = "from Factory where ctype='货物' and state=1";
        List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
        super.put("factoryList", factoryList);


        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update() {
        //调用业务
        ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        contractProduct.setFactory(model.getFactory());
        contractProduct.setFactoryName(model.getFactoryName());
        contractProduct.setProductNo(model.getProductNo());
        contractProduct.setProductImage(model.getProductImage());
        contractProduct.setCnumber(model.getCnumber());
        contractProduct.setAmount(model.getAmount());
        contractProduct.setPackingUnit(model.getPackingUnit());
        contractProduct.setLoadingRate(model.getLoadingRate());
        contractProduct.setBoxNum(model.getBoxNum());
        contractProduct.setPrice(model.getPrice());
        contractProduct.setOrderNo(model.getOrderNo());
        contractProduct.setProductDesc(model.getProductDesc());
        contractProduct.setProductRequest(model.getProductRequest());

        //保存
        contractProductService.saveOrUpdate(contractProduct);
        return tocreate();
    }

    /**
     * 删除
     */
    public String delete() {
      contractProductService.delete(ContractProduct.class, model);
      return tocreate();
    }


}
