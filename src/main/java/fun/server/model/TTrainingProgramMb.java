package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_mb
*/
@Table(name = "t_training_program_mb")
public class TTrainingProgramMb implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_mb.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_mb.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_mb.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_mb.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_mb.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_training_program_mb.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program_mb.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : student_capability_evaluation..t_training_program_mb.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 方案目标内容
     * 表字段 : student_capability_evaluation..t_training_program_mb.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 目标数
     * 表字段 : student_capability_evaluation..t_training_program_mb.FNum
     * @mbg.generated
     */
    private Integer fnum;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_mb
     * @mbg.generated
     */
    public TTrainingProgramMb(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, String fname, Integer fnum) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fname = fname;
        this.fnum = fnum;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_mb
     * @mbg.generated
     */
    public TTrainingProgramMb() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_mb.FKeyID
     * @return student_capability_evaluation..t_training_program_mb.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_mb.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program_mb.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_mb.FCID
     * @return student_capability_evaluation..t_training_program_mb.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_mb.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program_mb.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_mb.FUID
     * @return student_capability_evaluation..t_training_program_mb.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_mb.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program_mb.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_mb.FCDATE
     * @return student_capability_evaluation..t_training_program_mb.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_mb.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program_mb.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_mb.FUDATE
     * @return student_capability_evaluation..t_training_program_mb.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_mb.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program_mb.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_mb.FState
     * @return student_capability_evaluation..t_training_program_mb.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_mb.FState
     * @param fstate the value for student_capability_evaluation..t_training_program_mb.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_mb.FTMID
     * @return student_capability_evaluation..t_training_program_mb.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_mb.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program_mb.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_mb.FTPID
     * @return student_capability_evaluation..t_training_program_mb.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_mb.FTPID
     * @param ftpid the value for student_capability_evaluation..t_training_program_mb.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 方案目标内容 字段:student_capability_evaluation..t_training_program_mb.FName
     * @return student_capability_evaluation..t_training_program_mb.FName, 方案目标内容
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 方案目标内容 字段:student_capability_evaluation..t_training_program_mb.FName
     * @param fname the value for student_capability_evaluation..t_training_program_mb.FName, 方案目标内容
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 目标数 字段:student_capability_evaluation..t_training_program_mb.FNum
     * @return student_capability_evaluation..t_training_program_mb.FNum, 目标数
     * @mbg.generated
     */
    public Integer getFnum() {
        return fnum;
    }

    /**
     * 设置 目标数 字段:student_capability_evaluation..t_training_program_mb.FNum
     * @param fnum the value for student_capability_evaluation..t_training_program_mb.FNum, 目标数
     * @mbg.generated
     */
    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program_mb
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
        sb.append(", fname=").append(fname);
        sb.append(", fnum=").append(fnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program_mb
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
        TTrainingProgramMb other = (TTrainingProgramMb) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnum() == null ? other.getFnum() == null : this.getFnum().equals(other.getFnum()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program_mb
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnum() == null) ? 0 : getFnum().hashCode());
        return result;
    }
}