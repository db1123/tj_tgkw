package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_hire_ability
*/
@Table(name = "t_hire_ability")
public class THireAbility implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_ability.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_ability.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_ability.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_ability.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire_ability.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_hire_ability.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 招聘表ID，T_Hire
     * 表字段 : student_capability_evaluation..t_hire_ability.FHireID
     * @mbg.generated
     */
    private Long fhireid;

    /**
     * 需要的能力ID，T_Ability
     * 表字段 : student_capability_evaluation..t_hire_ability.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 需要的能力等级ID，T_Ability_Level
     * 表字段 : student_capability_evaluation..t_hire_ability.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 权重（能力在该职位中的占比）
     * 表字段 : student_capability_evaluation..t_hire_ability.FWeight
     * @mbg.generated
     */
    private Integer fweight;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public THireAbility(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fhireid, Long fabilityid, Long fabilitylevelid, Integer fweight) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fhireid = fhireid;
        this.fabilityid = fabilityid;
        this.fabilitylevelid = fabilitylevelid;
        this.fweight = fweight;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public THireAbility() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_ability.FKeyID
     * @return student_capability_evaluation..t_hire_ability.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_ability.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_hire_ability.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_ability.FCID
     * @return student_capability_evaluation..t_hire_ability.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_ability.FCID
     * @param fcid the value for student_capability_evaluation..t_hire_ability.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_ability.FUID
     * @return student_capability_evaluation..t_hire_ability.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_ability.FUID
     * @param fuid the value for student_capability_evaluation..t_hire_ability.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_ability.FCDATE
     * @return student_capability_evaluation..t_hire_ability.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_ability.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_hire_ability.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire_ability.FUDATE
     * @return student_capability_evaluation..t_hire_ability.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire_ability.FUDATE
     * @param fudate the value for student_capability_evaluation..t_hire_ability.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_hire_ability.FState
     * @return student_capability_evaluation..t_hire_ability.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_hire_ability.FState
     * @param fstate the value for student_capability_evaluation..t_hire_ability.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 招聘表ID，T_Hire 字段:student_capability_evaluation..t_hire_ability.FHireID
     * @return student_capability_evaluation..t_hire_ability.FHireID, 招聘表ID，T_Hire
     * @mbg.generated
     */
    public Long getFhireid() {
        return fhireid;
    }

    /**
     * 设置 招聘表ID，T_Hire 字段:student_capability_evaluation..t_hire_ability.FHireID
     * @param fhireid the value for student_capability_evaluation..t_hire_ability.FHireID, 招聘表ID，T_Hire
     * @mbg.generated
     */
    public void setFhireid(Long fhireid) {
        this.fhireid = fhireid;
    }

    /**
     * 获取 需要的能力ID，T_Ability 字段:student_capability_evaluation..t_hire_ability.FAbilityID
     * @return student_capability_evaluation..t_hire_ability.FAbilityID, 需要的能力ID，T_Ability
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 需要的能力ID，T_Ability 字段:student_capability_evaluation..t_hire_ability.FAbilityID
     * @param fabilityid the value for student_capability_evaluation..t_hire_ability.FAbilityID, 需要的能力ID，T_Ability
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 需要的能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_hire_ability.FAbilityLevelID
     * @return student_capability_evaluation..t_hire_ability.FAbilityLevelID, 需要的能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 需要的能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_hire_ability.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_hire_ability.FAbilityLevelID, 需要的能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 权重（能力在该职位中的占比） 字段:student_capability_evaluation..t_hire_ability.FWeight
     * @return student_capability_evaluation..t_hire_ability.FWeight, 权重（能力在该职位中的占比）
     * @mbg.generated
     */
    public Integer getFweight() {
        return fweight;
    }

    /**
     * 设置 权重（能力在该职位中的占比） 字段:student_capability_evaluation..t_hire_ability.FWeight
     * @param fweight the value for student_capability_evaluation..t_hire_ability.FWeight, 权重（能力在该职位中的占比）
     * @mbg.generated
     */
    public void setFweight(Integer fweight) {
        this.fweight = fweight;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_hire_ability
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
        sb.append(", fhireid=").append(fhireid);
        sb.append(", fabilityid=").append(fabilityid);
        sb.append(", fabilitylevelid=").append(fabilitylevelid);
        sb.append(", fweight=").append(fweight);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_hire_ability
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
        THireAbility other = (THireAbility) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFhireid() == null ? other.getFhireid() == null : this.getFhireid().equals(other.getFhireid()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFweight() == null ? other.getFweight() == null : this.getFweight().equals(other.getFweight()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_hire_ability
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
        result = prime * result + ((getFhireid() == null) ? 0 : getFhireid().hashCode());
        result = prime * result + ((getFabilityid() == null) ? 0 : getFabilityid().hashCode());
        result = prime * result + ((getFabilitylevelid() == null) ? 0 : getFabilitylevelid().hashCode());
        result = prime * result + ((getFweight() == null) ? 0 : getFweight().hashCode());
        return result;
    }
}