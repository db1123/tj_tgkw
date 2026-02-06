package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_hire_student
*/
@Table(name = "t_hire_student")
public class THireStudent implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_student.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_student.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_student.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_student.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_student.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态，0-未选择，1-已选择
     * 表字段 : student_capability_evaluation..t_hire_student.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 招聘表ID，T_Hire
     * 表字段 : student_capability_evaluation..t_hire_student.FHireID
     * @mbg.generated
     */
    private Long fhireid;

    /**
     * 学生ID，T_Student
     * 表字段 : student_capability_evaluation..t_hire_student.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 方式，1-选择，2-申请
     * 表字段 : student_capability_evaluation..t_hire_student.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 综合评分（根据已有能力和权重计算）
     * 表字段 : student_capability_evaluation..t_hire_student.FScore
     * @mbg.generated
     */
    private Integer fscore;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire_student
     * @mbg.generated
     */
    public THireStudent(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fhireid, Long fstudentid, Integer fmode, Integer fscore) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fhireid = fhireid;
        this.fstudentid = fstudentid;
        this.fmode = fmode;
        this.fscore = fscore;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire_student
     * @mbg.generated
     */
    public THireStudent() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_student.FKeyID
     * @return student_capability_evaluation..t_hire_student.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_student.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_hire_student.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_student.FCID
     * @return student_capability_evaluation..t_hire_student.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_student.FCID
     * @param fcid the value for student_capability_evaluation..t_hire_student.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_student.FUID
     * @return student_capability_evaluation..t_hire_student.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_student.FUID
     * @param fuid the value for student_capability_evaluation..t_hire_student.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_student.FCDATE
     * @return student_capability_evaluation..t_hire_student.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_student.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_hire_student.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_student.FUDATE
     * @return student_capability_evaluation..t_hire_student.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_student.FUDATE
     * @param fudate the value for student_capability_evaluation..t_hire_student.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态，0-未选择，1-已选择 字段:student_capability_evaluation..t_hire_student.FState
     * @return student_capability_evaluation..t_hire_student.FState, 状态，0-未选择，1-已选择
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态，0-未选择，1-已选择 字段:student_capability_evaluation..t_hire_student.FState
     * @param fstate the value for student_capability_evaluation..t_hire_student.FState, 状态，0-未选择，1-已选择
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 招聘表ID，T_Hire 字段:student_capability_evaluation..t_hire_student.FHireID
     * @return student_capability_evaluation..t_hire_student.FHireID, 招聘表ID，T_Hire
     * @mbg.generated
     */
    public Long getFhireid() {
        return fhireid;
    }

    /**
     * 设置 招聘表ID，T_Hire 字段:student_capability_evaluation..t_hire_student.FHireID
     * @param fhireid the value for student_capability_evaluation..t_hire_student.FHireID, 招聘表ID，T_Hire
     * @mbg.generated
     */
    public void setFhireid(Long fhireid) {
        this.fhireid = fhireid;
    }

    /**
     * 获取 学生ID，T_Student 字段:student_capability_evaluation..t_hire_student.FStudentID
     * @return student_capability_evaluation..t_hire_student.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID，T_Student 字段:student_capability_evaluation..t_hire_student.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_hire_student.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 方式，1-选择，2-申请 字段:student_capability_evaluation..t_hire_student.FMode
     * @return student_capability_evaluation..t_hire_student.FMode, 方式，1-选择，2-申请
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式，1-选择，2-申请 字段:student_capability_evaluation..t_hire_student.FMode
     * @param fmode the value for student_capability_evaluation..t_hire_student.FMode, 方式，1-选择，2-申请
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取 综合评分（根据已有能力和权重计算） 字段:student_capability_evaluation..t_hire_student.FScore
     * @return student_capability_evaluation..t_hire_student.FScore, 综合评分（根据已有能力和权重计算）
     * @mbg.generated
     */
    public Integer getFscore() {
        return fscore;
    }

    /**
     * 设置 综合评分（根据已有能力和权重计算） 字段:student_capability_evaluation..t_hire_student.FScore
     * @param fscore the value for student_capability_evaluation..t_hire_student.FScore, 综合评分（根据已有能力和权重计算）
     * @mbg.generated
     */
    public void setFscore(Integer fscore) {
        this.fscore = fscore;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_hire_student
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
        sb.append(", fhireid=").append(fhireid);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fmode=").append(fmode);
        sb.append(", fscore=").append(fscore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_hire_student
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
        THireStudent other = (THireStudent) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFhireid() == null ? other.getFhireid() == null : this.getFhireid().equals(other.getFhireid()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_hire_student
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
        result = prime * result + ((getFhireid() == null) ? 0 : getFhireid().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFmode() == null) ? 0 : getFmode().hashCode());
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        return result;
    }
}