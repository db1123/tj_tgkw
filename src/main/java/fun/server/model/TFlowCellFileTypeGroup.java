package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_flow_cell_file_type_group
*/
@Table(name = "t_flow_cell_file_type_group")
public class TFlowCellFileTypeGroup implements Serializable {
    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 分组名称
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 1-项目 2-模具
     * 表字段 : pdm_server..t_flow_cell_file_type_group.FType
     * @mbg.generated
     */
    private Integer ftype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * pdm_server..t_flow_cell_file_type_group
     * @mbg.generated
     */
    public TFlowCellFileTypeGroup(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, Integer ftype) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.ftype = ftype;
    }

    /**
     * 构造查询条件
     * pdm_server..t_flow_cell_file_type_group
     * @mbg.generated
     */
    public TFlowCellFileTypeGroup() {
        super();
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type_group.FKeyID
     * @return pdm_server..t_flow_cell_file_type_group.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type_group.FKeyID
     * @param fkeyid the value for pdm_server..t_flow_cell_file_type_group.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type_group.FCID
     * @return pdm_server..t_flow_cell_file_type_group.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type_group.FCID
     * @param fcid the value for pdm_server..t_flow_cell_file_type_group.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type_group.FUID
     * @return pdm_server..t_flow_cell_file_type_group.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type_group.FUID
     * @param fuid the value for pdm_server..t_flow_cell_file_type_group.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type_group.FCDATE
     * @return pdm_server..t_flow_cell_file_type_group.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type_group.FCDATE
     * @param fcdate the value for pdm_server..t_flow_cell_file_type_group.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:pdm_server..t_flow_cell_file_type_group.FUDATE
     * @return pdm_server..t_flow_cell_file_type_group.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:pdm_server..t_flow_cell_file_type_group.FUDATE
     * @param fudate the value for pdm_server..t_flow_cell_file_type_group.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:pdm_server..t_flow_cell_file_type_group.FState
     * @return pdm_server..t_flow_cell_file_type_group.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:pdm_server..t_flow_cell_file_type_group.FState
     * @param fstate the value for pdm_server..t_flow_cell_file_type_group.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 分组名称 字段:pdm_server..t_flow_cell_file_type_group.FName
     * @return pdm_server..t_flow_cell_file_type_group.FName, 分组名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 分组名称 字段:pdm_server..t_flow_cell_file_type_group.FName
     * @param fname the value for pdm_server..t_flow_cell_file_type_group.FName, 分组名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 1-项目 2-模具 字段:pdm_server..t_flow_cell_file_type_group.FType
     * @return pdm_server..t_flow_cell_file_type_group.FType, 1-项目 2-模具
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 1-项目 2-模具 字段:pdm_server..t_flow_cell_file_type_group.FType
     * @param ftype the value for pdm_server..t_flow_cell_file_type_group.FType, 1-项目 2-模具
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 实例输出为字符串
     * pdm_server..t_flow_cell_file_type_group
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fkeyid=").append(fkeyid);
        sb.append(", fcid=").append(fcid);
        sb.append(", fuid=").append(fuid);
        sb.append(", fcdate=").append(fcdate);
        sb.append(", fudate=").append(fudate);
        sb.append(", fstate=").append(fstate);
        sb.append(", fname=").append(fname);
        sb.append(", ftype=").append(ftype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * pdm_server..t_flow_cell_file_type_group
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
        TFlowCellFileTypeGroup other = (TFlowCellFileTypeGroup) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * pdm_server..t_flow_cell_file_type_group
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFcid() == null) ? 0 : getFcid().hashCode());
        result = prime * result + ((getFuid() == null) ? 0 : getFuid().hashCode());
        result = prime * result + ((getFcdate() == null) ? 0 : getFcdate().hashCode());
        result = prime * result + ((getFudate() == null) ? 0 : getFudate().hashCode());
        result = prime * result + ((getFstate() == null) ? 0 : getFstate().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        return result;
    }
}