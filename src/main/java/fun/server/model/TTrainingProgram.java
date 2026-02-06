package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program
*/
@Table(name = "t_training_program")
public class TTrainingProgram implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-编辑；1-发布
     * 表字段 : student_capability_evaluation..t_training_program.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * 方案名称
     * 表字段 : student_capability_evaluation..t_training_program.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 方案介绍
     * 表字段 : student_capability_evaluation..t_training_program.FJS
     * @mbg.generated
     */
    private String fjs;

    /**
     * 专业介绍
     * 表字段 : student_capability_evaluation..t_training_program.FZYJS
     * @mbg.generated
     */
    private String fzyjs;

    /**
     * 版本号 默认1
     * 表字段 : student_capability_evaluation..t_training_program.FEditionNo
     * @mbg.generated
     */
    private Integer feditionno;

    /**
     * 版本 V1.0
     * 表字段 : student_capability_evaluation..t_training_program.FEdition
     * @mbg.generated
     */
    private String fedition;

    /**
     * 有效版本,0-无效,1-有（只一个）默认1
     * 表字段 : student_capability_evaluation..t_training_program.FValid
     * @mbg.generated
     */
    private Integer fvalid;

    /**
     * 历史记录父ID
     * 表字段 : student_capability_evaluation..t_training_program.FParentId
     * @mbg.generated
     */
    private Long fparentid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public TTrainingProgram(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, String fname, String fjs, String fzyjs, Integer feditionno, String fedition, Integer fvalid, Long fparentid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.fname = fname;
        this.fjs = fjs;
        this.fzyjs = fzyjs;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.fparentid = fparentid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public TTrainingProgram() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program.FKeyID
     * @return student_capability_evaluation..t_training_program.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program.FCID
     * @return student_capability_evaluation..t_training_program.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program.FUID
     * @return student_capability_evaluation..t_training_program.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program.FCDATE
     * @return student_capability_evaluation..t_training_program.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program.FUDATE
     * @return student_capability_evaluation..t_training_program.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-编辑；1-发布 字段:student_capability_evaluation..t_training_program.FState
     * @return student_capability_evaluation..t_training_program.FState, 0-编辑；1-发布
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-编辑；1-发布 字段:student_capability_evaluation..t_training_program.FState
     * @param fstate the value for student_capability_evaluation..t_training_program.FState, 0-编辑；1-发布
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program.FTMID
     * @return student_capability_evaluation..t_training_program.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 方案名称 字段:student_capability_evaluation..t_training_program.FName
     * @return student_capability_evaluation..t_training_program.FName, 方案名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 方案名称 字段:student_capability_evaluation..t_training_program.FName
     * @param fname the value for student_capability_evaluation..t_training_program.FName, 方案名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 方案介绍 字段:student_capability_evaluation..t_training_program.FJS
     * @return student_capability_evaluation..t_training_program.FJS, 方案介绍
     * @mbg.generated
     */
    public String getFjs() {
        return fjs;
    }

    /**
     * 设置 方案介绍 字段:student_capability_evaluation..t_training_program.FJS
     * @param fjs the value for student_capability_evaluation..t_training_program.FJS, 方案介绍
     * @mbg.generated
     */
    public void setFjs(String fjs) {
        this.fjs = fjs == null ? null : fjs.trim();
    }

    /**
     * 获取 专业介绍 字段:student_capability_evaluation..t_training_program.FZYJS
     * @return student_capability_evaluation..t_training_program.FZYJS, 专业介绍
     * @mbg.generated
     */
    public String getFzyjs() {
        return fzyjs;
    }

    /**
     * 设置 专业介绍 字段:student_capability_evaluation..t_training_program.FZYJS
     * @param fzyjs the value for student_capability_evaluation..t_training_program.FZYJS, 专业介绍
     * @mbg.generated
     */
    public void setFzyjs(String fzyjs) {
        this.fzyjs = fzyjs == null ? null : fzyjs.trim();
    }

    /**
     * 获取 版本号 默认1 字段:student_capability_evaluation..t_training_program.FEditionNo
     * @return student_capability_evaluation..t_training_program.FEditionNo, 版本号 默认1
     * @mbg.generated
     */
    public Integer getFeditionno() {
        return feditionno;
    }

    /**
     * 设置 版本号 默认1 字段:student_capability_evaluation..t_training_program.FEditionNo
     * @param feditionno the value for student_capability_evaluation..t_training_program.FEditionNo, 版本号 默认1
     * @mbg.generated
     */
    public void setFeditionno(Integer feditionno) {
        this.feditionno = feditionno;
    }

    /**
     * 获取 版本 V1.0 字段:student_capability_evaluation..t_training_program.FEdition
     * @return student_capability_evaluation..t_training_program.FEdition, 版本 V1.0
     * @mbg.generated
     */
    public String getFedition() {
        return fedition;
    }

    /**
     * 设置 版本 V1.0 字段:student_capability_evaluation..t_training_program.FEdition
     * @param fedition the value for student_capability_evaluation..t_training_program.FEdition, 版本 V1.0
     * @mbg.generated
     */
    public void setFedition(String fedition) {
        this.fedition = fedition == null ? null : fedition.trim();
    }

    /**
     * 获取 有效版本,0-无效,1-有（只一个）默认1 字段:student_capability_evaluation..t_training_program.FValid
     * @return student_capability_evaluation..t_training_program.FValid, 有效版本,0-无效,1-有（只一个）默认1
     * @mbg.generated
     */
    public Integer getFvalid() {
        return fvalid;
    }

    /**
     * 设置 有效版本,0-无效,1-有（只一个）默认1 字段:student_capability_evaluation..t_training_program.FValid
     * @param fvalid the value for student_capability_evaluation..t_training_program.FValid, 有效版本,0-无效,1-有（只一个）默认1
     * @mbg.generated
     */
    public void setFvalid(Integer fvalid) {
        this.fvalid = fvalid;
    }

    /**
     * 获取 历史记录父ID 字段:student_capability_evaluation..t_training_program.FParentId
     * @return student_capability_evaluation..t_training_program.FParentId, 历史记录父ID
     * @mbg.generated
     */
    public Long getFparentid() {
        return fparentid;
    }

    /**
     * 设置 历史记录父ID 字段:student_capability_evaluation..t_training_program.FParentId
     * @param fparentid the value for student_capability_evaluation..t_training_program.FParentId, 历史记录父ID
     * @mbg.generated
     */
    public void setFparentid(Long fparentid) {
        this.fparentid = fparentid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program
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
        sb.append(", fname=").append(fname);
        sb.append(", fjs=").append(fjs);
        sb.append(", fzyjs=").append(fzyjs);
        sb.append(", feditionno=").append(feditionno);
        sb.append(", fedition=").append(fedition);
        sb.append(", fvalid=").append(fvalid);
        sb.append(", fparentid=").append(fparentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program
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
        TTrainingProgram other = (TTrainingProgram) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFjs() == null ? other.getFjs() == null : this.getFjs().equals(other.getFjs()))
            && (this.getFzyjs() == null ? other.getFzyjs() == null : this.getFzyjs().equals(other.getFzyjs()))
            && (this.getFeditionno() == null ? other.getFeditionno() == null : this.getFeditionno().equals(other.getFeditionno()))
            && (this.getFedition() == null ? other.getFedition() == null : this.getFedition().equals(other.getFedition()))
            && (this.getFvalid() == null ? other.getFvalid() == null : this.getFvalid().equals(other.getFvalid()))
            && (this.getFparentid() == null ? other.getFparentid() == null : this.getFparentid().equals(other.getFparentid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFjs() == null) ? 0 : getFjs().hashCode());
        result = prime * result + ((getFzyjs() == null) ? 0 : getFzyjs().hashCode());
        result = prime * result + ((getFeditionno() == null) ? 0 : getFeditionno().hashCode());
        result = prime * result + ((getFedition() == null) ? 0 : getFedition().hashCode());
        result = prime * result + ((getFvalid() == null) ? 0 : getFvalid().hashCode());
        result = prime * result + ((getFparentid() == null) ? 0 : getFparentid().hashCode());
        return result;
    }
}