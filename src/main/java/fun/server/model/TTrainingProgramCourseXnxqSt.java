package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_course_xnxq_st
*/
@Table(name = "t_training_program_course_xnxq_st")
public class TTrainingProgramCourseXnxqSt implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 课程ID
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 学年学期表ID  t_course_semester
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FXNXQID
     * @mbg.generated
     */
    private Long fxnxqid;

    /**
     * 课程安排新表t_training_program_course_st FKeyID
     * 表字段 : teaching_diversity..t_training_program_course_xnxq_st.FTPCSTID
     * @mbg.generated
     */
    private Long ftpcstid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_course_xnxq_st
     * @mbg.generated
     */
    public TTrainingProgramCourseXnxqSt(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, Long fcourseid, Long fxnxqid, Long ftpcstid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fcourseid = fcourseid;
        this.fxnxqid = fxnxqid;
        this.ftpcstid = ftpcstid;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_course_xnxq_st
     * @mbg.generated
     */
    public TTrainingProgramCourseXnxqSt() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_xnxq_st.FKeyID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_xnxq_st.FKeyID
     * @param fkeyid the value for teaching_diversity..t_training_program_course_xnxq_st.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_xnxq_st.FCID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_xnxq_st.FCID
     * @param fcid the value for teaching_diversity..t_training_program_course_xnxq_st.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_xnxq_st.FUID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_xnxq_st.FUID
     * @param fuid the value for teaching_diversity..t_training_program_course_xnxq_st.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_xnxq_st.FCDATE
     * @return teaching_diversity..t_training_program_course_xnxq_st.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_xnxq_st.FCDATE
     * @param fcdate the value for teaching_diversity..t_training_program_course_xnxq_st.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_xnxq_st.FUDATE
     * @return teaching_diversity..t_training_program_course_xnxq_st.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_xnxq_st.FUDATE
     * @param fudate the value for teaching_diversity..t_training_program_course_xnxq_st.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_training_program_course_xnxq_st.FState
     * @return teaching_diversity..t_training_program_course_xnxq_st.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_training_program_course_xnxq_st.FState
     * @param fstate the value for teaching_diversity..t_training_program_course_xnxq_st.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTMID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTMID
     * @param ftmid the value for teaching_diversity..t_training_program_course_xnxq_st.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTPID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTPID
     * @param ftpid the value for teaching_diversity..t_training_program_course_xnxq_st.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 课程ID 字段:teaching_diversity..t_training_program_course_xnxq_st.FCourseID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:teaching_diversity..t_training_program_course_xnxq_st.FCourseID
     * @param fcourseid the value for teaching_diversity..t_training_program_course_xnxq_st.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 学年学期表ID  t_course_semester 字段:teaching_diversity..t_training_program_course_xnxq_st.FXNXQID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FXNXQID, 学年学期表ID  t_course_semester
     * @mbg.generated
     */
    public Long getFxnxqid() {
        return fxnxqid;
    }

    /**
     * 设置 学年学期表ID  t_course_semester 字段:teaching_diversity..t_training_program_course_xnxq_st.FXNXQID
     * @param fxnxqid the value for teaching_diversity..t_training_program_course_xnxq_st.FXNXQID, 学年学期表ID  t_course_semester
     * @mbg.generated
     */
    public void setFxnxqid(Long fxnxqid) {
        this.fxnxqid = fxnxqid;
    }

    /**
     * 获取 课程安排新表t_training_program_course_st FKeyID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTPCSTID
     * @return teaching_diversity..t_training_program_course_xnxq_st.FTPCSTID, 课程安排新表t_training_program_course_st FKeyID
     * @mbg.generated
     */
    public Long getFtpcstid() {
        return ftpcstid;
    }

    /**
     * 设置 课程安排新表t_training_program_course_st FKeyID 字段:teaching_diversity..t_training_program_course_xnxq_st.FTPCSTID
     * @param ftpcstid the value for teaching_diversity..t_training_program_course_xnxq_st.FTPCSTID, 课程安排新表t_training_program_course_st FKeyID
     * @mbg.generated
     */
    public void setFtpcstid(Long ftpcstid) {
        this.ftpcstid = ftpcstid;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_training_program_course_xnxq_st
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
        sb.append(", ftmid=").append(ftmid);
        sb.append(", ftpid=").append(ftpid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fxnxqid=").append(fxnxqid);
        sb.append(", ftpcstid=").append(ftpcstid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_training_program_course_xnxq_st
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
        TTrainingProgramCourseXnxqSt other = (TTrainingProgramCourseXnxqSt) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFxnxqid() == null ? other.getFxnxqid() == null : this.getFxnxqid().equals(other.getFxnxqid()))
            && (this.getFtpcstid() == null ? other.getFtpcstid() == null : this.getFtpcstid().equals(other.getFtpcstid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_training_program_course_xnxq_st
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
        result = prime * result + ((getFtmid() == null) ? 0 : getFtmid().hashCode());
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFxnxqid() == null) ? 0 : getFxnxqid().hashCode());
        result = prime * result + ((getFtpcstid() == null) ? 0 : getFtpcstid().hashCode());
        return result;
    }
}