package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_student_gradehistory
*/
@Table(name = "t_student_gradehistory")
public class TStudentGradehistory implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 学生ID，T_Student
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 年级
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FGradeLevel
     * @mbg.generated
     */
    private Integer fgradelevel;

    /**
     * 年级开始日期
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FStartDate
     * @mbg.generated
     */
    private Date fstartdate;

    /**
     * 年级结束日期
     * 表字段 : student_capability_evaluation..t_student_gradehistory.FEndDate
     * @mbg.generated
     */
    private Date fenddate;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student_gradehistory
     * @mbg.generated
     */
    public TStudentGradehistory(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fstudentid, Integer fgradelevel, Date fstartdate, Date fenddate) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fstudentid = fstudentid;
        this.fgradelevel = fgradelevel;
        this.fstartdate = fstartdate;
        this.fenddate = fenddate;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student_gradehistory
     * @mbg.generated
     */
    public TStudentGradehistory() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_gradehistory.FKeyID
     * @return student_capability_evaluation..t_student_gradehistory.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_gradehistory.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_student_gradehistory.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_gradehistory.FCID
     * @return student_capability_evaluation..t_student_gradehistory.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_gradehistory.FCID
     * @param fcid the value for student_capability_evaluation..t_student_gradehistory.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_gradehistory.FUID
     * @return student_capability_evaluation..t_student_gradehistory.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_gradehistory.FUID
     * @param fuid the value for student_capability_evaluation..t_student_gradehistory.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_gradehistory.FCDATE
     * @return student_capability_evaluation..t_student_gradehistory.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_gradehistory.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_student_gradehistory.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_gradehistory.FUDATE
     * @return student_capability_evaluation..t_student_gradehistory.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_gradehistory.FUDATE
     * @param fudate the value for student_capability_evaluation..t_student_gradehistory.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_student_gradehistory.FState
     * @return student_capability_evaluation..t_student_gradehistory.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_student_gradehistory.FState
     * @param fstate the value for student_capability_evaluation..t_student_gradehistory.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 学生ID，T_Student 字段:student_capability_evaluation..t_student_gradehistory.FStudentID
     * @return student_capability_evaluation..t_student_gradehistory.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID，T_Student 字段:student_capability_evaluation..t_student_gradehistory.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_student_gradehistory.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 年级 字段:student_capability_evaluation..t_student_gradehistory.FGradeLevel
     * @return student_capability_evaluation..t_student_gradehistory.FGradeLevel, 年级
     * @mbg.generated
     */
    public Integer getFgradelevel() {
        return fgradelevel;
    }

    /**
     * 设置 年级 字段:student_capability_evaluation..t_student_gradehistory.FGradeLevel
     * @param fgradelevel the value for student_capability_evaluation..t_student_gradehistory.FGradeLevel, 年级
     * @mbg.generated
     */
    public void setFgradelevel(Integer fgradelevel) {
        this.fgradelevel = fgradelevel;
    }

    /**
     * 获取 年级开始日期 字段:student_capability_evaluation..t_student_gradehistory.FStartDate
     * @return student_capability_evaluation..t_student_gradehistory.FStartDate, 年级开始日期
     * @mbg.generated
     */
    public Date getFstartdate() {
        return fstartdate;
    }

    /**
     * 设置 年级开始日期 字段:student_capability_evaluation..t_student_gradehistory.FStartDate
     * @param fstartdate the value for student_capability_evaluation..t_student_gradehistory.FStartDate, 年级开始日期
     * @mbg.generated
     */
    public void setFstartdate(Date fstartdate) {
        this.fstartdate = fstartdate;
    }

    /**
     * 获取 年级结束日期 字段:student_capability_evaluation..t_student_gradehistory.FEndDate
     * @return student_capability_evaluation..t_student_gradehistory.FEndDate, 年级结束日期
     * @mbg.generated
     */
    public Date getFenddate() {
        return fenddate;
    }

    /**
     * 设置 年级结束日期 字段:student_capability_evaluation..t_student_gradehistory.FEndDate
     * @param fenddate the value for student_capability_evaluation..t_student_gradehistory.FEndDate, 年级结束日期
     * @mbg.generated
     */
    public void setFenddate(Date fenddate) {
        this.fenddate = fenddate;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_student_gradehistory
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
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fgradelevel=").append(fgradelevel);
        sb.append(", fstartdate=").append(fstartdate);
        sb.append(", fenddate=").append(fenddate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_student_gradehistory
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
        TStudentGradehistory other = (TStudentGradehistory) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFgradelevel() == null ? other.getFgradelevel() == null : this.getFgradelevel().equals(other.getFgradelevel()))
            && (this.getFstartdate() == null ? other.getFstartdate() == null : this.getFstartdate().equals(other.getFstartdate()))
            && (this.getFenddate() == null ? other.getFenddate() == null : this.getFenddate().equals(other.getFenddate()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_student_gradehistory
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
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFgradelevel() == null) ? 0 : getFgradelevel().hashCode());
        result = prime * result + ((getFstartdate() == null) ? 0 : getFstartdate().hashCode());
        result = prime * result + ((getFenddate() == null) ? 0 : getFenddate().hashCode());
        return result;
    }
}