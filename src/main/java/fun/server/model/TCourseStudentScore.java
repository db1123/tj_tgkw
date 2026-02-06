package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_student_score
*/
@Table(name = "t_course_student_score")
public class TCourseStudentScore implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_student_score.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_student_score.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 课程报名ID，T_Course_Student
     * 表字段 : student_capability_evaluation..t_course_student_score.FCourseStudentID
     * @mbg.generated
     */
    private Long fcoursestudentid;

    /**
     * 能力考核方式ID,T_Course_Assessment_Method
     * 表字段 : student_capability_evaluation..t_course_student_score.FCAMID
     * @mbg.generated
     */
    private Long fcamid;

    /**
     * 学生ID，T_Student
     * 表字段 : student_capability_evaluation..t_course_student_score.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 考核条件ID（逗号间隔）
     * 表字段 : student_capability_evaluation..t_course_student_score.FConditionID
     * @mbg.generated
     */
    private String fconditionid;

    /**
     * 考核条件总分
     * 表字段 : student_capability_evaluation..t_course_student_score.FZScore
     * @mbg.generated
     */
    private Float fzscore;

    /**
     * 学生得分(手填的)
     * 表字段 : student_capability_evaluation..t_course_student_score.FStudentScore
     * @mbg.generated
     */
    private Float fstudentscore;

    /**
     * 考核总权重
     * 表字段 : student_capability_evaluation..t_course_student_score.FContributionWeight
     * @mbg.generated
     */
    private Float fcontributionweight;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public TCourseStudentScore(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fcoursestudentid, Long fcamid, Long fstudentid, String fconditionid, Float fzscore, Float fstudentscore, Float fcontributionweight) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fcoursestudentid = fcoursestudentid;
        this.fcamid = fcamid;
        this.fstudentid = fstudentid;
        this.fconditionid = fconditionid;
        this.fzscore = fzscore;
        this.fstudentscore = fstudentscore;
        this.fcontributionweight = fcontributionweight;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public TCourseStudentScore() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score.FKeyID
     * @return student_capability_evaluation..t_course_student_score.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_student_score.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score.FCID
     * @return student_capability_evaluation..t_course_student_score.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score.FCID
     * @param fcid the value for student_capability_evaluation..t_course_student_score.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score.FUID
     * @return student_capability_evaluation..t_course_student_score.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score.FUID
     * @param fuid the value for student_capability_evaluation..t_course_student_score.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score.FCDATE
     * @return student_capability_evaluation..t_course_student_score.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_student_score.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score.FUDATE
     * @return student_capability_evaluation..t_course_student_score.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_student_score.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score.FState
     * @return student_capability_evaluation..t_course_student_score.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score.FState
     * @param fstate the value for student_capability_evaluation..t_course_student_score.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_student_score.FCourseID
     * @return student_capability_evaluation..t_course_student_score.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_student_score.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_student_score.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 课程报名ID，T_Course_Student 字段:student_capability_evaluation..t_course_student_score.FCourseStudentID
     * @return student_capability_evaluation..t_course_student_score.FCourseStudentID, 课程报名ID，T_Course_Student
     * @mbg.generated
     */
    public Long getFcoursestudentid() {
        return fcoursestudentid;
    }

    /**
     * 设置 课程报名ID，T_Course_Student 字段:student_capability_evaluation..t_course_student_score.FCourseStudentID
     * @param fcoursestudentid the value for student_capability_evaluation..t_course_student_score.FCourseStudentID, 课程报名ID，T_Course_Student
     * @mbg.generated
     */
    public void setFcoursestudentid(Long fcoursestudentid) {
        this.fcoursestudentid = fcoursestudentid;
    }

    /**
     * 获取 能力考核方式ID,T_Course_Assessment_Method 字段:student_capability_evaluation..t_course_student_score.FCAMID
     * @return student_capability_evaluation..t_course_student_score.FCAMID, 能力考核方式ID,T_Course_Assessment_Method
     * @mbg.generated
     */
    public Long getFcamid() {
        return fcamid;
    }

    /**
     * 设置 能力考核方式ID,T_Course_Assessment_Method 字段:student_capability_evaluation..t_course_student_score.FCAMID
     * @param fcamid the value for student_capability_evaluation..t_course_student_score.FCAMID, 能力考核方式ID,T_Course_Assessment_Method
     * @mbg.generated
     */
    public void setFcamid(Long fcamid) {
        this.fcamid = fcamid;
    }

    /**
     * 获取 学生ID，T_Student 字段:student_capability_evaluation..t_course_student_score.FStudentID
     * @return student_capability_evaluation..t_course_student_score.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID，T_Student 字段:student_capability_evaluation..t_course_student_score.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_course_student_score.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 考核条件ID（逗号间隔） 字段:student_capability_evaluation..t_course_student_score.FConditionID
     * @return student_capability_evaluation..t_course_student_score.FConditionID, 考核条件ID（逗号间隔）
     * @mbg.generated
     */
    public String getFconditionid() {
        return fconditionid;
    }

    /**
     * 设置 考核条件ID（逗号间隔） 字段:student_capability_evaluation..t_course_student_score.FConditionID
     * @param fconditionid the value for student_capability_evaluation..t_course_student_score.FConditionID, 考核条件ID（逗号间隔）
     * @mbg.generated
     */
    public void setFconditionid(String fconditionid) {
        this.fconditionid = fconditionid == null ? null : fconditionid.trim();
    }

    /**
     * 获取 考核条件总分 字段:student_capability_evaluation..t_course_student_score.FZScore
     * @return student_capability_evaluation..t_course_student_score.FZScore, 考核条件总分
     * @mbg.generated
     */
    public Float getFzscore() {
        return fzscore;
    }

    /**
     * 设置 考核条件总分 字段:student_capability_evaluation..t_course_student_score.FZScore
     * @param fzscore the value for student_capability_evaluation..t_course_student_score.FZScore, 考核条件总分
     * @mbg.generated
     */
    public void setFzscore(Float fzscore) {
        this.fzscore = fzscore;
    }

    /**
     * 获取 学生得分(手填的) 字段:student_capability_evaluation..t_course_student_score.FStudentScore
     * @return student_capability_evaluation..t_course_student_score.FStudentScore, 学生得分(手填的)
     * @mbg.generated
     */
    public Float getFstudentscore() {
        return fstudentscore;
    }

    /**
     * 设置 学生得分(手填的) 字段:student_capability_evaluation..t_course_student_score.FStudentScore
     * @param fstudentscore the value for student_capability_evaluation..t_course_student_score.FStudentScore, 学生得分(手填的)
     * @mbg.generated
     */
    public void setFstudentscore(Float fstudentscore) {
        this.fstudentscore = fstudentscore;
    }

    /**
     * 获取 考核总权重 字段:student_capability_evaluation..t_course_student_score.FContributionWeight
     * @return student_capability_evaluation..t_course_student_score.FContributionWeight, 考核总权重
     * @mbg.generated
     */
    public Float getFcontributionweight() {
        return fcontributionweight;
    }

    /**
     * 设置 考核总权重 字段:student_capability_evaluation..t_course_student_score.FContributionWeight
     * @param fcontributionweight the value for student_capability_evaluation..t_course_student_score.FContributionWeight, 考核总权重
     * @mbg.generated
     */
    public void setFcontributionweight(Float fcontributionweight) {
        this.fcontributionweight = fcontributionweight;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_student_score
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
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fcoursestudentid=").append(fcoursestudentid);
        sb.append(", fcamid=").append(fcamid);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fconditionid=").append(fconditionid);
        sb.append(", fzscore=").append(fzscore);
        sb.append(", fstudentscore=").append(fstudentscore);
        sb.append(", fcontributionweight=").append(fcontributionweight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_student_score
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
        TCourseStudentScore other = (TCourseStudentScore) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFcoursestudentid() == null ? other.getFcoursestudentid() == null : this.getFcoursestudentid().equals(other.getFcoursestudentid()))
            && (this.getFcamid() == null ? other.getFcamid() == null : this.getFcamid().equals(other.getFcamid()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFconditionid() == null ? other.getFconditionid() == null : this.getFconditionid().equals(other.getFconditionid()))
            && (this.getFzscore() == null ? other.getFzscore() == null : this.getFzscore().equals(other.getFzscore()))
            && (this.getFstudentscore() == null ? other.getFstudentscore() == null : this.getFstudentscore().equals(other.getFstudentscore()))
            && (this.getFcontributionweight() == null ? other.getFcontributionweight() == null : this.getFcontributionweight().equals(other.getFcontributionweight()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_student_score
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
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFcoursestudentid() == null) ? 0 : getFcoursestudentid().hashCode());
        result = prime * result + ((getFcamid() == null) ? 0 : getFcamid().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFconditionid() == null) ? 0 : getFconditionid().hashCode());
        result = prime * result + ((getFzscore() == null) ? 0 : getFzscore().hashCode());
        result = prime * result + ((getFstudentscore() == null) ? 0 : getFstudentscore().hashCode());
        result = prime * result + ((getFcontributionweight() == null) ? 0 : getFcontributionweight().hashCode());
        return result;
    }
}