package com.itheima.web.action;

import com.itheima.domain.Dict;
import com.itheima.service.DictService;
import com.itheima.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:字典控制器
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict>{

    private Dict dict = new Dict();

    @Override
    public Dict getModel() {
        return dict;
    }

    /*
    依赖注入
     */
    private DictService dictService;

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    /*
    通过字段的type_code值，查询客户的级别或者客户的来源
     */
    public String findByCode(){
        //调用业务层
        List<Dict> list = dictService.findByCode(dict.getDict_type_code());

        //List<Dict> list = dictService.findByCode("006");
        //使用fastjson，把list转换成json字符串
        String jsonString = FastJsonUtil.toJSONString(list);
        //System.out.println(jsonString);

        //把json字符串写到浏览器
        HttpServletResponse response = ServletActionContext.getResponse();
        FastJsonUtil.write_json(response, jsonString);

        return NONE;
    }
}
