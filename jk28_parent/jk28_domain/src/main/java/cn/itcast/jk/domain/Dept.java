package cn.itcast.jk.domain;

import java.io.Serializable;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/29
 * @description:
 */
public class Dept implements Serializable {
    private String id;
    private String deptName;//部门名称
    private Dept parent;//父部门 自关联  子部门与父部门  多对一
    private Integer state;//状态 1代表启用 0代表停用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
