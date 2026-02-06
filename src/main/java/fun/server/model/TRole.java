package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_role")
public class TRole implements Serializable {
    /**
     * 
     * 表字段 : t_role.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_role.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : t_role.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 
     * 表字段 : t_role.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : t_role.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : t_role.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : t_role.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_role
     * @mbg.generated
     */
    public TRole(Long fKeyId, String fName, Integer fState, Long fCId, Long fUId, Date fCDate, Date fUDate) {
        this.fKeyId = fKeyId;
        this.fName = fName;
        this.fState = fState;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
    }

    /**
     * 构造查询条件
     * t_role
     * @mbg.generated
     */
    public TRole() {
        super();
    }

    /**
     * 获取  字段:t_role.f_key_id
     * @return t_role.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_role.f_key_id
     * @param fKeyId the value for t_role.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_role.f_name
     * @return t_role.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:t_role.f_name
     * @param fName the value for t_role.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:t_role.f_state
     * @return t_role.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:t_role.f_state
     * @param fState the value for t_role.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取  字段:t_role.f_c_id
     * @return t_role.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:t_role.f_c_id
     * @param fCId the value for t_role.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:t_role.f_u_id
     * @return t_role.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:t_role.f_u_id
     * @param fUId the value for t_role.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:t_role.f_c_date
     * @return t_role.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:t_role.f_c_date
     * @param fCDate the value for t_role.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:t_role.f_u_date
     * @return t_role.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:t_role.f_u_date
     * @param fUDate the value for t_role.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 实例输出为字符串
     * t_role
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fName=").append(fName);
        sb.append(", fState=").append(fState);
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
     * t_role
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
        TRole other = (TRole) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_role
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        return result;
    }
}