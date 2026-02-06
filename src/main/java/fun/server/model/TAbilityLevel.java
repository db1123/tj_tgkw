package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_level
*/
@Table(name = "t_ability_level")
public class TAbilityLevel implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_level.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 能力类型，T_Ability_Type
     * 表字段 : student_capability_evaluation..t_ability_level.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_ability_level.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 描述
     * 表字段 : student_capability_evaluation..t_ability_level.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 难度积分
     * 表字段 : student_capability_evaluation..t_ability_level.FPoints
     * @mbg.generated
     */
    private Integer fpoints;

    /**
     * 达到该等级的最低分数
     * 表字段 : student_capability_evaluation..t_ability_level.FScoreMin
     * @mbg.generated
     */
    private Integer fscoremin;

    /**
     * 达到该等级的最高分数
     * 表字段 : student_capability_evaluation..t_ability_level.FScoreMax
     * @mbg.generated
     */
    private Integer fscoremax;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_level
     * @mbg.generated
     */
    public TAbilityLevel(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fabilityid, String fname, String fnote, Integer fpoints, Integer fscoremin, Integer fscoremax) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fabilityid = fabilityid;
        this.fname = fname;
        this.fnote = fnote;
        this.fpoints = fpoints;
        this.fscoremin = fscoremin;
        this.fscoremax = fscoremax;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_level
     * @mbg.generated
     */
    public TAbilityLevel() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level.FKeyID
     * @return student_capability_evaluation..t_ability_level.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_level.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level.FCID
     * @return student_capability_evaluation..t_ability_level.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_level.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level.FUID
     * @return student_capability_evaluation..t_ability_level.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_level.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level.FCDATE
     * @return student_capability_evaluation..t_ability_level.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_level.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level.FUDATE
     * @return student_capability_evaluation..t_ability_level.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_level.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_level.FState
     * @return student_capability_evaluation..t_ability_level.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_level.FState
     * @param fstate the value for student_capability_evaluation..t_ability_level.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability_level.FAbilityID
     * @return student_capability_evaluation..t_ability_level.FAbilityID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability_level.FAbilityID
     * @param fabilityid the value for student_capability_evaluation..t_ability_level.FAbilityID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_ability_level.FName
     * @return student_capability_evaluation..t_ability_level.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_ability_level.FName
     * @param fname the value for student_capability_evaluation..t_ability_level.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 描述 字段:student_capability_evaluation..t_ability_level.FNote
     * @return student_capability_evaluation..t_ability_level.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:student_capability_evaluation..t_ability_level.FNote
     * @param fnote the value for student_capability_evaluation..t_ability_level.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 难度积分 字段:student_capability_evaluation..t_ability_level.FPoints
     * @return student_capability_evaluation..t_ability_level.FPoints, 难度积分
     * @mbg.generated
     */
    public Integer getFpoints() {
        return fpoints;
    }

    /**
     * 设置 难度积分 字段:student_capability_evaluation..t_ability_level.FPoints
     * @param fpoints the value for student_capability_evaluation..t_ability_level.FPoints, 难度积分
     * @mbg.generated
     */
    public void setFpoints(Integer fpoints) {
        this.fpoints = fpoints;
    }

    /**
     * 获取 达到该等级的最低分数 字段:student_capability_evaluation..t_ability_level.FScoreMin
     * @return student_capability_evaluation..t_ability_level.FScoreMin, 达到该等级的最低分数
     * @mbg.generated
     */
    public Integer getFscoremin() {
        return fscoremin;
    }

    /**
     * 设置 达到该等级的最低分数 字段:student_capability_evaluation..t_ability_level.FScoreMin
     * @param fscoremin the value for student_capability_evaluation..t_ability_level.FScoreMin, 达到该等级的最低分数
     * @mbg.generated
     */
    public void setFscoremin(Integer fscoremin) {
        this.fscoremin = fscoremin;
    }

    /**
     * 获取 达到该等级的最高分数 字段:student_capability_evaluation..t_ability_level.FScoreMax
     * @return student_capability_evaluation..t_ability_level.FScoreMax, 达到该等级的最高分数
     * @mbg.generated
     */
    public Integer getFscoremax() {
        return fscoremax;
    }

    /**
     * 设置 达到该等级的最高分数 字段:student_capability_evaluation..t_ability_level.FScoreMax
     * @param fscoremax the value for student_capability_evaluation..t_ability_level.FScoreMax, 达到该等级的最高分数
     * @mbg.generated
     */
    public void setFscoremax(Integer fscoremax) {
        this.fscoremax = fscoremax;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_level
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
        sb.append(", fabilityid=").append(fabilityid);
        sb.append(", fname=").append(fname);
        sb.append(", fnote=").append(fnote);
        sb.append(", fpoints=").append(fpoints);
        sb.append(", fscoremin=").append(fscoremin);
        sb.append(", fscoremax=").append(fscoremax);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_level
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
        TAbilityLevel other = (TAbilityLevel) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFpoints() == null ? other.getFpoints() == null : this.getFpoints().equals(other.getFpoints()))
            && (this.getFscoremin() == null ? other.getFscoremin() == null : this.getFscoremin().equals(other.getFscoremin()))
            && (this.getFscoremax() == null ? other.getFscoremax() == null : this.getFscoremax().equals(other.getFscoremax()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_level
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
        result = prime * result + ((getFabilityid() == null) ? 0 : getFabilityid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFpoints() == null) ? 0 : getFpoints().hashCode());
        result = prime * result + ((getFscoremin() == null) ? 0 : getFscoremin().hashCode());
        result = prime * result + ((getFscoremax() == null) ? 0 : getFscoremax().hashCode());
        return result;
    }
}