package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_class_student
*/
@Table(name = "t_class_student")
public class TClassStudent implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_class_student.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FClassID
     * @mbg.generated
     */
    private Long fclassid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class_student.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_class_student
     * @mbg.generated
     */
    public TClassStudent(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fclassid, Long fstudentid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fclassid = fclassid;
        this.fstudentid = fstudentid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_class_student
     * @mbg.generated
     */
    public TClassStudent() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FKeyID
     * @return student_capability_evaluation..t_class_student.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_class_student.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FCID
     * @return student_capability_evaluation..t_class_student.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FCID
     * @param fcid the value for student_capability_evaluation..t_class_student.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FUID
     * @return student_capability_evaluation..t_class_student.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FUID
     * @param fuid the value for student_capability_evaluation..t_class_student.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FCDATE
     * @return student_capability_evaluation..t_class_student.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_class_student.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FUDATE
     * @return student_capability_evaluation..t_class_student.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FUDATE
     * @param fudate the value for student_capability_evaluation..t_class_student.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_class_student.FState
     * @return student_capability_evaluation..t_class_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_class_student.FState
     * @param fstate the value for student_capability_evaluation..t_class_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FClassID
     * @return student_capability_evaluation..t_class_student.FClassID, 
     * @mbg.generated
     */
    public Long getFclassid() {
        return fclassid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FClassID
     * @param fclassid the value for student_capability_evaluation..t_class_student.FClassID, 
     * @mbg.generated
     */
    public void setFclassid(Long fclassid) {
        this.fclassid = fclassid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class_student.FStudentID
     * @return student_capability_evaluation..t_class_student.FStudentID, 
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class_student.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_class_student.FStudentID, 
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_class_student
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
        sb.append(", fclassid=").append(fclassid);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_class_student
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
        TClassStudent other = (TClassStudent) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFclassid() == null ? other.getFclassid() == null : this.getFclassid().equals(other.getFclassid()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_class_student
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
        result = prime * result + ((getFclassid() == null) ? 0 : getFclassid().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        return result;
    }
}