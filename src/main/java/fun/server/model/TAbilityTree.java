package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_ability_tree
*/
@Table(name = "t_ability_tree")
public class TAbilityTree implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_tree.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_tree.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_tree.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_tree.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_ability_tree.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_ability_tree.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 能力类型，T_Ability_Type
     * 表字段 : student_capability_evaluation..t_ability_tree.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_ability_tree.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 描述
     * 表字段 : student_capability_evaluation..t_ability_tree.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 父节点
     * 表字段 : student_capability_evaluation..t_ability_tree.FPID
     * @mbg.generated
     */
    private Long fpid;

    /**
     * 叶子节点
     * 表字段 : student_capability_evaluation..t_ability_tree.FIsleaf
     * @mbg.generated
     */
    private Integer fisleaf;

    /**
     * 路径
     * 表字段 : student_capability_evaluation..t_ability_tree.FPath
     * @mbg.generated
     */
    private String fpath;

    /**
     * 权重，FNodeType =3 、4时会有
     * 表字段 : student_capability_evaluation..t_ability_tree.FMethodWeight
     * @mbg.generated
     */
    private Float fmethodweight;

    /**
     * 层级 默认1
     * 表字段 : student_capability_evaluation..t_ability_tree.FDiv
     * @mbg.generated
     */
    private Integer fdiv;

    /**
     * 1-能力 2-等级 3-条件 4-考核方式
     * 表字段 : student_capability_evaluation..t_ability_tree.FNodeType
     * @mbg.generated
     */
    private Integer fnodetype;

    /**
     * 难度积分，FNodeType =2时会有
     * 表字段 : student_capability_evaluation..t_ability_tree.FPoints
     * @mbg.generated
     */
    private Float fpoints;

    /**
     * 满足条件的分数,FNodeType =3
     * 表字段 : student_capability_evaluation..t_ability_tree.FConditionScore
     * @mbg.generated
     */
    private Integer fconditionscore;

    /**
     * 根节点，及FPID=-1的，只有子级会有
     * 表字段 : student_capability_evaluation..t_ability_tree.FGPID
     * @mbg.generated
     */
    private Long fgpid;

    /**
     * FNodeType = 2,等级区间最小值
     * 表字段 : student_capability_evaluation..t_ability_tree.FScoreMin
     * @mbg.generated
     */
    private Integer fscoremin;

    /**
     * FNodeType = 2,等级区间最大值
     * 表字段 : student_capability_evaluation..t_ability_tree.FScoreMax
     * @mbg.generated
     */
    private Integer fscoremax;

    /**
     * 1=再用2=删除
     * 表字段 : student_capability_evaluation..t_ability_tree.FDel
     * @mbg.generated
     */
    private Integer fdel;

    /**
     * 考核方式ID，FNodeType = 4
     * 表字段 : student_capability_evaluation..t_ability_tree.FMethodID
     * @mbg.generated
     */
    private Long fmethodid;

    /**
     * 能力水平条件表ID，FNodeType = 3,默认-1
     * 表字段 : student_capability_evaluation..t_ability_tree.FALCID
     * @mbg.generated
     */
    private Long falcid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public TAbilityTree(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftypeid, String fname, String fnote, Long fpid, Integer fisleaf, String fpath, Float fmethodweight, Integer fdiv, Integer fnodetype, Float fpoints, Integer fconditionscore, Long fgpid, Integer fscoremin, Integer fscoremax, Integer fdel, Long fmethodid, Long falcid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftypeid = ftypeid;
        this.fname = fname;
        this.fnote = fnote;
        this.fpid = fpid;
        this.fisleaf = fisleaf;
        this.fpath = fpath;
        this.fmethodweight = fmethodweight;
        this.fdiv = fdiv;
        this.fnodetype = fnodetype;
        this.fpoints = fpoints;
        this.fconditionscore = fconditionscore;
        this.fgpid = fgpid;
        this.fscoremin = fscoremin;
        this.fscoremax = fscoremax;
        this.fdel = fdel;
        this.fmethodid = fmethodid;
        this.falcid = falcid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public TAbilityTree() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_tree.FKeyID
     * @return student_capability_evaluation..t_ability_tree.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_tree.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_ability_tree.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_tree.FCID
     * @return student_capability_evaluation..t_ability_tree.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_tree.FCID
     * @param fcid the value for student_capability_evaluation..t_ability_tree.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_tree.FUID
     * @return student_capability_evaluation..t_ability_tree.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_tree.FUID
     * @param fuid the value for student_capability_evaluation..t_ability_tree.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_tree.FCDATE
     * @return student_capability_evaluation..t_ability_tree.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_tree.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_ability_tree.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_ability_tree.FUDATE
     * @return student_capability_evaluation..t_ability_tree.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_ability_tree.FUDATE
     * @param fudate the value for student_capability_evaluation..t_ability_tree.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_tree.FState
     * @return student_capability_evaluation..t_ability_tree.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_ability_tree.FState
     * @param fstate the value for student_capability_evaluation..t_ability_tree.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability_tree.FTypeID
     * @return student_capability_evaluation..t_ability_tree.FTypeID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 能力类型，T_Ability_Type 字段:student_capability_evaluation..t_ability_tree.FTypeID
     * @param ftypeid the value for student_capability_evaluation..t_ability_tree.FTypeID, 能力类型，T_Ability_Type
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_ability_tree.FName
     * @return student_capability_evaluation..t_ability_tree.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_ability_tree.FName
     * @param fname the value for student_capability_evaluation..t_ability_tree.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 描述 字段:student_capability_evaluation..t_ability_tree.FNote
     * @return student_capability_evaluation..t_ability_tree.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:student_capability_evaluation..t_ability_tree.FNote
     * @param fnote the value for student_capability_evaluation..t_ability_tree.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 父节点 字段:student_capability_evaluation..t_ability_tree.FPID
     * @return student_capability_evaluation..t_ability_tree.FPID, 父节点
     * @mbg.generated
     */
    public Long getFpid() {
        return fpid;
    }

    /**
     * 设置 父节点 字段:student_capability_evaluation..t_ability_tree.FPID
     * @param fpid the value for student_capability_evaluation..t_ability_tree.FPID, 父节点
     * @mbg.generated
     */
    public void setFpid(Long fpid) {
        this.fpid = fpid;
    }

    /**
     * 获取 叶子节点 字段:student_capability_evaluation..t_ability_tree.FIsleaf
     * @return student_capability_evaluation..t_ability_tree.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public Integer getFisleaf() {
        return fisleaf;
    }

    /**
     * 设置 叶子节点 字段:student_capability_evaluation..t_ability_tree.FIsleaf
     * @param fisleaf the value for student_capability_evaluation..t_ability_tree.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public void setFisleaf(Integer fisleaf) {
        this.fisleaf = fisleaf;
    }

    /**
     * 获取 路径 字段:student_capability_evaluation..t_ability_tree.FPath
     * @return student_capability_evaluation..t_ability_tree.FPath, 路径
     * @mbg.generated
     */
    public String getFpath() {
        return fpath;
    }

    /**
     * 设置 路径 字段:student_capability_evaluation..t_ability_tree.FPath
     * @param fpath the value for student_capability_evaluation..t_ability_tree.FPath, 路径
     * @mbg.generated
     */
    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }

    /**
     * 获取 权重，FNodeType =3 、4时会有 字段:student_capability_evaluation..t_ability_tree.FMethodWeight
     * @return student_capability_evaluation..t_ability_tree.FMethodWeight, 权重，FNodeType =3 、4时会有
     * @mbg.generated
     */
    public Float getFmethodweight() {
        return fmethodweight;
    }

    /**
     * 设置 权重，FNodeType =3 、4时会有 字段:student_capability_evaluation..t_ability_tree.FMethodWeight
     * @param fmethodweight the value for student_capability_evaluation..t_ability_tree.FMethodWeight, 权重，FNodeType =3 、4时会有
     * @mbg.generated
     */
    public void setFmethodweight(Float fmethodweight) {
        this.fmethodweight = fmethodweight;
    }

    /**
     * 获取 层级 默认1 字段:student_capability_evaluation..t_ability_tree.FDiv
     * @return student_capability_evaluation..t_ability_tree.FDiv, 层级 默认1
     * @mbg.generated
     */
    public Integer getFdiv() {
        return fdiv;
    }

    /**
     * 设置 层级 默认1 字段:student_capability_evaluation..t_ability_tree.FDiv
     * @param fdiv the value for student_capability_evaluation..t_ability_tree.FDiv, 层级 默认1
     * @mbg.generated
     */
    public void setFdiv(Integer fdiv) {
        this.fdiv = fdiv;
    }

    /**
     * 获取 1-能力 2-等级 3-条件 4-考核方式 字段:student_capability_evaluation..t_ability_tree.FNodeType
     * @return student_capability_evaluation..t_ability_tree.FNodeType, 1-能力 2-等级 3-条件 4-考核方式
     * @mbg.generated
     */
    public Integer getFnodetype() {
        return fnodetype;
    }

    /**
     * 设置 1-能力 2-等级 3-条件 4-考核方式 字段:student_capability_evaluation..t_ability_tree.FNodeType
     * @param fnodetype the value for student_capability_evaluation..t_ability_tree.FNodeType, 1-能力 2-等级 3-条件 4-考核方式
     * @mbg.generated
     */
    public void setFnodetype(Integer fnodetype) {
        this.fnodetype = fnodetype;
    }

    /**
     * 获取 难度积分，FNodeType =2时会有 字段:student_capability_evaluation..t_ability_tree.FPoints
     * @return student_capability_evaluation..t_ability_tree.FPoints, 难度积分，FNodeType =2时会有
     * @mbg.generated
     */
    public Float getFpoints() {
        return fpoints;
    }

    /**
     * 设置 难度积分，FNodeType =2时会有 字段:student_capability_evaluation..t_ability_tree.FPoints
     * @param fpoints the value for student_capability_evaluation..t_ability_tree.FPoints, 难度积分，FNodeType =2时会有
     * @mbg.generated
     */
    public void setFpoints(Float fpoints) {
        this.fpoints = fpoints;
    }

    /**
     * 获取 满足条件的分数,FNodeType =3 字段:student_capability_evaluation..t_ability_tree.FConditionScore
     * @return student_capability_evaluation..t_ability_tree.FConditionScore, 满足条件的分数,FNodeType =3
     * @mbg.generated
     */
    public Integer getFconditionscore() {
        return fconditionscore;
    }

    /**
     * 设置 满足条件的分数,FNodeType =3 字段:student_capability_evaluation..t_ability_tree.FConditionScore
     * @param fconditionscore the value for student_capability_evaluation..t_ability_tree.FConditionScore, 满足条件的分数,FNodeType =3
     * @mbg.generated
     */
    public void setFconditionscore(Integer fconditionscore) {
        this.fconditionscore = fconditionscore;
    }

    /**
     * 获取 根节点，及FPID=-1的，只有子级会有 字段:student_capability_evaluation..t_ability_tree.FGPID
     * @return student_capability_evaluation..t_ability_tree.FGPID, 根节点，及FPID=-1的，只有子级会有
     * @mbg.generated
     */
    public Long getFgpid() {
        return fgpid;
    }

    /**
     * 设置 根节点，及FPID=-1的，只有子级会有 字段:student_capability_evaluation..t_ability_tree.FGPID
     * @param fgpid the value for student_capability_evaluation..t_ability_tree.FGPID, 根节点，及FPID=-1的，只有子级会有
     * @mbg.generated
     */
    public void setFgpid(Long fgpid) {
        this.fgpid = fgpid;
    }

    /**
     * 获取 FNodeType = 2,等级区间最小值 字段:student_capability_evaluation..t_ability_tree.FScoreMin
     * @return student_capability_evaluation..t_ability_tree.FScoreMin, FNodeType = 2,等级区间最小值
     * @mbg.generated
     */
    public Integer getFscoremin() {
        return fscoremin;
    }

    /**
     * 设置 FNodeType = 2,等级区间最小值 字段:student_capability_evaluation..t_ability_tree.FScoreMin
     * @param fscoremin the value for student_capability_evaluation..t_ability_tree.FScoreMin, FNodeType = 2,等级区间最小值
     * @mbg.generated
     */
    public void setFscoremin(Integer fscoremin) {
        this.fscoremin = fscoremin;
    }

    /**
     * 获取 FNodeType = 2,等级区间最大值 字段:student_capability_evaluation..t_ability_tree.FScoreMax
     * @return student_capability_evaluation..t_ability_tree.FScoreMax, FNodeType = 2,等级区间最大值
     * @mbg.generated
     */
    public Integer getFscoremax() {
        return fscoremax;
    }

    /**
     * 设置 FNodeType = 2,等级区间最大值 字段:student_capability_evaluation..t_ability_tree.FScoreMax
     * @param fscoremax the value for student_capability_evaluation..t_ability_tree.FScoreMax, FNodeType = 2,等级区间最大值
     * @mbg.generated
     */
    public void setFscoremax(Integer fscoremax) {
        this.fscoremax = fscoremax;
    }

    /**
     * 获取 1=再用2=删除 字段:student_capability_evaluation..t_ability_tree.FDel
     * @return student_capability_evaluation..t_ability_tree.FDel, 1=再用2=删除
     * @mbg.generated
     */
    public Integer getFdel() {
        return fdel;
    }

    /**
     * 设置 1=再用2=删除 字段:student_capability_evaluation..t_ability_tree.FDel
     * @param fdel the value for student_capability_evaluation..t_ability_tree.FDel, 1=再用2=删除
     * @mbg.generated
     */
    public void setFdel(Integer fdel) {
        this.fdel = fdel;
    }

    /**
     * 获取 考核方式ID，FNodeType = 4 字段:student_capability_evaluation..t_ability_tree.FMethodID
     * @return student_capability_evaluation..t_ability_tree.FMethodID, 考核方式ID，FNodeType = 4
     * @mbg.generated
     */
    public Long getFmethodid() {
        return fmethodid;
    }

    /**
     * 设置 考核方式ID，FNodeType = 4 字段:student_capability_evaluation..t_ability_tree.FMethodID
     * @param fmethodid the value for student_capability_evaluation..t_ability_tree.FMethodID, 考核方式ID，FNodeType = 4
     * @mbg.generated
     */
    public void setFmethodid(Long fmethodid) {
        this.fmethodid = fmethodid;
    }

    /**
     * 获取 能力水平条件表ID，FNodeType = 3,默认-1 字段:student_capability_evaluation..t_ability_tree.FALCID
     * @return student_capability_evaluation..t_ability_tree.FALCID, 能力水平条件表ID，FNodeType = 3,默认-1
     * @mbg.generated
     */
    public Long getFalcid() {
        return falcid;
    }

    /**
     * 设置 能力水平条件表ID，FNodeType = 3,默认-1 字段:student_capability_evaluation..t_ability_tree.FALCID
     * @param falcid the value for student_capability_evaluation..t_ability_tree.FALCID, 能力水平条件表ID，FNodeType = 3,默认-1
     * @mbg.generated
     */
    public void setFalcid(Long falcid) {
        this.falcid = falcid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_ability_tree
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
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", fname=").append(fname);
        sb.append(", fnote=").append(fnote);
        sb.append(", fpid=").append(fpid);
        sb.append(", fisleaf=").append(fisleaf);
        sb.append(", fpath=").append(fpath);
        sb.append(", fmethodweight=").append(fmethodweight);
        sb.append(", fdiv=").append(fdiv);
        sb.append(", fnodetype=").append(fnodetype);
        sb.append(", fpoints=").append(fpoints);
        sb.append(", fconditionscore=").append(fconditionscore);
        sb.append(", fgpid=").append(fgpid);
        sb.append(", fscoremin=").append(fscoremin);
        sb.append(", fscoremax=").append(fscoremax);
        sb.append(", fdel=").append(fdel);
        sb.append(", fmethodid=").append(fmethodid);
        sb.append(", falcid=").append(falcid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_ability_tree
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
        TAbilityTree other = (TAbilityTree) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFpid() == null ? other.getFpid() == null : this.getFpid().equals(other.getFpid()))
            && (this.getFisleaf() == null ? other.getFisleaf() == null : this.getFisleaf().equals(other.getFisleaf()))
            && (this.getFpath() == null ? other.getFpath() == null : this.getFpath().equals(other.getFpath()))
            && (this.getFmethodweight() == null ? other.getFmethodweight() == null : this.getFmethodweight().equals(other.getFmethodweight()))
            && (this.getFdiv() == null ? other.getFdiv() == null : this.getFdiv().equals(other.getFdiv()))
            && (this.getFnodetype() == null ? other.getFnodetype() == null : this.getFnodetype().equals(other.getFnodetype()))
            && (this.getFpoints() == null ? other.getFpoints() == null : this.getFpoints().equals(other.getFpoints()))
            && (this.getFconditionscore() == null ? other.getFconditionscore() == null : this.getFconditionscore().equals(other.getFconditionscore()))
            && (this.getFgpid() == null ? other.getFgpid() == null : this.getFgpid().equals(other.getFgpid()))
            && (this.getFscoremin() == null ? other.getFscoremin() == null : this.getFscoremin().equals(other.getFscoremin()))
            && (this.getFscoremax() == null ? other.getFscoremax() == null : this.getFscoremax().equals(other.getFscoremax()))
            && (this.getFdel() == null ? other.getFdel() == null : this.getFdel().equals(other.getFdel()))
            && (this.getFmethodid() == null ? other.getFmethodid() == null : this.getFmethodid().equals(other.getFmethodid()))
            && (this.getFalcid() == null ? other.getFalcid() == null : this.getFalcid().equals(other.getFalcid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_ability_tree
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
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFpid() == null) ? 0 : getFpid().hashCode());
        result = prime * result + ((getFisleaf() == null) ? 0 : getFisleaf().hashCode());
        result = prime * result + ((getFpath() == null) ? 0 : getFpath().hashCode());
        result = prime * result + ((getFmethodweight() == null) ? 0 : getFmethodweight().hashCode());
        result = prime * result + ((getFdiv() == null) ? 0 : getFdiv().hashCode());
        result = prime * result + ((getFnodetype() == null) ? 0 : getFnodetype().hashCode());
        result = prime * result + ((getFpoints() == null) ? 0 : getFpoints().hashCode());
        result = prime * result + ((getFconditionscore() == null) ? 0 : getFconditionscore().hashCode());
        result = prime * result + ((getFgpid() == null) ? 0 : getFgpid().hashCode());
        result = prime * result + ((getFscoremin() == null) ? 0 : getFscoremin().hashCode());
        result = prime * result + ((getFscoremax() == null) ? 0 : getFscoremax().hashCode());
        result = prime * result + ((getFdel() == null) ? 0 : getFdel().hashCode());
        result = prime * result + ((getFmethodid() == null) ? 0 : getFmethodid().hashCode());
        result = prime * result + ((getFalcid() == null) ? 0 : getFalcid().hashCode());
        return result;
    }
}