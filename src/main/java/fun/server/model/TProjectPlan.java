package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_project_plan")
public class TProjectPlan implements Serializable {
    /**
     * 
     * 表字段 : t_project_plan.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_project_plan.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_project_plan.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_project_plan.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_project_plan.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-未开,1-开,2-完
     * 表字段 : t_project_plan.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 完成状态,0-未完,1-按时,2-逾期
     * 表字段 : t_project_plan.FEndState
     * @mbg.generated
     */
    private Integer fendstate;

    /**
     * 项目ID
     * 表字段 : t_project_plan.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 计划名称
     * 表字段 : t_project_plan.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 主要负责人
     * 表字段 : t_project_plan.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    /**
     * 计划开始时间
     * 表字段 : t_project_plan.FPSDate
     * @mbg.generated
     */
    private Date fpsdate;

    /**
     * 计划完成时间
     * 表字段 : t_project_plan.FPEDate
     * @mbg.generated
     */
    private Date fpedate;

    /**
     * 实际开始时间
     * 表字段 : t_project_plan.FStartDate
     * @mbg.generated
     */
    private Date fstartdate;

    /**
     * 实际完成时间
     * 表字段 : t_project_plan.FEndDate
     * @mbg.generated
     */
    private Date fenddate;

    /**
     * 是否为里程碑
     * 表字段 : t_project_plan.FISMilepost
     * @mbg.generated
     */
    private Integer fismilepost;

    /**
     * 是否需要交付物
     * 表字段 : t_project_plan.FISSubData
     * @mbg.generated
     */
    private Integer fissubdata;

    /**
     * 备注
     * 表字段 : t_project_plan.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_plan
     * @mbg.generated
     */
    public TProjectPlan(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer fendstate, Long fprojectid, String fname, Long fuserid, Date fpsdate, Date fpedate, Date fstartdate, Date fenddate, Integer fismilepost, Integer fissubdata, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fendstate = fendstate;
        this.fprojectid = fprojectid;
        this.fname = fname;
        this.fuserid = fuserid;
        this.fpsdate = fpsdate;
        this.fpedate = fpedate;
        this.fstartdate = fstartdate;
        this.fenddate = fenddate;
        this.fismilepost = fismilepost;
        this.fissubdata = fissubdata;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_project_plan
     * @mbg.generated
     */
    public TProjectPlan() {
        super();
    }

    /**
     * 获取  字段:t_project_plan.FKeyID
     * @return t_project_plan.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_plan.FKeyID
     * @param fkeyid the value for t_project_plan.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_project_plan.FCID
     * @return t_project_plan.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_project_plan.FCID
     * @param fcid the value for t_project_plan.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_project_plan.FUID
     * @return t_project_plan.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_project_plan.FUID
     * @param fuid the value for t_project_plan.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_project_plan.FCDATE
     * @return t_project_plan.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_project_plan.FCDATE
     * @param fcdate the value for t_project_plan.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_project_plan.FUDATE
     * @return t_project_plan.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_project_plan.FUDATE
     * @param fudate the value for t_project_plan.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-未开,1-开,2-完 字段:t_project_plan.FState
     * @return t_project_plan.FState, 0-未开,1-开,2-完
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-未开,1-开,2-完 字段:t_project_plan.FState
     * @param fstate the value for t_project_plan.FState, 0-未开,1-开,2-完
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 完成状态,0-未完,1-按时,2-逾期 字段:t_project_plan.FEndState
     * @return t_project_plan.FEndState, 完成状态,0-未完,1-按时,2-逾期
     * @mbg.generated
     */
    public Integer getFendstate() {
        return fendstate;
    }

    /**
     * 设置 完成状态,0-未完,1-按时,2-逾期 字段:t_project_plan.FEndState
     * @param fendstate the value for t_project_plan.FEndState, 完成状态,0-未完,1-按时,2-逾期
     * @mbg.generated
     */
    public void setFendstate(Integer fendstate) {
        this.fendstate = fendstate;
    }

    /**
     * 获取 项目ID 字段:t_project_plan.FProjectID
     * @return t_project_plan.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:t_project_plan.FProjectID
     * @param fprojectid the value for t_project_plan.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 计划名称 字段:t_project_plan.FName
     * @return t_project_plan.FName, 计划名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 计划名称 字段:t_project_plan.FName
     * @param fname the value for t_project_plan.FName, 计划名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 主要负责人 字段:t_project_plan.FUserID
     * @return t_project_plan.FUserID, 主要负责人
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 主要负责人 字段:t_project_plan.FUserID
     * @param fuserid the value for t_project_plan.FUserID, 主要负责人
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 获取 计划开始时间 字段:t_project_plan.FPSDate
     * @return t_project_plan.FPSDate, 计划开始时间
     * @mbg.generated
     */
    public Date getFpsdate() {
        return fpsdate;
    }

    /**
     * 设置 计划开始时间 字段:t_project_plan.FPSDate
     * @param fpsdate the value for t_project_plan.FPSDate, 计划开始时间
     * @mbg.generated
     */
    public void setFpsdate(Date fpsdate) {
        this.fpsdate = fpsdate;
    }

    /**
     * 获取 计划完成时间 字段:t_project_plan.FPEDate
     * @return t_project_plan.FPEDate, 计划完成时间
     * @mbg.generated
     */
    public Date getFpedate() {
        return fpedate;
    }

    /**
     * 设置 计划完成时间 字段:t_project_plan.FPEDate
     * @param fpedate the value for t_project_plan.FPEDate, 计划完成时间
     * @mbg.generated
     */
    public void setFpedate(Date fpedate) {
        this.fpedate = fpedate;
    }

    /**
     * 获取 实际开始时间 字段:t_project_plan.FStartDate
     * @return t_project_plan.FStartDate, 实际开始时间
     * @mbg.generated
     */
    public Date getFstartdate() {
        return fstartdate;
    }

    /**
     * 设置 实际开始时间 字段:t_project_plan.FStartDate
     * @param fstartdate the value for t_project_plan.FStartDate, 实际开始时间
     * @mbg.generated
     */
    public void setFstartdate(Date fstartdate) {
        this.fstartdate = fstartdate;
    }

    /**
     * 获取 实际完成时间 字段:t_project_plan.FEndDate
     * @return t_project_plan.FEndDate, 实际完成时间
     * @mbg.generated
     */
    public Date getFenddate() {
        return fenddate;
    }

    /**
     * 设置 实际完成时间 字段:t_project_plan.FEndDate
     * @param fenddate the value for t_project_plan.FEndDate, 实际完成时间
     * @mbg.generated
     */
    public void setFenddate(Date fenddate) {
        this.fenddate = fenddate;
    }

    /**
     * 获取 是否为里程碑 字段:t_project_plan.FISMilepost
     * @return t_project_plan.FISMilepost, 是否为里程碑
     * @mbg.generated
     */
    public Integer getFismilepost() {
        return fismilepost;
    }

    /**
     * 设置 是否为里程碑 字段:t_project_plan.FISMilepost
     * @param fismilepost the value for t_project_plan.FISMilepost, 是否为里程碑
     * @mbg.generated
     */
    public void setFismilepost(Integer fismilepost) {
        this.fismilepost = fismilepost;
    }

    /**
     * 获取 是否需要交付物 字段:t_project_plan.FISSubData
     * @return t_project_plan.FISSubData, 是否需要交付物
     * @mbg.generated
     */
    public Integer getFissubdata() {
        return fissubdata;
    }

    /**
     * 设置 是否需要交付物 字段:t_project_plan.FISSubData
     * @param fissubdata the value for t_project_plan.FISSubData, 是否需要交付物
     * @mbg.generated
     */
    public void setFissubdata(Integer fissubdata) {
        this.fissubdata = fissubdata;
    }

    /**
     * 获取 备注 字段:t_project_plan.FNote
     * @return t_project_plan.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_project_plan.FNote
     * @param fnote the value for t_project_plan.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_project_plan
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
        sb.append(", fendstate=").append(fendstate);
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", fname=").append(fname);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fpsdate=").append(fpsdate);
        sb.append(", fpedate=").append(fpedate);
        sb.append(", fstartdate=").append(fstartdate);
        sb.append(", fenddate=").append(fenddate);
        sb.append(", fismilepost=").append(fismilepost);
        sb.append(", fissubdata=").append(fissubdata);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_plan
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
        TProjectPlan other = (TProjectPlan) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFendstate() == null ? other.getFendstate() == null : this.getFendstate().equals(other.getFendstate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()))
            && (this.getFpsdate() == null ? other.getFpsdate() == null : this.getFpsdate().equals(other.getFpsdate()))
            && (this.getFpedate() == null ? other.getFpedate() == null : this.getFpedate().equals(other.getFpedate()))
            && (this.getFstartdate() == null ? other.getFstartdate() == null : this.getFstartdate().equals(other.getFstartdate()))
            && (this.getFenddate() == null ? other.getFenddate() == null : this.getFenddate().equals(other.getFenddate()))
            && (this.getFismilepost() == null ? other.getFismilepost() == null : this.getFismilepost().equals(other.getFismilepost()))
            && (this.getFissubdata() == null ? other.getFissubdata() == null : this.getFissubdata().equals(other.getFissubdata()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_plan
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
        result = prime * result + ((getFendstate() == null) ? 0 : getFendstate().hashCode());
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        result = prime * result + ((getFpsdate() == null) ? 0 : getFpsdate().hashCode());
        result = prime * result + ((getFpedate() == null) ? 0 : getFpedate().hashCode());
        result = prime * result + ((getFstartdate() == null) ? 0 : getFstartdate().hashCode());
        result = prime * result + ((getFenddate() == null) ? 0 : getFenddate().hashCode());
        result = prime * result + ((getFismilepost() == null) ? 0 : getFismilepost().hashCode());
        result = prime * result + ((getFissubdata() == null) ? 0 : getFissubdata().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}