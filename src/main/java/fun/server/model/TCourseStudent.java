package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_course_student
*/
@Table(name = "t_course_student")
public class TCourseStudent implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_student.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 时间
     * 表字段 : student_capability_evaluation..t_course_student.FDate
     * @mbg.generated
     */
    private Date fdate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_student.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 方式：0-申请、1-添加
     * 表字段 : student_capability_evaluation..t_course_student.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 学生ID，T_Student
     * 表字段 : student_capability_evaluation..t_course_student.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 分数
     * 表字段 : student_capability_evaluation..t_course_student.FScore
     * @mbg.generated
     */
    private BigDecimal fscore;

    /**
     * 是否通过，0-无结果，1-通过，2-不通过
     * 表字段 : student_capability_evaluation..t_course_student.FIfPass
     * @mbg.generated
     */
    private Integer fifpass;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student
     * @mbg.generated
     */
    public TCourseStudent(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Date fdate, Long fcourseid, Integer fmode, Long fstudentid, BigDecimal fscore, Integer fifpass) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fdate = fdate;
        this.fcourseid = fcourseid;
        this.fmode = fmode;
        this.fstudentid = fstudentid;
        this.fscore = fscore;
        this.fifpass = fifpass;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student
     * @mbg.generated
     */
    public TCourseStudent() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student.FKeyID
     * @return student_capability_evaluation..t_course_student.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_student.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student.FCID
     * @return student_capability_evaluation..t_course_student.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student.FCID
     * @param fcid the value for student_capability_evaluation..t_course_student.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student.FUID
     * @return student_capability_evaluation..t_course_student.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student.FUID
     * @param fuid the value for student_capability_evaluation..t_course_student.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student.FCDATE
     * @return student_capability_evaluation..t_course_student.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_student.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student.FUDATE
     * @return student_capability_evaluation..t_course_student.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_student.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student.FState
     * @return student_capability_evaluation..t_course_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student.FState
     * @param fstate the value for student_capability_evaluation..t_course_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 时间 字段:student_capability_evaluation..t_course_student.FDate
     * @return student_capability_evaluation..t_course_student.FDate, 时间
     * @mbg.generated
     */
    public Date getFdate() {
        return fdate;
    }

    /**
     * 设置 时间 字段:student_capability_evaluation..t_course_student.FDate
     * @param fdate the value for student_capability_evaluation..t_course_student.FDate, 时间
     * @mbg.generated
     */
    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_student.FCourseID
     * @return student_capability_evaluation..t_course_student.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_student.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_student.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 方式：0-申请、1-添加 字段:student_capability_evaluation..t_course_student.FMode
     * @return student_capability_evaluation..t_course_student.FMode, 方式：0-申请、1-添加
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式：0-申请、1-添加 字段:student_capability_evaluation..t_course_student.FMode
     * @param fmode the value for student_capability_evaluation..t_course_student.FMode, 方式：0-申请、1-添加
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取 学生ID，T_Student 字段:student_capability_evaluation..t_course_student.FStudentID
     * @return student_capability_evaluation..t_course_student.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID，T_Student 字段:student_capability_evaluation..t_course_student.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_course_student.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 分数 字段:student_capability_evaluation..t_course_student.FScore
     * @return student_capability_evaluation..t_course_student.FScore, 分数
     * @mbg.generated
     */
    public BigDecimal getFscore() {
        return fscore;
    }

    /**
     * 设置 分数 字段:student_capability_evaluation..t_course_student.FScore
     * @param fscore the value for student_capability_evaluation..t_course_student.FScore, 分数
     * @mbg.generated
     */
    public void setFscore(BigDecimal fscore) {
        this.fscore = fscore;
    }

    /**
     * 获取 是否通过，0-无结果，1-通过，2-不通过 字段:student_capability_evaluation..t_course_student.FIfPass
     * @return student_capability_evaluation..t_course_student.FIfPass, 是否通过，0-无结果，1-通过，2-不通过
     * @mbg.generated
     */
    public Integer getFifpass() {
        return fifpass;
    }

    /**
     * 设置 是否通过，0-无结果，1-通过，2-不通过 字段:student_capability_evaluation..t_course_student.FIfPass
     * @param fifpass the value for student_capability_evaluation..t_course_student.FIfPass, 是否通过，0-无结果，1-通过，2-不通过
     * @mbg.generated
     */
    public void setFifpass(Integer fifpass) {
        this.fifpass = fifpass;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_student
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
        sb.append(", fdate=").append(fdate);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fmode=").append(fmode);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fscore=").append(fscore);
        sb.append(", fifpass=").append(fifpass);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_student
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
        TCourseStudent other = (TCourseStudent) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFdate() == null ? other.getFdate() == null : this.getFdate().equals(other.getFdate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()))
            && (this.getFifpass() == null ? other.getFifpass() == null : this.getFifpass().equals(other.getFifpass()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_student
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
        result = prime * result + ((getFdate() == null) ? 0 : getFdate().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFmode() == null) ? 0 : getFmode().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        result = prime * result + ((getFifpass() == null) ? 0 : getFifpass().hashCode());
        return result;
    }
}