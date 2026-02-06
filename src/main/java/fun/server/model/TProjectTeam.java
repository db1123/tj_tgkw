package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_project_team")
public class TProjectTeam implements Serializable {
    /**
     * 
     * 表字段 : t_project_team.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_project_team.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_project_team.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_project_team.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_project_team.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态
     * 表字段 : t_project_team.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 项目ID
     * 表字段 : t_project_team.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 人员ID
     * 表字段 : t_project_team.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    /**
     * 1-负责人,0-参与人
     * 表字段 : t_project_team.FRole
     * @mbg.generated
     */
    private Integer frole;

    /**
     * 职责
     * 表字段 : t_project_team.FDuty
     * @mbg.generated
     */
    private String fduty;

    /**
     * 备注
     * 表字段 : t_project_team.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_team
     * @mbg.generated
     */
    public TProjectTeam(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fprojectid, Long fuserid, Integer frole, String fduty, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fprojectid = fprojectid;
        this.fuserid = fuserid;
        this.frole = frole;
        this.fduty = fduty;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_project_team
     * @mbg.generated
     */
    public TProjectTeam() {
        super();
    }

    /**
     * 获取  字段:t_project_team.FKeyID
     * @return t_project_team.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_team.FKeyID
     * @param fkeyid the value for t_project_team.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_project_team.FCID
     * @return t_project_team.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_project_team.FCID
     * @param fcid the value for t_project_team.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_project_team.FUID
     * @return t_project_team.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_project_team.FUID
     * @param fuid the value for t_project_team.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_project_team.FCDATE
     * @return t_project_team.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_project_team.FCDATE
     * @param fcdate the value for t_project_team.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_project_team.FUDATE
     * @return t_project_team.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_project_team.FUDATE
     * @param fudate the value for t_project_team.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态 字段:t_project_team.FState
     * @return t_project_team.FState, 状态
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态 字段:t_project_team.FState
     * @param fstate the value for t_project_team.FState, 状态
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 项目ID 字段:t_project_team.FProjectID
     * @return t_project_team.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:t_project_team.FProjectID
     * @param fprojectid the value for t_project_team.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 人员ID 字段:t_project_team.FUserID
     * @return t_project_team.FUserID, 人员ID
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 人员ID 字段:t_project_team.FUserID
     * @param fuserid the value for t_project_team.FUserID, 人员ID
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 获取 1-负责人,0-参与人 字段:t_project_team.FRole
     * @return t_project_team.FRole, 1-负责人,0-参与人
     * @mbg.generated
     */
    public Integer getFrole() {
        return frole;
    }

    /**
     * 设置 1-负责人,0-参与人 字段:t_project_team.FRole
     * @param frole the value for t_project_team.FRole, 1-负责人,0-参与人
     * @mbg.generated
     */
    public void setFrole(Integer frole) {
        this.frole = frole;
    }

    /**
     * 获取 职责 字段:t_project_team.FDuty
     * @return t_project_team.FDuty, 职责
     * @mbg.generated
     */
    public String getFduty() {
        return fduty;
    }

    /**
     * 设置 职责 字段:t_project_team.FDuty
     * @param fduty the value for t_project_team.FDuty, 职责
     * @mbg.generated
     */
    public void setFduty(String fduty) {
        this.fduty = fduty == null ? null : fduty.trim();
    }

    /**
     * 获取 备注 字段:t_project_team.FNote
     * @return t_project_team.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_project_team.FNote
     * @param fnote the value for t_project_team.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_project_team
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
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", frole=").append(frole);
        sb.append(", fduty=").append(fduty);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_team
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
        TProjectTeam other = (TProjectTeam) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()))
            && (this.getFrole() == null ? other.getFrole() == null : this.getFrole().equals(other.getFrole()))
            && (this.getFduty() == null ? other.getFduty() == null : this.getFduty().equals(other.getFduty()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_team
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
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        result = prime * result + ((getFrole() == null) ? 0 : getFrole().hashCode());
        result = prime * result + ((getFduty() == null) ? 0 : getFduty().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}