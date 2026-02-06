package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_student_score_tb_tree
*/
@Table(name = "t_course_student_score_tb_tree")
public class TCourseStudentScoreTbTree implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 课程报名ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FCourseStudentID
     * @mbg.generated
     */
    private Long fcoursestudentid;

    /**
     * 能力等级ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 考核条件ID 父节点
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FAbilityTreeID
     * @mbg.generated
     */
    private Long fabilitytreeid;

    /**
     * 父节点ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FPID
     * @mbg.generated
     */
    private Long fpid;

    /**
     * 权重
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FMethodWeight
     * @mbg.generated
     */
    private Float fmethodweight;

    /**
     * 层数
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FDIV
     * @mbg.generated
     */
    private Integer fdiv;

    /**
     * 类型1=能力 2=条件3=条件4=考核方式
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FNodeType
     * @mbg.generated
     */
    private Integer fnodetype;

    /**
     * 用户填写的内容
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FContent
     * @mbg.generated
     */
    private String fcontent;

    /**
     * 用户填写的内容可获得的分数
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FContentScore
     * @mbg.generated
     */
    private Float fcontentscore;

    /**
     * 得分，默认0，根据标准填写的内容获得的分数*权重的值
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FScore
     * @mbg.generated
     */
    private Float fscore;

    /**
     * 计算状态 1=未计算 2=已计算
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FJSState
     * @mbg.generated
     */
    private Integer fjsstate;

    /**
     * 计算后结果值
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FJSScore
     * @mbg.generated
     */
    private Float fjsscore;

    /**
     * 第几组结果，默认1
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FGroypNum
     * @mbg.generated
     */
    private Integer fgroypnum;

    /**
     * 根节点ID
     * 表字段 : student_capability_evaluation..t_course_student_score_tb_tree.FGPID
     * @mbg.generated
     */
    private Long fgpid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score_tb_tree
     * @mbg.generated
     */
    public TCourseStudentScoreTbTree(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, Long fcoursestudentid, Long fabilitylevelid, Long fabilitytreeid, Long fpid, Float fmethodweight, Integer fdiv, Integer fnodetype, String fcontent, Float fcontentscore, Float fscore, Integer fjsstate, Float fjsscore, Integer fgroypnum, Long fgpid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.fcoursestudentid = fcoursestudentid;
        this.fabilitylevelid = fabilitylevelid;
        this.fabilitytreeid = fabilitytreeid;
        this.fpid = fpid;
        this.fmethodweight = fmethodweight;
        this.fdiv = fdiv;
        this.fnodetype = fnodetype;
        this.fcontent = fcontent;
        this.fcontentscore = fcontentscore;
        this.fscore = fscore;
        this.fjsstate = fjsstate;
        this.fjsscore = fjsscore;
        this.fgroypnum = fgroypnum;
        this.fgpid = fgpid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score_tb_tree
     * @mbg.generated
     */
    public TCourseStudentScoreTbTree() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb_tree.FKeyID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb_tree.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_student_score_tb_tree.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb_tree.FCID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb_tree.FCID
     * @param fcid the value for student_capability_evaluation..t_course_student_score_tb_tree.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb_tree.FUID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb_tree.FUID
     * @param fuid the value for student_capability_evaluation..t_course_student_score_tb_tree.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb_tree.FCDATE
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb_tree.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_student_score_tb_tree.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_student_score_tb_tree.FUDATE
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_student_score_tb_tree.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_student_score_tb_tree.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score_tb_tree.FState
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_student_score_tb_tree.FState
     * @param fstate the value for student_capability_evaluation..t_course_student_score_tb_tree.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FCourseID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_student_score_tb_tree.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 课程报名ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FCourseStudentID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FCourseStudentID, 课程报名ID
     * @mbg.generated
     */
    public Long getFcoursestudentid() {
        return fcoursestudentid;
    }

    /**
     * 设置 课程报名ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FCourseStudentID
     * @param fcoursestudentid the value for student_capability_evaluation..t_course_student_score_tb_tree.FCourseStudentID, 课程报名ID
     * @mbg.generated
     */
    public void setFcoursestudentid(Long fcoursestudentid) {
        this.fcoursestudentid = fcoursestudentid;
    }

    /**
     * 获取 能力等级ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FAbilityLevelID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FAbilityLevelID, 能力等级ID
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 能力等级ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FAbilityLevelID
     * @param fabilitylevelid the value for student_capability_evaluation..t_course_student_score_tb_tree.FAbilityLevelID, 能力等级ID
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 考核条件ID 父节点 字段:student_capability_evaluation..t_course_student_score_tb_tree.FAbilityTreeID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FAbilityTreeID, 考核条件ID 父节点
     * @mbg.generated
     */
    public Long getFabilitytreeid() {
        return fabilitytreeid;
    }

    /**
     * 设置 考核条件ID 父节点 字段:student_capability_evaluation..t_course_student_score_tb_tree.FAbilityTreeID
     * @param fabilitytreeid the value for student_capability_evaluation..t_course_student_score_tb_tree.FAbilityTreeID, 考核条件ID 父节点
     * @mbg.generated
     */
    public void setFabilitytreeid(Long fabilitytreeid) {
        this.fabilitytreeid = fabilitytreeid;
    }

    /**
     * 获取 父节点ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FPID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FPID, 父节点ID
     * @mbg.generated
     */
    public Long getFpid() {
        return fpid;
    }

    /**
     * 设置 父节点ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FPID
     * @param fpid the value for student_capability_evaluation..t_course_student_score_tb_tree.FPID, 父节点ID
     * @mbg.generated
     */
    public void setFpid(Long fpid) {
        this.fpid = fpid;
    }

    /**
     * 获取 权重 字段:student_capability_evaluation..t_course_student_score_tb_tree.FMethodWeight
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FMethodWeight, 权重
     * @mbg.generated
     */
    public Float getFmethodweight() {
        return fmethodweight;
    }

    /**
     * 设置 权重 字段:student_capability_evaluation..t_course_student_score_tb_tree.FMethodWeight
     * @param fmethodweight the value for student_capability_evaluation..t_course_student_score_tb_tree.FMethodWeight, 权重
     * @mbg.generated
     */
    public void setFmethodweight(Float fmethodweight) {
        this.fmethodweight = fmethodweight;
    }

    /**
     * 获取 层数 字段:student_capability_evaluation..t_course_student_score_tb_tree.FDIV
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FDIV, 层数
     * @mbg.generated
     */
    public Integer getFdiv() {
        return fdiv;
    }

    /**
     * 设置 层数 字段:student_capability_evaluation..t_course_student_score_tb_tree.FDIV
     * @param fdiv the value for student_capability_evaluation..t_course_student_score_tb_tree.FDIV, 层数
     * @mbg.generated
     */
    public void setFdiv(Integer fdiv) {
        this.fdiv = fdiv;
    }

    /**
     * 获取 类型1=能力 2=条件3=条件4=考核方式 字段:student_capability_evaluation..t_course_student_score_tb_tree.FNodeType
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FNodeType, 类型1=能力 2=条件3=条件4=考核方式
     * @mbg.generated
     */
    public Integer getFnodetype() {
        return fnodetype;
    }

    /**
     * 设置 类型1=能力 2=条件3=条件4=考核方式 字段:student_capability_evaluation..t_course_student_score_tb_tree.FNodeType
     * @param fnodetype the value for student_capability_evaluation..t_course_student_score_tb_tree.FNodeType, 类型1=能力 2=条件3=条件4=考核方式
     * @mbg.generated
     */
    public void setFnodetype(Integer fnodetype) {
        this.fnodetype = fnodetype;
    }

    /**
     * 获取 用户填写的内容 字段:student_capability_evaluation..t_course_student_score_tb_tree.FContent
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FContent, 用户填写的内容
     * @mbg.generated
     */
    public String getFcontent() {
        return fcontent;
    }

    /**
     * 设置 用户填写的内容 字段:student_capability_evaluation..t_course_student_score_tb_tree.FContent
     * @param fcontent the value for student_capability_evaluation..t_course_student_score_tb_tree.FContent, 用户填写的内容
     * @mbg.generated
     */
    public void setFcontent(String fcontent) {
        this.fcontent = fcontent == null ? null : fcontent.trim();
    }

    /**
     * 获取 用户填写的内容可获得的分数 字段:student_capability_evaluation..t_course_student_score_tb_tree.FContentScore
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FContentScore, 用户填写的内容可获得的分数
     * @mbg.generated
     */
    public Float getFcontentscore() {
        return fcontentscore;
    }

    /**
     * 设置 用户填写的内容可获得的分数 字段:student_capability_evaluation..t_course_student_score_tb_tree.FContentScore
     * @param fcontentscore the value for student_capability_evaluation..t_course_student_score_tb_tree.FContentScore, 用户填写的内容可获得的分数
     * @mbg.generated
     */
    public void setFcontentscore(Float fcontentscore) {
        this.fcontentscore = fcontentscore;
    }

    /**
     * 获取 得分，默认0，根据标准填写的内容获得的分数*权重的值 字段:student_capability_evaluation..t_course_student_score_tb_tree.FScore
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FScore, 得分，默认0，根据标准填写的内容获得的分数*权重的值
     * @mbg.generated
     */
    public Float getFscore() {
        return fscore;
    }

    /**
     * 设置 得分，默认0，根据标准填写的内容获得的分数*权重的值 字段:student_capability_evaluation..t_course_student_score_tb_tree.FScore
     * @param fscore the value for student_capability_evaluation..t_course_student_score_tb_tree.FScore, 得分，默认0，根据标准填写的内容获得的分数*权重的值
     * @mbg.generated
     */
    public void setFscore(Float fscore) {
        this.fscore = fscore;
    }

    /**
     * 获取 计算状态 1=未计算 2=已计算 字段:student_capability_evaluation..t_course_student_score_tb_tree.FJSState
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FJSState, 计算状态 1=未计算 2=已计算
     * @mbg.generated
     */
    public Integer getFjsstate() {
        return fjsstate;
    }

    /**
     * 设置 计算状态 1=未计算 2=已计算 字段:student_capability_evaluation..t_course_student_score_tb_tree.FJSState
     * @param fjsstate the value for student_capability_evaluation..t_course_student_score_tb_tree.FJSState, 计算状态 1=未计算 2=已计算
     * @mbg.generated
     */
    public void setFjsstate(Integer fjsstate) {
        this.fjsstate = fjsstate;
    }

    /**
     * 获取 计算后结果值 字段:student_capability_evaluation..t_course_student_score_tb_tree.FJSScore
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FJSScore, 计算后结果值
     * @mbg.generated
     */
    public Float getFjsscore() {
        return fjsscore;
    }

    /**
     * 设置 计算后结果值 字段:student_capability_evaluation..t_course_student_score_tb_tree.FJSScore
     * @param fjsscore the value for student_capability_evaluation..t_course_student_score_tb_tree.FJSScore, 计算后结果值
     * @mbg.generated
     */
    public void setFjsscore(Float fjsscore) {
        this.fjsscore = fjsscore;
    }

    /**
     * 获取 第几组结果，默认1 字段:student_capability_evaluation..t_course_student_score_tb_tree.FGroypNum
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FGroypNum, 第几组结果，默认1
     * @mbg.generated
     */
    public Integer getFgroypnum() {
        return fgroypnum;
    }

    /**
     * 设置 第几组结果，默认1 字段:student_capability_evaluation..t_course_student_score_tb_tree.FGroypNum
     * @param fgroypnum the value for student_capability_evaluation..t_course_student_score_tb_tree.FGroypNum, 第几组结果，默认1
     * @mbg.generated
     */
    public void setFgroypnum(Integer fgroypnum) {
        this.fgroypnum = fgroypnum;
    }

    /**
     * 获取 根节点ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FGPID
     * @return student_capability_evaluation..t_course_student_score_tb_tree.FGPID, 根节点ID
     * @mbg.generated
     */
    public Long getFgpid() {
        return fgpid;
    }

    /**
     * 设置 根节点ID 字段:student_capability_evaluation..t_course_student_score_tb_tree.FGPID
     * @param fgpid the value for student_capability_evaluation..t_course_student_score_tb_tree.FGPID, 根节点ID
     * @mbg.generated
     */
    public void setFgpid(Long fgpid) {
        this.fgpid = fgpid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_student_score_tb_tree
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
        sb.append(", fabilitytreeid=").append(fabilitytreeid);
        sb.append(", fpid=").append(fpid);
        sb.append(", fmethodweight=").append(fmethodweight);
        sb.append(", fdiv=").append(fdiv);
        sb.append(", fnodetype=").append(fnodetype);
        sb.append(", fcontent=").append(fcontent);
        sb.append(", fcontentscore=").append(fcontentscore);
        sb.append(", fscore=").append(fscore);
        sb.append(", fjsstate=").append(fjsstate);
        sb.append(", fjsscore=").append(fjsscore);
        sb.append(", fgroypnum=").append(fgroypnum);
        sb.append(", fgpid=").append(fgpid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_student_score_tb_tree
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
        TCourseStudentScoreTbTree other = (TCourseStudentScoreTbTree) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFcoursestudentid() == null ? other.getFcoursestudentid() == null : this.getFcoursestudentid().equals(other.getFcoursestudentid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFabilitytreeid() == null ? other.getFabilitytreeid() == null : this.getFabilitytreeid().equals(other.getFabilitytreeid()))
            && (this.getFpid() == null ? other.getFpid() == null : this.getFpid().equals(other.getFpid()))
            && (this.getFmethodweight() == null ? other.getFmethodweight() == null : this.getFmethodweight().equals(other.getFmethodweight()))
            && (this.getFdiv() == null ? other.getFdiv() == null : this.getFdiv().equals(other.getFdiv()))
            && (this.getFnodetype() == null ? other.getFnodetype() == null : this.getFnodetype().equals(other.getFnodetype()))
            && (this.getFcontent() == null ? other.getFcontent() == null : this.getFcontent().equals(other.getFcontent()))
            && (this.getFcontentscore() == null ? other.getFcontentscore() == null : this.getFcontentscore().equals(other.getFcontentscore()))
            && (this.getFscore() == null ? other.getFscore() == null : this.getFscore().equals(other.getFscore()))
            && (this.getFjsstate() == null ? other.getFjsstate() == null : this.getFjsstate().equals(other.getFjsstate()))
            && (this.getFjsscore() == null ? other.getFjsscore() == null : this.getFjsscore().equals(other.getFjsscore()))
            && (this.getFgroypnum() == null ? other.getFgroypnum() == null : this.getFgroypnum().equals(other.getFgroypnum()))
            && (this.getFgpid() == null ? other.getFgpid() == null : this.getFgpid().equals(other.getFgpid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_student_score_tb_tree
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
        result = prime * result + ((getFabilitytreeid() == null) ? 0 : getFabilitytreeid().hashCode());
        result = prime * result + ((getFpid() == null) ? 0 : getFpid().hashCode());
        result = prime * result + ((getFmethodweight() == null) ? 0 : getFmethodweight().hashCode());
        result = prime * result + ((getFdiv() == null) ? 0 : getFdiv().hashCode());
        result = prime * result + ((getFnodetype() == null) ? 0 : getFnodetype().hashCode());
        result = prime * result + ((getFcontent() == null) ? 0 : getFcontent().hashCode());
        result = prime * result + ((getFcontentscore() == null) ? 0 : getFcontentscore().hashCode());
        result = prime * result + ((getFscore() == null) ? 0 : getFscore().hashCode());
        result = prime * result + ((getFjsstate() == null) ? 0 : getFjsstate().hashCode());
        result = prime * result + ((getFjsscore() == null) ? 0 : getFjsscore().hashCode());
        result = prime * result + ((getFgroypnum() == null) ? 0 : getFgroypnum().hashCode());
        result = prime * result + ((getFgpid() == null) ? 0 : getFgpid().hashCode());
        return result;
    }
}