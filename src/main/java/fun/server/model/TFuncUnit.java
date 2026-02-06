package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_func_unit")
public class TFuncUnit implements Serializable {
    /**
     * 
     * 表字段 : t_func_unit.fkeyid
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_func_unit.fname
     * @mbg.generated
     */
    private String fname;

    /**
     * 
     * 表字段 : t_func_unit.Fmemo
     * @mbg.generated
     */
    private String fmemo;

    /**
     * 
     * 表字段 : t_func_unit.ftype
     * @mbg.generated
     */
    private Long ftype;

    /**
     * 默认：0；0:失效;1:有效
     * 表字段 : t_func_unit.factive
     * @mbg.generated
     */
    private Integer factive;

    /**
     * 
     * 表字段 : t_func_unit.fcid
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_func_unit.fuid
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_func_unit.fcdt
     * @mbg.generated
     */
    private Date fcdt;

    /**
     * 
     * 表字段 : t_func_unit.fudt
     * @mbg.generated
     */
    private Date fudt;

    /**
     * 示意图
     * 表字段 : t_func_unit.fimg
     * @mbg.generated
     */
    private String fimg;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_func_unit
     * @mbg.generated
     */
    public TFuncUnit(Long fkeyid, String fname, String fmemo, Long ftype, Integer factive, Long fcid, Long fuid, Date fcdt, Date fudt) {
        this.fkeyid = fkeyid;
        this.fname = fname;
        this.fmemo = fmemo;
        this.ftype = ftype;
        this.factive = factive;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdt = fcdt;
        this.fudt = fudt;
    }

    /**
     * 构造查询条件
     * t_func_unit
     * @mbg.generated
     */
    public TFuncUnit(Long fkeyid, String fname, String fmemo, Long ftype, Integer factive, Long fcid, Long fuid, Date fcdt, Date fudt, String fimg) {
        this.fkeyid = fkeyid;
        this.fname = fname;
        this.fmemo = fmemo;
        this.ftype = ftype;
        this.factive = factive;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdt = fcdt;
        this.fudt = fudt;
        this.fimg = fimg;
    }

    /**
     * 构造查询条件
     * t_func_unit
     * @mbg.generated
     */
    public TFuncUnit() {
        super();
    }

    /**
     * 获取  字段:t_func_unit.fkeyid
     * @return t_func_unit.fkeyid, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_func_unit.fkeyid
     * @param fkeyid the value for t_func_unit.fkeyid, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_func_unit.fname
     * @return t_func_unit.fname, 
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置  字段:t_func_unit.fname
     * @param fname the value for t_func_unit.fname, 
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取  字段:t_func_unit.Fmemo
     * @return t_func_unit.Fmemo, 
     * @mbg.generated
     */
    public String getFmemo() {
        return fmemo;
    }

    /**
     * 设置  字段:t_func_unit.Fmemo
     * @param fmemo the value for t_func_unit.Fmemo, 
     * @mbg.generated
     */
    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    /**
     * 获取  字段:t_func_unit.ftype
     * @return t_func_unit.ftype, 
     * @mbg.generated
     */
    public Long getFtype() {
        return ftype;
    }

    /**
     * 设置  字段:t_func_unit.ftype
     * @param ftype the value for t_func_unit.ftype, 
     * @mbg.generated
     */
    public void setFtype(Long ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 默认：0；0:失效;1:有效 字段:t_func_unit.factive
     * @return t_func_unit.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public Integer getFactive() {
        return factive;
    }

    /**
     * 设置 默认：0；0:失效;1:有效 字段:t_func_unit.factive
     * @param factive the value for t_func_unit.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public void setFactive(Integer factive) {
        this.factive = factive;
    }

    /**
     * 获取  字段:t_func_unit.fcid
     * @return t_func_unit.fcid, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_func_unit.fcid
     * @param fcid the value for t_func_unit.fcid, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_func_unit.fuid
     * @return t_func_unit.fuid, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_func_unit.fuid
     * @param fuid the value for t_func_unit.fuid, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_func_unit.fcdt
     * @return t_func_unit.fcdt, 
     * @mbg.generated
     */
    public Date getFcdt() {
        return fcdt;
    }

    /**
     * 设置  字段:t_func_unit.fcdt
     * @param fcdt the value for t_func_unit.fcdt, 
     * @mbg.generated
     */
    public void setFcdt(Date fcdt) {
        this.fcdt = fcdt;
    }

    /**
     * 获取  字段:t_func_unit.fudt
     * @return t_func_unit.fudt, 
     * @mbg.generated
     */
    public Date getFudt() {
        return fudt;
    }

    /**
     * 设置  字段:t_func_unit.fudt
     * @param fudt the value for t_func_unit.fudt, 
     * @mbg.generated
     */
    public void setFudt(Date fudt) {
        this.fudt = fudt;
    }

    /**
     * 获取 示意图 字段:t_func_unit.fimg
     * @return t_func_unit.fimg, 示意图
     * @mbg.generated
     */
    public String getFimg() {
        return fimg;
    }

    /**
     * 设置 示意图 字段:t_func_unit.fimg
     * @param fimg the value for t_func_unit.fimg, 示意图
     * @mbg.generated
     */
    public void setFimg(String fimg) {
        this.fimg = fimg == null ? null : fimg.trim();
    }

    /**
     * 实例输出为字符串
     * t_func_unit
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fkeyid=").append(fkeyid);
        sb.append(", fname=").append(fname);
        sb.append(", fmemo=").append(fmemo);
        sb.append(", ftype=").append(ftype);
        sb.append(", factive=").append(factive);
        sb.append(", fcid=").append(fcid);
        sb.append(", fuid=").append(fuid);
        sb.append(", fcdt=").append(fcdt);
        sb.append(", fudt=").append(fudt);
        sb.append(", fimg=").append(fimg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_func_unit
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
        TFuncUnit other = (TFuncUnit) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFmemo() == null ? other.getFmemo() == null : this.getFmemo().equals(other.getFmemo()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFactive() == null ? other.getFactive() == null : this.getFactive().equals(other.getFactive()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdt() == null ? other.getFcdt() == null : this.getFcdt().equals(other.getFcdt()))
            && (this.getFudt() == null ? other.getFudt() == null : this.getFudt().equals(other.getFudt()))
            && (this.getFimg() == null ? other.getFimg() == null : this.getFimg().equals(other.getFimg()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_func_unit
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFmemo() == null) ? 0 : getFmemo().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFactive() == null) ? 0 : getFactive().hashCode());
        result = prime * result + ((getFcid() == null) ? 0 : getFcid().hashCode());
        result = prime * result + ((getFuid() == null) ? 0 : getFuid().hashCode());
        result = prime * result + ((getFcdt() == null) ? 0 : getFcdt().hashCode());
        result = prime * result + ((getFudt() == null) ? 0 : getFudt().hashCode());
        result = prime * result + ((getFimg() == null) ? 0 : getFimg().hashCode());
        return result;
    }
}