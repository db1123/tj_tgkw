package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 表名：t_teacher
*/
@Table(name = "t_teacher")
public class TTeacher implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_teacher.FKeyID
     * @mbg.generated
     */
	@Id
	@KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_teacher.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_teacher.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_teacher.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_teacher.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_teacher.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 教师姓名
     * 表字段 : teaching_diversity..t_teacher.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 1-男 2-女
     * 表字段 : teaching_diversity..t_teacher.FGender
     * @mbg.generated
     */
    private Integer fgender;

    /**
     * 职称
     * 表字段 : teaching_diversity..t_teacher.FTitle
     * @mbg.generated
     */
    private String ftitle;

    /**
     * 所属院系
     * 表字段 : teaching_diversity..t_teacher.FCollegeID
     * @mbg.generated
     */
    private Long fcollegeid;

    /**
     * 电子邮箱
     * 表字段 : teaching_diversity..t_teacher.FEmail
     * @mbg.generated
     */
    private String femail;

    /**
     * 联系电话，不能为空， 作为用户表用户名
     * 表字段 : teaching_diversity..t_teacher.FPhone
     * @mbg.generated
     */
    private String fphone;

    /**
     * 入职日期
     * 表字段 : teaching_diversity..t_teacher.FHireDate
     * @mbg.generated
     */
    private Date fhiredate;

    /**
     * 在职状态 1-在职 2-离职 3- 休假
     * 表字段 : teaching_diversity..t_teacher.FStatus
     * @mbg.generated
     */
    private Integer fstatus;

    /**
     * 备注
     * 表字段 : teaching_diversity..t_teacher.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 图谱系统ID
     * 表字段 : teaching_diversity..t_teacher.FTPID
     * @mbg.generated
     */
    private String ftpid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_teacher
     * @mbg.generated
     */
    public TTeacher(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, Integer fgender, String ftitle, Long fcollegeid, String femail, String fphone, Date fhiredate, Integer fstatus, String fnote, String ftpid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fgender = fgender;
        this.ftitle = ftitle;
        this.fcollegeid = fcollegeid;
        this.femail = femail;
        this.fphone = fphone;
        this.fhiredate = fhiredate;
        this.fstatus = fstatus;
        this.fnote = fnote;
        this.ftpid = ftpid;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_teacher
     * @mbg.generated
     */
    public TTeacher() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_teacher.FKeyID
     * @return teaching_diversity..t_teacher.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_teacher.FKeyID
     * @param fkeyid the value for teaching_diversity..t_teacher.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_teacher.FCID
     * @return teaching_diversity..t_teacher.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_teacher.FCID
     * @param fcid the value for teaching_diversity..t_teacher.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_teacher.FUID
     * @return teaching_diversity..t_teacher.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_teacher.FUID
     * @param fuid the value for teaching_diversity..t_teacher.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_teacher.FCDATE
     * @return teaching_diversity..t_teacher.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_teacher.FCDATE
     * @param fcdate the value for teaching_diversity..t_teacher.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_teacher.FUDATE
     * @return teaching_diversity..t_teacher.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_teacher.FUDATE
     * @param fudate the value for teaching_diversity..t_teacher.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_teacher.FState
     * @return teaching_diversity..t_teacher.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_teacher.FState
     * @param fstate the value for teaching_diversity..t_teacher.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 教师姓名 字段:teaching_diversity..t_teacher.FName
     * @return teaching_diversity..t_teacher.FName, 教师姓名
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 教师姓名 字段:teaching_diversity..t_teacher.FName
     * @param fname the value for teaching_diversity..t_teacher.FName, 教师姓名
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 1-男 2-女 字段:teaching_diversity..t_teacher.FGender
     * @return teaching_diversity..t_teacher.FGender, 1-男 2-女
     * @mbg.generated
     */
    public Integer getFgender() {
        return fgender;
    }

    /**
     * 设置 1-男 2-女 字段:teaching_diversity..t_teacher.FGender
     * @param fgender the value for teaching_diversity..t_teacher.FGender, 1-男 2-女
     * @mbg.generated
     */
    public void setFgender(Integer fgender) {
        this.fgender = fgender;
    }

    /**
     * 获取 职称 字段:teaching_diversity..t_teacher.FTitle
     * @return teaching_diversity..t_teacher.FTitle, 职称
     * @mbg.generated
     */
    public String getFtitle() {
        return ftitle;
    }

    /**
     * 设置 职称 字段:teaching_diversity..t_teacher.FTitle
     * @param ftitle the value for teaching_diversity..t_teacher.FTitle, 职称
     * @mbg.generated
     */
    public void setFtitle(String ftitle) {
        this.ftitle = ftitle == null ? null : ftitle.trim();
    }

    /**
     * 获取 所属院系 字段:teaching_diversity..t_teacher.FCollegeID
     * @return teaching_diversity..t_teacher.FCollegeID, 所属院系
     * @mbg.generated
     */
    public Long getFcollegeid() {
        return fcollegeid;
    }

    /**
     * 设置 所属院系 字段:teaching_diversity..t_teacher.FCollegeID
     * @param fcollegeid the value for teaching_diversity..t_teacher.FCollegeID, 所属院系
     * @mbg.generated
     */
    public void setFcollegeid(Long fcollegeid) {
        this.fcollegeid = fcollegeid;
    }

    /**
     * 获取 电子邮箱 字段:teaching_diversity..t_teacher.FEmail
     * @return teaching_diversity..t_teacher.FEmail, 电子邮箱
     * @mbg.generated
     */
    public String getFemail() {
        return femail;
    }

    /**
     * 设置 电子邮箱 字段:teaching_diversity..t_teacher.FEmail
     * @param femail the value for teaching_diversity..t_teacher.FEmail, 电子邮箱
     * @mbg.generated
     */
    public void setFemail(String femail) {
        this.femail = femail == null ? null : femail.trim();
    }

    /**
     * 获取 联系电话，不能为空， 作为用户表用户名 字段:teaching_diversity..t_teacher.FPhone
     * @return teaching_diversity..t_teacher.FPhone, 联系电话，不能为空， 作为用户表用户名
     * @mbg.generated
     */
    public String getFphone() {
        return fphone;
    }

    /**
     * 设置 联系电话，不能为空， 作为用户表用户名 字段:teaching_diversity..t_teacher.FPhone
     * @param fphone the value for teaching_diversity..t_teacher.FPhone, 联系电话，不能为空， 作为用户表用户名
     * @mbg.generated
     */
    public void setFphone(String fphone) {
        this.fphone = fphone == null ? null : fphone.trim();
    }

    /**
     * 获取 入职日期 字段:teaching_diversity..t_teacher.FHireDate
     * @return teaching_diversity..t_teacher.FHireDate, 入职日期
     * @mbg.generated
     */
    public Date getFhiredate() {
        return fhiredate;
    }

    /**
     * 设置 入职日期 字段:teaching_diversity..t_teacher.FHireDate
     * @param fhiredate the value for teaching_diversity..t_teacher.FHireDate, 入职日期
     * @mbg.generated
     */
    public void setFhiredate(Date fhiredate) {
        this.fhiredate = fhiredate;
    }

    /**
     * 获取 在职状态 1-在职 2-离职 3- 休假 字段:teaching_diversity..t_teacher.FStatus
     * @return teaching_diversity..t_teacher.FStatus, 在职状态 1-在职 2-离职 3- 休假
     * @mbg.generated
     */
    public Integer getFstatus() {
        return fstatus;
    }

    /**
     * 设置 在职状态 1-在职 2-离职 3- 休假 字段:teaching_diversity..t_teacher.FStatus
     * @param fstatus the value for teaching_diversity..t_teacher.FStatus, 在职状态 1-在职 2-离职 3- 休假
     * @mbg.generated
     */
    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    /**
     * 获取 备注 字段:teaching_diversity..t_teacher.FNote
     * @return teaching_diversity..t_teacher.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:teaching_diversity..t_teacher.FNote
     * @param fnote the value for teaching_diversity..t_teacher.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 图谱系统ID 字段:teaching_diversity..t_teacher.FTPID
     * @return teaching_diversity..t_teacher.FTPID, 图谱系统ID
     * @mbg.generated
     */
    public String getFtpid() {
        return ftpid;
    }

    /**
     * 设置 图谱系统ID 字段:teaching_diversity..t_teacher.FTPID
     * @param ftpid the value for teaching_diversity..t_teacher.FTPID, 图谱系统ID
     * @mbg.generated
     */
    public void setFtpid(String ftpid) {
        this.ftpid = ftpid == null ? null : ftpid.trim();
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_teacher
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
        sb.append(", fname=").append(fname);
        sb.append(", fgender=").append(fgender);
        sb.append(", ftitle=").append(ftitle);
        sb.append(", fcollegeid=").append(fcollegeid);
        sb.append(", femail=").append(femail);
        sb.append(", fphone=").append(fphone);
        sb.append(", fhiredate=").append(fhiredate);
        sb.append(", fstatus=").append(fstatus);
        sb.append(", fnote=").append(fnote);
        sb.append(", ftpid=").append(ftpid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_teacher
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
        TTeacher other = (TTeacher) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFgender() == null ? other.getFgender() == null : this.getFgender().equals(other.getFgender()))
            && (this.getFtitle() == null ? other.getFtitle() == null : this.getFtitle().equals(other.getFtitle()))
            && (this.getFcollegeid() == null ? other.getFcollegeid() == null : this.getFcollegeid().equals(other.getFcollegeid()))
            && (this.getFemail() == null ? other.getFemail() == null : this.getFemail().equals(other.getFemail()))
            && (this.getFphone() == null ? other.getFphone() == null : this.getFphone().equals(other.getFphone()))
            && (this.getFhiredate() == null ? other.getFhiredate() == null : this.getFhiredate().equals(other.getFhiredate()))
            && (this.getFstatus() == null ? other.getFstatus() == null : this.getFstatus().equals(other.getFstatus()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_teacher
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFgender() == null) ? 0 : getFgender().hashCode());
        result = prime * result + ((getFtitle() == null) ? 0 : getFtitle().hashCode());
        result = prime * result + ((getFcollegeid() == null) ? 0 : getFcollegeid().hashCode());
        result = prime * result + ((getFemail() == null) ? 0 : getFemail().hashCode());
        result = prime * result + ((getFphone() == null) ? 0 : getFphone().hashCode());
        result = prime * result + ((getFhiredate() == null) ? 0 : getFhiredate().hashCode());
        result = prime * result + ((getFstatus() == null) ? 0 : getFstatus().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        return result;
    }
}