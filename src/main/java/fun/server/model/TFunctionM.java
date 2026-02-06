package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_function_m")
public class TFunctionM implements Serializable {
    /**
     * 
     * 表字段 : t_function_m.fkeyid
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_function_m.Fname
     * @mbg.generated
     */
    private String fname;

    /**
     * 
     * 表字段 : t_function_m.fmemo
     * @mbg.generated
     */
    private String fmemo;

    /**
     * 默认0,0=编辑1=提交
     * 表字段 : t_function_m.fstate
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 默认：0；0:失效;1:有效
     * 表字段 : t_function_m.factive
     * @mbg.generated
     */
    private Integer factive;

    /**
     * 
     * 表字段 : t_function_m.fcid
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_function_m.fuid
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_function_m.fcdt
     * @mbg.generated
     */
    private Date fcdt;

    /**
     * 
     * 表字段 : t_function_m.fudt
     * @mbg.generated
     */
    private Date fudt;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_function_m
     * @mbg.generated
     */
    public TFunctionM(Long fkeyid, String fname, String fmemo, Integer fstate, Integer factive, Long fcid, Long fuid, Date fcdt, Date fudt) {
        this.fkeyid = fkeyid;
        this.fname = fname;
        this.fmemo = fmemo;
        this.fstate = fstate;
        this.factive = factive;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdt = fcdt;
        this.fudt = fudt;
    }

    /**
     * 构造查询条件
     * t_function_m
     * @mbg.generated
     */
    public TFunctionM() {
        super();
    }

    /**
     * 获取  字段:t_function_m.fkeyid
     * @return t_function_m.fkeyid, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_function_m.fkeyid
     * @param fkeyid the value for t_function_m.fkeyid, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_function_m.Fname
     * @return t_function_m.Fname, 
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置  字段:t_function_m.Fname
     * @param fname the value for t_function_m.Fname, 
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取  字段:t_function_m.fmemo
     * @return t_function_m.fmemo, 
     * @mbg.generated
     */
    public String getFmemo() {
        return fmemo;
    }

    /**
     * 设置  字段:t_function_m.fmemo
     * @param fmemo the value for t_function_m.fmemo, 
     * @mbg.generated
     */
    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    /**
     * 获取 默认0,0=编辑1=提交 字段:t_function_m.fstate
     * @return t_function_m.fstate, 默认0,0=编辑1=提交
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 默认0,0=编辑1=提交 字段:t_function_m.fstate
     * @param fstate the value for t_function_m.fstate, 默认0,0=编辑1=提交
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 默认：0；0:失效;1:有效 字段:t_function_m.factive
     * @return t_function_m.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public Integer getFactive() {
        return factive;
    }

    /**
     * 设置 默认：0；0:失效;1:有效 字段:t_function_m.factive
     * @param factive the value for t_function_m.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public void setFactive(Integer factive) {
        this.factive = factive;
    }

    /**
     * 获取  字段:t_function_m.fcid
     * @return t_function_m.fcid, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_function_m.fcid
     * @param fcid the value for t_function_m.fcid, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_function_m.fuid
     * @return t_function_m.fuid, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_function_m.fuid
     * @param fuid the value for t_function_m.fuid, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_function_m.fcdt
     * @return t_function_m.fcdt, 
     * @mbg.generated
     */
    public Date getFcdt() {
        return fcdt;
    }

    /**
     * 设置  字段:t_function_m.fcdt
     * @param fcdt the value for t_function_m.fcdt, 
     * @mbg.generated
     */
    public void setFcdt(Date fcdt) {
        this.fcdt = fcdt;
    }

    /**
     * 获取  字段:t_function_m.fudt
     * @return t_function_m.fudt, 
     * @mbg.generated
     */
    public Date getFudt() {
        return fudt;
    }

    /**
     * 设置  字段:t_function_m.fudt
     * @param fudt the value for t_function_m.fudt, 
     * @mbg.generated
     */
    public void setFudt(Date fudt) {
        this.fudt = fudt;
    }

    /**
     * 实例输出为字符串
     * t_function_m
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
        sb.append(", fstate=").append(fstate);
        sb.append(", factive=").append(factive);
        sb.append(", fcid=").append(fcid);
        sb.append(", fuid=").append(fuid);
        sb.append(", fcdt=").append(fcdt);
        sb.append(", fudt=").append(fudt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_function_m
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
        TFunctionM other = (TFunctionM) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFmemo() == null ? other.getFmemo() == null : this.getFmemo().equals(other.getFmemo()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFactive() == null ? other.getFactive() == null : this.getFactive().equals(other.getFactive()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdt() == null ? other.getFcdt() == null : this.getFcdt().equals(other.getFcdt()))
            && (this.getFudt() == null ? other.getFudt() == null : this.getFudt().equals(other.getFudt()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_function_m
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFmemo() == null) ? 0 : getFmemo().hashCode());
        result = prime * result + ((getFstate() == null) ? 0 : getFstate().hashCode());
        result = prime * result + ((getFactive() == null) ? 0 : getFactive().hashCode());
        result = prime * result + ((getFcid() == null) ? 0 : getFcid().hashCode());
        result = prime * result + ((getFuid() == null) ? 0 : getFuid().hashCode());
        result = prime * result + ((getFcdt() == null) ? 0 : getFcdt().hashCode());
        result = prime * result + ((getFudt() == null) ? 0 : getFudt().hashCode());
        return result;
    }
}