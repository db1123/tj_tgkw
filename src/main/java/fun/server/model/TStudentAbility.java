package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_student_ability
*/
@Table(name = "t_student_ability")
public class TStudentAbility implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_ability.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_ability.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_ability.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_ability.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student_ability.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态，0-申请，1-获取，2-注销
     * 表字段 : student_capability_evaluation..t_student_ability.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 学生ID，T_Student
     * 表字段 : student_capability_evaluation..t_student_ability.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 获取时间
     * 表字段 : student_capability_evaluation..t_student_ability.FDate
     * @mbg.generated
     */
    private Date fdate;

    /**
     * 方式，1-考核，2-材料认定，3-直接认定
     * 表字段 : student_capability_evaluation..t_student_ability.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 能力ID，T_Ability
     * 表字段 : student_capability_evaluation..t_student_ability.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 能力等级ID，T_Ability_Level
     * 表字段 : student_capability_evaluation..t_student_ability.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 能力描述，如python（中级）
     * 表字段 : student_capability_evaluation..t_student_ability.FAbilityInf
     * @mbg.generated
     */
    private String fabilityinf;

    /**
     * 材料附件链接
     * 表字段 : student_capability_evaluation..t_student_ability.FUrl
     * @mbg.generated
     */
    private String furl;

    /**
     * 能力条件ID t_ability_level_condition
     * 表字段 : student_capability_evaluation..t_student_ability.FConditionID
     * @mbg.generated
     */
    private Long fconditionid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student_ability
     * @mbg.generated
     */
    public TStudentAbility(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fstudentid, Date fdate, Integer fmode, Long fabilityid, Long fabilitylevelid, String fabilityinf, String furl, Long fconditionid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fstudentid = fstudentid;
        this.fdate = fdate;
        this.fmode = fmode;
        this.fabilityid = fabilityid;
        this.fabilitylevelid = fabilitylevelid;
        this.fabilityinf = fabilityinf;
        this.furl = furl;
        this.fconditionid = fconditionid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student_ability
     * @mbg.generated
     */
    public TStudentAbility() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_ability.FKeyID
     * @return student_capability_evaluation..t_student_ability.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_ability.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_student_ability.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_ability.FCID
     * @return student_capability_evaluation..t_student_ability.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_ability.FCID
     * @param fcid the value for student_capability_evaluation..t_student_ability.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_ability.FUID
     * @return student_capability_evaluation..t_student_ability.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_ability.FUID
     * @param fuid the value for student_capability_evaluation..t_student_ability.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_ability.FCDATE
     * @return student_capability_evaluation..t_student_ability.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_ability.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_student_ability.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student_ability.FUDATE
     * @return student_capability_evaluation..t_student_ability.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student_ability.FUDATE
     * @param fudate the value for student_capability_evaluation..t_student_ability.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态，0-申请，1-获取，2-注销 字段:student_capability_evaluation..t_student_ability.FState
     * @return student_capability_evaluation..t_student_ability.FState, 状态，0-申请，1-获取，2-注销
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态，0-申请，1-获取，2-注销 字段:student_capability_evaluation..t_student_ability.FState
     * @param fstate the value for student_capability_evaluation..t_student_ability.FState, 状态，0-申请，1-获取，2-注销
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 学生ID，T_Student 字段:student_capability_evaluation..t_student_ability.FStudentID
     * @return student_capability_evaluation..t_student_ability.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID，T_Student 字段:student_capability_evaluation..t_student_ability.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_student_ability.FStudentID, 学生ID，T_Student
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 获取时间 字段:student_capability_evaluation..t_student_ability.FDate
     * @return student_capability_evaluation..t_student_ability.FDate, 获取时间
     * @mbg.generated
     */
    public Date getFdate() {
        return fdate;
    }

    /**
     * 设置 获取时间 字段:student_capability_evaluation..t_student_ability.FDate
     * @param fdate the value for student_capability_evaluation..t_student_ability.FDate, 获取时间
     * @mbg.generated
     */
    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    /**
     * 获取 方式，1-考核，2-材料认定，3-直接认定 字段:student_capability_evaluation..t_student_ability.FMode
     * @return student_capability_evaluation..t_student_ability.FMode, 方式，1-考核，2-材料认定，3-直接认定
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式，1-考核，2-材料认定，3-直接认定 字段:student_capability_evaluation..t_student_ability.FMode
     * @param fmode the value for student_capability_evaluation..t_student_ability.FMode, 方式，1-考核，2-材料认定，3-直接认定
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取 能力ID，T_Ability 字段:student_capability_evaluation..t_student_ability.FAbilityID
     * @return student_capability_evaluation..t_student_ability.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 能力ID，T_Ability 字段:student_capability_evaluation..t_student_ability.FAbilityID
     * @param fabilityid the value for student_capability_evaluation..t_student_ability.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_student_ability.FAbilityLevelID
     * @return student_capability_evaluation..t_student_ability.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_student_ability.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_student_ability.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 能力描述，如python（中级） 字段:student_capability_evaluation..t_student_ability.FAbilityInf
     * @return student_capability_evaluation..t_student_ability.FAbilityInf, 能力描述，如python（中级）
     * @mbg.generated
     */
    public String getFabilityinf() {
        return fabilityinf;
    }

    /**
     * 设置 能力描述，如python（中级） 字段:student_capability_evaluation..t_student_ability.FAbilityInf
     * @param fabilityinf the value for student_capability_evaluation..t_student_ability.FAbilityInf, 能力描述，如python（中级）
     * @mbg.generated
     */
    public void setFabilityinf(String fabilityinf) {
        this.fabilityinf = fabilityinf == null ? null : fabilityinf.trim();
    }

    /**
     * 获取 材料附件链接 字段:student_capability_evaluation..t_student_ability.FUrl
     * @return student_capability_evaluation..t_student_ability.FUrl, 材料附件链接
     * @mbg.generated
     */
    public String getFurl() {
        return furl;
    }

    /**
     * 设置 材料附件链接 字段:student_capability_evaluation..t_student_ability.FUrl
     * @param furl the value for student_capability_evaluation..t_student_ability.FUrl, 材料附件链接
     * @mbg.generated
     */
    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    /**
     * 获取 能力条件ID t_ability_level_condition 字段:student_capability_evaluation..t_student_ability.FConditionID
     * @return student_capability_evaluation..t_student_ability.FConditionID, 能力条件ID t_ability_level_condition
     * @mbg.generated
     */
    public Long getFconditionid() {
        return fconditionid;
    }

    /**
     * 设置 能力条件ID t_ability_level_condition 字段:student_capability_evaluation..t_student_ability.FConditionID
     * @param fconditionid the value for student_capability_evaluation..t_student_ability.FConditionID, 能力条件ID t_ability_level_condition
     * @mbg.generated
     */
    public void setFconditionid(Long fconditionid) {
        this.fconditionid = fconditionid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_student_ability
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
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fdate=").append(fdate);
        sb.append(", fmode=").append(fmode);
        sb.append(", fabilityid=").append(fabilityid);
        sb.append(", fabilitylevelid=").append(fabilitylevelid);
        sb.append(", fabilityinf=").append(fabilityinf);
        sb.append(", furl=").append(furl);
        sb.append(", fconditionid=").append(fconditionid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_student_ability
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
        TStudentAbility other = (TStudentAbility) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFdate() == null ? other.getFdate() == null : this.getFdate().equals(other.getFdate()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFabilityinf() == null ? other.getFabilityinf() == null : this.getFabilityinf().equals(other.getFabilityinf()))
            && (this.getFurl() == null ? other.getFurl() == null : this.getFurl().equals(other.getFurl()))
            && (this.getFconditionid() == null ? other.getFconditionid() == null : this.getFconditionid().equals(other.getFconditionid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_student_ability
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
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFdate() == null) ? 0 : getFdate().hashCode());
        result = prime * result + ((getFmode() == null) ? 0 : getFmode().hashCode());
        result = prime * result + ((getFabilityid() == null) ? 0 : getFabilityid().hashCode());
        result = prime * result + ((getFabilitylevelid() == null) ? 0 : getFabilitylevelid().hashCode());
        result = prime * result + ((getFabilityinf() == null) ? 0 : getFabilityinf().hashCode());
        result = prime * result + ((getFurl() == null) ? 0 : getFurl().hashCode());
        result = prime * result + ((getFconditionid() == null) ? 0 : getFconditionid().hashCode());
        return result;
    }
}