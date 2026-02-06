package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_project_exceed")
public class TProjectExceed implements Serializable {
    /**
     * 
     * 表字段 : t_project_exceed.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 0-禁用；1-可用
     * 表字段 : t_project_exceed.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 1-项目 2-任务
     * 表字段 : t_project_exceed.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 差值
     * 表字段 : t_project_exceed.FNum
     * @mbg.generated
     */
    private Integer fnum;

    /**
     * 提前几天预警
     * 表字段 : t_project_exceed.FDay
     * @mbg.generated
     */
    private Integer fday;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_exceed
     * @mbg.generated
     */
    public TProjectExceed(Long fkeyid, Integer fstate, Integer ftype, Integer fnum, Integer fday) {
        this.fkeyid = fkeyid;
        this.fstate = fstate;
        this.ftype = ftype;
        this.fnum = fnum;
        this.fday = fday;
    }

    /**
     * 构造查询条件
     * t_project_exceed
     * @mbg.generated
     */
    public TProjectExceed() {
        super();
    }

    /**
     * 获取  字段:t_project_exceed.FKeyID
     * @return t_project_exceed.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_exceed.FKeyID
     * @param fkeyid the value for t_project_exceed.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取 0-禁用；1-可用 字段:t_project_exceed.FState
     * @return t_project_exceed.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:t_project_exceed.FState
     * @param fstate the value for t_project_exceed.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 1-项目 2-任务 字段:t_project_exceed.FType
     * @return t_project_exceed.FType, 1-项目 2-任务
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 1-项目 2-任务 字段:t_project_exceed.FType
     * @param ftype the value for t_project_exceed.FType, 1-项目 2-任务
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 差值 字段:t_project_exceed.FNum
     * @return t_project_exceed.FNum, 差值
     * @mbg.generated
     */
    public Integer getFnum() {
        return fnum;
    }

    /**
     * 设置 差值 字段:t_project_exceed.FNum
     * @param fnum the value for t_project_exceed.FNum, 差值
     * @mbg.generated
     */
    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    /**
     * 获取 提前几天预警 字段:t_project_exceed.FDay
     * @return t_project_exceed.FDay, 提前几天预警
     * @mbg.generated
     */
    public Integer getFday() {
        return fday;
    }

    /**
     * 设置 提前几天预警 字段:t_project_exceed.FDay
     * @param fday the value for t_project_exceed.FDay, 提前几天预警
     * @mbg.generated
     */
    public void setFday(Integer fday) {
        this.fday = fday;
    }

    /**
     * 实例输出为字符串
     * t_project_exceed
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fkeyid=").append(fkeyid);
        sb.append(", fstate=").append(fstate);
        sb.append(", ftype=").append(ftype);
        sb.append(", fnum=").append(fnum);
        sb.append(", fday=").append(fday);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_exceed
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
        TProjectExceed other = (TProjectExceed) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFnum() == null ? other.getFnum() == null : this.getFnum().equals(other.getFnum()))
            && (this.getFday() == null ? other.getFday() == null : this.getFday().equals(other.getFday()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_exceed
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFstate() == null) ? 0 : getFstate().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFnum() == null) ? 0 : getFnum().hashCode());
        result = prime * result + ((getFday() == null) ? 0 : getFday().hashCode());
        return result;
    }
}