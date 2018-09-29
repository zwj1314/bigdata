package com.itheima.domain;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public class PageBean<T> {
    //当前页
    private int pageCode;
    //总页数
    //private int totalPage;
    //总记录数
    private int totalCount;
    //每页显示的记录条数
    private int pageSize;
    //每页显示的数据
    private List<T> beanList;

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    /*
        调用getTotalPage() 获取到总页数
        JavaBean的属性规定：totalPage是JavaBean是属性 ${pageBean.totalPage}
         */
    public int getTotalPage(){
        //计算
        int toatalPage = totalCount / pageSize;
        //说明整除
        if (totalCount / pageSize == 0){
            return toatalPage;
        }else {
            return toatalPage + 1;
        }

    }


}
