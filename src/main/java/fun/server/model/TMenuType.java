package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_menu_type")
public class TMenuType implements Serializable {
    /**
     * 
     * 表字段 : t_menu_type.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_menu_type.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : t_menu_type.f_icon
     * @mbg.generated
     */
    private String fIcon;

    /**
     * 
     * 表字段 : t_menu_type.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 
     * 表字段 : t_menu_type.f_order
     * @mbg.generated
     */
    private Integer fOrder;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_menu_type
     * @mbg.generated
     */
    public TMenuType(Long fKeyId, String fName, String fIcon, Integer fState, Integer fOrder) {
        this.fKeyId = fKeyId;
        this.fName = fName;
        this.fIcon = fIcon;
        this.fState = fState;
        this.fOrder = fOrder;
    }

    /**
     * 构造查询条件
     * t_menu_type
     * @mbg.generated
     */
    public TMenuType() {
        super();
    }

    /**
     * 获取  字段:t_menu_type.f_key_id
     * @return t_menu_type.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_menu_type.f_key_id
     * @param fKeyId the value for t_menu_type.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_menu_type.f_name
     * @return t_menu_type.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:t_menu_type.f_name
     * @param fName the value for t_menu_type.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:t_menu_type.f_icon
     * @return t_menu_type.f_icon, 
     * @mbg.generated
     */
    public String getfIcon() {
        return fIcon;
    }

    /**
     * 设置  字段:t_menu_type.f_icon
     * @param fIcon the value for t_menu_type.f_icon, 
     * @mbg.generated
     */
    public void setfIcon(String fIcon) {
        this.fIcon = fIcon == null ? null : fIcon.trim();
    }

    /**
     * 获取  字段:t_menu_type.f_state
     * @return t_menu_type.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:t_menu_type.f_state
     * @param fState the value for t_menu_type.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取  字段:t_menu_type.f_order
     * @return t_menu_type.f_order, 
     * @mbg.generated
     */
    public Integer getfOrder() {
        return fOrder;
    }

    /**
     * 设置  字段:t_menu_type.f_order
     * @param fOrder the value for t_menu_type.f_order, 
     * @mbg.generated
     */
    public void setfOrder(Integer fOrder) {
        this.fOrder = fOrder;
    }

    /**
     * 实例输出为字符串
     * t_menu_type
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
        sb.append(", fIcon=").append(fIcon);
        sb.append(", fState=").append(fState);
        sb.append(", fOrder=").append(fOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_menu_type
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
        TMenuType other = (TMenuType) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfIcon() == null ? other.getfIcon() == null : this.getfIcon().equals(other.getfIcon()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfOrder() == null ? other.getfOrder() == null : this.getfOrder().equals(other.getfOrder()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_menu_type
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfIcon() == null) ? 0 : getfIcon().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfOrder() == null) ? 0 : getfOrder().hashCode());
        return result;
    }
}