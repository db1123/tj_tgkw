package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_target
*/
@Table(name = "t_course_target")
public class TCourseTarget implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_course_target.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_target.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_target.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_target.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_target.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_course_target.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : teaching_diversity..t_course_target.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 课程目标内容
     * 表字段 : teaching_diversity..t_course_target.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 课程目标数值
     * 表字段 : teaching_diversity..t_course_target.FNum
     * @mbg.generated
     */
    private Integer fnum;

    /**
     * 课程目标类型
     * 表字段 : teaching_diversity..t_course_target.FType
     * @mbg.generated
     */
    private Long ftype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_course_target
     * @mbg.generated
     */
    public TCourseTarget(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, String fname, Integer fnum, Long ftype) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fname = fname;
        this.fnum = fnum;
        this.ftype = ftype;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_course_target
     * @mbg.generated
     */
    public TCourseTarget() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_course_target.FKeyID
     * @return teaching_diversity..t_course_target.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_target.FKeyID
     * @param fkeyid the value for teaching_diversity..t_course_target.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_target.FCID
     * @return teaching_diversity..t_course_target.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_target.FCID
     * @param fcid the value for teaching_diversity..t_course_target.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_target.FUID
     * @return teaching_diversity..t_course_target.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_target.FUID
     * @param fuid the value for teaching_diversity..t_course_target.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_target.FCDATE
     * @return teaching_diversity..t_course_target.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_target.FCDATE
     * @param fcdate the value for teaching_diversity..t_course_target.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_target.FUDATE
     * @return teaching_diversity..t_course_target.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_target.FUDATE
     * @param fudate the value for teaching_diversity..t_course_target.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_course_target.FState
     * @return teaching_diversity..t_course_target.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_course_target.FState
     * @param fstate the value for teaching_diversity..t_course_target.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:teaching_diversity..t_course_target.FCourseID
     * @return teaching_diversity..t_course_target.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:teaching_diversity..t_course_target.FCourseID
     * @param fcourseid the value for teaching_diversity..t_course_target.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 课程目标内容 字段:teaching_diversity..t_course_target.FName
     * @return teaching_diversity..t_course_target.FName, 课程目标内容
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 课程目标内容 字段:teaching_diversity..t_course_target.FName
     * @param fname the value for teaching_diversity..t_course_target.FName, 课程目标内容
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 课程目标数值 字段:teaching_diversity..t_course_target.FNum
     * @return teaching_diversity..t_course_target.FNum, 课程目标数值
     * @mbg.generated
     */
    public Integer getFnum() {
        return fnum;
    }

    /**
     * 设置 课程目标数值 字段:teaching_diversity..t_course_target.FNum
     * @param fnum the value for teaching_diversity..t_course_target.FNum, 课程目标数值
     * @mbg.generated
     */
    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    /**
     * 获取 课程目标类型 字段:teaching_diversity..t_course_target.FType
     * @return teaching_diversity..t_course_target.FType, 课程目标类型
     * @mbg.generated
     */
    public Long getFtype() {
        return ftype;
    }

    /**
     * 设置 课程目标类型 字段:teaching_diversity..t_course_target.FType
     * @param ftype the value for teaching_diversity..t_course_target.FType, 课程目标类型
     * @mbg.generated
     */
    public void setFtype(Long ftype) {
        this.ftype = ftype;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_course_target
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
        sb.append(", fname=").append(fname);
        sb.append(", fnum=").append(fnum);
        sb.append(", ftype=").append(ftype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_course_target
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
        TCourseTarget other = (TCourseTarget) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnum() == null ? other.getFnum() == null : this.getFnum().equals(other.getFnum()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_course_target
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnum() == null) ? 0 : getFnum().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        return result;
    }
}