package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_power_role_user")
public class TPowerRoleUser implements Serializable {
    /**
     * 
     * 表字段 : t_power_role_user.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_power_role_user.f_role_id
     * @mbg.generated
     */
    private Long fRoleId;

    /**
     * 
     * 表字段 : t_power_role_user.f_user_id
     * @mbg.generated
     */
    private Long fUserId;

    /**
     * 
     * 表字段 : t_power_role_user.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : t_power_role_user.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : t_power_role_user.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : t_power_role_user.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_power_role_user
     * @mbg.generated
     */
    public TPowerRoleUser(Long fKeyId, Long fRoleId, Long fUserId, Long fCId, Long fUId, Date fCDate, Date fUDate) {
        this.fKeyId = fKeyId;
        this.fRoleId = fRoleId;
        this.fUserId = fUserId;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
    }

    /**
     * 构造查询条件
     * t_power_role_user
     * @mbg.generated
     */
    public TPowerRoleUser() {
        super();
    }

    /**
     * 获取  字段:t_power_role_user.f_key_id
     * @return t_power_role_user.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_power_role_user.f_key_id
     * @param fKeyId the value for t_power_role_user.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_power_role_user.f_role_id
     * @return t_power_role_user.f_role_id, 
     * @mbg.generated
     */
    public Long getfRoleId() {
        return fRoleId;
    }

    /**
     * 设置  字段:t_power_role_user.f_role_id
     * @param fRoleId the value for t_power_role_user.f_role_id, 
     * @mbg.generated
     */
    public void setfRoleId(Long fRoleId) {
        this.fRoleId = fRoleId;
    }

    /**
     * 获取  字段:t_power_role_user.f_user_id
     * @return t_power_role_user.f_user_id, 
     * @mbg.generated
     */
    public Long getfUserId() {
        return fUserId;
    }

    /**
     * 设置  字段:t_power_role_user.f_user_id
     * @param fUserId the value for t_power_role_user.f_user_id, 
     * @mbg.generated
     */
    public void setfUserId(Long fUserId) {
        this.fUserId = fUserId;
    }

    /**
     * 获取  字段:t_power_role_user.f_c_id
     * @return t_power_role_user.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:t_power_role_user.f_c_id
     * @param fCId the value for t_power_role_user.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:t_power_role_user.f_u_id
     * @return t_power_role_user.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:t_power_role_user.f_u_id
     * @param fUId the value for t_power_role_user.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:t_power_role_user.f_c_date
     * @return t_power_role_user.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:t_power_role_user.f_c_date
     * @param fCDate the value for t_power_role_user.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:t_power_role_user.f_u_date
     * @return t_power_role_user.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:t_power_role_user.f_u_date
     * @param fUDate the value for t_power_role_user.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 实例输出为字符串
     * t_power_role_user
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fRoleId=").append(fRoleId);
        sb.append(", fUserId=").append(fUserId);
        sb.append(", fCId=").append(fCId);
        sb.append(", fUId=").append(fUId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUDate=").append(fUDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_power_role_user
     * @param that
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TPowerRoleUser other = (TPowerRoleUser) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfRoleId() == null ? other.getfRoleId() == null : this.getfRoleId().equals(other.getfRoleId()))
            && (this.getfUserId() == null ? other.getfUserId() == null : this.getfUserId().equals(other.getfUserId()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_power_role_user
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfRoleId() == null) ? 0 : getfRoleId().hashCode());
        result = prime * result + ((getfUserId() == null) ? 0 : getfUserId().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        return result;
    }
}