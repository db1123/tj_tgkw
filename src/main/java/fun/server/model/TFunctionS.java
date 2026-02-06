package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_function_s")
public class TFunctionS implements Serializable {
    /**
     * 
     * 表字段 : t_function_s.fkeyid
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_function_s.ffuncmid
     * @mbg.generated
     */
    private Long ffuncmid;

    /**
     * 
     * 表字段 : t_function_s.ffid
     * @mbg.generated
     */
    private Long ffid;

    /**
     * 
     * 表字段 : t_function_s.ffuncunitid
     * @mbg.generated
     */
    private Long ffuncunitid;

    /**
     * 
     * 表字段 : t_function_s.flevel
     * @mbg.generated
     */
    private Integer flevel;

    /**
     * 
     * 表字段 : t_function_s.fleaf
     * @mbg.generated
     */
    private Integer fleaf;

    /**
     * 
     * 表字段 : t_function_s.forder
     * @mbg.generated
     */
    private Integer forder;

    /**
     * 
     * 表字段 : t_function_s.fmemo
     * @mbg.generated
     */
    private String fmemo;

    /**
     * 默认：0；0:失效;1:有效
     * 表字段 : t_function_s.factive
     * @mbg.generated
     */
    private Integer factive;

    /**
     * 
     * 表字段 : t_function_s.fcid
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_function_s.fuid
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_function_s.fcdt
     * @mbg.generated
     */
    private Date fcdt;

    /**
     * 
     * 表字段 : t_function_s.fudt
     * @mbg.generated
     */
    private Date fudt;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_function_s
     * @mbg.generated
     */
    public TFunctionS(Long fkeyid, Long ffuncmid, Long ffid, Long ffuncunitid, Integer flevel, Integer fleaf, Integer forder, String fmemo, Integer factive, Long fcid, Long fuid, Date fcdt, Date fudt) {
        this.fkeyid = fkeyid;
        this.ffuncmid = ffuncmid;
        this.ffid = ffid;
        this.ffuncunitid = ffuncunitid;
        this.flevel = flevel;
        this.fleaf = fleaf;
        this.forder = forder;
        this.fmemo = fmemo;
        this.factive = factive;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdt = fcdt;
        this.fudt = fudt;
    }

    /**
     * 构造查询条件
     * t_function_s
     * @mbg.generated
     */
    public TFunctionS() {
        super();
    }

    /**
     * 获取  字段:t_function_s.fkeyid
     * @return t_function_s.fkeyid, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_function_s.fkeyid
     * @param fkeyid the value for t_function_s.fkeyid, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_function_s.ffuncmid
     * @return t_function_s.ffuncmid, 
     * @mbg.generated
     */
    public Long getFfuncmid() {
        return ffuncmid;
    }

    /**
     * 设置  字段:t_function_s.ffuncmid
     * @param ffuncmid the value for t_function_s.ffuncmid, 
     * @mbg.generated
     */
    public void setFfuncmid(Long ffuncmid) {
        this.ffuncmid = ffuncmid;
    }

    /**
     * 获取  字段:t_function_s.ffid
     * @return t_function_s.ffid, 
     * @mbg.generated
     */
    public Long getFfid() {
        return ffid;
    }

    /**
     * 设置  字段:t_function_s.ffid
     * @param ffid the value for t_function_s.ffid, 
     * @mbg.generated
     */
    public void setFfid(Long ffid) {
        this.ffid = ffid;
    }

    /**
     * 获取  字段:t_function_s.ffuncunitid
     * @return t_function_s.ffuncunitid, 
     * @mbg.generated
     */
    public Long getFfuncunitid() {
        return ffuncunitid;
    }

    /**
     * 设置  字段:t_function_s.ffuncunitid
     * @param ffuncunitid the value for t_function_s.ffuncunitid, 
     * @mbg.generated
     */
    public void setFfuncunitid(Long ffuncunitid) {
        this.ffuncunitid = ffuncunitid;
    }

    /**
     * 获取  字段:t_function_s.flevel
     * @return t_function_s.flevel, 
     * @mbg.generated
     */
    public Integer getFlevel() {
        return flevel;
    }

    /**
     * 设置  字段:t_function_s.flevel
     * @param flevel the value for t_function_s.flevel, 
     * @mbg.generated
     */
    public void setFlevel(Integer flevel) {
        this.flevel = flevel;
    }

    /**
     * 获取  字段:t_function_s.fleaf
     * @return t_function_s.fleaf, 
     * @mbg.generated
     */
    public Integer getFleaf() {
        return fleaf;
    }

    /**
     * 设置  字段:t_function_s.fleaf
     * @param fleaf the value for t_function_s.fleaf, 
     * @mbg.generated
     */
    public void setFleaf(Integer fleaf) {
        this.fleaf = fleaf;
    }

    /**
     * 获取  字段:t_function_s.forder
     * @return t_function_s.forder, 
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置  字段:t_function_s.forder
     * @param forder the value for t_function_s.forder, 
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 获取  字段:t_function_s.fmemo
     * @return t_function_s.fmemo, 
     * @mbg.generated
     */
    public String getFmemo() {
        return fmemo;
    }

    /**
     * 设置  字段:t_function_s.fmemo
     * @param fmemo the value for t_function_s.fmemo, 
     * @mbg.generated
     */
    public void setFmemo(String fmemo) {
        this.fmemo = fmemo == null ? null : fmemo.trim();
    }

    /**
     * 获取 默认：0；0:失效;1:有效 字段:t_function_s.factive
     * @return t_function_s.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public Integer getFactive() {
        return factive;
    }

    /**
     * 设置 默认：0；0:失效;1:有效 字段:t_function_s.factive
     * @param factive the value for t_function_s.factive, 默认：0；0:失效;1:有效
     * @mbg.generated
     */
    public void setFactive(Integer factive) {
        this.factive = factive;
    }

    /**
     * 获取  字段:t_function_s.fcid
     * @return t_function_s.fcid, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_function_s.fcid
     * @param fcid the value for t_function_s.fcid, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_function_s.fuid
     * @return t_function_s.fuid, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_function_s.fuid
     * @param fuid the value for t_function_s.fuid, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_function_s.fcdt
     * @return t_function_s.fcdt, 
     * @mbg.generated
     */
    public Date getFcdt() {
        return fcdt;
    }

    /**
     * 设置  字段:t_function_s.fcdt
     * @param fcdt the value for t_function_s.fcdt, 
     * @mbg.generated
     */
    public void setFcdt(Date fcdt) {
        this.fcdt = fcdt;
    }

    /**
     * 获取  字段:t_function_s.fudt
     * @return t_function_s.fudt, 
     * @mbg.generated
     */
    public Date getFudt() {
        return fudt;
    }

    /**
     * 设置  字段:t_function_s.fudt
     * @param fudt the value for t_function_s.fudt, 
     * @mbg.generated
     */
    public void setFudt(Date fudt) {
        this.fudt = fudt;
    }

    /**
     * 实例输出为字符串
     * t_function_s
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fkeyid=").append(fkeyid);
        sb.append(", ffuncmid=").append(ffuncmid);
        sb.append(", ffid=").append(ffid);
        sb.append(", ffuncunitid=").append(ffuncunitid);
        sb.append(", flevel=").append(flevel);
        sb.append(", fleaf=").append(fleaf);
        sb.append(", forder=").append(forder);
        sb.append(", fmemo=").append(fmemo);
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
     * t_function_s
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
        TFunctionS other = (TFunctionS) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFfuncmid() == null ? other.getFfuncmid() == null : this.getFfuncmid().equals(other.getFfuncmid()))
            && (this.getFfid() == null ? other.getFfid() == null : this.getFfid().equals(other.getFfid()))
            && (this.getFfuncunitid() == null ? other.getFfuncunitid() == null : this.getFfuncunitid().equals(other.getFfuncunitid()))
            && (this.getFlevel() == null ? other.getFlevel() == null : this.getFlevel().equals(other.getFlevel()))
            && (this.getFleaf() == null ? other.getFleaf() == null : this.getFleaf().equals(other.getFleaf()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()))
            && (this.getFmemo() == null ? other.getFmemo() == null : this.getFmemo().equals(other.getFmemo()))
            && (this.getFactive() == null ? other.getFactive() == null : this.getFactive().equals(other.getFactive()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdt() == null ? other.getFcdt() == null : this.getFcdt().equals(other.getFcdt()))
            && (this.getFudt() == null ? other.getFudt() == null : this.getFudt().equals(other.getFudt()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_function_s
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFfuncmid() == null) ? 0 : getFfuncmid().hashCode());
        result = prime * result + ((getFfid() == null) ? 0 : getFfid().hashCode());
        result = prime * result + ((getFfuncunitid() == null) ? 0 : getFfuncunitid().hashCode());
        result = prime * result + ((getFlevel() == null) ? 0 : getFlevel().hashCode());
        result = prime * result + ((getFleaf() == null) ? 0 : getFleaf().hashCode());
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        result = prime * result + ((getFmemo() == null) ? 0 : getFmemo().hashCode());
        result = prime * result + ((getFactive() == null) ? 0 : getFactive().hashCode());
        result = prime * result + ((getFcid() == null) ? 0 : getFcid().hashCode());
        result = prime * result + ((getFuid() == null) ? 0 : getFuid().hashCode());
        result = prime * result + ((getFcdt() == null) ? 0 : getFcdt().hashCode());
        result = prime * result + ((getFudt() == null) ? 0 : getFudt().hashCode());
        return result;
    }
}