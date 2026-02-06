package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_usergroupsrelationship
*/
@Table(name = "t_usergroupsrelationship")
public class TUsergroupsrelationship implements Serializable {
    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FUGID
     * @mbg.generated
     */
    private Long fugid;

    /**
     * 
     * 表字段 : pdm_drawingdocument_server..t_usergroupsrelationship.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * pdm_drawingdocument_server..t_usergroupsrelationship
     * @mbg.generated
     */
    public TUsergroupsrelationship(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fugid, Long fuserid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fugid = fugid;
        this.fuserid = fuserid;
    }

    /**
     * 构造查询条件
     * pdm_drawingdocument_server..t_usergroupsrelationship
     * @mbg.generated
     */
    public TUsergroupsrelationship() {
        super();
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FKeyID
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FKeyID
     * @param fkeyid the value for pdm_drawingdocument_server..t_usergroupsrelationship.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FCID
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FCID
     * @param fcid the value for pdm_drawingdocument_server..t_usergroupsrelationship.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUID
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUID
     * @param fuid the value for pdm_drawingdocument_server..t_usergroupsrelationship.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FCDATE
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FCDATE
     * @param fcdate the value for pdm_drawingdocument_server..t_usergroupsrelationship.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUDATE
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUDATE
     * @param fudate the value for pdm_drawingdocument_server..t_usergroupsrelationship.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:pdm_drawingdocument_server..t_usergroupsrelationship.FState
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:pdm_drawingdocument_server..t_usergroupsrelationship.FState
     * @param fstate the value for pdm_drawingdocument_server..t_usergroupsrelationship.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUGID
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FUGID, 
     * @mbg.generated
     */
    public Long getFugid() {
        return fugid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUGID
     * @param fugid the value for pdm_drawingdocument_server..t_usergroupsrelationship.FUGID, 
     * @mbg.generated
     */
    public void setFugid(Long fugid) {
        this.fugid = fugid;
    }

    /**
     * 获取  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUserID
     * @return pdm_drawingdocument_server..t_usergroupsrelationship.FUserID, 
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置  字段:pdm_drawingdocument_server..t_usergroupsrelationship.FUserID
     * @param fuserid the value for pdm_drawingdocument_server..t_usergroupsrelationship.FUserID, 
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 实例输出为字符串
     * pdm_drawingdocument_server..t_usergroupsrelationship
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
        sb.append(", fugid=").append(fugid);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * pdm_drawingdocument_server..t_usergroupsrelationship
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
        TUsergroupsrelationship other = (TUsergroupsrelationship) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFugid() == null ? other.getFugid() == null : this.getFugid().equals(other.getFugid()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * pdm_drawingdocument_server..t_usergroupsrelationship
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
        result = prime * result + ((getFugid() == null) ? 0 : getFugid().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        return result;
    }
}