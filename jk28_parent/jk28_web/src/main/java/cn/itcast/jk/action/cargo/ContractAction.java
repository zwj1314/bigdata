package cn.itcast.jk.action.cargo;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.common.SysConstant;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class ContractAction extends BaseAction implements ModelDriven<Contract> {

    private Contract model = new Contract();

    @Override
    public Contract getModel() {
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

    //注入ContractService和DeptService
    private ContractService contractService;

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    private DeptService deptService;

    public void setDeptService(DeptService deptService){
        this.deptService = deptService;
    }


    /**
     * 分页查询
     */
    public String list() throws Exception {
        page = contractService.findPage("from Contract", page, Contract.class, null);
        //设置分页的url
        page.setUrl("contractAction_list");

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
    public String toview(){
        //1.调用业务方法，根据id得到对象
        Contract contract = contractService.get(Contract.class, model.getId());

        //2.放入栈顶
        super.push(contract);

        return "toview";
    }

    /**
     * 新增
     */
    public String tocreate(){

        //跳转页面
        return "tocreate";
    }

    /**
     * 保存
     * <s:select name="parent.id"
     * <input type="text" name="contractName" Value=""/>
     * model对象接收
     */
    public String insert(){
        //加入细粒度权限设置
        User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
        model.setCreateBy(user.getId());//设置创建者的id
        model.setCreateDept(user.getDept().getId());//设置创建者所在部门的id
        //1.调用业务方法实现保存
        contractService.saveOrUpdate(model);

        //跳页面
        return "alist";
    }

    /**
     * 进入修改页面
     */
    public String toupdate(){
        //1.根据选中的id得到一个对象
        Contract contract = contractService.get(Contract.class, model.getId());
        //2.将对象放入值栈中
        super.push(contract);

        //5.跳转页面
        return "toupdate";
    }

    /**
     * 修改
     */
    public String update(){
        //调用业务
        Contract contract = contractService.get(Contract.class, model.getId());//根据id，得到一个数据库中保存的对象
        //设置修改的属性
        contract.setCustomName(model.getCustomName());
        contract.setPrintStyle(model.getPrintStyle());
        contract.setContractNo(model.getContractNo());
        contract.setOfferor(model.getOfferor());
        contract.setInputBy(model.getInputBy());
        contract.setCheckBy(model.getCheckBy());
        contract.setInspector(model.getInspector());
        contract.setSigningDate(model.getSigningDate());
        contract.setImportNum(model.getImportNum());
        contract.setShipTime(model.getShipTime());
        contract.setTradeTerms(model.getTradeTerms());
        contract.setDeliveryPeriod(model.getDeliveryPeriod());
        contract.setCrequest(model.getCrequest());
        contract.setRemark(model.getRemark());

        //保存
        contractService.saveOrUpdate(contract);
        return "alist";
    }

    /**
     * 删除
     * <input type="checkbox" name="id" value="100"/>
     * <input type="checkbox" name="id" value="3d00290a-1af0-4c28-853e-29fbf96a2722"/>
     *  ....
     * model
     *      id:String类型
     *      具有同名框的一组值如何封装数据？\
     *      如果服务器端是String类型(可以正常提取)：
     *              100，3d00290a-1af0-4c28-853e-29fbf96a2722
     *
     *      id:Integer,float,double,date类型 id=100, id=200, id=300
     *              id=300(只能提取最后一个值)
     *              Integer[] ids;
     *
     */
    public String delete() {
        String ids[] = model.getId().split(",");

        //调用业务方法,实现批量删除
        contractService.delete(Contract.class, ids);
        return "alist";
    }

    /*
    提交
     */
    public String submit(){
        //1.获取到用户勾选的哪些id值
        String[] ids = model.getId().split(",");
        //2.遍历ids，并加载每个购销合同的对象，再修改购销合同的状态
        contractService.changeState(ids, 1);
        return "alist";
    }

    /*
    取消
     */
    public String cancel(){
        //1.获取到用户勾选的哪些id值
        String[] ids = model.getId().split(",");
        //2.遍历ids，并加载每个购销合同的对象，再修改购销合同的状态
        contractService.changeState(ids, 0);
        return "alist";
    }

}
