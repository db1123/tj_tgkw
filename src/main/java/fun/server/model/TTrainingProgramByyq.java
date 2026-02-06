package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_byyq
*/
@Table(name = "t_training_program_byyq")
public class TTrainingProgramByyq implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_byyq.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_byyq.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_byyq.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_byyq.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_byyq.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_training_program_byyq.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : teaching_diversity..t_training_program_byyq.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : teaching_diversity..t_training_program_byyq.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 毕业要求内容
     * 表字段 : teaching_diversity..t_training_program_byyq.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 顺序
     * 表字段 : teaching_diversity..t_training_program_byyq.FOrder
     * @mbg.generated
     */
    private Integer forder;

    /**
     * 描述
     * 表字段 : teaching_diversity..t_training_program_byyq.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_byyq
     * @mbg.generated
     */
    public TTrainingProgramByyq(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, String fname, Integer forder, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fname = fname;
        this.forder = forder;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_byyq
     * @mbg.generated
     */
    public TTrainingProgramByyq() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_byyq.FKeyID
     * @return teaching_diversity..t_training_program_byyq.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_byyq.FKeyID
     * @param fkeyid the value for teaching_diversity..t_training_program_byyq.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_byyq.FCID
     * @return teaching_diversity..t_training_program_byyq.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_byyq.FCID
     * @param fcid the value for teaching_diversity..t_training_program_byyq.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_byyq.FUID
     * @return teaching_diversity..t_training_program_byyq.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_byyq.FUID
     * @param fuid the value for teaching_diversity..t_training_program_byyq.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_byyq.FCDATE
     * @return teaching_diversity..t_training_program_byyq.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_byyq.FCDATE
     * @param fcdate the value for teaching_diversity..t_training_program_byyq.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_byyq.FUDATE
     * @return teaching_diversity..t_training_program_byyq.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_byyq.FUDATE
     * @param fudate the value for teaching_diversity..t_training_program_byyq.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_training_program_byyq.FState
     * @return teaching_diversity..t_training_program_byyq.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_training_program_byyq.FState
     * @param fstate the value for teaching_diversity..t_training_program_byyq.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:teaching_diversity..t_training_program_byyq.FTMID
     * @return teaching_diversity..t_training_program_byyq.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:teaching_diversity..t_training_program_byyq.FTMID
     * @param ftmid the value for teaching_diversity..t_training_program_byyq.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:teaching_diversity..t_training_program_byyq.FTPID
     * @return teaching_diversity..t_training_program_byyq.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:teaching_diversity..t_training_program_byyq.FTPID
     * @param ftpid the value for teaching_diversity..t_training_program_byyq.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 毕业要求内容 字段:teaching_diversity..t_training_program_byyq.FName
     * @return teaching_diversity..t_training_program_byyq.FName, 毕业要求内容
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 毕业要求内容 字段:teaching_diversity..t_training_program_byyq.FName
     * @param fname the value for teaching_diversity..t_training_program_byyq.FName, 毕业要求内容
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 顺序 字段:teaching_diversity..t_training_program_byyq.FOrder
     * @return teaching_diversity..t_training_program_byyq.FOrder, 顺序
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置 顺序 字段:teaching_diversity..t_training_program_byyq.FOrder
     * @param forder the value for teaching_diversity..t_training_program_byyq.FOrder, 顺序
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 获取 描述 字段:teaching_diversity..t_training_program_byyq.FNote
     * @return teaching_diversity..t_training_program_byyq.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:teaching_diversity..t_training_program_byyq.FNote
     * @param fnote the value for teaching_diversity..t_training_program_byyq.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_training_program_byyq
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
        sb.append(", forder=").append(forder);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_training_program_byyq
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
        TTrainingProgramByyq other = (TTrainingProgramByyq) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_training_program_byyq
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
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}