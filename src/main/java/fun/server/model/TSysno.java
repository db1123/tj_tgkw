package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_sysno
*/
@Table(name = "t_sysno")
public class TSysno implements Serializable {
    /**
     * 
     * 表字段 : t_sysno.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_sysno.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_sysno.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_sysno.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_sysno.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : t_sysno.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 1=任务,
     * 表字段 : t_sysno.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 系统编码,生成(XXXXX+年月+月流水3位)
     * 表字段 : t_sysno.FSysNo
     * @mbg.generated
     */
    private String fsysno;

    /**
     * 系统编码流水
     * 表字段 : t_sysno.FSysNoNum
     * @mbg.generated
     */
    private Integer fsysnonum;

    /**
     * 年月
     * 表字段 : t_sysno.FYYMMNum
     * @mbg.generated
     */
    private String fyymmnum;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_sysno
     * @mbg.generated
     */
    public TSysno(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer ftype, String fsysno, Integer fsysnonum, String fyymmnum) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftype = ftype;
        this.fsysno = fsysno;
        this.fsysnonum = fsysnonum;
        this.fyymmnum = fyymmnum;
    }

    /**
     * 构造查询条件
     * t_sysno
     * @mbg.generated
     */
    public TSysno() {
        super();
    }

    /**
     * 获取  字段:t_sysno.FKeyID
     * @return t_sysno.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_sysno.FKeyID
     * @param fkeyid the value for t_sysno.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_sysno.FCID
     * @return t_sysno.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_sysno.FCID
     * @param fcid the value for t_sysno.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_sysno.FUID
     * @return t_sysno.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_sysno.FUID
     * @param fuid the value for t_sysno.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_sysno.FCDATE
     * @return t_sysno.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_sysno.FCDATE
     * @param fcdate the value for t_sysno.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_sysno.FUDATE
     * @return t_sysno.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_sysno.FUDATE
     * @param fudate the value for t_sysno.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:t_sysno.FState
     * @return t_sysno.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:t_sysno.FState
     * @param fstate the value for t_sysno.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 1=任务, 字段:t_sysno.FType
     * @return t_sysno.FType, 1=任务,
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 1=任务, 字段:t_sysno.FType
     * @param ftype the value for t_sysno.FType, 1=任务,
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 系统编码,生成(XXXXX+年月+月流水3位) 字段:t_sysno.FSysNo
     * @return t_sysno.FSysNo, 系统编码,生成(XXXXX+年月+月流水3位)
     * @mbg.generated
     */
    public String getFsysno() {
        return fsysno;
    }

    /**
     * 设置 系统编码,生成(XXXXX+年月+月流水3位) 字段:t_sysno.FSysNo
     * @param fsysno the value for t_sysno.FSysNo, 系统编码,生成(XXXXX+年月+月流水3位)
     * @mbg.generated
     */
    public void setFsysno(String fsysno) {
        this.fsysno = fsysno == null ? null : fsysno.trim();
    }

    /**
     * 获取 系统编码流水 字段:t_sysno.FSysNoNum
     * @return t_sysno.FSysNoNum, 系统编码流水
     * @mbg.generated
     */
    public Integer getFsysnonum() {
        return fsysnonum;
    }

    /**
     * 设置 系统编码流水 字段:t_sysno.FSysNoNum
     * @param fsysnonum the value for t_sysno.FSysNoNum, 系统编码流水
     * @mbg.generated
     */
    public void setFsysnonum(Integer fsysnonum) {
        this.fsysnonum = fsysnonum;
    }

    /**
     * 获取 年月 字段:t_sysno.FYYMMNum
     * @return t_sysno.FYYMMNum, 年月
     * @mbg.generated
     */
    public String getFyymmnum() {
        return fyymmnum;
    }

    /**
     * 设置 年月 字段:t_sysno.FYYMMNum
     * @param fyymmnum the value for t_sysno.FYYMMNum, 年月
     * @mbg.generated
     */
    public void setFyymmnum(String fyymmnum) {
        this.fyymmnum = fyymmnum == null ? null : fyymmnum.trim();
    }

    /**
     * 实例输出为字符串
     * t_sysno
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
        sb.append(", ftype=").append(ftype);
        sb.append(", fsysno=").append(fsysno);
        sb.append(", fsysnonum=").append(fsysnonum);
        sb.append(", fyymmnum=").append(fyymmnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_sysno
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
        TSysno other = (TSysno) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFsysno() == null ? other.getFsysno() == null : this.getFsysno().equals(other.getFsysno()))
            && (this.getFsysnonum() == null ? other.getFsysnonum() == null : this.getFsysnonum().equals(other.getFsysnonum()))
            && (this.getFyymmnum() == null ? other.getFyymmnum() == null : this.getFyymmnum().equals(other.getFyymmnum()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_sysno
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
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFsysno() == null) ? 0 : getFsysno().hashCode());
        result = prime * result + ((getFsysnonum() == null) ? 0 : getFsysnonum().hashCode());
        result = prime * result + ((getFyymmnum() == null) ? 0 : getFyymmnum().hashCode());
        return result;
    }
}