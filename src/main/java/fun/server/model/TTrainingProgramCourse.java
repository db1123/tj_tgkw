package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_course
*/
@Table(name = "t_training_program_course")
public class TTrainingProgramCourse implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_course.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_course.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_course.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_course.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_course.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_training_program_course.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program_course.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : student_capability_evaluation..t_training_program_course.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_training_program_course.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 顺序
     * 表字段 : student_capability_evaluation..t_training_program_course.FOrder
     * @mbg.generated
     */
    private Integer forder;

    /**
     * 学年
     * 表字段 : student_capability_evaluation..t_training_program_course.FXN
     * @mbg.generated
     */
    private String fxn;

    /**
     * 学期
     * 表字段 : student_capability_evaluation..t_training_program_course.FXQ
     * @mbg.generated
     */
    private Integer fxq;

    /**
     * 学分
     * 表字段 : student_capability_evaluation..t_training_program_course.FXF
     * @mbg.generated
     */
    private Float fxf;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_course
     * @mbg.generated
     */
    public TTrainingProgramCourse(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, Long fcourseid, Integer forder, String fxn, Integer fxq, Float fxf) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fcourseid = fcourseid;
        this.forder = forder;
        this.fxn = fxn;
        this.fxq = fxq;
        this.fxf = fxf;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_course
     * @mbg.generated
     */
    public TTrainingProgramCourse() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_course.FKeyID
     * @return student_capability_evaluation..t_training_program_course.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_course.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program_course.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_course.FCID
     * @return student_capability_evaluation..t_training_program_course.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_course.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program_course.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_course.FUID
     * @return student_capability_evaluation..t_training_program_course.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_course.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program_course.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_course.FCDATE
     * @return student_capability_evaluation..t_training_program_course.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_course.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program_course.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_course.FUDATE
     * @return student_capability_evaluation..t_training_program_course.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_course.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program_course.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_course.FState
     * @return student_capability_evaluation..t_training_program_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_course.FState
     * @param fstate the value for student_capability_evaluation..t_training_program_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_course.FTMID
     * @return student_capability_evaluation..t_training_program_course.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_course.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program_course.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_course.FTPID
     * @return student_capability_evaluation..t_training_program_course.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_course.FTPID
     * @param ftpid the value for student_capability_evaluation..t_training_program_course.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_training_program_course.FCourseID
     * @return student_capability_evaluation..t_training_program_course.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_training_program_course.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_training_program_course.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 顺序 字段:student_capability_evaluation..t_training_program_course.FOrder
     * @return student_capability_evaluation..t_training_program_course.FOrder, 顺序
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置 顺序 字段:student_capability_evaluation..t_training_program_course.FOrder
     * @param forder the value for student_capability_evaluation..t_training_program_course.FOrder, 顺序
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 获取 学年 字段:student_capability_evaluation..t_training_program_course.FXN
     * @return student_capability_evaluation..t_training_program_course.FXN, 学年
     * @mbg.generated
     */
    public String getFxn() {
        return fxn;
    }

    /**
     * 设置 学年 字段:student_capability_evaluation..t_training_program_course.FXN
     * @param fxn the value for student_capability_evaluation..t_training_program_course.FXN, 学年
     * @mbg.generated
     */
    public void setFxn(String fxn) {
        this.fxn = fxn == null ? null : fxn.trim();
    }

    /**
     * 获取 学期 字段:student_capability_evaluation..t_training_program_course.FXQ
     * @return student_capability_evaluation..t_training_program_course.FXQ, 学期
     * @mbg.generated
     */
    public Integer getFxq() {
        return fxq;
    }

    /**
     * 设置 学期 字段:student_capability_evaluation..t_training_program_course.FXQ
     * @param fxq the value for student_capability_evaluation..t_training_program_course.FXQ, 学期
     * @mbg.generated
     */
    public void setFxq(Integer fxq) {
        this.fxq = fxq;
    }

    /**
     * 获取 学分 字段:student_capability_evaluation..t_training_program_course.FXF
     * @return student_capability_evaluation..t_training_program_course.FXF, 学分
     * @mbg.generated
     */
    public Float getFxf() {
        return fxf;
    }

    /**
     * 设置 学分 字段:student_capability_evaluation..t_training_program_course.FXF
     * @param fxf the value for student_capability_evaluation..t_training_program_course.FXF, 学分
     * @mbg.generated
     */
    public void setFxf(Float fxf) {
        this.fxf = fxf;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program_course
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
        sb.append(", forder=").append(forder);
        sb.append(", fxn=").append(fxn);
        sb.append(", fxq=").append(fxq);
        sb.append(", fxf=").append(fxf);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program_course
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
        TTrainingProgramCourse other = (TTrainingProgramCourse) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()))
            && (this.getFxn() == null ? other.getFxn() == null : this.getFxn().equals(other.getFxn()))
            && (this.getFxq() == null ? other.getFxq() == null : this.getFxq().equals(other.getFxq()))
            && (this.getFxf() == null ? other.getFxf() == null : this.getFxf().equals(other.getFxf()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program_course
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
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        result = prime * result + ((getFxn() == null) ? 0 : getFxn().hashCode());
        result = prime * result + ((getFxq() == null) ? 0 : getFxq().hashCode());
        result = prime * result + ((getFxf() == null) ? 0 : getFxf().hashCode());
        return result;
    }
}