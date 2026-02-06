package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_student_score_tb
*/
@Table(name = "t_course_student_score_tb")
public class TCourseStudentScoreTb implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 课程报名ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FCourseStudentID
     * @mbg.generated
     */
    private Long fcoursestudentid;

    /**
     * 能力等级ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 考核条件ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FConditionID
     * @mbg.generated
     */
    private Long fconditionid;

    /**
     * 条件与考核方式关系ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FACAMID
     * @mbg.generated
     */
    private Long facamid;

    /**
     * 对应的标准子表ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FCourseStandardsID
     * @mbg.generated
     */
    private Long fcoursestandardsid;

    /**
     * 填写的内容
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FContent
     * @mbg.generated
     */
    private String fcontent;

    /**
     * 得分
     * 表字段 : student_capability_evaluation..t_course_student_score_tb.FScore
     * @mbg.generated
     */
    private Float fscore;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public TCourseStudentScoreTb(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fcoursestudentid, Long fabilitylevelid, Long fconditionid, Long facamid, Long fcoursestandardsid, String fcontent, Float fscore) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fcoursestudentid = fcoursestudentid;
        this.fabilitylevelid = fabilitylevelid;
        this.fconditionid = fconditionid;
        this.facamid = facamid;
        this.fcoursestandardsid = fcoursestandardsid;
        this.fcontent = fcontent;
        this.fscore = fscore;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public TCourseStudentScoreTb() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb.FKeyID
     * @return student_capability_evaluation..t_course_student_score_tb.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_student_score_tb.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb.FCID
     * @return student_capability_evaluation..t_course_student_score_tb.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb.FCID
     * @param fcid the value for student_capability_evaluation..t_course_student_score_tb.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb.FUID
     * @return student_capability_evaluation..t_course_student_score_tb.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb.FUID
     * @param fuid the value for student_capability_evaluation..t_course_student_score_tb.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb.FCDATE
     * @return student_capability_evaluation..t_course_student_score_tb.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_student_score_tb.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb.FUDATE
     * @return student_capability_evaluation..t_course_student_score_tb.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_student_score_tb.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score_tb.FState
     * @return student_capability_evaluation..t_course_student_score_tb.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score_tb.FState
     * @param fstate the value for student_capability_evaluation..t_course_student_score_tb.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseID
     * @return student_capability_evaluation..t_course_student_score_tb.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_student_score_tb.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 课程报名ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseStudentID
     * @return student_capability_evaluation..t_course_student_score_tb.FCourseStudentID, 课程报名ID
     * @mbg.generated
     */
    public Long getFcoursestudentid() {
        return fcoursestudentid;
    }

    /**
     * 设置 课程报名ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseStudentID
     * @param fcoursestudentid the value for student_capability_evaluation..t_course_student_score_tb.FCourseStudentID, 课程报名ID
     * @mbg.generated
     */
    public void setFcoursestudentid(Long fcoursestudentid) {
        this.fcoursestudentid = fcoursestudentid;
    }

    /**
     * 获取 能力等级ID 字段:student_capability_evaluation..t_course_student_score_tb.FAbilityLevelID
     * @return student_capability_evaluation..t_course_student_score_tb.FAbilityLevelID, 能力等级ID
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 能力等级ID 字段:student_capability_evaluation..t_course_student_score_tb.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_course_student_score_tb.FAbilityLevelID, 能力等级ID
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 考核条件ID 字段:student_capability_evaluation..t_course_student_score_tb.FConditionID
     * @return student_capability_evaluation..t_course_student_score_tb.FConditionID, 考核条件ID
     * @mbg.generated
     */
    public Long getFconditionid() {
        return fconditionid;
    }

    /**
     * 设置 考核条件ID 字段:student_capability_evaluation..t_course_student_score_tb.FConditionID
     * @param fconditionid the value for student_capability_evaluation..t_course_student_score_tb.FConditionID, 考核条件ID
     * @mbg.generated
     */
    public void setFconditionid(Long fconditionid) {
        this.fconditionid = fconditionid;
    }

    /**
     * 获取 条件与考核方式关系ID 字段:student_capability_evaluation..t_course_student_score_tb.FACAMID
     * @return student_capability_evaluation..t_course_student_score_tb.FACAMID, 条件与考核方式关系ID
     * @mbg.generated
     */
    public Long getFacamid() {
        return facamid;
    }

    /**
     * 设置 条件与考核方式关系ID 字段:student_capability_evaluation..t_course_student_score_tb.FACAMID
     * @param facamid the value for student_capability_evaluation..t_course_student_score_tb.FACAMID, 条件与考核方式关系ID
     * @mbg.generated
     */
    public void setFacamid(Long facamid) {
        this.facamid = facamid;
    }

    /**
     * 获取 对应的标准子表ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseStandardsID
     * @return student_capability_evaluation..t_course_student_score_tb.FCourseStandardsID, 对应的标准子表ID
     * @mbg.generated
     */
    public Long getFcoursestandardsid() {
        return fcoursestandardsid;
    }

    /**
     * 设置 对应的标准子表ID 字段:student_capability_evaluation..t_course_student_score_tb.FCourseStandardsID
     * @param fcoursestandardsid the value for student_capability_evaluation..t_course_student_score_tb.FCourseStandardsID, 对应的标准子表ID
     * @mbg.generated
     */
    public void setFcoursestandardsid(Long fcoursestandardsid) {
        this.fcoursestandardsid = fcoursestandardsid;
    }

    /**
     * 获取 填写的内容 字段:student_capability_evaluation..t_course_student_score_tb.FContent
     * @return student_capability_evaluation..t_course_student_score_tb.FContent, 填写的内容
     * @mbg.generated
     */
    public String getFcontent() {
        return fcontent;
    }

    /**
     * 设置 填写的内容 字段:student_capability_evaluation..t_course_student_score_tb.FContent
     * @param fcontent the value for student_capability_evaluation..t_course_student_score_tb.FContent, 填写的内容
     * @mbg.generated
     */
    public void setFcontent(String fcontent) {
        this.fcontent = fcontent == null ? null : fcontent.trim();
    }

    /**
     * 获取 得分 字段:student_capability_evaluation..t_course_student_score_tb.FScore
     * @return student_capability_evaluation..t_course_student_score_tb.FScore, 得分
     * @mbg.generated
     */
    public Float getFscore() {
        return fscore;
    }

    /**
     * 设置 得分 字段:student_capability_evaluation..t_course_student_score_tb.FScore
     * @param fscore the value for student_capability_evaluation..t_course_student_score_tb.FScore, 得分
     * @mbg.generated
     */
    public void setFscore(Float fscore) {
        this.fscore = fscore;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_student_score_tb
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
        sb.append(", fcoursestudentid=").append(fcoursestudentid);
        sb.append(", fabilitylevelid=").append(fabilitylevelid);
        sb.append(", fconditionid=").append(fconditionid);
        sb.append(", facamid=").append(facamid);
        sb.append(", fcoursestandardsid=").append(fcoursestandardsid);
        sb.append(", fcontent=").append(fcontent);
        sb.append(", fscore=").append(fscore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_student_score_tb
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
        TCourseStudentScoreTb other = (TCourseStudentScoreTb) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFcoursestudentid() == null ? other.getFcoursestudentid() == null : this.getFcoursestudentid().equals(other.getFcoursestudentid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFconditionid() == null ? other.getFconditionid() == null : this.getFconditionid().equals(other.getFconditionid()))
            && (this.getFacamid() == null ? other.getFacamid() == null : this.getFacamid().equals(other.getFacamid()))
            && (this.getFcoursestandardsid() == null ? other.getFcoursestandardsid() == null : this.getFcoursestandardsid().equals(other.getFcoursestandardsid()))
            && (this.getFcontent() == null ? other.getFcontent() == null : this.getFcontent().equals(other.getFcontent()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_student_score_tb
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
        result = prime * result + ((getFcoursestudentid() == null) ? 0 : getFcoursestudentid().hashCode());
        result = prime * result + ((getFabilitylevelid() == null) ? 0 : getFabilitylevelid().hashCode());
        result = prime * result + ((getFconditionid() == null) ? 0 : getFconditionid().hashCode());
        result = prime * result + ((getFacamid() == null) ? 0 : getFacamid().hashCode());
        result = prime * result + ((getFcoursestandardsid() == null) ? 0 : getFcoursestandardsid().hashCode());
        result = prime * result + ((getFcontent() == null) ? 0 : getFcontent().hashCode());
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        return result;
    }
}