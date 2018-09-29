package com.itheima.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:客户（一）
 */
@Entity
@Table(name = "cst_customer", schema = "hibernate_day03", catalog = "")
public class CstCustomerEntity {
    private long custId;
    private String custName;
    private Long custUserId;
    private Long custCreateId;
    private String custSource;
    private String custIndustry;
    private String custLevel;
    private String custLinkman;
    private String custPhone;
    private String custMobile;

    //Hibernate框架默认的集合是set集合，集合必须自己手动初始化
    private Set<CstLinkmanEntity> linkmans = new HashSet<CstLinkmanEntity>();

    @Id
    @Column(name = "cust_id")
    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "cust_name")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "cust_user_id")
    public Long getCustUserId() {
        return custUserId;
    }

    public void setCustUserId(Long custUserId) {
        this.custUserId = custUserId;
    }

    @Basic
    @Column(name = "cust_create_id")
    public Long getCustCreateId() {
        return custCreateId;
    }

    public void setCustCreateId(Long custCreateId) {
        this.custCreateId = custCreateId;
    }

    @Basic
    @Column(name = "cust_source")
    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    @Basic
    @Column(name = "cust_industry")
    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    @Basic
    @Column(name = "cust_level")
    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    @Basic
    @Column(name = "cust_linkman")
    public String getCustLinkman() {
        return custLinkman;
    }

    public void setCustLinkman(String custLinkman) {
        this.custLinkman = custLinkman;
    }

    @Basic
    @Column(name = "cust_phone")
    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Basic
    @Column(name = "cust_mobile")
    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public Set<CstLinkmanEntity> getLinkmans() {
        return linkmans;
    }

    public void setLinkmans(Set<CstLinkmanEntity> linkmans) {
        this.linkmans = linkmans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CstCustomerEntity that = (CstCustomerEntity) o;

        if (custId != that.custId) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;
        if (custUserId != null ? !custUserId.equals(that.custUserId) : that.custUserId != null) return false;
        if (custCreateId != null ? !custCreateId.equals(that.custCreateId) : that.custCreateId != null) return false;
        if (custSource != null ? !custSource.equals(that.custSource) : that.custSource != null) return false;
        if (custIndustry != null ? !custIndustry.equals(that.custIndustry) : that.custIndustry != null) return false;
        if (custLevel != null ? !custLevel.equals(that.custLevel) : that.custLevel != null) return false;
        if (custLinkman != null ? !custLinkman.equals(that.custLinkman) : that.custLinkman != null) return false;
        if (custPhone != null ? !custPhone.equals(that.custPhone) : that.custPhone != null) return false;
        if (custMobile != null ? !custMobile.equals(that.custMobile) : that.custMobile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (custId ^ (custId >>> 32));
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        result = 31 * result + (custUserId != null ? custUserId.hashCode() : 0);
        result = 31 * result + (custCreateId != null ? custCreateId.hashCode() : 0);
        result = 31 * result + (custSource != null ? custSource.hashCode() : 0);
        result = 31 * result + (custIndustry != null ? custIndustry.hashCode() : 0);
        result = 31 * result + (custLevel != null ? custLevel.hashCode() : 0);
        result = 31 * result + (custLinkman != null ? custLinkman.hashCode() : 0);
        result = 31 * result + (custPhone != null ? custPhone.hashCode() : 0);
        result = 31 * result + (custMobile != null ? custMobile.hashCode() : 0);
        return result;
    }

    public CstCustomerEntity() {
    }

    @Override
    public String toString() {
        return "CstCustomerEntity{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custUserId=" + custUserId +
                ", custCreateId=" + custCreateId +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custLinkman='" + custLinkman + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custMobile='" + custMobile + '\'' +
                '}';
    }
}
