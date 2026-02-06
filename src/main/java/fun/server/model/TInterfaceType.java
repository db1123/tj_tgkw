package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 表名：t_interface_type
 * 表注释：数据接口类型表
*/
@Table(name = "t_interface_type")
public class TInterfaceType implements Serializable {
    /**
     * 系统唯一ID，系统生成
     * 表字段 : student_capability_evaluation..t_interface_type.FKeyID
     * @mbg.generated
     */
	@Id
	@KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 创建者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_interface_type.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 最后修改者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_interface_type.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 创建时间
     * 表字段 : student_capability_evaluation..t_interface_type.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 最后修改时间
     * 表字段 : student_capability_evaluation..t_interface_type.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态，1-可用、0-禁用
     * 表字段 : student_capability_evaluation..t_interface_type.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 名称，不可重复
     * 表字段 : student_capability_evaluation..t_interface_type.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_interface_type.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_interface_type.FPID
     * @mbg.generated
     */
    private Long fpid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_interface_type.FIsleaf
     * @mbg.generated
     */
    private Integer fisleaf;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_interface_type.FPath
     * @mbg.generated
     */
    private String fpath;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_interface_type
     * @mbg.generated
     */
    public TInterfaceType(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, String fnote, Long fpid, Integer fisleaf, String fpath) {
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
     * student_capability_evaluation..t_interface_type
     * @mbg.generated
     */
    public TInterfaceType() {
        super();
    }

    /**
     * 获取 系统唯一ID，系统生成 字段:student_capability_evaluation..t_interface_type.FKeyID
     * @return student_capability_evaluation..t_interface_type.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置 系统唯一ID，系统生成 字段:student_capability_evaluation..t_interface_type.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_interface_type.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取 创建者，来源T_User主键 字段:student_capability_evaluation..t_interface_type.FCID
     * @return student_capability_evaluation..t_interface_type.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置 创建者，来源T_User主键 字段:student_capability_evaluation..t_interface_type.FCID
     * @param fcid the value for student_capability_evaluation..t_interface_type.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_interface_type.FUID
     * @return student_capability_evaluation..t_interface_type.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_interface_type.FUID
     * @param fuid the value for student_capability_evaluation..t_interface_type.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取 创建时间 字段:student_capability_evaluation..t_interface_type.FCDATE
     * @return student_capability_evaluation..t_interface_type.FCDATE, 创建时间
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置 创建时间 字段:student_capability_evaluation..t_interface_type.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_interface_type.FCDATE, 创建时间
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取 最后修改时间 字段:student_capability_evaluation..t_interface_type.FUDATE
     * @return student_capability_evaluation..t_interface_type.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置 最后修改时间 字段:student_capability_evaluation..t_interface_type.FUDATE
     * @param fudate the value for student_capability_evaluation..t_interface_type.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态，1-可用、0-禁用 字段:student_capability_evaluation..t_interface_type.FState
     * @return student_capability_evaluation..t_interface_type.FState, 状态，1-可用、0-禁用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态，1-可用、0-禁用 字段:student_capability_evaluation..t_interface_type.FState
     * @param fstate the value for student_capability_evaluation..t_interface_type.FState, 状态，1-可用、0-禁用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 名称，不可重复 字段:student_capability_evaluation..t_interface_type.FName
     * @return student_capability_evaluation..t_interface_type.FName, 名称，不可重复
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称，不可重复 字段:student_capability_evaluation..t_interface_type.FName
     * @param fname the value for student_capability_evaluation..t_interface_type.FName, 名称，不可重复
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_interface_type.FNote
     * @return student_capability_evaluation..t_interface_type.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_interface_type.FNote
     * @param fnote the value for student_capability_evaluation..t_interface_type.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_interface_type.FPID
     * @return student_capability_evaluation..t_interface_type.FPID, 
     * @mbg.generated
     */
    public Long getFpid() {
        return fpid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_interface_type.FPID
     * @param fpid the value for student_capability_evaluation..t_interface_type.FPID, 
     * @mbg.generated
     */
    public void setFpid(Long fpid) {
        this.fpid = fpid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_interface_type.FIsleaf
     * @return student_capability_evaluation..t_interface_type.FIsleaf, 
     * @mbg.generated
     */
    public Integer getFisleaf() {
        return fisleaf;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_interface_type.FIsleaf
     * @param fisleaf the value for student_capability_evaluation..t_interface_type.FIsleaf, 
     * @mbg.generated
     */
    public void setFisleaf(Integer fisleaf) {
        this.fisleaf = fisleaf;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_interface_type.FPath
     * @return student_capability_evaluation..t_interface_type.FPath, 
     * @mbg.generated
     */
    public String getFpath() {
        return fpath;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_interface_type.FPath
     * @param fpath the value for student_capability_evaluation..t_interface_type.FPath, 
     * @mbg.generated
     */
    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_interface_type
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
     * student_capability_evaluation..t_interface_type
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
        TInterfaceType other = (TInterfaceType) that;
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
     * student_capability_evaluation..t_interface_type
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