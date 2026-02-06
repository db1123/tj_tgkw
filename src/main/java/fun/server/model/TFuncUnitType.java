package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_func_unit_type")
public class TFuncUnitType implements Serializable {
    /**
     * 
     * 表字段 : t_func_unit_type.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_func_unit_type.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : t_func_unit_type.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : t_func_unit_type.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : t_func_unit_type.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    /**
     * 
     * 表字段 : t_func_unit_type.f_p_id
     * @mbg.generated
     */
    private Long fPId;

    /**
     * 
     * 表字段 : t_func_unit_type.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : t_func_unit_type.f_is_leaf
     * @mbg.generated
     */
    private Integer fIsLeaf;

    /**
     * 1:功能类型
     * 表字段 : t_func_unit_type.f_type
     * @mbg.generated
     */
    private Integer fType;

    /**
     * 
     * 表字段 : t_func_unit_type.f_path
     * @mbg.generated
     */
    private String fPath;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_func_unit_type
     * @mbg.generated
     */
    public TFuncUnitType(Long fKeyId, Long fCId, Long fUId, Date fCDate, Date fUDate, Long fPId, String fName, Integer fIsLeaf, Integer fType, String fPath) {
        this.fKeyId = fKeyId;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
        this.fPId = fPId;
        this.fName = fName;
        this.fIsLeaf = fIsLeaf;
        this.fType = fType;
        this.fPath = fPath;
    }

    /**
     * 构造查询条件
     * t_func_unit_type
     * @mbg.generated
     */
    public TFuncUnitType() {
        super();
    }

    /**
     * 获取  字段:t_func_unit_type.f_key_id
     * @return t_func_unit_type.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_func_unit_type.f_key_id
     * @param fKeyId the value for t_func_unit_type.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_func_unit_type.f_c_id
     * @return t_func_unit_type.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:t_func_unit_type.f_c_id
     * @param fCId the value for t_func_unit_type.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:t_func_unit_type.f_u_id
     * @return t_func_unit_type.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:t_func_unit_type.f_u_id
     * @param fUId the value for t_func_unit_type.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:t_func_unit_type.f_c_date
     * @return t_func_unit_type.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:t_func_unit_type.f_c_date
     * @param fCDate the value for t_func_unit_type.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:t_func_unit_type.f_u_date
     * @return t_func_unit_type.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:t_func_unit_type.f_u_date
     * @param fUDate the value for t_func_unit_type.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 获取  字段:t_func_unit_type.f_p_id
     * @return t_func_unit_type.f_p_id, 
     * @mbg.generated
     */
    public Long getfPId() {
        return fPId;
    }

    /**
     * 设置  字段:t_func_unit_type.f_p_id
     * @param fPId the value for t_func_unit_type.f_p_id, 
     * @mbg.generated
     */
    public void setfPId(Long fPId) {
        this.fPId = fPId;
    }

    /**
     * 获取  字段:t_func_unit_type.f_name
     * @return t_func_unit_type.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:t_func_unit_type.f_name
     * @param fName the value for t_func_unit_type.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:t_func_unit_type.f_is_leaf
     * @return t_func_unit_type.f_is_leaf, 
     * @mbg.generated
     */
    public Integer getfIsLeaf() {
        return fIsLeaf;
    }

    /**
     * 设置  字段:t_func_unit_type.f_is_leaf
     * @param fIsLeaf the value for t_func_unit_type.f_is_leaf, 
     * @mbg.generated
     */
    public void setfIsLeaf(Integer fIsLeaf) {
        this.fIsLeaf = fIsLeaf;
    }

    /**
     * 获取 1:功能类型 字段:t_func_unit_type.f_type
     * @return t_func_unit_type.f_type, 1:功能类型
     * @mbg.generated
     */
    public Integer getfType() {
        return fType;
    }

    /**
     * 设置 1:功能类型 字段:t_func_unit_type.f_type
     * @param fType the value for t_func_unit_type.f_type, 1:功能类型
     * @mbg.generated
     */
    public void setfType(Integer fType) {
        this.fType = fType;
    }

    /**
     * 获取  字段:t_func_unit_type.f_path
     * @return t_func_unit_type.f_path, 
     * @mbg.generated
     */
    public String getfPath() {
        return fPath;
    }

    /**
     * 设置  字段:t_func_unit_type.f_path
     * @param fPath the value for t_func_unit_type.f_path, 
     * @mbg.generated
     */
    public void setfPath(String fPath) {
        this.fPath = fPath == null ? null : fPath.trim();
    }

    /**
     * 实例输出为字符串
     * t_func_unit_type
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fCId=").append(fCId);
        sb.append(", fUId=").append(fUId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUDate=").append(fUDate);
        sb.append(", fPId=").append(fPId);
        sb.append(", fName=").append(fName);
        sb.append(", fIsLeaf=").append(fIsLeaf);
        sb.append(", fType=").append(fType);
        sb.append(", fPath=").append(fPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_func_unit_type
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
        TFuncUnitType other = (TFuncUnitType) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()))
            && (this.getfPId() == null ? other.getfPId() == null : this.getfPId().equals(other.getfPId()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfIsLeaf() == null ? other.getfIsLeaf() == null : this.getfIsLeaf().equals(other.getfIsLeaf()))
            && (this.getfType() == null ? other.getfType() == null : this.getfType().equals(other.getfType()))
            && (this.getfPath() == null ? other.getfPath() == null : this.getfPath().equals(other.getfPath()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_func_unit_type
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        result = prime * result + ((getfPId() == null) ? 0 : getfPId().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfIsLeaf() == null) ? 0 : getfIsLeaf().hashCode());
        result = prime * result + ((getfType() == null) ? 0 : getfType().hashCode());
        result = prime * result + ((getfPath() == null) ? 0 : getfPath().hashCode());
        return result;
    }
}