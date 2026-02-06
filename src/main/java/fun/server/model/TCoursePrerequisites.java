package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_prerequisites
*/
@Table(name = "t_course_prerequisites")
public class TCoursePrerequisites implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 先修课程ID（不能选自身）
     * 表字段 : student_capability_evaluation..t_course_prerequisites.FPrereqID
     * @mbg.generated
     */
    private Long fprereqid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_prerequisites
     * @mbg.generated
     */
    public TCoursePrerequisites(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fprereqid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fprereqid = fprereqid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_prerequisites
     * @mbg.generated
     */
    public TCoursePrerequisites() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_prerequisites.FKeyID
     * @return student_capability_evaluation..t_course_prerequisites.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_prerequisites.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_prerequisites.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_prerequisites.FCID
     * @return student_capability_evaluation..t_course_prerequisites.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_prerequisites.FCID
     * @param fcid the value for student_capability_evaluation..t_course_prerequisites.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_prerequisites.FUID
     * @return student_capability_evaluation..t_course_prerequisites.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_prerequisites.FUID
     * @param fuid the value for student_capability_evaluation..t_course_prerequisites.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_prerequisites.FCDATE
     * @return student_capability_evaluation..t_course_prerequisites.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_prerequisites.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_prerequisites.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_prerequisites.FUDATE
     * @return student_capability_evaluation..t_course_prerequisites.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_prerequisites.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_prerequisites.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_prerequisites.FState
     * @return student_capability_evaluation..t_course_prerequisites.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_prerequisites.FState
     * @param fstate the value for student_capability_evaluation..t_course_prerequisites.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_course_prerequisites.FCourseID
     * @return student_capability_evaluation..t_course_prerequisites.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_course_prerequisites.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_prerequisites.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 先修课程ID（不能选自身） 字段:student_capability_evaluation..t_course_prerequisites.FPrereqID
     * @return student_capability_evaluation..t_course_prerequisites.FPrereqID, 先修课程ID（不能选自身）
     * @mbg.generated
     */
    public Long getFprereqid() {
        return fprereqid;
    }

    /**
     * 设置 先修课程ID（不能选自身） 字段:student_capability_evaluation..t_course_prerequisites.FPrereqID
     * @param fprereqid the value for student_capability_evaluation..t_course_prerequisites.FPrereqID, 先修课程ID（不能选自身）
     * @mbg.generated
     */
    public void setFprereqid(Long fprereqid) {
        this.fprereqid = fprereqid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_prerequisites
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
        sb.append(", fprereqid=").append(fprereqid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_prerequisites
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
        TCoursePrerequisites other = (TCoursePrerequisites) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFprereqid() == null ? other.getFprereqid() == null : this.getFprereqid().equals(other.getFprereqid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_prerequisites
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
        result = prime * result + ((getFprereqid() == null) ? 0 : getFprereqid().hashCode());
        return result;
    }
}