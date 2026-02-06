package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_course_standard_s
*/
@Table(name = "t_course_standard_s")
public class TCourseStandardS implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard_s.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard_s.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard_s.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard_s.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_standard_s.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_standard_s.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID，T_Course
     * 表字段 : student_capability_evaluation..t_course_standard_s.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 能力ID，T_Ability
     * 表字段 : student_capability_evaluation..t_course_standard_s.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 能力等级ID，T_Ability_Level
     * 表字段 : student_capability_evaluation..t_course_standard_s.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * T_Course_Standard主表ID
     * 表字段 : student_capability_evaluation..t_course_standard_s.FCSID
     * @mbg.generated
     */
    private Long fcsid;

    /**
     * 区间上限 默认值：0 FQZType=1  or FQZType =0
     * 表字段 : student_capability_evaluation..t_course_standard_s.FValueUL
     * @mbg.generated
     */
    private BigDecimal fvalueul;

    /**
     * 区间下限 默认值：0 FQZType=1  or FQZType =0
     * 表字段 : student_capability_evaluation..t_course_standard_s.FValueLL
     * @mbg.generated
     */
    private BigDecimal fvaluell;

    /**
     * 得分描述
     * 表字段 : student_capability_evaluation..t_course_standard_s.FMemo
     * @mbg.generated
     */
    private String fmemo;

    /**
     * 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * 表字段 : student_capability_evaluation..t_course_standard_s.FQZType
     * @mbg.generated
     */
    private Integer fqztype;

    /**
     * 布尔 默认值-1 0 = True 1 = False FQZType=0
     * 表字段 : student_capability_evaluation..t_course_standard_s.FBool
     * @mbg.generated
     */
    private Integer fbool;

    /**
     * 结果值 FQZType =1,2
     * 表字段 : student_capability_evaluation..t_course_standard_s.FSValue
     * @mbg.generated
     */
    private String fsvalue;

    /**
     * 顺序 FQZType = 1
     * 表字段 : student_capability_evaluation..t_course_standard_s.FOrder
     * @mbg.generated
     */
    private Integer forder;

    /**
     * T_Ability_Condition_Assessment_Method 表ID
     * 表字段 : student_capability_evaluation..t_course_standard_s.FAbilityCAMID
     * @mbg.generated
     */
    private Long fabilitycamid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_standard_s
     * @mbg.generated
     */
    public TCourseStandardS(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fabilityid, Long fabilitylevelid, Long fcsid, BigDecimal fvalueul, BigDecimal fvaluell, String fmemo, Integer fqztype, Integer fbool, String fsvalue, Integer forder, Long fabilitycamid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fabilityid = fabilityid;
        this.fabilitylevelid = fabilitylevelid;
        this.fcsid = fcsid;
        this.fvalueul = fvalueul;
        this.fvaluell = fvaluell;
        this.fmemo = fmemo;
        this.fqztype = fqztype;
        this.fbool = fbool;
        this.fsvalue = fsvalue;
        this.forder = forder;
        this.fabilitycamid = fabilitycamid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_standard_s
     * @mbg.generated
     */
    public TCourseStandardS() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard_s.FKeyID
     * @return student_capability_evaluation..t_course_standard_s.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard_s.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_standard_s.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard_s.FCID
     * @return student_capability_evaluation..t_course_standard_s.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard_s.FCID
     * @param fcid the value for student_capability_evaluation..t_course_standard_s.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard_s.FUID
     * @return student_capability_evaluation..t_course_standard_s.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard_s.FUID
     * @param fuid the value for student_capability_evaluation..t_course_standard_s.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard_s.FCDATE
     * @return student_capability_evaluation..t_course_standard_s.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard_s.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_standard_s.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_standard_s.FUDATE
     * @return student_capability_evaluation..t_course_standard_s.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_standard_s.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_standard_s.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_standard_s.FState
     * @return student_capability_evaluation..t_course_standard_s.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_standard_s.FState
     * @param fstate the value for student_capability_evaluation..t_course_standard_s.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID，T_Course 字段:student_capability_evaluation..t_course_standard_s.FCourseID
     * @return student_capability_evaluation..t_course_standard_s.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID，T_Course 字段:student_capability_evaluation..t_course_standard_s.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_standard_s.FCourseID, 课程ID，T_Course
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 能力ID，T_Ability 字段:student_capability_evaluation..t_course_standard_s.FAbilityID
     * @return student_capability_evaluation..t_course_standard_s.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 能力ID，T_Ability 字段:student_capability_evaluation..t_course_standard_s.FAbilityID
     * @param fabilityid the value for student_capability_evaluation..t_course_standard_s.FAbilityID, 能力ID，T_Ability
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_course_standard_s.FAbilityLevelID
     * @return student_capability_evaluation..t_course_standard_s.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 能力等级ID，T_Ability_Level 字段:student_capability_evaluation..t_course_standard_s.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_course_standard_s.FAbilityLevelID, 能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 T_Course_Standard主表ID 字段:student_capability_evaluation..t_course_standard_s.FCSID
     * @return student_capability_evaluation..t_course_standard_s.FCSID, T_Course_Standard主表ID
     * @mbg.generated
     */
    public Long getFcsid() {
        return fcsid;
    }

    /**
     * 设置 T_Course_Standard主表ID 字段:student_capability_evaluation..t_course_standard_s.FCSID
     * @param fcsid the value for student_capability_evaluation..t_course_standard_s.FCSID, T_Course_Standard主表ID
     * @mbg.generated
     */
    public void setFcsid(Long fcsid) {
        this.fcsid = fcsid;
    }

    /**
     * 获取 区间上限 默认值：0 FQZType=1  or FQZType =0 字段:student_capability_evaluation..t_course_standard_s.FValueUL
     * @return student_capability_evaluation..t_course_standard_s.FValueUL, 区间上限 默认值：0 FQZType=1  or FQZType =0
     * @mbg.generated
     */
    public BigDecimal getFvalueul() {
        return fvalueul;
    }

    /**
     * 设置 区间上限 默认值：0 FQZType=1  or FQZType =0 字段:student_capability_evaluation..t_course_standard_s.FValueUL
     * @param fvalueul the value for student_capability_evaluation..t_course_standard_s.FValueUL, 区间上限 默认值：0 FQZType=1  or FQZType =0
     * @mbg.generated
     */
    public void setFvalueul(BigDecimal fvalueul) {
        this.fvalueul = fvalueul;
    }

    /**
     * 获取 区间下限 默认值：0 FQZType=1  or FQZType =0 字段:student_capability_evaluation..t_course_standard_s.FValueLL
     * @return student_capability_evaluation..t_course_standard_s.FValueLL, 区间下限 默认值：0 FQZType=1  or FQZType =0
     * @mbg.generated
     */
    public BigDecimal getFvaluell() {
        return fvaluell;
    }

    /**
     * 设置 区间下限 默认值：0 FQZType=1  or FQZType =0 字段:student_capability_evaluation..t_course_standard_s.FValueLL
     * @param fvaluell the value for student_capability_evaluation..t_course_standard_s.FValueLL, 区间下限 默认值：0 FQZType=1  or FQZType =0
     * @mbg.generated
     */
    public void setFvaluell(BigDecimal fvaluell) {
        this.fvaluell = fvaluell;
    }

    /**
     * 获取 得分描述 字段:student_capability_evaluation..t_course_standard_s.FMemo
     * @return student_capability_evaluation..t_course_standard_s.FMemo, 得分描述
     * @mbg.generated
     */
    public String getFmemo() {
        return fmemo;
    }

    /**
     * 设置 得分描述 字段:student_capability_evaluation..t_course_standard_s.FMemo
     * @param fmemo the value for student_capability_evaluation..t_course_standard_s.FMemo, 得分描述
     * @mbg.generated
     */
    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    /**
     * 获取 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值 字段:student_capability_evaluation..t_course_standard_s.FQZType
     * @return student_capability_evaluation..t_course_standard_s.FQZType, 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * @mbg.generated
     */
    public Integer getFqztype() {
        return fqztype;
    }

    /**
     * 设置 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值 字段:student_capability_evaluation..t_course_standard_s.FQZType
     * @param fqztype the value for student_capability_evaluation..t_course_standard_s.FQZType, 取值类型 默认 -1  0 = bool 1 = 数值2 = 字符串 3 = 原值
     * @mbg.generated
     */
    public void setFqztype(Integer fqztype) {
        this.fqztype = fqztype;
    }

    /**
     * 获取 布尔 默认值-1 0 = True 1 = False FQZType=0 字段:student_capability_evaluation..t_course_standard_s.FBool
     * @return student_capability_evaluation..t_course_standard_s.FBool, 布尔 默认值-1 0 = True 1 = False FQZType=0
     * @mbg.generated
     */
    public Integer getFbool() {
        return fbool;
    }

    /**
     * 设置 布尔 默认值-1 0 = True 1 = False FQZType=0 字段:student_capability_evaluation..t_course_standard_s.FBool
     * @param fbool the value for student_capability_evaluation..t_course_standard_s.FBool, 布尔 默认值-1 0 = True 1 = False FQZType=0
     * @mbg.generated
     */
    public void setFbool(Integer fbool) {
        this.fbool = fbool;
    }

    /**
     * 获取 结果值 FQZType =1,2 字段:student_capability_evaluation..t_course_standard_s.FSValue
     * @return student_capability_evaluation..t_course_standard_s.FSValue, 结果值 FQZType =1,2
     * @mbg.generated
     */
    public String getFsvalue() {
        return fsvalue;
    }

    /**
     * 设置 结果值 FQZType =1,2 字段:student_capability_evaluation..t_course_standard_s.FSValue
     * @param fsvalue the value for student_capability_evaluation..t_course_standard_s.FSValue, 结果值 FQZType =1,2
     * @mbg.generated
     */
    public void setFsvalue(String fsvalue) {
        this.fsvalue = fsvalue == null ? null : fsvalue.trim();
    }

    /**
     * 获取 顺序 FQZType = 1 字段:student_capability_evaluation..t_course_standard_s.FOrder
     * @return student_capability_evaluation..t_course_standard_s.FOrder, 顺序 FQZType = 1
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置 顺序 FQZType = 1 字段:student_capability_evaluation..t_course_standard_s.FOrder
     * @param forder the value for student_capability_evaluation..t_course_standard_s.FOrder, 顺序 FQZType = 1
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 获取 T_Ability_Condition_Assessment_Method 表ID 字段:student_capability_evaluation..t_course_standard_s.FAbilityCAMID
     * @return student_capability_evaluation..t_course_standard_s.FAbilityCAMID, T_Ability_Condition_Assessment_Method 表ID
     * @mbg.generated
     */
    public Long getFabilitycamid() {
        return fabilitycamid;
    }

    /**
     * 设置 T_Ability_Condition_Assessment_Method 表ID 字段:student_capability_evaluation..t_course_standard_s.FAbilityCAMID
     * @param fabilitycamid the value for student_capability_evaluation..t_course_standard_s.FAbilityCAMID, T_Ability_Condition_Assessment_Method 表ID
     * @mbg.generated
     */
    public void setFabilitycamid(Long fabilitycamid) {
        this.fabilitycamid = fabilitycamid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_standard_s
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
        sb.append(", fcsid=").append(fcsid);
        sb.append(", fvalueul=").append(fvalueul);
        sb.append(", fvaluell=").append(fvaluell);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", fqztype=").append(fqztype);
        sb.append(", fbool=").append(fbool);
        sb.append(", fsvalue=").append(fsvalue);
        sb.append(", forder=").append(forder);
        sb.append(", fabilitycamid=").append(fabilitycamid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_standard_s
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
        TCourseStandardS other = (TCourseStandardS) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFcsid() == null ? other.getFcsid() == null : this.getFcsid().equals(other.getFcsid()))
            && (this.getFvalueul() == null ? other.getFvalueul() == null : this.getFvalueul().equals(other.getFvalueul()))
            && (this.getFvaluell() == null ? other.getFvaluell() == null : this.getFvaluell().equals(other.getFvaluell()))
            && (this.getFmemo() == null ? other.getFmemo() == null : this.getFmemo().equals(other.getFmemo()))
            && (this.getFqztype() == null ? other.getFqztype() == null : this.getFqztype().equals(other.getFqztype()))
            && (this.getFbool() == null ? other.getFbool() == null : this.getFbool().equals(other.getFbool()))
            && (this.getFsvalue() == null ? other.getFsvalue() == null : this.getFsvalue().equals(other.getFsvalue()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()))
            && (this.getFabilitycamid() == null ? other.getFabilitycamid() == null : this.getFabilitycamid().equals(other.getFabilitycamid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_standard_s
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
        result = prime * result + ((getFcsid() == null) ? 0 : getFcsid().hashCode());
        result = prime * result + ((getFvalueul() == null) ? 0 : getFvalueul().hashCode());
        result = prime * result + ((getFvaluell() == null) ? 0 : getFvaluell().hashCode());
        result = prime * result + ((getFmemo() == null) ? 0 : getFmemo().hashCode());
        result = prime * result + ((getFqztype() == null) ? 0 : getFqztype().hashCode());
        result = prime * result + ((getFbool() == null) ? 0 : getFbool().hashCode());
        result = prime * result + ((getFsvalue() == null) ? 0 : getFsvalue().hashCode());
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        result = prime * result + ((getFabilitycamid() == null) ? 0 : getFabilitycamid().hashCode());
        return result;
    }
}