package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_qt
*/
@Table(name = "t_training_program_qt")
public class TTrainingProgramQt implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_qt.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_qt.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_qt.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_qt.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_qt.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_training_program_qt.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program_qt.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : student_capability_evaluation..t_training_program_qt.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_training_program_qt.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 单位
     * 表字段 : student_capability_evaluation..t_training_program_qt.FDW
     * @mbg.generated
     */
    private String fdw;

    /**
     * 简介
     * 表字段 : student_capability_evaluation..t_training_program_qt.FJS
     * @mbg.generated
     */
    private String fjs;

    /**
     * 类型  1=专业技能2=竞赛3=学习网站
     * 表字段 : student_capability_evaluation..t_training_program_qt.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 顺序
     * 表字段 : student_capability_evaluation..t_training_program_qt.FOrder
     * @mbg.generated
     */
    private Integer forder;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_qt
     * @mbg.generated
     */
    public TTrainingProgramQt(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, String fname, String fdw, String fjs, Integer ftype, Integer forder) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fname = fname;
        this.fdw = fdw;
        this.fjs = fjs;
        this.ftype = ftype;
        this.forder = forder;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_qt
     * @mbg.generated
     */
    public TTrainingProgramQt() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_qt.FKeyID
     * @return student_capability_evaluation..t_training_program_qt.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_qt.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program_qt.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_qt.FCID
     * @return student_capability_evaluation..t_training_program_qt.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_qt.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program_qt.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_qt.FUID
     * @return student_capability_evaluation..t_training_program_qt.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_qt.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program_qt.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_qt.FCDATE
     * @return student_capability_evaluation..t_training_program_qt.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_qt.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program_qt.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_qt.FUDATE
     * @return student_capability_evaluation..t_training_program_qt.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_qt.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program_qt.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_qt.FState
     * @return student_capability_evaluation..t_training_program_qt.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_qt.FState
     * @param fstate the value for student_capability_evaluation..t_training_program_qt.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_qt.FTMID
     * @return student_capability_evaluation..t_training_program_qt.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_qt.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program_qt.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_qt.FTPID
     * @return student_capability_evaluation..t_training_program_qt.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_qt.FTPID
     * @param ftpid the value for student_capability_evaluation..t_training_program_qt.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_training_program_qt.FName
     * @return student_capability_evaluation..t_training_program_qt.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_training_program_qt.FName
     * @param fname the value for student_capability_evaluation..t_training_program_qt.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 单位 字段:student_capability_evaluation..t_training_program_qt.FDW
     * @return student_capability_evaluation..t_training_program_qt.FDW, 单位
     * @mbg.generated
     */
    public String getFdw() {
        return fdw;
    }

    /**
     * 设置 单位 字段:student_capability_evaluation..t_training_program_qt.FDW
     * @param fdw the value for student_capability_evaluation..t_training_program_qt.FDW, 单位
     * @mbg.generated
     */
    public void setFdw(String fdw) {
        this.fdw = fdw == null ? null : fdw.trim();
    }

    /**
     * 获取 简介 字段:student_capability_evaluation..t_training_program_qt.FJS
     * @return student_capability_evaluation..t_training_program_qt.FJS, 简介
     * @mbg.generated
     */
    public String getFjs() {
        return fjs;
    }

    /**
     * 设置 简介 字段:student_capability_evaluation..t_training_program_qt.FJS
     * @param fjs the value for student_capability_evaluation..t_training_program_qt.FJS, 简介
     * @mbg.generated
     */
    public void setFjs(String fjs) {
        this.fjs = fjs == null ? null : fjs.trim();
    }

    /**
     * 获取 类型  1=专业技能2=竞赛3=学习网站 字段:student_capability_evaluation..t_training_program_qt.FType
     * @return student_capability_evaluation..t_training_program_qt.FType, 类型  1=专业技能2=竞赛3=学习网站
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 类型  1=专业技能2=竞赛3=学习网站 字段:student_capability_evaluation..t_training_program_qt.FType
     * @param ftype the value for student_capability_evaluation..t_training_program_qt.FType, 类型  1=专业技能2=竞赛3=学习网站
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 顺序 字段:student_capability_evaluation..t_training_program_qt.FOrder
     * @return student_capability_evaluation..t_training_program_qt.FOrder, 顺序
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置 顺序 字段:student_capability_evaluation..t_training_program_qt.FOrder
     * @param forder the value for student_capability_evaluation..t_training_program_qt.FOrder, 顺序
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program_qt
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
        sb.append(", fdw=").append(fdw);
        sb.append(", fjs=").append(fjs);
        sb.append(", ftype=").append(ftype);
        sb.append(", forder=").append(forder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program_qt
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
        TTrainingProgramQt other = (TTrainingProgramQt) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFdw() == null ? other.getFdw() == null : this.getFdw().equals(other.getFdw()))
            && (this.getFjs() == null ? other.getFjs() == null : this.getFjs().equals(other.getFjs()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program_qt
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
        result = prime * result + ((getFdw() == null) ? 0 : getFdw().hashCode());
        result = prime * result + ((getFjs() == null) ? 0 : getFjs().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        return result;
    }
}