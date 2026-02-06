package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_semester
*/
@Table(name = "t_semester")
public class TSemester implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_semester.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_semester.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_semester.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_semester.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_semester.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_semester.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 学期名称
     * 表字段 : student_capability_evaluation..t_semester.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 学期开始时间
     * 表字段 : student_capability_evaluation..t_semester.FStartDate
     * @mbg.generated
     */
    private Date fstartdate;

    /**
     * 学期结束时间
     * 表字段 : student_capability_evaluation..t_semester.FEndDate
     * @mbg.generated
     */
    private Date fenddate;

    /**
     * 学期类型 1 秋季/ 2春季
     * 表字段 : student_capability_evaluation..t_semester.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 状态（1规划中/2进行中/3已结束）
     * 表字段 : student_capability_evaluation..t_semester.FStatus
     * @mbg.generated
     */
    private Integer fstatus;

    /**
     * 选课开始时间
     * 表字段 : student_capability_evaluation..t_semester.FRegistrationStart
     * @mbg.generated
     */
    private Date fregistrationstart;

    /**
     * 选课结束时间
     * 表字段 : student_capability_evaluation..t_semester.FRegistrationEnd
     * @mbg.generated
     */
    private Date fregistrationend;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_semester.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_semester
     * @mbg.generated
     */
    public TSemester(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, Date fstartdate, Date fenddate, Integer ftype, Integer fstatus, Date fregistrationstart, Date fregistrationend, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fstartdate = fstartdate;
        this.fenddate = fenddate;
        this.ftype = ftype;
        this.fstatus = fstatus;
        this.fregistrationstart = fregistrationstart;
        this.fregistrationend = fregistrationend;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_semester
     * @mbg.generated
     */
    public TSemester() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_semester.FKeyID
     * @return student_capability_evaluation..t_semester.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_semester.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_semester.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_semester.FCID
     * @return student_capability_evaluation..t_semester.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_semester.FCID
     * @param fcid the value for student_capability_evaluation..t_semester.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_semester.FUID
     * @return student_capability_evaluation..t_semester.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_semester.FUID
     * @param fuid the value for student_capability_evaluation..t_semester.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_semester.FCDATE
     * @return student_capability_evaluation..t_semester.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_semester.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_semester.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_semester.FUDATE
     * @return student_capability_evaluation..t_semester.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_semester.FUDATE
     * @param fudate the value for student_capability_evaluation..t_semester.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_semester.FState
     * @return student_capability_evaluation..t_semester.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_semester.FState
     * @param fstate the value for student_capability_evaluation..t_semester.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 学期名称 字段:student_capability_evaluation..t_semester.FName
     * @return student_capability_evaluation..t_semester.FName, 学期名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 学期名称 字段:student_capability_evaluation..t_semester.FName
     * @param fname the value for student_capability_evaluation..t_semester.FName, 学期名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 学期开始时间 字段:student_capability_evaluation..t_semester.FStartDate
     * @return student_capability_evaluation..t_semester.FStartDate, 学期开始时间
     * @mbg.generated
     */
    public Date getFstartdate() {
        return fstartdate;
    }

    /**
     * 设置 学期开始时间 字段:student_capability_evaluation..t_semester.FStartDate
     * @param fstartdate the value for student_capability_evaluation..t_semester.FStartDate, 学期开始时间
     * @mbg.generated
     */
    public void setFstartdate(Date fstartdate) {
        this.fstartdate = fstartdate;
    }

    /**
     * 获取 学期结束时间 字段:student_capability_evaluation..t_semester.FEndDate
     * @return student_capability_evaluation..t_semester.FEndDate, 学期结束时间
     * @mbg.generated
     */
    public Date getFenddate() {
        return fenddate;
    }

    /**
     * 设置 学期结束时间 字段:student_capability_evaluation..t_semester.FEndDate
     * @param fenddate the value for student_capability_evaluation..t_semester.FEndDate, 学期结束时间
     * @mbg.generated
     */
    public void setFenddate(Date fenddate) {
        this.fenddate = fenddate;
    }

    /**
     * 获取 学期类型 1 秋季/ 2春季 字段:student_capability_evaluation..t_semester.FType
     * @return student_capability_evaluation..t_semester.FType, 学期类型 1 秋季/ 2春季
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 学期类型 1 秋季/ 2春季 字段:student_capability_evaluation..t_semester.FType
     * @param ftype the value for student_capability_evaluation..t_semester.FType, 学期类型 1 秋季/ 2春季
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 状态（1规划中/2进行中/3已结束） 字段:student_capability_evaluation..t_semester.FStatus
     * @return student_capability_evaluation..t_semester.FStatus, 状态（1规划中/2进行中/3已结束）
     * @mbg.generated
     */
    public Integer getFstatus() {
        return fstatus;
    }

    /**
     * 设置 状态（1规划中/2进行中/3已结束） 字段:student_capability_evaluation..t_semester.FStatus
     * @param fstatus the value for student_capability_evaluation..t_semester.FStatus, 状态（1规划中/2进行中/3已结束）
     * @mbg.generated
     */
    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * 获取 选课开始时间 字段:student_capability_evaluation..t_semester.FRegistrationStart
     * @return student_capability_evaluation..t_semester.FRegistrationStart, 选课开始时间
     * @mbg.generated
     */
    public Date getFregistrationstart() {
        return fregistrationstart;
    }

    /**
     * 设置 选课开始时间 字段:student_capability_evaluation..t_semester.FRegistrationStart
     * @param fregistrationstart the value for student_capability_evaluation..t_semester.FRegistrationStart, 选课开始时间
     * @mbg.generated
     */
    public void setFregistrationstart(Date fregistrationstart) {
        this.fregistrationstart = fregistrationstart;
    }

    /**
     * 获取 选课结束时间 字段:student_capability_evaluation..t_semester.FRegistrationEnd
     * @return student_capability_evaluation..t_semester.FRegistrationEnd, 选课结束时间
     * @mbg.generated
     */
    public Date getFregistrationend() {
        return fregistrationend;
    }

    /**
     * 设置 选课结束时间 字段:student_capability_evaluation..t_semester.FRegistrationEnd
     * @param fregistrationend the value for student_capability_evaluation..t_semester.FRegistrationEnd, 选课结束时间
     * @mbg.generated
     */
    public void setFregistrationend(Date fregistrationend) {
        this.fregistrationend = fregistrationend;
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_semester.FNote
     * @return student_capability_evaluation..t_semester.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_semester.FNote
     * @param fnote the value for student_capability_evaluation..t_semester.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_semester
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
        sb.append(", fstartdate=").append(fstartdate);
        sb.append(", fenddate=").append(fenddate);
        sb.append(", ftype=").append(ftype);
        sb.append(", fstatus=").append(fstatus);
        sb.append(", fregistrationstart=").append(fregistrationstart);
        sb.append(", fregistrationend=").append(fregistrationend);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_semester
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
        TSemester other = (TSemester) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFstartdate() == null ? other.getFstartdate() == null : this.getFstartdate().equals(other.getFstartdate()))
            && (this.getFenddate() == null ? other.getFenddate() == null : this.getFenddate().equals(other.getFenddate()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFstatus() == null ? other.getFstatus() == null : this.getFstatus().equals(other.getFstatus()))
            && (this.getFregistrationstart() == null ? other.getFregistrationstart() == null : this.getFregistrationstart().equals(other.getFregistrationstart()))
            && (this.getFregistrationend() == null ? other.getFregistrationend() == null : this.getFregistrationend().equals(other.getFregistrationend()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_semester
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
        result = prime * result + ((getFstartdate() == null) ? 0 : getFstartdate().hashCode());
        result = prime * result + ((getFenddate() == null) ? 0 : getFenddate().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFstatus() == null) ? 0 : getFstatus().hashCode());
        result = prime * result + ((getFregistrationstart() == null) ? 0 : getFregistrationstart().hashCode());
        result = prime * result + ((getFregistrationend() == null) ? 0 : getFregistrationend().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}