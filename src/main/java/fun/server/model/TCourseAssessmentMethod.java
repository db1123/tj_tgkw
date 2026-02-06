package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_assessment_method
*/
@Table(name = "t_course_assessment_method")
public class TCourseAssessmentMethod implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 考核方式名称（如平时作业、期中考试等）
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FMethodName
     * @mbg.generated
     */
    private String fmethodname;

    /**
     * 权重（如20.00表示20%）
     * 表字段 : student_capability_evaluation..t_course_assessment_method.FWeight
     * @mbg.generated
     */
    private Float fweight;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_assessment_method
     * @mbg.generated
     */
    public TCourseAssessmentMethod(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, String fmethodname, Float fweight) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fmethodname = fmethodname;
        this.fweight = fweight;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_assessment_method
     * @mbg.generated
     */
    public TCourseAssessmentMethod() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_assessment_method.FKeyID
     * @return student_capability_evaluation..t_course_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_assessment_method.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_assessment_method.FCID
     * @return student_capability_evaluation..t_course_assessment_method.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_assessment_method.FCID
     * @param fcid the value for student_capability_evaluation..t_course_assessment_method.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_assessment_method.FUID
     * @return student_capability_evaluation..t_course_assessment_method.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_assessment_method.FUID
     * @param fuid the value for student_capability_evaluation..t_course_assessment_method.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_assessment_method.FCDATE
     * @return student_capability_evaluation..t_course_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_assessment_method.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_assessment_method.FUDATE
     * @return student_capability_evaluation..t_course_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_assessment_method.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_assessment_method.FState
     * @return student_capability_evaluation..t_course_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_assessment_method.FState
     * @param fstate the value for student_capability_evaluation..t_course_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_assessment_method.FCourseID
     * @return student_capability_evaluation..t_course_assessment_method.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_assessment_method.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_assessment_method.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 考核方式名称（如平时作业、期中考试等） 字段:student_capability_evaluation..t_course_assessment_method.FMethodName
     * @return student_capability_evaluation..t_course_assessment_method.FMethodName, 考核方式名称（如平时作业、期中考试等）
     * @mbg.generated
     */
    public String getFmethodname() {
        return fmethodname;
    }

    /**
     * 设置 考核方式名称（如平时作业、期中考试等） 字段:student_capability_evaluation..t_course_assessment_method.FMethodName
     * @param fmethodname the value for student_capability_evaluation..t_course_assessment_method.FMethodName, 考核方式名称（如平时作业、期中考试等）
     * @mbg.generated
     */
    public void setFmethodname(String fmethodname) {
        this.fmethodname = fmethodname == null ? null : fmethodname.trim();
    }

    /**
     * 获取 权重（如20.00表示20%） 字段:student_capability_evaluation..t_course_assessment_method.FWeight
     * @return student_capability_evaluation..t_course_assessment_method.FWeight, 权重（如20.00表示20%）
     * @mbg.generated
     */
    public Float getFweight() {
        return fweight;
    }

    /**
     * 设置 权重（如20.00表示20%） 字段:student_capability_evaluation..t_course_assessment_method.FWeight
     * @param fweight the value for student_capability_evaluation..t_course_assessment_method.FWeight, 权重（如20.00表示20%）
     * @mbg.generated
     */
    public void setFweight(Float fweight) {
        this.fweight = fweight;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_assessment_method
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
        sb.append(", fmethodname=").append(fmethodname);
        sb.append(", fweight=").append(fweight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_assessment_method
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
        TCourseAssessmentMethod other = (TCourseAssessmentMethod) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFmethodname() == null ? other.getFmethodname() == null : this.getFmethodname().equals(other.getFmethodname()))
            && (this.getFweight() == null ? other.getFweight() == null : this.getFweight().equals(other.getFweight()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_assessment_method
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
        result = prime * result + ((getFmethodname() == null) ? 0 : getFmethodname().hashCode());
        result = prime * result + ((getFweight() == null) ? 0 : getFweight().hashCode());
        return result;
    }
}