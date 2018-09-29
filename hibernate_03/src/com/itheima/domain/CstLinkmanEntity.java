package com.itheima.domain;

import javax.persistence.*;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:联系人（多）
 */
@Entity
@Table(name = "cst_linkman", schema = "hibernate_day03", catalog = "")
public class CstLinkmanEntity {
    private long lkmId;
    private String lkmName;
    private String lkmGender;
    private String lkmPhone;
    private String lkmMobile;
    private String lkmEmail;
    private String lkmQq;
    private String lkmPosition;
    private String lkmMemo;

    //编写一个对象
    private CstCustomerEntity customer;

    @Id
    @Column(name = "lkm_id")
    public long getLkmId() {
        return lkmId;
    }

    public void setLkmId(long lkmId) {
        this.lkmId = lkmId;
    }

    @Basic
    @Column(name = "lkm_name")
    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    @Basic
    @Column(name = "lkm_gender")
    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    @Basic
    @Column(name = "lkm_phone")
    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    @Basic
    @Column(name = "lkm_mobile")
    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    @Basic
    @Column(name = "lkm_email")
    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    @Basic
    @Column(name = "lkm_qq")
    public String getLkmQq() {
        return lkmQq;
    }

    public void setLkmQq(String lkmQq) {
        this.lkmQq = lkmQq;
    }

    @Basic
    @Column(name = "lkm_position")
    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    @Basic
    @Column(name = "lkm_memo")
    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    public CstCustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CstCustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CstLinkmanEntity that = (CstLinkmanEntity) o;

        if (lkmId != that.lkmId) return false;
        if (lkmName != null ? !lkmName.equals(that.lkmName) : that.lkmName != null) return false;
        if (lkmGender != null ? !lkmGender.equals(that.lkmGender) : that.lkmGender != null) return false;
        if (lkmPhone != null ? !lkmPhone.equals(that.lkmPhone) : that.lkmPhone != null) return false;
        if (lkmMobile != null ? !lkmMobile.equals(that.lkmMobile) : that.lkmMobile != null) return false;
        if (lkmEmail != null ? !lkmEmail.equals(that.lkmEmail) : that.lkmEmail != null) return false;
        if (lkmQq != null ? !lkmQq.equals(that.lkmQq) : that.lkmQq != null) return false;
        if (lkmPosition != null ? !lkmPosition.equals(that.lkmPosition) : that.lkmPosition != null) return false;
        if (lkmMemo != null ? !lkmMemo.equals(that.lkmMemo) : that.lkmMemo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (lkmId ^ (lkmId >>> 32));
        result = 31 * result + (lkmName != null ? lkmName.hashCode() : 0);
        result = 31 * result + (lkmGender != null ? lkmGender.hashCode() : 0);
        result = 31 * result + (lkmPhone != null ? lkmPhone.hashCode() : 0);
        result = 31 * result + (lkmMobile != null ? lkmMobile.hashCode() : 0);
        result = 31 * result + (lkmEmail != null ? lkmEmail.hashCode() : 0);
        result = 31 * result + (lkmQq != null ? lkmQq.hashCode() : 0);
        result = 31 * result + (lkmPosition != null ? lkmPosition.hashCode() : 0);
        result = 31 * result + (lkmMemo != null ? lkmMemo.hashCode() : 0);
        return result;
    }

    public CstLinkmanEntity() {
    }

    @Override
    public String toString() {
        return "CstLinkmanEntity{" +
                "lkmId=" + lkmId +
                ", lkmName='" + lkmName + '\'' +
                ", lkmGender='" + lkmGender + '\'' +
                ", lkmPhone='" + lkmPhone + '\'' +
                ", lkmMobile='" + lkmMobile + '\'' +
                ", lkmEmail='" + lkmEmail + '\'' +
                ", lkmQq='" + lkmQq + '\'' +
                ", lkmPosition='" + lkmPosition + '\'' +
                ", lkmMemo='" + lkmMemo + '\'' +
                '}';
    }
}
