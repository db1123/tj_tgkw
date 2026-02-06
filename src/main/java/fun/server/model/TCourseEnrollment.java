package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_enrollment
*/
@Table(name = "t_course_enrollment")
public class TCourseEnrollment implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_enrollment.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_enrollment.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_enrollment.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_enrollment.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_enrollment.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_enrollment.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 培养方案ID
     * 表字段 : student_capability_evaluation..t_course_enrollment.FCOID
     * @mbg.generated
     */
    private Long fcoid;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_course_enrollment.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 学生ID
     * 表字段 : student_capability_evaluation..t_course_enrollment.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 选课时间
     * 表字段 : student_capability_evaluation..t_course_enrollment.FEnrollTime
     * @mbg.generated
     */
    private Date fenrolltime;

    /**
     * 状态：1成功 2=退课
     * 表字段 : student_capability_evaluation..t_course_enrollment.FStatus
     * @mbg.generated
     */
    private Integer fstatus;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_course_enrollment.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 方式：1-申请、2-添加
     * 表字段 : student_capability_evaluation..t_course_enrollment.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 分数
     * 表字段 : student_capability_evaluation..t_course_enrollment.FScore
     * @mbg.generated
     */
    private Float fscore;

    /**
     * 是否通过，-1-无结果，1-通过，2-不通过
     * 表字段 : student_capability_evaluation..t_course_enrollment.FIfPass
     * @mbg.generated
     */
    private Integer fifpass;

    /**
     * 默认-1，成绩获取方式，1=Excel 2=接口3=在线评价
     * 表字段 : student_capability_evaluation..t_course_enrollment.FCJType
     * @mbg.generated
     */
    private Integer fcjtype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_enrollment
     * @mbg.generated
     */
    public TCourseEnrollment(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcoid, Long fcourseid, Long fstudentid, Date fenrolltime, Integer fstatus, String fnote, Integer fmode, Float fscore, Integer fifpass, Integer fcjtype) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcoid = fcoid;
        this.fcourseid = fcourseid;
        this.fstudentid = fstudentid;
        this.fenrolltime = fenrolltime;
        this.fstatus = fstatus;
        this.fnote = fnote;
        this.fmode = fmode;
        this.fscore = fscore;
        this.fifpass = fifpass;
        this.fcjtype = fcjtype;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_enrollment
     * @mbg.generated
     */
    public TCourseEnrollment() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_enrollment.FKeyID
     * @return student_capability_evaluation..t_course_enrollment.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_enrollment.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_enrollment.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_enrollment.FCID
     * @return student_capability_evaluation..t_course_enrollment.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_enrollment.FCID
     * @param fcid the value for student_capability_evaluation..t_course_enrollment.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_enrollment.FUID
     * @return student_capability_evaluation..t_course_enrollment.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_enrollment.FUID
     * @param fuid the value for student_capability_evaluation..t_course_enrollment.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_enrollment.FCDATE
     * @return student_capability_evaluation..t_course_enrollment.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_enrollment.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_enrollment.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_enrollment.FUDATE
     * @return student_capability_evaluation..t_course_enrollment.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_enrollment.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_enrollment.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_enrollment.FState
     * @return student_capability_evaluation..t_course_enrollment.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_enrollment.FState
     * @param fstate the value for student_capability_evaluation..t_course_enrollment.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 培养方案ID 字段:student_capability_evaluation..t_course_enrollment.FCOID
     * @return student_capability_evaluation..t_course_enrollment.FCOID, 培养方案ID
     * @mbg.generated
     */
    public Long getFcoid() {
        return fcoid;
    }

    /**
     * 设置 培养方案ID 字段:student_capability_evaluation..t_course_enrollment.FCOID
     * @param fcoid the value for student_capability_evaluation..t_course_enrollment.FCOID, 培养方案ID
     * @mbg.generated
     */
    public void setFcoid(Long fcoid) {
        this.fcoid = fcoid;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_course_enrollment.FCourseID
     * @return student_capability_evaluation..t_course_enrollment.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_course_enrollment.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_enrollment.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 学生ID 字段:student_capability_evaluation..t_course_enrollment.FStudentID
     * @return student_capability_evaluation..t_course_enrollment.FStudentID, 学生ID
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID 字段:student_capability_evaluation..t_course_enrollment.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_course_enrollment.FStudentID, 学生ID
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 选课时间 字段:student_capability_evaluation..t_course_enrollment.FEnrollTime
     * @return student_capability_evaluation..t_course_enrollment.FEnrollTime, 选课时间
     * @mbg.generated
     */
    public Date getFenrolltime() {
        return fenrolltime;
    }

    /**
     * 设置 选课时间 字段:student_capability_evaluation..t_course_enrollment.FEnrollTime
     * @param fenrolltime the value for student_capability_evaluation..t_course_enrollment.FEnrollTime, 选课时间
     * @mbg.generated
     */
    public void setFenrolltime(Date fenrolltime) {
        this.fenrolltime = fenrolltime;
    }

    /**
     * 获取 状态：1成功 2=退课 字段:student_capability_evaluation..t_course_enrollment.FStatus
     * @return student_capability_evaluation..t_course_enrollment.FStatus, 状态：1成功 2=退课
     * @mbg.generated
     */
    public Integer getFstatus() {
        return fstatus;
    }

    /**
     * 设置 状态：1成功 2=退课 字段:student_capability_evaluation..t_course_enrollment.FStatus
     * @param fstatus the value for student_capability_evaluation..t_course_enrollment.FStatus, 状态：1成功 2=退课
     * @mbg.generated
     */
    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_course_enrollment.FNote
     * @return student_capability_evaluation..t_course_enrollment.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_course_enrollment.FNote
     * @param fnote the value for student_capability_evaluation..t_course_enrollment.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 方式：1-申请、2-添加 字段:student_capability_evaluation..t_course_enrollment.FMode
     * @return student_capability_evaluation..t_course_enrollment.FMode, 方式：1-申请、2-添加
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式：1-申请、2-添加 字段:student_capability_evaluation..t_course_enrollment.FMode
     * @param fmode the value for student_capability_evaluation..t_course_enrollment.FMode, 方式：1-申请、2-添加
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取 分数 字段:student_capability_evaluation..t_course_enrollment.FScore
     * @return student_capability_evaluation..t_course_enrollment.FScore, 分数
     * @mbg.generated
     */
    public Float getFscore() {
        return fscore;
    }

    /**
     * 设置 分数 字段:student_capability_evaluation..t_course_enrollment.FScore
     * @param fscore the value for student_capability_evaluation..t_course_enrollment.FScore, 分数
     * @mbg.generated
     */
    public void setFscore(Float fscore) {
        this.fscore = fscore;
    }

    /**
     * 获取 是否通过，-1-无结果，1-通过，2-不通过 字段:student_capability_evaluation..t_course_enrollment.FIfPass
     * @return student_capability_evaluation..t_course_enrollment.FIfPass, 是否通过，-1-无结果，1-通过，2-不通过
     * @mbg.generated
     */
    public Integer getFifpass() {
        return fifpass;
    }

    /**
     * 设置 是否通过，-1-无结果，1-通过，2-不通过 字段:student_capability_evaluation..t_course_enrollment.FIfPass
     * @param fifpass the value for student_capability_evaluation..t_course_enrollment.FIfPass, 是否通过，-1-无结果，1-通过，2-不通过
     * @mbg.generated
     */
    public void setFifpass(Integer fifpass) {
        this.fifpass = fifpass;
    }

    /**
     * 获取 默认-1，成绩获取方式，1=Excel 2=接口3=在线评价 字段:student_capability_evaluation..t_course_enrollment.FCJType
     * @return student_capability_evaluation..t_course_enrollment.FCJType, 默认-1，成绩获取方式，1=Excel 2=接口3=在线评价
     * @mbg.generated
     */
    public Integer getFcjtype() {
        return fcjtype;
    }

    /**
     * 设置 默认-1，成绩获取方式，1=Excel 2=接口3=在线评价 字段:student_capability_evaluation..t_course_enrollment.FCJType
     * @param fcjtype the value for student_capability_evaluation..t_course_enrollment.FCJType, 默认-1，成绩获取方式，1=Excel 2=接口3=在线评价
     * @mbg.generated
     */
    public void setFcjtype(Integer fcjtype) {
        this.fcjtype = fcjtype;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_enrollment
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
        sb.append(", fcoid=").append(fcoid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fenrolltime=").append(fenrolltime);
        sb.append(", fstatus=").append(fstatus);
        sb.append(", fnote=").append(fnote);
        sb.append(", fmode=").append(fmode);
        sb.append(", fscore=").append(fscore);
        sb.append(", fifpass=").append(fifpass);
        sb.append(", fcjtype=").append(fcjtype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_enrollment
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
        TCourseEnrollment other = (TCourseEnrollment) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcoid() == null ? other.getFcoid() == null : this.getFcoid().equals(other.getFcoid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFenrolltime() == null ? other.getFenrolltime() == null : this.getFenrolltime().equals(other.getFenrolltime()))
            && (this.getFstatus() == null ? other.getFstatus() == null : this.getFstatus().equals(other.getFstatus()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()))
            && (this.getFifpass() == null ? other.getFifpass() == null : this.getFifpass().equals(other.getFifpass()))
            && (this.getFcjtype() == null ? other.getFcjtype() == null : this.getFcjtype().equals(other.getFcjtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_enrollment
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
        result = prime * result + ((getFcoid() == null) ? 0 : getFcoid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFenrolltime() == null) ? 0 : getFenrolltime().hashCode());
        result = prime * result + ((getFstatus() == null) ? 0 : getFstatus().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFmode() == null) ? 0 : getFmode().hashCode());
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        result = prime * result + ((getFifpass() == null) ? 0 : getFifpass().hashCode());
        result = prime * result + ((getFcjtype() == null) ? 0 : getFcjtype().hashCode());
        return result;
    }
}