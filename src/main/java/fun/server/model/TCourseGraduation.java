package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_graduation
*/
@Table(name = "t_course_graduation")
public class TCourseGraduation implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_course_graduation.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_graduation.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_graduation.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_graduation.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_graduation.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_course_graduation.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : teaching_diversity..t_course_graduation.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 毕业要求指标点
     * 表字段 : teaching_diversity..t_course_graduation.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 课程目标ID， 以逗号间隔
     * 表字段 : teaching_diversity..t_course_graduation.FCourseCID
     * @mbg.generated
     */
    private String fcoursecid;

    /**
     * 培养方案毕业要求
     * 表字段 : teaching_diversity..t_course_graduation.FTBYYQID
     * @mbg.generated
     */
    private Long ftbyyqid;

    /**
     * 培养方案ID
     * 表字段 : teaching_diversity..t_course_graduation.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_course_graduation
     * @mbg.generated
     */
    public TCourseGraduation(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, String fname, String fcoursecid, Long ftbyyqid, Long ftpid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fname = fname;
        this.fcoursecid = fcoursecid;
        this.ftbyyqid = ftbyyqid;
        this.ftpid = ftpid;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_course_graduation
     * @mbg.generated
     */
    public TCourseGraduation() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_course_graduation.FKeyID
     * @return teaching_diversity..t_course_graduation.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_graduation.FKeyID
     * @param fkeyid the value for teaching_diversity..t_course_graduation.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_graduation.FCID
     * @return teaching_diversity..t_course_graduation.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_graduation.FCID
     * @param fcid the value for teaching_diversity..t_course_graduation.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_graduation.FUID
     * @return teaching_diversity..t_course_graduation.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_graduation.FUID
     * @param fuid the value for teaching_diversity..t_course_graduation.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_graduation.FCDATE
     * @return teaching_diversity..t_course_graduation.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_graduation.FCDATE
     * @param fcdate the value for teaching_diversity..t_course_graduation.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_graduation.FUDATE
     * @return teaching_diversity..t_course_graduation.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_graduation.FUDATE
     * @param fudate the value for teaching_diversity..t_course_graduation.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_course_graduation.FState
     * @return teaching_diversity..t_course_graduation.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_course_graduation.FState
     * @param fstate the value for teaching_diversity..t_course_graduation.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:teaching_diversity..t_course_graduation.FCourseID
     * @return teaching_diversity..t_course_graduation.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:teaching_diversity..t_course_graduation.FCourseID
     * @param fcourseid the value for teaching_diversity..t_course_graduation.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 毕业要求指标点 字段:teaching_diversity..t_course_graduation.FName
     * @return teaching_diversity..t_course_graduation.FName, 毕业要求指标点
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 毕业要求指标点 字段:teaching_diversity..t_course_graduation.FName
     * @param fname the value for teaching_diversity..t_course_graduation.FName, 毕业要求指标点
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 课程目标ID， 以逗号间隔 字段:teaching_diversity..t_course_graduation.FCourseCID
     * @return teaching_diversity..t_course_graduation.FCourseCID, 课程目标ID， 以逗号间隔
     * @mbg.generated
     */
    public String getFcoursecid() {
        return fcoursecid;
    }

    /**
     * 设置 课程目标ID， 以逗号间隔 字段:teaching_diversity..t_course_graduation.FCourseCID
     * @param fcoursecid the value for teaching_diversity..t_course_graduation.FCourseCID, 课程目标ID， 以逗号间隔
     * @mbg.generated
     */
    public void setFcoursecid(String fcoursecid) {
        this.fcoursecid = fcoursecid == null ? null : fcoursecid.trim();
    }

    /**
     * 获取 培养方案毕业要求 字段:teaching_diversity..t_course_graduation.FTBYYQID
     * @return teaching_diversity..t_course_graduation.FTBYYQID, 培养方案毕业要求
     * @mbg.generated
     */
    public Long getFtbyyqid() {
        return ftbyyqid;
    }

    /**
     * 设置 培养方案毕业要求 字段:teaching_diversity..t_course_graduation.FTBYYQID
     * @param ftbyyqid the value for teaching_diversity..t_course_graduation.FTBYYQID, 培养方案毕业要求
     * @mbg.generated
     */
    public void setFtbyyqid(Long ftbyyqid) {
        this.ftbyyqid = ftbyyqid;
    }

    /**
     * 获取 培养方案ID 字段:teaching_diversity..t_course_graduation.FTPID
     * @return teaching_diversity..t_course_graduation.FTPID, 培养方案ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 培养方案ID 字段:teaching_diversity..t_course_graduation.FTPID
     * @param ftpid the value for teaching_diversity..t_course_graduation.FTPID, 培养方案ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_course_graduation
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
        sb.append(", fcoursecid=").append(fcoursecid);
        sb.append(", ftbyyqid=").append(ftbyyqid);
        sb.append(", ftpid=").append(ftpid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_course_graduation
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
        TCourseGraduation other = (TCourseGraduation) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFcoursecid() == null ? other.getFcoursecid() == null : this.getFcoursecid().equals(other.getFcoursecid()))
            && (this.getFtbyyqid() == null ? other.getFtbyyqid() == null : this.getFtbyyqid().equals(other.getFtbyyqid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_course_graduation
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
        result = prime * result + ((getFcoursecid() == null) ? 0 : getFcoursecid().hashCode());
        result = prime * result + ((getFtbyyqid() == null) ? 0 : getFtbyyqid().hashCode());
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        return result;
    }
}