package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_offering
*/
@Table(name = "t_course_offering")
public class TCourseOffering implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_offering.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_offering.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_offering.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_offering.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_offering.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_offering.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 培养方案表ID
     * 表字段 : student_capability_evaluation..t_course_offering.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 课程表ID
     * 表字段 : student_capability_evaluation..t_course_offering.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 学期表ID
     * 表字段 : student_capability_evaluation..t_course_offering.FSemester
     * @mbg.generated
     */
    private Long fsemester;

    /**
     * 授课教师表ID
     * 表字段 : student_capability_evaluation..t_course_offering.FTeacher
     * @mbg.generated
     */
    private Long fteacher;

    /**
     * 最大人数
     * 表字段 : student_capability_evaluation..t_course_offering.FMaxCapacity
     * @mbg.generated
     */
    private Integer fmaxcapacity;

    /**
     * 选课人数
     * 表字段 : student_capability_evaluation..t_course_offering.FCurrentEnrollment
     * @mbg.generated
     */
    private Integer fcurrentenrollment;

    /**
     * 0-编辑、1-审核？2开放3已结束
     * 表字段 : student_capability_evaluation..t_course_offering.FStatus
     * @mbg.generated
     */
    private Integer fstatus;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_course_offering.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public TCourseOffering(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftpid, Long fcourseid, Long fsemester, Long fteacher, Integer fmaxcapacity, Integer fcurrentenrollment, Integer fstatus, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftpid = ftpid;
        this.fcourseid = fcourseid;
        this.fsemester = fsemester;
        this.fteacher = fteacher;
        this.fmaxcapacity = fmaxcapacity;
        this.fcurrentenrollment = fcurrentenrollment;
        this.fstatus = fstatus;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public TCourseOffering() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_offering.FKeyID
     * @return student_capability_evaluation..t_course_offering.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_offering.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_offering.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_offering.FCID
     * @return student_capability_evaluation..t_course_offering.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_offering.FCID
     * @param fcid the value for student_capability_evaluation..t_course_offering.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_offering.FUID
     * @return student_capability_evaluation..t_course_offering.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_offering.FUID
     * @param fuid the value for student_capability_evaluation..t_course_offering.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_offering.FCDATE
     * @return student_capability_evaluation..t_course_offering.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_offering.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_offering.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_offering.FUDATE
     * @return student_capability_evaluation..t_course_offering.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_offering.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_offering.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_offering.FState
     * @return student_capability_evaluation..t_course_offering.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_offering.FState
     * @param fstate the value for student_capability_evaluation..t_course_offering.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 培养方案表ID 字段:student_capability_evaluation..t_course_offering.FTPID
     * @return student_capability_evaluation..t_course_offering.FTPID, 培养方案表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 培养方案表ID 字段:student_capability_evaluation..t_course_offering.FTPID
     * @param ftpid the value for student_capability_evaluation..t_course_offering.FTPID, 培养方案表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 课程表ID 字段:student_capability_evaluation..t_course_offering.FCourseID
     * @return student_capability_evaluation..t_course_offering.FCourseID, 课程表ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程表ID 字段:student_capability_evaluation..t_course_offering.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_offering.FCourseID, 课程表ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 学期表ID 字段:student_capability_evaluation..t_course_offering.FSemester
     * @return student_capability_evaluation..t_course_offering.FSemester, 学期表ID
     * @mbg.generated
     */
    public Long getFsemester() {
        return fsemester;
    }

    /**
     * 设置 学期表ID 字段:student_capability_evaluation..t_course_offering.FSemester
     * @param fsemester the value for student_capability_evaluation..t_course_offering.FSemester, 学期表ID
     * @mbg.generated
     */
    public void setFsemester(Long fsemester) {
        this.fsemester = fsemester;
    }

    /**
     * 获取 授课教师表ID 字段:student_capability_evaluation..t_course_offering.FTeacher
     * @return student_capability_evaluation..t_course_offering.FTeacher, 授课教师表ID
     * @mbg.generated
     */
    public Long getFteacher() {
        return fteacher;
    }

    /**
     * 设置 授课教师表ID 字段:student_capability_evaluation..t_course_offering.FTeacher
     * @param fteacher the value for student_capability_evaluation..t_course_offering.FTeacher, 授课教师表ID
     * @mbg.generated
     */
    public void setFteacher(Long fteacher) {
        this.fteacher = fteacher;
    }

    /**
     * 获取 最大人数 字段:student_capability_evaluation..t_course_offering.FMaxCapacity
     * @return student_capability_evaluation..t_course_offering.FMaxCapacity, 最大人数
     * @mbg.generated
     */
    public Integer getFmaxcapacity() {
        return fmaxcapacity;
    }

    /**
     * 设置 最大人数 字段:student_capability_evaluation..t_course_offering.FMaxCapacity
     * @param fmaxcapacity the value for student_capability_evaluation..t_course_offering.FMaxCapacity, 最大人数
     * @mbg.generated
     */
    public void setFmaxcapacity(Integer fmaxcapacity) {
        this.fmaxcapacity = fmaxcapacity;
    }

    /**
     * 获取 选课人数 字段:student_capability_evaluation..t_course_offering.FCurrentEnrollment
     * @return student_capability_evaluation..t_course_offering.FCurrentEnrollment, 选课人数
     * @mbg.generated
     */
    public Integer getFcurrentenrollment() {
        return fcurrentenrollment;
    }

    /**
     * 设置 选课人数 字段:student_capability_evaluation..t_course_offering.FCurrentEnrollment
     * @param fcurrentenrollment the value for student_capability_evaluation..t_course_offering.FCurrentEnrollment, 选课人数
     * @mbg.generated
     */
    public void setFcurrentenrollment(Integer fcurrentenrollment) {
        this.fcurrentenrollment = fcurrentenrollment;
    }

    /**
     * 获取 0-编辑、1-审核？2开放3已结束 字段:student_capability_evaluation..t_course_offering.FStatus
     * @return student_capability_evaluation..t_course_offering.FStatus, 0-编辑、1-审核？2开放3已结束
     * @mbg.generated
     */
    public Integer getFstatus() {
        return fstatus;
    }

    /**
     * 设置 0-编辑、1-审核？2开放3已结束 字段:student_capability_evaluation..t_course_offering.FStatus
     * @param fstatus the value for student_capability_evaluation..t_course_offering.FStatus, 0-编辑、1-审核？2开放3已结束
     * @mbg.generated
     */
    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_course_offering.FNote
     * @return student_capability_evaluation..t_course_offering.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_course_offering.FNote
     * @param fnote the value for student_capability_evaluation..t_course_offering.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_offering
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
        sb.append(", ftpid=").append(ftpid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fsemester=").append(fsemester);
        sb.append(", fteacher=").append(fteacher);
        sb.append(", fmaxcapacity=").append(fmaxcapacity);
        sb.append(", fcurrentenrollment=").append(fcurrentenrollment);
        sb.append(", fstatus=").append(fstatus);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_offering
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
        TCourseOffering other = (TCourseOffering) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFsemester() == null ? other.getFsemester() == null : this.getFsemester().equals(other.getFsemester()))
            && (this.getFteacher() == null ? other.getFteacher() == null : this.getFteacher().equals(other.getFteacher()))
            && (this.getFmaxcapacity() == null ? other.getFmaxcapacity() == null : this.getFmaxcapacity().equals(other.getFmaxcapacity()))
            && (this.getFcurrentenrollment() == null ? other.getFcurrentenrollment() == null : this.getFcurrentenrollment().equals(other.getFcurrentenrollment()))
            && (this.getFstatus() == null ? other.getFstatus() == null : this.getFstatus().equals(other.getFstatus()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_offering
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
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFsemester() == null) ? 0 : getFsemester().hashCode());
        result = prime * result + ((getFteacher() == null) ? 0 : getFteacher().hashCode());
        result = prime * result + ((getFmaxcapacity() == null) ? 0 : getFmaxcapacity().hashCode());
        result = prime * result + ((getFcurrentenrollment() == null) ? 0 : getFcurrentenrollment().hashCode());
        result = prime * result + ((getFstatus() == null) ? 0 : getFstatus().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}