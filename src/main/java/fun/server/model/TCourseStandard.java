package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_standard
*/
@Table(name = "t_course_standard")
public class TCourseStandard implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_standard.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_standard.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 能力ID，T_Ability
     * 表字段 : student_capability_evaluation..t_course_standard.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 能力等级ID，T_Ability_Level
     * 表字段 : student_capability_evaluation..t_course_standard.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 课程能力ID
     * 表字段 : student_capability_evaluation..t_course_standard.FCourseAbilityID
     * @mbg.generated
     */
    private Long fcourseabilityid;

    /**
     * 类型 2 = 填报,1 = 关联
     * 表字段 : student_capability_evaluation..t_course_standard.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 计算方式 FType=1时有
     * 表字段 : student_capability_evaluation..t_course_standard.FcalcType
     * @mbg.generated
     */
    private Long fcalctype;

    /**
     * 设置状态 默认-1 保存但未提交=1 提交=2
     * 表字段 : student_capability_evaluation..t_course_standard.FSZState
     * @mbg.generated
     */
    private Integer fszstate;

    /**
     * T_Ability_Condition表ID
     * 表字段 : student_capability_evaluation..t_course_standard.FAbilityCAMID
     * @mbg.generated
     */
    private Long fabilitycamid;

    /**
     * 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * 表字段 : student_capability_evaluation..t_course_standard.FQZType
     * @mbg.generated
     */
    private Integer fqztype;

    /**
     * 1=T_Ability_Condition表ID 2=T_Ability_Conditions表ID
     * 表字段 : student_capability_evaluation..t_course_standard.FCAMType
     * @mbg.generated
     */
    private Integer fcamtype;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public TCourseStandard(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fabilityid, Long fabilitylevelid, Long fcourseabilityid, Integer ftype, Long fcalctype, Integer fszstate, Long fabilitycamid, Integer fqztype, Integer fcamtype) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fabilityid = fabilityid;
        this.fabilitylevelid = fabilitylevelid;
        this.fcourseabilityid = fcourseabilityid;
        this.ftype = ftype;
        this.fcalctype = fcalctype;
        this.fszstate = fszstate;
        this.fabilitycamid = fabilitycamid;
        this.fqztype = fqztype;
        this.fcamtype = fcamtype;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public TCourseStandard() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard.FKeyID
     * @return student_capability_evaluation..t_course_standard.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_standard.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard.FCID
     * @return student_capability_evaluation..t_course_standard.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard.FCID
     * @param fcid the value for student_capability_evaluation..t_course_standard.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard.FUID
     * @return student_capability_evaluation..t_course_standard.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard.FUID
     * @param fuid the value for student_capability_evaluation..t_course_standard.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard.FCDATE
     * @return student_capability_evaluation..t_course_standard.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_standard.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard.FUDATE
     * @return student_capability_evaluation..t_course_standard.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_standard.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_standard.FState
     * @return student_capability_evaluation..t_course_standard.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_standard.FState
     * @param fstate the value for student_capability_evaluation..t_course_standard.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_standard.FCourseID
     * @return student_capability_evaluation..t_course_standard.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_standard.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_standard.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 能力ID，T_Ability 字段:student_capability_evaluation..t_course_standard.FAbilityID
     * @return student_capability_evaluation..t_course_standard.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 能力ID，T_Ability 字段:student_capability_evaluation..t_course_standard.FAbilityID
     * @param fabilityid the value for student_capability_evaluation..t_course_standard.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_course_standard.FAbilityLevelID
     * @return student_capability_evaluation..t_course_standard.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_course_standard.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_course_standard.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 课程能力ID 字段:student_capability_evaluation..t_course_standard.FCourseAbilityID
     * @return student_capability_evaluation..t_course_standard.FCourseAbilityID, 课程能力ID
     * @mbg.generated
     */
    public Long getFcourseabilityid() {
        return fcourseabilityid;
    }

    /**
     * 设置 课程能力ID 字段:student_capability_evaluation..t_course_standard.FCourseAbilityID
     * @param fcourseabilityid the value for student_capability_evaluation..t_course_standard.FCourseAbilityID, 课程能力ID
     * @mbg.generated
     */
    public void setFcourseabilityid(Long fcourseabilityid) {
        this.fcourseabilityid = fcourseabilityid;
    }

    /**
     * 获取 类型 2 = 填报,1 = 关联 字段:student_capability_evaluation..t_course_standard.FType
     * @return student_capability_evaluation..t_course_standard.FType, 类型 2 = 填报,1 = 关联
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 类型 2 = 填报,1 = 关联 字段:student_capability_evaluation..t_course_standard.FType
     * @param ftype the value for student_capability_evaluation..t_course_standard.FType, 类型 2 = 填报,1 = 关联
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 计算方式 FType=1时有 字段:student_capability_evaluation..t_course_standard.FcalcType
     * @return student_capability_evaluation..t_course_standard.FcalcType, 计算方式 FType=1时有
     * @mbg.generated
     */
    public Long getFcalctype() {
        return fcalctype;
    }

    /**
     * 设置 计算方式 FType=1时有 字段:student_capability_evaluation..t_course_standard.FcalcType
     * @param fcalctype the value for student_capability_evaluation..t_course_standard.FcalcType, 计算方式 FType=1时有
     * @mbg.generated
     */
    public void setFcalctype(Long fcalctype) {
        this.fcalctype = fcalctype;
    }

    /**
     * 获取 设置状态 默认-1 保存但未提交=1 提交=2 字段:student_capability_evaluation..t_course_standard.FSZState
     * @return student_capability_evaluation..t_course_standard.FSZState, 设置状态 默认-1 保存但未提交=1 提交=2
     * @mbg.generated
     */
    public Integer getFszstate() {
        return fszstate;
    }

    /**
     * 设置 设置状态 默认-1 保存但未提交=1 提交=2 字段:student_capability_evaluation..t_course_standard.FSZState
     * @param fszstate the value for student_capability_evaluation..t_course_standard.FSZState, 设置状态 默认-1 保存但未提交=1 提交=2
     * @mbg.generated
     */
    public void setFszstate(Integer fszstate) {
        this.fszstate = fszstate;
    }

    /**
     * 获取 T_Ability_Condition表ID 字段:student_capability_evaluation..t_course_standard.FAbilityCAMID
     * @return student_capability_evaluation..t_course_standard.FAbilityCAMID, T_Ability_Condition表ID
     * @mbg.generated
     */
    public Long getFabilitycamid() {
        return fabilitycamid;
    }

    /**
     * 设置 T_Ability_Condition表ID 字段:student_capability_evaluation..t_course_standard.FAbilityCAMID
     * @param fabilitycamid the value for student_capability_evaluation..t_course_standard.FAbilityCAMID, T_Ability_Condition表ID
     * @mbg.generated
     */
    public void setFabilitycamid(Long fabilitycamid) {
        this.fabilitycamid = fabilitycamid;
    }

    /**
     * 获取 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值 字段:student_capability_evaluation..t_course_standard.FQZType
     * @return student_capability_evaluation..t_course_standard.FQZType, 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * @mbg.generated
     */
    public Integer getFqztype() {
        return fqztype;
    }

    /**
     * 设置 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值 字段:student_capability_evaluation..t_course_standard.FQZType
     * @param fqztype the value for student_capability_evaluation..t_course_standard.FQZType, 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * @mbg.generated
     */
    public void setFqztype(Integer fqztype) {
        this.fqztype = fqztype;
    }

    /**
     * 获取 1=T_Ability_Condition表ID 2=T_Ability_Conditions表ID 字段:student_capability_evaluation..t_course_standard.FCAMType
     * @return student_capability_evaluation..t_course_standard.FCAMType, 1=T_Ability_Condition表ID 2=T_Ability_Conditions表ID
     * @mbg.generated
     */
    public Integer getFcamtype() {
        return fcamtype;
    }

    /**
     * 设置 1=T_Ability_Condition表ID 2=T_Ability_Conditions表ID 字段:student_capability_evaluation..t_course_standard.FCAMType
     * @param fcamtype the value for student_capability_evaluation..t_course_standard.FCAMType, 1=T_Ability_Condition表ID 2=T_Ability_Conditions表ID
     * @mbg.generated
     */
    public void setFcamtype(Integer fcamtype) {
        this.fcamtype = fcamtype;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_standard
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
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fabilityid=").append(fabilityid);
        sb.append(", fabilitylevelid=").append(fabilitylevelid);
        sb.append(", fcourseabilityid=").append(fcourseabilityid);
        sb.append(", ftype=").append(ftype);
        sb.append(", fcalctype=").append(fcalctype);
        sb.append(", fszstate=").append(fszstate);
        sb.append(", fabilitycamid=").append(fabilitycamid);
        sb.append(", fqztype=").append(fqztype);
        sb.append(", fcamtype=").append(fcamtype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_standard
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
        TCourseStandard other = (TCourseStandard) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFcourseabilityid() == null ? other.getFcourseabilityid() == null : this.getFcourseabilityid().equals(other.getFcourseabilityid()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFcalctype() == null ? other.getFcalctype() == null : this.getFcalctype().equals(other.getFcalctype()))
            && (this.getFszstate() == null ? other.getFszstate() == null : this.getFszstate().equals(other.getFszstate()))
            && (this.getFabilitycamid() == null ? other.getFabilitycamid() == null : this.getFabilitycamid().equals(other.getFabilitycamid()))
            && (this.getFqztype() == null ? other.getFqztype() == null : this.getFqztype().equals(other.getFqztype()))
            && (this.getFcamtype() == null ? other.getFcamtype() == null : this.getFcamtype().equals(other.getFcamtype()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_standard
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
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFabilityid() == null) ? 0 : getFabilityid().hashCode());
        result = prime * result + ((getFabilitylevelid() == null) ? 0 : getFabilitylevelid().hashCode());
        result = prime * result + ((getFcourseabilityid() == null) ? 0 : getFcourseabilityid().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFcalctype() == null) ? 0 : getFcalctype().hashCode());
        result = prime * result + ((getFszstate() == null) ? 0 : getFszstate().hashCode());
        result = prime * result + ((getFabilitycamid() == null) ? 0 : getFabilitycamid().hashCode());
        result = prime * result + ((getFqztype() == null) ? 0 : getFqztype().hashCode());
        result = prime * result + ((getFcamtype() == null) ? 0 : getFcamtype().hashCode());
        return result;
    }
}