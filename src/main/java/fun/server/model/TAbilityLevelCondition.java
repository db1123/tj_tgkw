package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_level_condition
*/
@Table(name = "t_ability_level_condition")
public class TAbilityLevelCondition implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 条件名称
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 满足条件的分数
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FScore
     * @mbg.generated
     */
    private Integer fscore;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_ability_level_condition.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_level_condition
     * @mbg.generated
     */
    public TAbilityLevelCondition(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, Integer fscore, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fscore = fscore;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_level_condition
     * @mbg.generated
     */
    public TAbilityLevelCondition() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level_condition.FKeyID
     * @return student_capability_evaluation..t_ability_level_condition.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level_condition.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_level_condition.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level_condition.FCID
     * @return student_capability_evaluation..t_ability_level_condition.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level_condition.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_level_condition.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level_condition.FUID
     * @return student_capability_evaluation..t_ability_level_condition.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level_condition.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_level_condition.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level_condition.FCDATE
     * @return student_capability_evaluation..t_ability_level_condition.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level_condition.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_level_condition.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_level_condition.FUDATE
     * @return student_capability_evaluation..t_ability_level_condition.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_level_condition.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_level_condition.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_level_condition.FState
     * @return student_capability_evaluation..t_ability_level_condition.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_level_condition.FState
     * @param fstate the value for student_capability_evaluation..t_ability_level_condition.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 条件名称 字段:student_capability_evaluation..t_ability_level_condition.FName
     * @return student_capability_evaluation..t_ability_level_condition.FName, 条件名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 条件名称 字段:student_capability_evaluation..t_ability_level_condition.FName
     * @param fname the value for student_capability_evaluation..t_ability_level_condition.FName, 条件名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 满足条件的分数 字段:student_capability_evaluation..t_ability_level_condition.FScore
     * @return student_capability_evaluation..t_ability_level_condition.FScore, 满足条件的分数
     * @mbg.generated
     */
    public Integer getFscore() {
        return fscore;
    }

    /**
     * 设置 满足条件的分数 字段:student_capability_evaluation..t_ability_level_condition.FScore
     * @param fscore the value for student_capability_evaluation..t_ability_level_condition.FScore, 满足条件的分数
     * @mbg.generated
     */
    public void setFscore(Integer fscore) {
        this.fscore = fscore;
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_ability_level_condition.FNote
     * @return student_capability_evaluation..t_ability_level_condition.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_ability_level_condition.FNote
     * @param fnote the value for student_capability_evaluation..t_ability_level_condition.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_level_condition
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
        sb.append(", fscore=").append(fscore);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_level_condition
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
        TAbilityLevelCondition other = (TAbilityLevelCondition) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_level_condition
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
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}