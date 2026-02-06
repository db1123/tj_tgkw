package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_assessment_method
*/
@Table(name = "t_ability_assessment_method")
public class TAbilityAssessmentMethod implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FMethodName
     * @mbg.generated
     */
    private String fmethodname;

    /**
     * 描述
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FDescription
     * @mbg.generated
     */
    private String fdescription;

    /**
     * 考核方式的满分（动态录入） 默认100
     * 表字段 : student_capability_evaluation..t_ability_assessment_method.FMethodMaxScore
     * @mbg.generated
     */
    private Integer fmethodmaxscore;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public TAbilityAssessmentMethod(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fmethodname, String fdescription, Integer fmethodmaxscore) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fmethodname = fmethodname;
        this.fdescription = fdescription;
        this.fmethodmaxscore = fmethodmaxscore;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public TAbilityAssessmentMethod() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_assessment_method.FKeyID
     * @return student_capability_evaluation..t_ability_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_assessment_method.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_assessment_method.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_assessment_method.FCID
     * @return student_capability_evaluation..t_ability_assessment_method.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_assessment_method.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_assessment_method.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_assessment_method.FUID
     * @return student_capability_evaluation..t_ability_assessment_method.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_assessment_method.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_assessment_method.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_assessment_method.FCDATE
     * @return student_capability_evaluation..t_ability_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_assessment_method.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_assessment_method.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_assessment_method.FUDATE
     * @return student_capability_evaluation..t_ability_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_assessment_method.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_assessment_method.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_assessment_method.FState
     * @return student_capability_evaluation..t_ability_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_assessment_method.FState
     * @param fstate the value for student_capability_evaluation..t_ability_assessment_method.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_ability_assessment_method.FMethodName
     * @return student_capability_evaluation..t_ability_assessment_method.FMethodName, 名称
     * @mbg.generated
     */
    public String getFmethodname() {
        return fmethodname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_ability_assessment_method.FMethodName
     * @param fmethodname the value for student_capability_evaluation..t_ability_assessment_method.FMethodName, 名称
     * @mbg.generated
     */
    public void setFmethodname(String fmethodname) {
        this.fmethodname = fmethodname == null ? null : fmethodname.trim();
    }

    /**
     * 获取 描述 字段:student_capability_evaluation..t_ability_assessment_method.FDescription
     * @return student_capability_evaluation..t_ability_assessment_method.FDescription, 描述
     * @mbg.generated
     */
    public String getFdescription() {
        return fdescription;
    }

    /**
     * 设置 描述 字段:student_capability_evaluation..t_ability_assessment_method.FDescription
     * @param fdescription the value for student_capability_evaluation..t_ability_assessment_method.FDescription, 描述
     * @mbg.generated
     */
    public void setFdescription(String fdescription) {
        this.fdescription = fdescription == null ? null : fdescription.trim();
    }

    /**
     * 获取 考核方式的满分（动态录入） 默认100 字段:student_capability_evaluation..t_ability_assessment_method.FMethodMaxScore
     * @return student_capability_evaluation..t_ability_assessment_method.FMethodMaxScore, 考核方式的满分（动态录入） 默认100
     * @mbg.generated
     */
    public Integer getFmethodmaxscore() {
        return fmethodmaxscore;
    }

    /**
     * 设置 考核方式的满分（动态录入） 默认100 字段:student_capability_evaluation..t_ability_assessment_method.FMethodMaxScore
     * @param fmethodmaxscore the value for student_capability_evaluation..t_ability_assessment_method.FMethodMaxScore, 考核方式的满分（动态录入） 默认100
     * @mbg.generated
     */
    public void setFmethodmaxscore(Integer fmethodmaxscore) {
        this.fmethodmaxscore = fmethodmaxscore;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_assessment_method
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
        sb.append(", fmethodname=").append(fmethodname);
        sb.append(", fdescription=").append(fdescription);
        sb.append(", fmethodmaxscore=").append(fmethodmaxscore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_assessment_method
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
        TAbilityAssessmentMethod other = (TAbilityAssessmentMethod) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFmethodname() == null ? other.getFmethodname() == null : this.getFmethodname().equals(other.getFmethodname()))
            && (this.getFdescription() == null ? other.getFdescription() == null : this.getFdescription().equals(other.getFdescription()))
            && (this.getFmethodmaxscore() == null ? other.getFmethodmaxscore() == null : this.getFmethodmaxscore().equals(other.getFmethodmaxscore()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_assessment_method
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
        result = prime * result + ((getFmethodname() == null) ? 0 : getFmethodname().hashCode());
        result = prime * result + ((getFdescription() == null) ? 0 : getFdescription().hashCode());
        result = prime * result + ((getFmethodmaxscore() == null) ? 0 : getFmethodmaxscore().hashCode());
        return result;
    }
}