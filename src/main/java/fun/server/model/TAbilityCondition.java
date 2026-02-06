package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_condition
*/
@Table(name = "t_ability_condition")
public class TAbilityCondition implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_condition.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 等级表ID
     * 表字段 : student_capability_evaluation..t_ability_condition.FALID
     * @mbg.generated
     */
    private Long falid;

    /**
     * 条件名称
     * 表字段 : student_capability_evaluation..t_ability_condition.FConditionName
     * @mbg.generated
     */
    private String fconditionname;

    /**
     * 条件类型 1-数值 2-布尔 3-字符串 4-关联（预留）
     * 表字段 : student_capability_evaluation..t_ability_condition.FConditionType
     * @mbg.generated
     */
    private Integer fconditiontype;

    /**
     * 条件值
     * 表字段 : student_capability_evaluation..t_ability_condition.FConditionValue
     * @mbg.generated
     */
    private String fconditionvalue;

    /**
     * 满足条件的分数
     * 表字段 : student_capability_evaluation..t_ability_condition.FConditionScore
     * @mbg.generated
     */
    private Float fconditionscore;

    /**
     * 说明
     * 表字段 : student_capability_evaluation..t_ability_condition.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 权重
     * 表字段 : student_capability_evaluation..t_ability_condition.FMethodWeight
     * @mbg.generated
     */
    private Float fmethodweight;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition
     * @mbg.generated
     */
    public TAbilityCondition(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long falid, String fconditionname, Integer fconditiontype, String fconditionvalue, Float fconditionscore, String fnote, Float fmethodweight) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.falid = falid;
        this.fconditionname = fconditionname;
        this.fconditiontype = fconditiontype;
        this.fconditionvalue = fconditionvalue;
        this.fconditionscore = fconditionscore;
        this.fnote = fnote;
        this.fmethodweight = fmethodweight;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition
     * @mbg.generated
     */
    public TAbilityCondition() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition.FKeyID
     * @return student_capability_evaluation..t_ability_condition.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_condition.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition.FCID
     * @return student_capability_evaluation..t_ability_condition.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_condition.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition.FUID
     * @return student_capability_evaluation..t_ability_condition.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_condition.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition.FCDATE
     * @return student_capability_evaluation..t_ability_condition.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_condition.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition.FUDATE
     * @return student_capability_evaluation..t_ability_condition.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_condition.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition.FState
     * @return student_capability_evaluation..t_ability_condition.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition.FState
     * @param fstate the value for student_capability_evaluation..t_ability_condition.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 等级表ID 字段:student_capability_evaluation..t_ability_condition.FALID
     * @return student_capability_evaluation..t_ability_condition.FALID, 等级表ID
     * @mbg.generated
     */
    public Long getFalid() {
        return falid;
    }

    /**
     * 设置 等级表ID 字段:student_capability_evaluation..t_ability_condition.FALID
     * @param falid the value for student_capability_evaluation..t_ability_condition.FALID, 等级表ID
     * @mbg.generated
     */
    public void setFalid(Long falid) {
        this.falid = falid;
    }

    /**
     * 获取 条件名称 字段:student_capability_evaluation..t_ability_condition.FConditionName
     * @return student_capability_evaluation..t_ability_condition.FConditionName, 条件名称
     * @mbg.generated
     */
    public String getFconditionname() {
        return fconditionname;
    }

    /**
     * 设置 条件名称 字段:student_capability_evaluation..t_ability_condition.FConditionName
     * @param fconditionname the value for student_capability_evaluation..t_ability_condition.FConditionName, 条件名称
     * @mbg.generated
     */
    public void setFconditionname(String fconditionname) {
        this.fconditionname = fconditionname == null ? null : fconditionname.trim();
    }

    /**
     * 获取 条件类型 1-数值 2-布尔 3-字符串 4-关联（预留） 字段:student_capability_evaluation..t_ability_condition.FConditionType
     * @return student_capability_evaluation..t_ability_condition.FConditionType, 条件类型 1-数值 2-布尔 3-字符串 4-关联（预留）
     * @mbg.generated
     */
    public Integer getFconditiontype() {
        return fconditiontype;
    }

    /**
     * 设置 条件类型 1-数值 2-布尔 3-字符串 4-关联（预留） 字段:student_capability_evaluation..t_ability_condition.FConditionType
     * @param fconditiontype the value for student_capability_evaluation..t_ability_condition.FConditionType, 条件类型 1-数值 2-布尔 3-字符串 4-关联（预留）
     * @mbg.generated
     */
    public void setFconditiontype(Integer fconditiontype) {
        this.fconditiontype = fconditiontype;
    }

    /**
     * 获取 条件值 字段:student_capability_evaluation..t_ability_condition.FConditionValue
     * @return student_capability_evaluation..t_ability_condition.FConditionValue, 条件值
     * @mbg.generated
     */
    public String getFconditionvalue() {
        return fconditionvalue;
    }

    /**
     * 设置 条件值 字段:student_capability_evaluation..t_ability_condition.FConditionValue
     * @param fconditionvalue the value for student_capability_evaluation..t_ability_condition.FConditionValue, 条件值
     * @mbg.generated
     */
    public void setFconditionvalue(String fconditionvalue) {
        this.fconditionvalue = fconditionvalue == null ? null : fconditionvalue.trim();
    }

    /**
     * 获取 满足条件的分数 字段:student_capability_evaluation..t_ability_condition.FConditionScore
     * @return student_capability_evaluation..t_ability_condition.FConditionScore, 满足条件的分数
     * @mbg.generated
     */
    public Float getFconditionscore() {
        return fconditionscore;
    }

    /**
     * 设置 满足条件的分数 字段:student_capability_evaluation..t_ability_condition.FConditionScore
     * @param fconditionscore the value for student_capability_evaluation..t_ability_condition.FConditionScore, 满足条件的分数
     * @mbg.generated
     */
    public void setFconditionscore(Float fconditionscore) {
        this.fconditionscore = fconditionscore;
    }

    /**
     * 获取 说明 字段:student_capability_evaluation..t_ability_condition.FNote
     * @return student_capability_evaluation..t_ability_condition.FNote, 说明
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 说明 字段:student_capability_evaluation..t_ability_condition.FNote
     * @param fnote the value for student_capability_evaluation..t_ability_condition.FNote, 说明
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 权重 字段:student_capability_evaluation..t_ability_condition.FMethodWeight
     * @return student_capability_evaluation..t_ability_condition.FMethodWeight, 权重
     * @mbg.generated
     */
    public Float getFmethodweight() {
        return fmethodweight;
    }

    /**
     * 设置 权重 字段:student_capability_evaluation..t_ability_condition.FMethodWeight
     * @param fmethodweight the value for student_capability_evaluation..t_ability_condition.FMethodWeight, 权重
     * @mbg.generated
     */
    public void setFmethodweight(Float fmethodweight) {
        this.fmethodweight = fmethodweight;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_condition
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
        sb.append(", falid=").append(falid);
        sb.append(", fconditionname=").append(fconditionname);
        sb.append(", fconditiontype=").append(fconditiontype);
        sb.append(", fconditionvalue=").append(fconditionvalue);
        sb.append(", fconditionscore=").append(fconditionscore);
        sb.append(", fnote=").append(fnote);
        sb.append(", fmethodweight=").append(fmethodweight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_condition
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
        TAbilityCondition other = (TAbilityCondition) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFalid() == null ? other.getFalid() == null : this.getFalid().equals(other.getFalid()))
            && (this.getFconditionname() == null ? other.getFconditionname() == null : this.getFconditionname().equals(other.getFconditionname()))
            && (this.getFconditiontype() == null ? other.getFconditiontype() == null : this.getFconditiontype().equals(other.getFconditiontype()))
            && (this.getFconditionvalue() == null ? other.getFconditionvalue() == null : this.getFconditionvalue().equals(other.getFconditionvalue()))
            && (this.getFconditionscore() == null ? other.getFconditionscore() == null : this.getFconditionscore().equals(other.getFconditionscore()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFmethodweight() == null ? other.getFmethodweight() == null : this.getFmethodweight().equals(other.getFmethodweight()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_condition
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
        result = prime * result + ((getFalid() == null) ? 0 : getFalid().hashCode());
        result = prime * result + ((getFconditionname() == null) ? 0 : getFconditionname().hashCode());
        result = prime * result + ((getFconditiontype() == null) ? 0 : getFconditiontype().hashCode());
        result = prime * result + ((getFconditionvalue() == null) ? 0 : getFconditionvalue().hashCode());
        result = prime * result + ((getFconditionscore() == null) ? 0 : getFconditionscore().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFmethodweight() == null) ? 0 : getFmethodweight().hashCode());
        return result;
    }
}