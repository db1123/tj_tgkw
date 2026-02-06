package fun.server.model;

import java.io.Serializable;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "t_menu_options")
public class TMenuOptions implements Serializable {
    /**
     * 
     * 表字段 : t_menu_options.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_menu_options.f_type_id
     * @mbg.generated
     */
    private Long fTypeId;

    /**
     * 
     * 表字段 : t_menu_options.f_p_id
     * @mbg.generated
     */
    private Long fPId;

    /**
     * 
     * 表字段 : t_menu_options.f_is_leaf
     * @mbg.generated
     */
    private Integer fIsLeaf;

    /**
     * 
     * 表字段 : t_menu_options.f_no
     * @mbg.generated
     */
    private String fNo;

    /**
     * 
     * 表字段 : t_menu_options.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : t_menu_options.f_icon
     * @mbg.generated
     */
    private String fIcon;

    /**
     * 
     * 表字段 : t_menu_options.f_style
     * @mbg.generated
     */
    private String fStyle;

    /**
     * 
     * 表字段 : t_menu_options.f_url
     * @mbg.generated
     */
    private String fUrl;

    /**
     * 
     * 表字段 : t_menu_options.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 
     * 表字段 : t_menu_options.f_is_admin
     * @mbg.generated
     */
    private Integer fIsAdmin;

    /**
     * 
     * 表字段 : t_menu_options.f_order
     * @mbg.generated
     */
    private Integer fOrder;

    /**
     * 1、系统内部菜单
2、外部URL
     * 表字段 : t_menu_options.f_menu_type
     * @mbg.generated
     */
    private Integer fMenuType;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_menu_options
     * @mbg.generated
     */
    public TMenuOptions(Long fKeyId, Long fTypeId, Long fPId, Integer fIsLeaf, String fNo, String fName, String fIcon, String fStyle, String fUrl, Integer fState, Integer fIsAdmin, Integer fOrder, Integer fMenuType) {
        this.fKeyId = fKeyId;
        this.fTypeId = fTypeId;
        this.fPId = fPId;
        this.fIsLeaf = fIsLeaf;
        this.fNo = fNo;
        this.fName = fName;
        this.fIcon = fIcon;
        this.fStyle = fStyle;
        this.fUrl = fUrl;
        this.fState = fState;
        this.fIsAdmin = fIsAdmin;
        this.fOrder = fOrder;
        this.fMenuType = fMenuType;
    }

    /**
     * 构造查询条件
     * t_menu_options
     * @mbg.generated
     */
    public TMenuOptions() {
        super();
    }

    /**
     * 获取  字段:t_menu_options.f_key_id
     * @return t_menu_options.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_menu_options.f_key_id
     * @param fKeyId the value for t_menu_options.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_menu_options.f_type_id
     * @return t_menu_options.f_type_id, 
     * @mbg.generated
     */
    public Long getfTypeId() {
        return fTypeId;
    }

    /**
     * 设置  字段:t_menu_options.f_type_id
     * @param fTypeId the value for t_menu_options.f_type_id, 
     * @mbg.generated
     */
    public void setfTypeId(Long fTypeId) {
        this.fTypeId = fTypeId;
    }

    /**
     * 获取  字段:t_menu_options.f_p_id
     * @return t_menu_options.f_p_id, 
     * @mbg.generated
     */
    public Long getfPId() {
        return fPId;
    }

    /**
     * 设置  字段:t_menu_options.f_p_id
     * @param fPId the value for t_menu_options.f_p_id, 
     * @mbg.generated
     */
    public void setfPId(Long fPId) {
        this.fPId = fPId;
    }

    /**
     * 获取  字段:t_menu_options.f_is_leaf
     * @return t_menu_options.f_is_leaf, 
     * @mbg.generated
     */
    public Integer getfIsLeaf() {
        return fIsLeaf;
    }

    /**
     * 设置  字段:t_menu_options.f_is_leaf
     * @param fIsLeaf the value for t_menu_options.f_is_leaf, 
     * @mbg.generated
     */
    public void setfIsLeaf(Integer fIsLeaf) {
        this.fIsLeaf = fIsLeaf;
    }

    /**
     * 获取  字段:t_menu_options.f_no
     * @return t_menu_options.f_no, 
     * @mbg.generated
     */
    public String getfNo() {
        return fNo;
    }

    /**
     * 设置  字段:t_menu_options.f_no
     * @param fNo the value for t_menu_options.f_no, 
     * @mbg.generated
     */
    public void setfNo(String fNo) {
        this.fNo = fNo == null ? null : fNo.trim();
    }

    /**
     * 获取  字段:t_menu_options.f_name
     * @return t_menu_options.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:t_menu_options.f_name
     * @param fName the value for t_menu_options.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:t_menu_options.f_icon
     * @return t_menu_options.f_icon, 
     * @mbg.generated
     */
    public String getfIcon() {
        return fIcon;
    }

    /**
     * 设置  字段:t_menu_options.f_icon
     * @param fIcon the value for t_menu_options.f_icon, 
     * @mbg.generated
     */
    public void setfIcon(String fIcon) {
        this.fIcon = fIcon == null ? null : fIcon.trim();
    }

    /**
     * 获取  字段:t_menu_options.f_style
     * @return t_menu_options.f_style, 
     * @mbg.generated
     */
    public String getfStyle() {
        return fStyle;
    }

    /**
     * 设置  字段:t_menu_options.f_style
     * @param fStyle the value for t_menu_options.f_style, 
     * @mbg.generated
     */
    public void setfStyle(String fStyle) {
        this.fStyle = fStyle == null ? null : fStyle.trim();
    }

    /**
     * 获取  字段:t_menu_options.f_url
     * @return t_menu_options.f_url, 
     * @mbg.generated
     */
    public String getfUrl() {
        return fUrl;
    }

    /**
     * 设置  字段:t_menu_options.f_url
     * @param fUrl the value for t_menu_options.f_url, 
     * @mbg.generated
     */
    public void setfUrl(String fUrl) {
        this.fUrl = fUrl == null ? null : fUrl.trim();
    }

    /**
     * 获取  字段:t_menu_options.f_state
     * @return t_menu_options.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:t_menu_options.f_state
     * @param fState the value for t_menu_options.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取  字段:t_menu_options.f_is_admin
     * @return t_menu_options.f_is_admin, 
     * @mbg.generated
     */
    public Integer getfIsAdmin() {
        return fIsAdmin;
    }

    /**
     * 设置  字段:t_menu_options.f_is_admin
     * @param fIsAdmin the value for t_menu_options.f_is_admin, 
     * @mbg.generated
     */
    public void setfIsAdmin(Integer fIsAdmin) {
        this.fIsAdmin = fIsAdmin;
    }

    /**
     * 获取  字段:t_menu_options.f_order
     * @return t_menu_options.f_order, 
     * @mbg.generated
     */
    public Integer getfOrder() {
        return fOrder;
    }

    /**
     * 设置  字段:t_menu_options.f_order
     * @param fOrder the value for t_menu_options.f_order, 
     * @mbg.generated
     */
    public void setfOrder(Integer fOrder) {
        this.fOrder = fOrder;
    }

    /**
     * 获取 1、系统内部菜单
2、外部URL 字段:t_menu_options.f_menu_type
     * @return t_menu_options.f_menu_type, 1、系统内部菜单
2、外部URL
     * @mbg.generated
     */
    public Integer getfMenuType() {
        return fMenuType;
    }

    /**
     * 设置 1、系统内部菜单
2、外部URL 字段:t_menu_options.f_menu_type
     * @param fMenuType the value for t_menu_options.f_menu_type, 1、系统内部菜单
2、外部URL
     * @mbg.generated
     */
    public void setfMenuType(Integer fMenuType) {
        this.fMenuType = fMenuType;
    }

    /**
     * 实例输出为字符串
     * t_menu_options
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fTypeId=").append(fTypeId);
        sb.append(", fPId=").append(fPId);
        sb.append(", fIsLeaf=").append(fIsLeaf);
        sb.append(", fNo=").append(fNo);
        sb.append(", fName=").append(fName);
        sb.append(", fIcon=").append(fIcon);
        sb.append(", fStyle=").append(fStyle);
        sb.append(", fUrl=").append(fUrl);
        sb.append(", fState=").append(fState);
        sb.append(", fIsAdmin=").append(fIsAdmin);
        sb.append(", fOrder=").append(fOrder);
        sb.append(", fMenuType=").append(fMenuType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_menu_options
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
        TMenuOptions other = (TMenuOptions) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfTypeId() == null ? other.getfTypeId() == null : this.getfTypeId().equals(other.getfTypeId()))
            && (this.getfPId() == null ? other.getfPId() == null : this.getfPId().equals(other.getfPId()))
            && (this.getfIsLeaf() == null ? other.getfIsLeaf() == null : this.getfIsLeaf().equals(other.getfIsLeaf()))
            && (this.getfNo() == null ? other.getfNo() == null : this.getfNo().equals(other.getfNo()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfIcon() == null ? other.getfIcon() == null : this.getfIcon().equals(other.getfIcon()))
            && (this.getfStyle() == null ? other.getfStyle() == null : this.getfStyle().equals(other.getfStyle()))
            && (this.getfUrl() == null ? other.getfUrl() == null : this.getfUrl().equals(other.getfUrl()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfIsAdmin() == null ? other.getfIsAdmin() == null : this.getfIsAdmin().equals(other.getfIsAdmin()))
            && (this.getfOrder() == null ? other.getfOrder() == null : this.getfOrder().equals(other.getfOrder()))
            && (this.getfMenuType() == null ? other.getfMenuType() == null : this.getfMenuType().equals(other.getfMenuType()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_menu_options
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfTypeId() == null) ? 0 : getfTypeId().hashCode());
        result = prime * result + ((getfPId() == null) ? 0 : getfPId().hashCode());
        result = prime * result + ((getfIsLeaf() == null) ? 0 : getfIsLeaf().hashCode());
        result = prime * result + ((getfNo() == null) ? 0 : getfNo().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfIcon() == null) ? 0 : getfIcon().hashCode());
        result = prime * result + ((getfStyle() == null) ? 0 : getfStyle().hashCode());
        result = prime * result + ((getfUrl() == null) ? 0 : getfUrl().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfIsAdmin() == null) ? 0 : getfIsAdmin().hashCode());
        result = prime * result + ((getfOrder() == null) ? 0 : getfOrder().hashCode());
        result = prime * result + ((getfMenuType() == null) ? 0 : getfMenuType().hashCode());
        return result;
    }
}