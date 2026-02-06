package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_category
*/
@Table(name = "t_course_category")
public class TCourseCategory implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_category.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_category.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_category.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_category.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_category.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_category.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_course_category.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_course_category.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 父节点
     * 表字段 : student_capability_evaluation..t_course_category.FPID
     * @mbg.generated
     */
    private Long fpid;

    /**
     * 叶子节点
     * 表字段 : student_capability_evaluation..t_course_category.FIsleaf
     * @mbg.generated
     */
    private Integer fisleaf;

    /**
     * 路径
     * 表字段 : student_capability_evaluation..t_course_category.FPath
     * @mbg.generated
     */
    private String fpath;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_category
     * @mbg.generated
     */
    public TCourseCategory(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, String fnote, Long fpid, Integer fisleaf, String fpath) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fnote = fnote;
        this.fpid = fpid;
        this.fisleaf = fisleaf;
        this.fpath = fpath;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_category
     * @mbg.generated
     */
    public TCourseCategory() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_category.FKeyID
     * @return student_capability_evaluation..t_course_category.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_category.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_category.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_category.FCID
     * @return student_capability_evaluation..t_course_category.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_category.FCID
     * @param fcid the value for student_capability_evaluation..t_course_category.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_category.FUID
     * @return student_capability_evaluation..t_course_category.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_category.FUID
     * @param fuid the value for student_capability_evaluation..t_course_category.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_category.FCDATE
     * @return student_capability_evaluation..t_course_category.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_category.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_category.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_category.FUDATE
     * @return student_capability_evaluation..t_course_category.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_category.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_category.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_category.FState
     * @return student_capability_evaluation..t_course_category.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_category.FState
     * @param fstate the value for student_capability_evaluation..t_course_category.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_course_category.FName
     * @return student_capability_evaluation..t_course_category.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_course_category.FName
     * @param fname the value for student_capability_evaluation..t_course_category.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_course_category.FNote
     * @return student_capability_evaluation..t_course_category.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_course_category.FNote
     * @param fnote the value for student_capability_evaluation..t_course_category.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 父节点 字段:student_capability_evaluation..t_course_category.FPID
     * @return student_capability_evaluation..t_course_category.FPID, 父节点
     * @mbg.generated
     */
    public Long getFpid() {
        return fpid;
    }

    /**
     * 设置 父节点 字段:student_capability_evaluation..t_course_category.FPID
     * @param fpid the value for student_capability_evaluation..t_course_category.FPID, 父节点
     * @mbg.generated
     */
    public void setFpid(Long fpid) {
        this.fpid = fpid;
    }

    /**
     * 获取 叶子节点 字段:student_capability_evaluation..t_course_category.FIsleaf
     * @return student_capability_evaluation..t_course_category.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public Integer getFisleaf() {
        return fisleaf;
    }

    /**
     * 设置 叶子节点 字段:student_capability_evaluation..t_course_category.FIsleaf
     * @param fisleaf the value for student_capability_evaluation..t_course_category.FIsleaf, 叶子节点
     * @mbg.generated
     */
    public void setFisleaf(Integer fisleaf) {
        this.fisleaf = fisleaf;
    }

    /**
     * 获取 路径 字段:student_capability_evaluation..t_course_category.FPath
     * @return student_capability_evaluation..t_course_category.FPath, 路径
     * @mbg.generated
     */
    public String getFpath() {
        return fpath;
    }

    /**
     * 设置 路径 字段:student_capability_evaluation..t_course_category.FPath
     * @param fpath the value for student_capability_evaluation..t_course_category.FPath, 路径
     * @mbg.generated
     */
    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_category
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
        sb.append(", fname=").append(fname);
        sb.append(", fnote=").append(fnote);
        sb.append(", fpid=").append(fpid);
        sb.append(", fisleaf=").append(fisleaf);
        sb.append(", fpath=").append(fpath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_category
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
        TCourseCategory other = (TCourseCategory) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFpid() == null ? other.getFpid() == null : this.getFpid().equals(other.getFpid()))
            && (this.getFisleaf() == null ? other.getFisleaf() == null : this.getFisleaf().equals(other.getFisleaf()))
            && (this.getFpath() == null ? other.getFpath() == null : this.getFpath().equals(other.getFpath()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_category
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFpid() == null) ? 0 : getFpid().hashCode());
        result = prime * result + ((getFisleaf() == null) ? 0 : getFisleaf().hashCode());
        result = prime * result + ((getFpath() == null) ? 0 : getFpath().hashCode());
        return result;
    }
}