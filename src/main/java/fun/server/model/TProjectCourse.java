package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_project_course
 * 表注释：项目课程关联表
*/
@Table(name = "t_project_course")
public class TProjectCourse implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_project_course.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project_course.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project_course.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project_course.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_project_course.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_project_course.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 项目ID
     * 表字段 : teaching_diversity..t_project_course.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 课程ID
     * 表字段 : teaching_diversity..t_project_course.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * T_Major表FKeyID
     * 表字段 : teaching_diversity..t_project_course.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : teaching_diversity..t_project_course.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * T_Training_Program_Course表ID
     * 表字段 : teaching_diversity..t_project_course.FTPCID
     * @mbg.generated
     */
    private Long ftpcid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_project_course
     * @mbg.generated
     */
    public TProjectCourse(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fprojectid, Long fcourseid, Long ftmid, Long ftpid, Long ftpcid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fprojectid = fprojectid;
        this.fcourseid = fcourseid;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.ftpcid = ftpcid;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_project_course
     * @mbg.generated
     */
    public TProjectCourse() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_project_course.FKeyID
     * @return teaching_diversity..t_project_course.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project_course.FKeyID
     * @param fkeyid the value for teaching_diversity..t_project_course.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project_course.FCID
     * @return teaching_diversity..t_project_course.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project_course.FCID
     * @param fcid the value for teaching_diversity..t_project_course.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project_course.FUID
     * @return teaching_diversity..t_project_course.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project_course.FUID
     * @param fuid the value for teaching_diversity..t_project_course.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project_course.FCDATE
     * @return teaching_diversity..t_project_course.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_project_course.FCDATE
     * @param fcdate the value for teaching_diversity..t_project_course.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_project_course.FUDATE
     * @return teaching_diversity..t_project_course.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_project_course.FUDATE
     * @param fudate the value for teaching_diversity..t_project_course.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_project_course.FState
     * @return teaching_diversity..t_project_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_project_course.FState
     * @param fstate the value for teaching_diversity..t_project_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 项目ID 字段:teaching_diversity..t_project_course.FProjectID
     * @return teaching_diversity..t_project_course.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:teaching_diversity..t_project_course.FProjectID
     * @param fprojectid the value for teaching_diversity..t_project_course.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 课程ID 字段:teaching_diversity..t_project_course.FCourseID
     * @return teaching_diversity..t_project_course.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:teaching_diversity..t_project_course.FCourseID
     * @param fcourseid the value for teaching_diversity..t_project_course.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 T_Major表FKeyID 字段:teaching_diversity..t_project_course.FTMID
     * @return teaching_diversity..t_project_course.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:teaching_diversity..t_project_course.FTMID
     * @param ftmid the value for teaching_diversity..t_project_course.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:teaching_diversity..t_project_course.FTPID
     * @return teaching_diversity..t_project_course.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:teaching_diversity..t_project_course.FTPID
     * @param ftpid the value for teaching_diversity..t_project_course.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 T_Training_Program_Course表ID 字段:teaching_diversity..t_project_course.FTPCID
     * @return teaching_diversity..t_project_course.FTPCID, T_Training_Program_Course表ID
     * @mbg.generated
     */
    public Long getFtpcid() {
        return ftpcid;
    }

    /**
     * 设置 T_Training_Program_Course表ID 字段:teaching_diversity..t_project_course.FTPCID
     * @param ftpcid the value for teaching_diversity..t_project_course.FTPCID, T_Training_Program_Course表ID
     * @mbg.generated
     */
    public void setFtpcid(Long ftpcid) {
        this.ftpcid = ftpcid;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_project_course
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
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", ftmid=").append(ftmid);
        sb.append(", ftpid=").append(ftpid);
        sb.append(", ftpcid=").append(ftpcid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_project_course
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
        TProjectCourse other = (TProjectCourse) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFtpcid() == null ? other.getFtpcid() == null : this.getFtpcid().equals(other.getFtpcid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_project_course
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
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFtmid() == null) ? 0 : getFtmid().hashCode());
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        result = prime * result + ((getFtpcid() == null) ? 0 : getFtpcid().hashCode());
        return result;
    }
}