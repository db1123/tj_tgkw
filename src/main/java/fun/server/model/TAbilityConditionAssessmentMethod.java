package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_condition_assessment_method
*/
@Table(name = "t_ability_condition_assessment_method")
public class TAbilityConditionAssessmentMethod implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 等级表ID
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FALID
     * @mbg.generated
     */
    private Long falid;

    /**
     * 条件表的FKeyID
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FConditionID
     * @mbg.generated
     */
    private Long fconditionid;

    /**
     * 考核方式的FKeyID
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FMethodID
     * @mbg.generated
     */
    private Long fmethodid;

    /**
     * 权重
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FMethodWeight
     * @mbg.generated
     */
    private Float fmethodweight;

    /**
     * 1=关联条件表的FKeyID ，2=条件树形子表FKeyID
     * 表字段 : student_capability_evaluation..t_ability_condition_assessment_method.FType
     * @mbg.generated
     */
    private Integer ftype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition_assessment_method
     * @mbg.generated
     */
    public TAbilityConditionAssessmentMethod(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long falid, Long fconditionid, Long fmethodid, Float fmethodweight, Integer ftype) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.falid = falid;
        this.fconditionid = fconditionid;
        this.fmethodid = fmethodid;
        this.fmethodweight = fmethodweight;
        this.ftype = ftype;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition_assessment_method
     * @mbg.generated
     */
    public TAbilityConditionAssessmentMethod() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_assessment_method.FKeyID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_assessment_method.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_condition_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_assessment_method.FCID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_assessment_method.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_condition_assessment_method.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_assessment_method.FUID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_assessment_method.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_condition_assessment_method.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_assessment_method.FCDATE
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_assessment_method.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_condition_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_assessment_method.FUDATE
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_assessment_method.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_condition_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition_assessment_method.FState
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition_assessment_method.FState
     * @param fstate the value for student_capability_evaluation..t_ability_condition_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 等级表ID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FALID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FALID, 等级表ID
     * @mbg.generated
     */
    public Long getFalid() {
        return falid;
    }

    /**
     * 设置 等级表ID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FALID
     * @param falid the value for student_capability_evaluation..t_ability_condition_assessment_method.FALID, 等级表ID
     * @mbg.generated
     */
    public void setFalid(Long falid) {
        this.falid = falid;
    }

    /**
     * 获取 条件表的FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FConditionID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FConditionID, 条件表的FKeyID
     * @mbg.generated
     */
    public Long getFconditionid() {
        return fconditionid;
    }

    /**
     * 设置 条件表的FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FConditionID
     * @param fconditionid the value for student_capability_evaluation..t_ability_condition_assessment_method.FConditionID, 条件表的FKeyID
     * @mbg.generated
     */
    public void setFconditionid(Long fconditionid) {
        this.fconditionid = fconditionid;
    }

    /**
     * 获取 考核方式的FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FMethodID
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FMethodID, 考核方式的FKeyID
     * @mbg.generated
     */
    public Long getFmethodid() {
        return fmethodid;
    }

    /**
     * 设置 考核方式的FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FMethodID
     * @param fmethodid the value for student_capability_evaluation..t_ability_condition_assessment_method.FMethodID, 考核方式的FKeyID
     * @mbg.generated
     */
    public void setFmethodid(Long fmethodid) {
        this.fmethodid = fmethodid;
    }

    /**
     * 获取 权重 字段:student_capability_evaluation..t_ability_condition_assessment_method.FMethodWeight
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FMethodWeight, 权重
     * @mbg.generated
     */
    public Float getFmethodweight() {
        return fmethodweight;
    }

    /**
     * 设置 权重 字段:student_capability_evaluation..t_ability_condition_assessment_method.FMethodWeight
     * @param fmethodweight the value for student_capability_evaluation..t_ability_condition_assessment_method.FMethodWeight, 权重
     * @mbg.generated
     */
    public void setFmethodweight(Float fmethodweight) {
        this.fmethodweight = fmethodweight;
    }

    /**
     * 获取 1=关联条件表的FKeyID ，2=条件树形子表FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FType
     * @return student_capability_evaluation..t_ability_condition_assessment_method.FType, 1=关联条件表的FKeyID ，2=条件树形子表FKeyID
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 1=关联条件表的FKeyID ，2=条件树形子表FKeyID 字段:student_capability_evaluation..t_ability_condition_assessment_method.FType
     * @param ftype the value for student_capability_evaluation..t_ability_condition_assessment_method.FType, 1=关联条件表的FKeyID ，2=条件树形子表FKeyID
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_condition_assessment_method
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
        sb.append(", fconditionid=").append(fconditionid);
        sb.append(", fmethodid=").append(fmethodid);
        sb.append(", fmethodweight=").append(fmethodweight);
        sb.append(", ftype=").append(ftype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_condition_assessment_method
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
        TAbilityConditionAssessmentMethod other = (TAbilityConditionAssessmentMethod) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFalid() == null ? other.getFalid() == null : this.getFalid().equals(other.getFalid()))
            && (this.getFconditionid() == null ? other.getFconditionid() == null : this.getFconditionid().equals(other.getFconditionid()))
            && (this.getFmethodid() == null ? other.getFmethodid() == null : this.getFmethodid().equals(other.getFmethodid()))
            && (this.getFmethodweight() == null ? other.getFmethodweight() == null : this.getFmethodweight().equals(other.getFmethodweight()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_condition_assessment_method
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
        result = prime * result + ((getFconditionid() == null) ? 0 : getFconditionid().hashCode());
        result = prime * result + ((getFmethodid() == null) ? 0 : getFmethodid().hashCode());
        result = prime * result + ((getFmethodweight() == null) ? 0 : getFmethodweight().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        return result;
    }
}