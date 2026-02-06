package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_xzwf
*/
@Table(name = "t_training_program_xzwf")
public class TTrainingProgramXzwf implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 学制
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FXZ
     * @mbg.generated
     */
    private String fxz;

    /**
     * 学位
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FXW
     * @mbg.generated
     */
    private String fxw;

    /**
     * 学分
     * 表字段 : student_capability_evaluation..t_training_program_xzwf.FXF
     * @mbg.generated
     */
    private String fxf;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_xzwf
     * @mbg.generated
     */
    public TTrainingProgramXzwf(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, String fxz, String fxw, String fxf) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fxz = fxz;
        this.fxw = fxw;
        this.fxf = fxf;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_xzwf
     * @mbg.generated
     */
    public TTrainingProgramXzwf() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_xzwf.FKeyID
     * @return student_capability_evaluation..t_training_program_xzwf.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_xzwf.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program_xzwf.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_xzwf.FCID
     * @return student_capability_evaluation..t_training_program_xzwf.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_xzwf.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program_xzwf.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_xzwf.FUID
     * @return student_capability_evaluation..t_training_program_xzwf.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_xzwf.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program_xzwf.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_xzwf.FCDATE
     * @return student_capability_evaluation..t_training_program_xzwf.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_xzwf.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program_xzwf.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_xzwf.FUDATE
     * @return student_capability_evaluation..t_training_program_xzwf.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_xzwf.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program_xzwf.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_xzwf.FState
     * @return student_capability_evaluation..t_training_program_xzwf.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_xzwf.FState
     * @param fstate the value for student_capability_evaluation..t_training_program_xzwf.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_xzwf.FTMID
     * @return student_capability_evaluation..t_training_program_xzwf.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_xzwf.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program_xzwf.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_xzwf.FTPID
     * @return student_capability_evaluation..t_training_program_xzwf.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_xzwf.FTPID
     * @param ftpid the value for student_capability_evaluation..t_training_program_xzwf.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 学制 字段:student_capability_evaluation..t_training_program_xzwf.FXZ
     * @return student_capability_evaluation..t_training_program_xzwf.FXZ, 学制
     * @mbg.generated
     */
    public String getFxz() {
        return fxz;
    }

    /**
     * 设置 学制 字段:student_capability_evaluation..t_training_program_xzwf.FXZ
     * @param fxz the value for student_capability_evaluation..t_training_program_xzwf.FXZ, 学制
     * @mbg.generated
     */
    public void setFxz(String fxz) {
        this.fxz = fxz == null ? null : fxz.trim();
    }

    /**
     * 获取 学位 字段:student_capability_evaluation..t_training_program_xzwf.FXW
     * @return student_capability_evaluation..t_training_program_xzwf.FXW, 学位
     * @mbg.generated
     */
    public String getFxw() {
        return fxw;
    }

    /**
     * 设置 学位 字段:student_capability_evaluation..t_training_program_xzwf.FXW
     * @param fxw the value for student_capability_evaluation..t_training_program_xzwf.FXW, 学位
     * @mbg.generated
     */
    public void setFxw(String fxw) {
        this.fxw = fxw == null ? null : fxw.trim();
    }

    /**
     * 获取 学分 字段:student_capability_evaluation..t_training_program_xzwf.FXF
     * @return student_capability_evaluation..t_training_program_xzwf.FXF, 学分
     * @mbg.generated
     */
    public String getFxf() {
        return fxf;
    }

    /**
     * 设置 学分 字段:student_capability_evaluation..t_training_program_xzwf.FXF
     * @param fxf the value for student_capability_evaluation..t_training_program_xzwf.FXF, 学分
     * @mbg.generated
     */
    public void setFxf(String fxf) {
        this.fxf = fxf == null ? null : fxf.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program_xzwf
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
        sb.append(", fxz=").append(fxz);
        sb.append(", fxw=").append(fxw);
        sb.append(", fxf=").append(fxf);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program_xzwf
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
        TTrainingProgramXzwf other = (TTrainingProgramXzwf) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFxz() == null ? other.getFxz() == null : this.getFxz().equals(other.getFxz()))
            && (this.getFxw() == null ? other.getFxw() == null : this.getFxw().equals(other.getFxw()))
            && (this.getFxf() == null ? other.getFxf() == null : this.getFxf().equals(other.getFxf()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program_xzwf
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
        result = prime * result + ((getFxz() == null) ? 0 : getFxz().hashCode());
        result = prime * result + ((getFxw() == null) ? 0 : getFxw().hashCode());
        result = prime * result + ((getFxf() == null) ? 0 : getFxf().hashCode());
        return result;
    }
}