package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_textbook
*/
@Table(name = "t_course_textbook")
public class TCourseTextbook implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_textbook.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_textbook.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_textbook.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_textbook.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_textbook.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_textbook.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_textbook.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 类型：1-教材 2-主要参考资料
     * 表字段 : student_capability_evaluation..t_course_textbook.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 内容
     * 表字段 : student_capability_evaluation..t_course_textbook.FName
     * @mbg.generated
     */
    private String fname;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_textbook
     * @mbg.generated
     */
    public TCourseTextbook(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Integer ftype, String fname) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.ftype = ftype;
        this.fname = fname;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_textbook
     * @mbg.generated
     */
    public TCourseTextbook() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_textbook.FKeyID
     * @return student_capability_evaluation..t_course_textbook.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_textbook.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_textbook.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_textbook.FCID
     * @return student_capability_evaluation..t_course_textbook.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_textbook.FCID
     * @param fcid the value for student_capability_evaluation..t_course_textbook.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_textbook.FUID
     * @return student_capability_evaluation..t_course_textbook.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_textbook.FUID
     * @param fuid the value for student_capability_evaluation..t_course_textbook.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_textbook.FCDATE
     * @return student_capability_evaluation..t_course_textbook.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_textbook.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_textbook.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_textbook.FUDATE
     * @return student_capability_evaluation..t_course_textbook.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_textbook.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_textbook.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_textbook.FState
     * @return student_capability_evaluation..t_course_textbook.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_textbook.FState
     * @param fstate the value for student_capability_evaluation..t_course_textbook.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_textbook.FCourseID
     * @return student_capability_evaluation..t_course_textbook.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_textbook.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_textbook.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 类型：1-教材 2-主要参考资料 字段:student_capability_evaluation..t_course_textbook.FType
     * @return student_capability_evaluation..t_course_textbook.FType, 类型：1-教材 2-主要参考资料
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 类型：1-教材 2-主要参考资料 字段:student_capability_evaluation..t_course_textbook.FType
     * @param ftype the value for student_capability_evaluation..t_course_textbook.FType, 类型：1-教材 2-主要参考资料
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 内容 字段:student_capability_evaluation..t_course_textbook.FName
     * @return student_capability_evaluation..t_course_textbook.FName, 内容
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 内容 字段:student_capability_evaluation..t_course_textbook.FName
     * @param fname the value for student_capability_evaluation..t_course_textbook.FName, 内容
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_textbook
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
        sb.append(", ftype=").append(ftype);
        sb.append(", fname=").append(fname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_textbook
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
        TCourseTextbook other = (TCourseTextbook) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_textbook
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
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        return result;
    }
}