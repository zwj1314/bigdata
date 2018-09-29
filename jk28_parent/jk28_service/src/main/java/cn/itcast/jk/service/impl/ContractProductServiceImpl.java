package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/21
 * @description:
 */
public class ContractProductServiceImpl implements ContractProductService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass, params);
    }

    @Override
    public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass, params);
    }

    @Override
    public void saveOrUpdate(ContractProduct entity) {
        double amount = 0d;
        if (UtilFuns.isEmpty(entity.getId())) {
            //新增
            if (UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())){
                amount = entity.getPrice()*entity.getCnumber();//货物总金额
                entity.setAmount(amount);
            }

            //修改购销合同的总金额
            Contract contract = baseDao.get(Contract.class, entity.getContract().getId());//根据购销合同的id，得到购销合同的对象
            contract.setTotalAmount(contract.getTotalAmount()+amount);

            //保存购销合同的总金额
            baseDao.saveOrUpdate(contract);



        } else {
            //修改
            double oldAmount = entity.getAmount();//取出货物的原有总金额
            if (UtilFuns.isNotEmpty(entity.getPrice()) && UtilFuns.isNotEmpty(entity.getCnumber())){
                amount = entity.getPrice()*entity.getCnumber();//货物总金额
                entity.setAmount(amount);
            }
            Contract contract = baseDao.get(Contract.class, entity.getContract().getId());//根据购销合同的id，得到购销合同的对象
            contract.setTotalAmount(contract.getTotalAmount()+amount-oldAmount);

        }
        baseDao.saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
        baseDao.saveOrUpdateAll(entitys);
    }

    @Override
    public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
        for (Serializable id : ids) {
            this.deleteById(ContractProduct.class, id);
        }
    }

    @Override
    public void delete(Class<ContractProduct> entityClass, ContractProduct model) {
        //1.加载要删除的货物对象
        ContractProduct cp = baseDao.get(ContractProduct.class, model.getId());
        //2.通过关联级别的数据加载，得到当前货物下的所有附件列表
        Set<ExtCproduct> extSet = cp.getExtCproducts();
        //3.加载购销合同对象
        Contract contract = baseDao.get(Contract.class, model.getContract().getId());
        //4.遍历附件列表，并修改购销合同总金额
        for (ExtCproduct ec : extSet){
            contract.setTotalAmount(contract.getTotalAmount()-ec.getAmount());

        }
        //5.购销合同总金额-货物总金额
        contract.setTotalAmount(contract.getTotalAmount()-cp.getAmount());
        //6.更新购销合同金额
        baseDao.saveOrUpdate(contract);
        //7.删除货物对象，级联删除附件
        baseDao.deleteById(ContractProduct.class, model.getId());

    }
}
