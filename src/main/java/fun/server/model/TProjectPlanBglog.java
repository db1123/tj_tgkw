package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_project_plan_bglog")
public class TProjectPlanBglog implements Serializable {
    /**
     * 
     * 表字段 : t_project_plan_bglog.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_project_plan_bglog.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_project_plan_bglog.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_project_plan_bglog.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_project_plan_bglog.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 项目ID
     * 表字段 : t_project_plan_bglog.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 项目计划ID
     * 表字段 : t_project_plan_bglog.FProjectPlanID
     * @mbg.generated
     */
    private Long fprojectplanid;

    /**
     * 更新前计划开始
     * 表字段 : t_project_plan_bglog.FQPSDate
     * @mbg.generated
     */
    private Date fqpsdate;

    /**
     * 更新前计划结束
     * 表字段 : t_project_plan_bglog.FQPEDate
     * @mbg.generated
     */
    private Date fqpedate;

    /**
     * 更新后计划开始
     * 表字段 : t_project_plan_bglog.FPSDate
     * @mbg.generated
     */
    private Date fpsdate;

    /**
     * 更新后计划结束
     * 表字段 : t_project_plan_bglog.FPEDate
     * @mbg.generated
     */
    private Date fpedate;

    /**
     * 备注
     * 表字段 : t_project_plan_bglog.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_plan_bglog
     * @mbg.generated
     */
    public TProjectPlanBglog(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Long fprojectid, Long fprojectplanid, Date fqpsdate, Date fqpedate, Date fpsdate, Date fpedate) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fprojectid = fprojectid;
        this.fprojectplanid = fprojectplanid;
        this.fqpsdate = fqpsdate;
        this.fqpedate = fqpedate;
        this.fpsdate = fpsdate;
        this.fpedate = fpedate;
    }

    /**
     * 构造查询条件
     * t_project_plan_bglog
     * @mbg.generated
     */
    public TProjectPlanBglog(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Long fprojectid, Long fprojectplanid, Date fqpsdate, Date fqpedate, Date fpsdate, Date fpedate, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fprojectid = fprojectid;
        this.fprojectplanid = fprojectplanid;
        this.fqpsdate = fqpsdate;
        this.fqpedate = fqpedate;
        this.fpsdate = fpsdate;
        this.fpedate = fpedate;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_project_plan_bglog
     * @mbg.generated
     */
    public TProjectPlanBglog() {
        super();
    }

    /**
     * 获取  字段:t_project_plan_bglog.FKeyID
     * @return t_project_plan_bglog.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_plan_bglog.FKeyID
     * @param fkeyid the value for t_project_plan_bglog.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_project_plan_bglog.FCID
     * @return t_project_plan_bglog.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_project_plan_bglog.FCID
     * @param fcid the value for t_project_plan_bglog.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_project_plan_bglog.FUID
     * @return t_project_plan_bglog.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_project_plan_bglog.FUID
     * @param fuid the value for t_project_plan_bglog.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_project_plan_bglog.FCDATE
     * @return t_project_plan_bglog.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_project_plan_bglog.FCDATE
     * @param fcdate the value for t_project_plan_bglog.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_project_plan_bglog.FUDATE
     * @return t_project_plan_bglog.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_project_plan_bglog.FUDATE
     * @param fudate the value for t_project_plan_bglog.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 项目ID 字段:t_project_plan_bglog.FProjectID
     * @return t_project_plan_bglog.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:t_project_plan_bglog.FProjectID
     * @param fprojectid the value for t_project_plan_bglog.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 项目计划ID 字段:t_project_plan_bglog.FProjectPlanID
     * @return t_project_plan_bglog.FProjectPlanID, 项目计划ID
     * @mbg.generated
     */
    public Long getFprojectplanid() {
        return fprojectplanid;
    }

    /**
     * 设置 项目计划ID 字段:t_project_plan_bglog.FProjectPlanID
     * @param fprojectplanid the value for t_project_plan_bglog.FProjectPlanID, 项目计划ID
     * @mbg.generated
     */
    public void setFprojectplanid(Long fprojectplanid) {
        this.fprojectplanid = fprojectplanid;
    }

    /**
     * 获取 更新前计划开始 字段:t_project_plan_bglog.FQPSDate
     * @return t_project_plan_bglog.FQPSDate, 更新前计划开始
     * @mbg.generated
     */
    public Date getFqpsdate() {
        return fqpsdate;
    }

    /**
     * 设置 更新前计划开始 字段:t_project_plan_bglog.FQPSDate
     * @param fqpsdate the value for t_project_plan_bglog.FQPSDate, 更新前计划开始
     * @mbg.generated
     */
    public void setFqpsdate(Date fqpsdate) {
        this.fqpsdate = fqpsdate;
    }

    /**
     * 获取 更新前计划结束 字段:t_project_plan_bglog.FQPEDate
     * @return t_project_plan_bglog.FQPEDate, 更新前计划结束
     * @mbg.generated
     */
    public Date getFqpedate() {
        return fqpedate;
    }

    /**
     * 设置 更新前计划结束 字段:t_project_plan_bglog.FQPEDate
     * @param fqpedate the value for t_project_plan_bglog.FQPEDate, 更新前计划结束
     * @mbg.generated
     */
    public void setFqpedate(Date fqpedate) {
        this.fqpedate = fqpedate;
    }

    /**
     * 获取 更新后计划开始 字段:t_project_plan_bglog.FPSDate
     * @return t_project_plan_bglog.FPSDate, 更新后计划开始
     * @mbg.generated
     */
    public Date getFpsdate() {
        return fpsdate;
    }

    /**
     * 设置 更新后计划开始 字段:t_project_plan_bglog.FPSDate
     * @param fpsdate the value for t_project_plan_bglog.FPSDate, 更新后计划开始
     * @mbg.generated
     */
    public void setFpsdate(Date fpsdate) {
        this.fpsdate = fpsdate;
    }

    /**
     * 获取 更新后计划结束 字段:t_project_plan_bglog.FPEDate
     * @return t_project_plan_bglog.FPEDate, 更新后计划结束
     * @mbg.generated
     */
    public Date getFpedate() {
        return fpedate;
    }

    /**
     * 设置 更新后计划结束 字段:t_project_plan_bglog.FPEDate
     * @param fpedate the value for t_project_plan_bglog.FPEDate, 更新后计划结束
     * @mbg.generated
     */
    public void setFpedate(Date fpedate) {
        this.fpedate = fpedate;
    }

    /**
     * 获取 备注 字段:t_project_plan_bglog.FNote
     * @return t_project_plan_bglog.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_project_plan_bglog.FNote
     * @param fnote the value for t_project_plan_bglog.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_project_plan_bglog
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
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", fprojectplanid=").append(fprojectplanid);
        sb.append(", fqpsdate=").append(fqpsdate);
        sb.append(", fqpedate=").append(fqpedate);
        sb.append(", fpsdate=").append(fpsdate);
        sb.append(", fpedate=").append(fpedate);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_plan_bglog
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
        TProjectPlanBglog other = (TProjectPlanBglog) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFprojectplanid() == null ? other.getFprojectplanid() == null : this.getFprojectplanid().equals(other.getFprojectplanid()))
            && (this.getFqpsdate() == null ? other.getFqpsdate() == null : this.getFqpsdate().equals(other.getFqpsdate()))
            && (this.getFqpedate() == null ? other.getFqpedate() == null : this.getFqpedate().equals(other.getFqpedate()))
            && (this.getFpsdate() == null ? other.getFpsdate() == null : this.getFpsdate().equals(other.getFpsdate()))
            && (this.getFpedate() == null ? other.getFpedate() == null : this.getFpedate().equals(other.getFpedate()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_plan_bglog
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
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFprojectplanid() == null) ? 0 : getFprojectplanid().hashCode());
        result = prime * result + ((getFqpsdate() == null) ? 0 : getFqpsdate().hashCode());
        result = prime * result + ((getFqpedate() == null) ? 0 : getFqpedate().hashCode());
        result = prime * result + ((getFpsdate() == null) ? 0 : getFpsdate().hashCode());
        result = prime * result + ((getFpedate() == null) ? 0 : getFpedate().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}