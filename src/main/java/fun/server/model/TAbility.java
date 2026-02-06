package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability
*/
@Table(name = "t_ability")
public class TAbility implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability.FKeyID
     * @mbg.generated
     */

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 能力类型，T_Ability_Type
     * 表字段 : student_capability_evaluation..t_ability.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_ability.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 描述
     * 表字段 : student_capability_evaluation..t_ability.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability
     * @mbg.generated
     */
    public TAbility(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftypeid, String fname, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftypeid = ftypeid;
        this.fname = fname;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability
     * @mbg.generated
     */
    public TAbility() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability.FKeyID
     * @return student_capability_evaluation..t_ability.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability.FCID
     * @return student_capability_evaluation..t_ability.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability.FCID
     * @param fcid the value for student_capability_evaluation..t_ability.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability.FUID
     * @return student_capability_evaluation..t_ability.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability.FUID
     * @param fuid the value for student_capability_evaluation..t_ability.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability.FCDATE
     * @return student_capability_evaluation..t_ability.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability.FUDATE
     * @return student_capability_evaluation..t_ability.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability.FState
     * @return student_capability_evaluation..t_ability.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability.FState
     * @param fstate the value for student_capability_evaluation..t_ability.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability.FTypeID
     * @return student_capability_evaluation..t_ability.FTypeID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability.FTypeID
     * @param ftypeid the value for student_capability_evaluation..t_ability.FTypeID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_ability.FName
     * @return student_capability_evaluation..t_ability.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_ability.FName
     * @param fname the value for student_capability_evaluation..t_ability.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 描述 字段:student_capability_evaluation..t_ability.FNote
     * @return student_capability_evaluation..t_ability.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:student_capability_evaluation..t_ability.FNote
     * @param fnote the value for student_capability_evaluation..t_ability.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability
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
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", fname=").append(fname);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability
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
        TAbility other = (TAbility) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability
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
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}