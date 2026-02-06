package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_major
*/
@Table(name = "t_major")
public class TMajor implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_major.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_major.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_major.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_major.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_major.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_major.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 专业名称
     * 表字段 : student_capability_evaluation..t_major.FMajorName
     * @mbg.generated
     */
    private String fmajorname;

    /**
     * 学院id
     * 表字段 : student_capability_evaluation..t_major.FCollegeID
     * @mbg.generated
     */
    private Long fcollegeid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_major
     * @mbg.generated
     */
    public TMajor(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fmajorname, Long fcollegeid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fmajorname = fmajorname;
        this.fcollegeid = fcollegeid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_major
     * @mbg.generated
     */
    public TMajor() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_major.FKeyID
     * @return student_capability_evaluation..t_major.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_major.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_major.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_major.FCID
     * @return student_capability_evaluation..t_major.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_major.FCID
     * @param fcid the value for student_capability_evaluation..t_major.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_major.FUID
     * @return student_capability_evaluation..t_major.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_major.FUID
     * @param fuid the value for student_capability_evaluation..t_major.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_major.FCDATE
     * @return student_capability_evaluation..t_major.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_major.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_major.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_major.FUDATE
     * @return student_capability_evaluation..t_major.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_major.FUDATE
     * @param fudate the value for student_capability_evaluation..t_major.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_major.FState
     * @return student_capability_evaluation..t_major.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_major.FState
     * @param fstate the value for student_capability_evaluation..t_major.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 专业名称 字段:student_capability_evaluation..t_major.FMajorName
     * @return student_capability_evaluation..t_major.FMajorName, 专业名称
     * @mbg.generated
     */
    public String getFmajorname() {
        return fmajorname;
    }

    /**
     * 设置 专业名称 字段:student_capability_evaluation..t_major.FMajorName
     * @param fmajorname the value for student_capability_evaluation..t_major.FMajorName, 专业名称
     * @mbg.generated
     */
    public void setFmajorname(String fmajorname) {
        this.fmajorname = fmajorname == null ? null : fmajorname.trim();
    }

    /**
     * 获取 学院id 字段:student_capability_evaluation..t_major.FCollegeID
     * @return student_capability_evaluation..t_major.FCollegeID, 学院id
     * @mbg.generated
     */
    public Long getFcollegeid() {
        return fcollegeid;
    }

    /**
     * 设置 学院id 字段:student_capability_evaluation..t_major.FCollegeID
     * @param fcollegeid the value for student_capability_evaluation..t_major.FCollegeID, 学院id
     * @mbg.generated
     */
    public void setFcollegeid(Long fcollegeid) {
        this.fcollegeid = fcollegeid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_major
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
        sb.append(", fmajorname=").append(fmajorname);
        sb.append(", fcollegeid=").append(fcollegeid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_major
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
        TMajor other = (TMajor) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFmajorname() == null ? other.getFmajorname() == null : this.getFmajorname().equals(other.getFmajorname()))
            && (this.getFcollegeid() == null ? other.getFcollegeid() == null : this.getFcollegeid().equals(other.getFcollegeid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_major
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
        result = prime * result + ((getFmajorname() == null) ? 0 : getFmajorname().hashCode());
        result = prime * result + ((getFcollegeid() == null) ? 0 : getFcollegeid().hashCode());
        return result;
    }
}