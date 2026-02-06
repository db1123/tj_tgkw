package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_condition_s
*/
@Table(name = "t_ability_condition_s")
public class TAbilityConditionS implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Ability_Condition表FKeyID
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FTACID
     * @mbg.generated
     */
    private Long ftacid;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 描述
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 父节点
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FPID
     * @mbg.generated
     */
    private Long fpid;

    /**
     * 叶子节点
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FIsleaf
     * @mbg.generated
     */
    private Integer fisleaf;

    /**
     * 路径
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FPath
     * @mbg.generated
     */
    private String fpath;

    /**
     * 满足条件的分数
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FConditionScore
     * @mbg.generated
     */
    private Float fconditionscore;

    /**
     * 等级表ID
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FALID
     * @mbg.generated
     */
    private Long falid;

    /**
     * 权重
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FMethodWeight
     * @mbg.generated
     */
    private Float fmethodweight;

    /**
     * 层级，默认2 第一层为T_Ability_Condition
     * 表字段 : student_capability_evaluation..t_ability_condition_s.FDiv
     * @mbg.generated
     */
    private Integer fdiv;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition_s
     * @mbg.generated
     */
    public TAbilityConditionS(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftacid, String fname, String fnote, Long fpid, Integer fisleaf, String fpath, Float fconditionscore, Long falid, Float fmethodweight, Integer fdiv) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftacid = ftacid;
        this.fname = fname;
        this.fnote = fnote;
        this.fpid = fpid;
        this.fisleaf = fisleaf;
        this.fpath = fpath;
        this.fconditionscore = fconditionscore;
        this.falid = falid;
        this.fmethodweight = fmethodweight;
        this.fdiv = fdiv;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_condition_s
     * @mbg.generated
     */
    public TAbilityConditionS() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_s.FKeyID
     * @return student_capability_evaluation..t_ability_condition_s.FKeyID,
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_s.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_condition_s.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_s.FCID
     * @return student_capability_evaluation..t_ability_condition_s.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_s.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_condition_s.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_s.FUID
     * @return student_capability_evaluation..t_ability_condition_s.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_s.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_condition_s.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_s.FCDATE
     * @return student_capability_evaluation..t_ability_condition_s.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_s.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_condition_s.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_condition_s.FUDATE
     * @return student_capability_evaluation..t_ability_condition_s.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_condition_s.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_condition_s.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition_s.FState
     * @return student_capability_evaluation..t_ability_condition_s.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_condition_s.FState
     * @param fstate the value for student_capability_evaluation..t_ability_condition_s.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Ability_Condition表FKeyID 字段:student_capability_evaluation..t_ability_condition_s.FTACID
     * @return student_capability_evaluation..t_ability_condition_s.FTACID, T_Ability_Condition表FKeyID
     * @mbg.generated
     */
    public Long getFtacid() {
        return ftacid;
    }

    /**
     * 设置 T_Ability_Condition表FKeyID 字段:student_capability_evaluation..t_ability_condition_s.FTACID
     * @param ftacid the value for student_capability_evaluation..t_ability_condition_s.FTACID, T_Ability_Condition表FKeyID
     * @mbg.generated
     */
    public void setFtacid(Long ftacid) {
        this.ftacid = ftacid;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_ability_condition_s.FName
     * @return student_capability_evaluation..t_ability_condition_s.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_ability_condition_s.FName
     * @param fname the value for student_capability_evaluation..t_ability_condition_s.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 描述 字段:student_capability_evaluation..t_ability_condition_s.FNote
     * @return student_capability_evaluation..t_ability_condition_s.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:student_capability_evaluation..t_ability_condition_s.FNote
     * @param fnote the value for student_capability_evaluation..t_ability_condition_s.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 父节点 字段:student_capability_evaluation..t_ability_condition_s.FPID
     * @return student_capability_evaluation..t_ability_condition_s.FPID, 父节点
     * @mbg.generated
     */
    public Long getFpid() {
        return fpid;
    }

    /**
     * 设置 父节点 字段:student_capability_evaluation..t_ability_condition_s.FPID
     * @param fpid the value for student_capability_evaluation..t_ability_condition_s.FPID, 父节点
     * @mbg.generated
     */
    public void setFpid(Long fpid) {
        this.fpid = fpid;
    }

    /**
     * 获取 叶子节点 字段:student_capability_evaluation..t_ability_condition_s.FIsleaf
     * @return student_capability_evaluation..t_ability_condition_s.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public Integer getFisleaf() {
        return fisleaf;
    }

    /**
     * 设置 叶子节点 字段:student_capability_evaluation..t_ability_condition_s.FIsleaf
     * @param fisleaf the value for student_capability_evaluation..t_ability_condition_s.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public void setFisleaf(Integer fisleaf) {
        this.fisleaf = fisleaf;
    }

    /**
     * 获取 路径 字段:student_capability_evaluation..t_ability_condition_s.FPath
     * @return student_capability_evaluation..t_ability_condition_s.FPath, 路径
     * @mbg.generated
     */
    public String getFpath() {
        return fpath;
    }

    /**
     * 设置 路径 字段:student_capability_evaluation..t_ability_condition_s.FPath
     * @param fpath the value for student_capability_evaluation..t_ability_condition_s.FPath, 路径
     * @mbg.generated
     */
    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }

    /**
     * 获取 满足条件的分数 字段:student_capability_evaluation..t_ability_condition_s.FConditionScore
     * @return student_capability_evaluation..t_ability_condition_s.FConditionScore, 满足条件的分数
     * @mbg.generated
     */
    public Float getFconditionscore() {
        return fconditionscore;
    }

    /**
     * 设置 满足条件的分数 字段:student_capability_evaluation..t_ability_condition_s.FConditionScore
     * @param fconditionscore the value for student_capability_evaluation..t_ability_condition_s.FConditionScore, 满足条件的分数
     * @mbg.generated
     */
    public void setFconditionscore(Float fconditionscore) {
        this.fconditionscore = fconditionscore;
    }

    /**
     * 获取 等级表ID 字段:student_capability_evaluation..t_ability_condition_s.FALID
     * @return student_capability_evaluation..t_ability_condition_s.FALID, 等级表ID
     * @mbg.generated
     */
    public Long getFalid() {
        return falid;
    }

    /**
     * 设置 等级表ID 字段:student_capability_evaluation..t_ability_condition_s.FALID
     * @param falid the value for student_capability_evaluation..t_ability_condition_s.FALID, 等级表ID
     * @mbg.generated
     */
    public void setFalid(Long falid) {
        this.falid = falid;
    }

    /**
     * 获取 权重 字段:student_capability_evaluation..t_ability_condition_s.FMethodWeight
     * @return student_capability_evaluation..t_ability_condition_s.FMethodWeight, 权重
     * @mbg.generated
     */
    public Float getFmethodweight() {
        return fmethodweight;
    }

    /**
     * 设置 权重 字段:student_capability_evaluation..t_ability_condition_s.FMethodWeight
     * @param fmethodweight the value for student_capability_evaluation..t_ability_condition_s.FMethodWeight, 权重
     * @mbg.generated
     */
    public void setFmethodweight(Float fmethodweight) {
        this.fmethodweight = fmethodweight;
    }

    /**
     * 获取 层级，默认2 第一层为T_Ability_Condition 字段:student_capability_evaluation..t_ability_condition_s.FDiv
     * @return student_capability_evaluation..t_ability_condition_s.FDiv, 层级，默认2 第一层为T_Ability_Condition
     * @mbg.generated
     */
    public Integer getFdiv() {
        return fdiv;
    }

    /**
     * 设置 层级，默认2 第一层为T_Ability_Condition 字段:student_capability_evaluation..t_ability_condition_s.FDiv
     * @param fdiv the value for student_capability_evaluation..t_ability_condition_s.FDiv, 层级，默认2 第一层为T_Ability_Condition
     * @mbg.generated
     */
    public void setFdiv(Integer fdiv) {
        this.fdiv = fdiv;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_condition_s
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
        sb.append(", ftacid=").append(ftacid);
        sb.append(", fname=").append(fname);
        sb.append(", fnote=").append(fnote);
        sb.append(", fpid=").append(fpid);
        sb.append(", fisleaf=").append(fisleaf);
        sb.append(", fpath=").append(fpath);
        sb.append(", fconditionscore=").append(fconditionscore);
        sb.append(", falid=").append(falid);
        sb.append(", fmethodweight=").append(fmethodweight);
        sb.append(", fdiv=").append(fdiv);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_condition_s
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
        TAbilityConditionS other = (TAbilityConditionS) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtacid() == null ? other.getFtacid() == null : this.getFtacid().equals(other.getFtacid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFpid() == null ? other.getFpid() == null : this.getFpid().equals(other.getFpid()))
            && (this.getFisleaf() == null ? other.getFisleaf() == null : this.getFisleaf().equals(other.getFisleaf()))
            && (this.getFpath() == null ? other.getFpath() == null : this.getFpath().equals(other.getFpath()))
            && (this.getFconditionscore() == null ? other.getFconditionscore() == null : this.getFconditionscore().equals(other.getFconditionscore()))
            && (this.getFalid() == null ? other.getFalid() == null : this.getFalid().equals(other.getFalid()))
            && (this.getFmethodweight() == null ? other.getFmethodweight() == null : this.getFmethodweight().equals(other.getFmethodweight()))
            && (this.getFdiv() == null ? other.getFdiv() == null : this.getFdiv().equals(other.getFdiv()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_condition_s
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
        result = prime * result + ((getFtacid() == null) ? 0 : getFtacid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFpid() == null) ? 0 : getFpid().hashCode());
        result = prime * result + ((getFisleaf() == null) ? 0 : getFisleaf().hashCode());
        result = prime * result + ((getFpath() == null) ? 0 : getFpath().hashCode());
        result = prime * result + ((getFconditionscore() == null) ? 0 : getFconditionscore().hashCode());
        result = prime * result + ((getFalid() == null) ? 0 : getFalid().hashCode());
        result = prime * result + ((getFmethodweight() == null) ? 0 : getFmethodweight().hashCode());
        result = prime * result + ((getFdiv() == null) ? 0 : getFdiv().hashCode());
        return result;
    }
}