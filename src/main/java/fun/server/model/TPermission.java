package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_permission
*/
@Table(name = "t_permission")
public class TPermission implements Serializable {
    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_permission.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_permission.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_permission.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_permission.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_permission.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : pdm_drawingdocument_server..t_permission.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 权限组ID
     * 表字段 : pdm_drawingdocument_server..t_permission.FPGID
     * @mbg.generated
     */
    private Long fpgid;

    /**
     * 权限类型：1-功能 2-数据 -1全部（有-1存在时其他规则都不执行）
     * 表字段 : pdm_drawingdocument_server..t_permission.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 菜单ID
     * 表字段 : pdm_drawingdocument_server..t_permission.FMenID
     * @mbg.generated
     */
    private Long fmenid;

    /**
     * 权限项表ID
     * 表字段 : pdm_drawingdocument_server..t_permission.FTerm
     * @mbg.generated
     */
    private Long fterm;

    /**
     * 备注
     * 表字段 : pdm_drawingdocument_server..t_permission.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * pdm_drawingdocument_server..t_permission
     * @mbg.generated
     */
    public TPermission(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fpgid, Integer ftype, Long fmenid, Long fterm, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fpgid = fpgid;
        this.ftype = ftype;
        this.fmenid = fmenid;
        this.fterm = fterm;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * pdm_drawingdocument_server..t_permission
     * @mbg.generated
     */
    public TPermission() {
        super();
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_permission.FKeyID
     * @return pdm_drawingdocument_server..t_permission.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_permission.FKeyID
     * @param fkeyid the value for pdm_drawingdocument_server..t_permission.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_permission.FCID
     * @return pdm_drawingdocument_server..t_permission.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_permission.FCID
     * @param fcid the value for pdm_drawingdocument_server..t_permission.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_permission.FUID
     * @return pdm_drawingdocument_server..t_permission.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_permission.FUID
     * @param fuid the value for pdm_drawingdocument_server..t_permission.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_permission.FCDATE
     * @return pdm_drawingdocument_server..t_permission.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_permission.FCDATE
     * @param fcdate the value for pdm_drawingdocument_server..t_permission.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_permission.FUDATE
     * @return pdm_drawingdocument_server..t_permission.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_permission.FUDATE
     * @param fudate the value for pdm_drawingdocument_server..t_permission.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:pdm_drawingdocument_server..t_permission.FState
     * @return pdm_drawingdocument_server..t_permission.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:pdm_drawingdocument_server..t_permission.FState
     * @param fstate the value for pdm_drawingdocument_server..t_permission.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 权限组ID 字段:pdm_drawingdocument_server..t_permission.FPGID
     * @return pdm_drawingdocument_server..t_permission.FPGID, 权限组ID
     * @mbg.generated
     */
    public Long getFpgid() {
        return fpgid;
    }

    /**
     * 设置 权限组ID 字段:pdm_drawingdocument_server..t_permission.FPGID
     * @param fpgid the value for pdm_drawingdocument_server..t_permission.FPGID, 权限组ID
     * @mbg.generated
     */
    public void setFpgid(Long fpgid) {
        this.fpgid = fpgid;
    }

    /**
     * 获取 权限类型：1-功能 2-数据 -1全部（有-1存在时其他规则都不执行） 字段:pdm_drawingdocument_server..t_permission.FType
     * @return pdm_drawingdocument_server..t_permission.FType, 权限类型：1-功能 2-数据 -1全部（有-1存在时其他规则都不执行）
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 权限类型：1-功能 2-数据 -1全部（有-1存在时其他规则都不执行） 字段:pdm_drawingdocument_server..t_permission.FType
     * @param ftype the value for pdm_drawingdocument_server..t_permission.FType, 权限类型：1-功能 2-数据 -1全部（有-1存在时其他规则都不执行）
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 菜单ID 字段:pdm_drawingdocument_server..t_permission.FMenID
     * @return pdm_drawingdocument_server..t_permission.FMenID, 菜单ID
     * @mbg.generated
     */
    public Long getFmenid() {
        return fmenid;
    }

    /**
     * 设置 菜单ID 字段:pdm_drawingdocument_server..t_permission.FMenID
     * @param fmenid the value for pdm_drawingdocument_server..t_permission.FMenID, 菜单ID
     * @mbg.generated
     */
    public void setFmenid(Long fmenid) {
        this.fmenid = fmenid;
    }

    /**
     * 获取 权限项表ID 字段:pdm_drawingdocument_server..t_permission.FTerm
     * @return pdm_drawingdocument_server..t_permission.FTerm, 权限项表ID
     * @mbg.generated
     */
    public Long getFterm() {
        return fterm;
    }

    /**
     * 设置 权限项表ID 字段:pdm_drawingdocument_server..t_permission.FTerm
     * @param fterm the value for pdm_drawingdocument_server..t_permission.FTerm, 权限项表ID
     * @mbg.generated
     */
    public void setFterm(Long fterm) {
        this.fterm = fterm;
    }

    /**
     * 获取 备注 字段:pdm_drawingdocument_server..t_permission.FNote
     * @return pdm_drawingdocument_server..t_permission.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:pdm_drawingdocument_server..t_permission.FNote
     * @param fnote the value for pdm_drawingdocument_server..t_permission.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * pdm_drawingdocument_server..t_permission
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
        sb.append(", fpgid=").append(fpgid);
        sb.append(", ftype=").append(ftype);
        sb.append(", fmenid=").append(fmenid);
        sb.append(", fterm=").append(fterm);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * pdm_drawingdocument_server..t_permission
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
        TPermission other = (TPermission) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFpgid() == null ? other.getFpgid() == null : this.getFpgid().equals(other.getFpgid()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFmenid() == null ? other.getFmenid() == null : this.getFmenid().equals(other.getFmenid()))
            && (this.getFterm() == null ? other.getFterm() == null : this.getFterm().equals(other.getFterm()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * pdm_drawingdocument_server..t_permission
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
        result = prime * result + ((getFpgid() == null) ? 0 : getFpgid().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFmenid() == null) ? 0 : getFmenid().hashCode());
        result = prime * result + ((getFterm() == null) ? 0 : getFterm().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}