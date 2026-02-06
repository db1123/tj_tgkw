package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_flow_cell_file_type
*/
@Table(name = "t_flow_cell_file_type")
public class TFlowCellFileType implements Serializable {
    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_cid
     * @mbg.generated
     */
    private Long fCid;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_uid
     * @mbg.generated
     */
    private Long fUid;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_cdate
     * @mbg.generated
     */
    private Date fCdate;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_udate
     * @mbg.generated
     */
    private Date fUdate;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 1-项目 2-模具
     * 表字段 : pdm_server..t_flow_cell_file_type.f_porm
     * @mbg.generated
     */
    private Integer fPorm;

    /**
     * t_flow_cell_file_type_group-->fkeyid
     * 表字段 : pdm_server..t_flow_cell_file_type.f_ftype
     * @mbg.generated
     */
    private Long fFtype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public TFlowCellFileType(Long fKeyId, String fName, Long fCid, Long fUid, Date fCdate, Date fUdate, Integer fState, Integer fPorm, Long fFtype) {
        this.fKeyId = fKeyId;
        this.fName = fName;
        this.fCid = fCid;
        this.fUid = fUid;
        this.fCdate = fCdate;
        this.fUdate = fUdate;
        this.fState = fState;
        this.fPorm = fPorm;
        this.fFtype = fFtype;
    }

    /**
     * 构造查询条件
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public TFlowCellFileType() {
        super();
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_key_id
     * @return pdm_server..t_flow_cell_file_type.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_key_id
     * @param fKeyId the value for pdm_server..t_flow_cell_file_type.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_name
     * @return pdm_server..t_flow_cell_file_type.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_name
     * @param fName the value for pdm_server..t_flow_cell_file_type.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_cid
     * @return pdm_server..t_flow_cell_file_type.f_cid, 
     * @mbg.generated
     */
    public Long getfCid() {
        return fCid;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_cid
     * @param fCid the value for pdm_server..t_flow_cell_file_type.f_cid, 
     * @mbg.generated
     */
    public void setfCid(Long fCid) {
        this.fCid = fCid;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_uid
     * @return pdm_server..t_flow_cell_file_type.f_uid, 
     * @mbg.generated
     */
    public Long getfUid() {
        return fUid;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_uid
     * @param fUid the value for pdm_server..t_flow_cell_file_type.f_uid, 
     * @mbg.generated
     */
    public void setfUid(Long fUid) {
        this.fUid = fUid;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_cdate
     * @return pdm_server..t_flow_cell_file_type.f_cdate, 
     * @mbg.generated
     */
    public Date getfCdate() {
        return fCdate;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_cdate
     * @param fCdate the value for pdm_server..t_flow_cell_file_type.f_cdate, 
     * @mbg.generated
     */
    public void setfCdate(Date fCdate) {
        this.fCdate = fCdate;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_udate
     * @return pdm_server..t_flow_cell_file_type.f_udate, 
     * @mbg.generated
     */
    public Date getfUdate() {
        return fUdate;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_udate
     * @param fUdate the value for pdm_server..t_flow_cell_file_type.f_udate, 
     * @mbg.generated
     */
    public void setfUdate(Date fUdate) {
        this.fUdate = fUdate;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type.f_state
     * @return pdm_server..t_flow_cell_file_type.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type.f_state
     * @param fState the value for pdm_server..t_flow_cell_file_type.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取 1-项目 2-模具 字段:pdm_server..t_flow_cell_file_type.f_porm
     * @return pdm_server..t_flow_cell_file_type.f_porm, 1-项目 2-模具
     * @mbg.generated
     */
    public Integer getfPorm() {
        return fPorm;
    }

    /**
     * 设置 1-项目 2-模具 字段:pdm_server..t_flow_cell_file_type.f_porm
     * @param fPorm the value for pdm_server..t_flow_cell_file_type.f_porm, 1-项目 2-模具
     * @mbg.generated
     */
    public void setfPorm(Integer fPorm) {
        this.fPorm = fPorm;
    }

    /**
     * 获取 t_flow_cell_file_type_group-->fkeyid 字段:pdm_server..t_flow_cell_file_type.f_ftype
     * @return pdm_server..t_flow_cell_file_type.f_ftype, t_flow_cell_file_type_group-->fkeyid
     * @mbg.generated
     */
    public Long getfFtype() {
        return fFtype;
    }

    /**
     * 设置 t_flow_cell_file_type_group-->fkeyid 字段:pdm_server..t_flow_cell_file_type.f_ftype
     * @param fFtype the value for pdm_server..t_flow_cell_file_type.f_ftype, t_flow_cell_file_type_group-->fkeyid
     * @mbg.generated
     */
    public void setfFtype(Long fFtype) {
        this.fFtype = fFtype;
    }

    /**
     * 实例输出为字符串
     * pdm_server..t_flow_cell_file_type
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
        sb.append(", fCid=").append(fCid);
        sb.append(", fUid=").append(fUid);
        sb.append(", fCdate=").append(fCdate);
        sb.append(", fUdate=").append(fUdate);
        sb.append(", fState=").append(fState);
        sb.append(", fPorm=").append(fPorm);
        sb.append(", fFtype=").append(fFtype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * pdm_server..t_flow_cell_file_type
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
        TFlowCellFileType other = (TFlowCellFileType) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfCid() == null ? other.getfCid() == null : this.getfCid().equals(other.getfCid()))
            && (this.getfUid() == null ? other.getfUid() == null : this.getfUid().equals(other.getfUid()))
            && (this.getfCdate() == null ? other.getfCdate() == null : this.getfCdate().equals(other.getfCdate()))
            && (this.getfUdate() == null ? other.getfUdate() == null : this.getfUdate().equals(other.getfUdate()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfPorm() == null ? other.getfPorm() == null : this.getfPorm().equals(other.getfPorm()))
            && (this.getfFtype() == null ? other.getfFtype() == null : this.getfFtype().equals(other.getfFtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfCid() == null) ? 0 : getfCid().hashCode());
        result = prime * result + ((getfUid() == null) ? 0 : getfUid().hashCode());
        result = prime * result + ((getfCdate() == null) ? 0 : getfCdate().hashCode());
        result = prime * result + ((getfUdate() == null) ? 0 : getfUdate().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfPorm() == null) ? 0 : getfPorm().hashCode());
        result = prime * result + ((getfFtype() == null) ? 0 : getfFtype().hashCode());
        return result;
    }
}