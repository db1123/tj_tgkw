package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_subdata_check")
public class TSubdataCheck implements Serializable {
    /**
     * 
     * 表字段 : t_subdata_check.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_subdata_check.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_subdata_check.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_subdata_check.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_subdata_check.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-未审核；1-已审核
     * 表字段 : t_subdata_check.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 审核状态,0-未到审核,1-需要审核
     * 表字段 : t_subdata_check.FCheckState
     * @mbg.generated
     */
    private Integer fcheckstate;

    /**
     * 交付物ID
     * 表字段 : t_subdata_check.FSubDataID
     * @mbg.generated
     */
    private Long fsubdataid;

    /**
     * 审核流程ID
     * 表字段 : t_subdata_check.FCheckFlowID
     * @mbg.generated
     */
    private Long fcheckflowid;

    /**
     * 工作流节点ID
     * 表字段 : t_subdata_check.FNodeID
     * @mbg.generated
     */
    private Long fnodeid;

    /**
     * 审核人ID,T_User主键,继承节点人
     * 表字段 : t_subdata_check.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    /**
     * 1-通过,2-不通过
     * 表字段 : t_subdata_check.FCheckResult
     * @mbg.generated
     */
    private Integer fcheckresult;

    /**
     * 审核意见
     * 表字段 : t_subdata_check.FCheckOpinion
     * @mbg.generated
     */
    private String fcheckopinion;

    /**
     * 备注
     * 表字段 : t_subdata_check.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_subdata_check
     * @mbg.generated
     */
    public TSubdataCheck(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer fcheckstate, Long fsubdataid, Long fcheckflowid, Long fnodeid, Long fuserid, Integer fcheckresult, String fcheckopinion, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcheckstate = fcheckstate;
        this.fsubdataid = fsubdataid;
        this.fcheckflowid = fcheckflowid;
        this.fnodeid = fnodeid;
        this.fuserid = fuserid;
        this.fcheckresult = fcheckresult;
        this.fcheckopinion = fcheckopinion;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_subdata_check
     * @mbg.generated
     */
    public TSubdataCheck() {
        super();
    }

    /**
     * 获取  字段:t_subdata_check.FKeyID
     * @return t_subdata_check.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_subdata_check.FKeyID
     * @param fkeyid the value for t_subdata_check.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_subdata_check.FCID
     * @return t_subdata_check.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_subdata_check.FCID
     * @param fcid the value for t_subdata_check.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_subdata_check.FUID
     * @return t_subdata_check.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_subdata_check.FUID
     * @param fuid the value for t_subdata_check.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_subdata_check.FCDATE
     * @return t_subdata_check.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_subdata_check.FCDATE
     * @param fcdate the value for t_subdata_check.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_subdata_check.FUDATE
     * @return t_subdata_check.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_subdata_check.FUDATE
     * @param fudate the value for t_subdata_check.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-未审核；1-已审核 字段:t_subdata_check.FState
     * @return t_subdata_check.FState, 0-未审核；1-已审核
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-未审核；1-已审核 字段:t_subdata_check.FState
     * @param fstate the value for t_subdata_check.FState, 0-未审核；1-已审核
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 审核状态,0-未到审核,1-需要审核 字段:t_subdata_check.FCheckState
     * @return t_subdata_check.FCheckState, 审核状态,0-未到审核,1-需要审核
     * @mbg.generated
     */
    public Integer getFcheckstate() {
        return fcheckstate;
    }

    /**
     * 设置 审核状态,0-未到审核,1-需要审核 字段:t_subdata_check.FCheckState
     * @param fcheckstate the value for t_subdata_check.FCheckState, 审核状态,0-未到审核,1-需要审核
     * @mbg.generated
     */
    public void setFcheckstate(Integer fcheckstate) {
        this.fcheckstate = fcheckstate;
    }

    /**
     * 获取 交付物ID 字段:t_subdata_check.FSubDataID
     * @return t_subdata_check.FSubDataID, 交付物ID
     * @mbg.generated
     */
    public Long getFsubdataid() {
        return fsubdataid;
    }

    /**
     * 设置 交付物ID 字段:t_subdata_check.FSubDataID
     * @param fsubdataid the value for t_subdata_check.FSubDataID, 交付物ID
     * @mbg.generated
     */
    public void setFsubdataid(Long fsubdataid) {
        this.fsubdataid = fsubdataid;
    }

    /**
     * 获取 审核流程ID 字段:t_subdata_check.FCheckFlowID
     * @return t_subdata_check.FCheckFlowID, 审核流程ID
     * @mbg.generated
     */
    public Long getFcheckflowid() {
        return fcheckflowid;
    }

    /**
     * 设置 审核流程ID 字段:t_subdata_check.FCheckFlowID
     * @param fcheckflowid the value for t_subdata_check.FCheckFlowID, 审核流程ID
     * @mbg.generated
     */
    public void setFcheckflowid(Long fcheckflowid) {
        this.fcheckflowid = fcheckflowid;
    }

    /**
     * 获取 工作流节点ID 字段:t_subdata_check.FNodeID
     * @return t_subdata_check.FNodeID, 工作流节点ID
     * @mbg.generated
     */
    public Long getFnodeid() {
        return fnodeid;
    }

    /**
     * 设置 工作流节点ID 字段:t_subdata_check.FNodeID
     * @param fnodeid the value for t_subdata_check.FNodeID, 工作流节点ID
     * @mbg.generated
     */
    public void setFnodeid(Long fnodeid) {
        this.fnodeid = fnodeid;
    }

    /**
     * 获取 审核人ID,T_User主键,继承节点人 字段:t_subdata_check.FUserID
     * @return t_subdata_check.FUserID, 审核人ID,T_User主键,继承节点人
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 审核人ID,T_User主键,继承节点人 字段:t_subdata_check.FUserID
     * @param fuserid the value for t_subdata_check.FUserID, 审核人ID,T_User主键,继承节点人
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 获取 1-通过,2-不通过 字段:t_subdata_check.FCheckResult
     * @return t_subdata_check.FCheckResult, 1-通过,2-不通过
     * @mbg.generated
     */
    public Integer getFcheckresult() {
        return fcheckresult;
    }

    /**
     * 设置 1-通过,2-不通过 字段:t_subdata_check.FCheckResult
     * @param fcheckresult the value for t_subdata_check.FCheckResult, 1-通过,2-不通过
     * @mbg.generated
     */
    public void setFcheckresult(Integer fcheckresult) {
        this.fcheckresult = fcheckresult;
    }

    /**
     * 获取 审核意见 字段:t_subdata_check.FCheckOpinion
     * @return t_subdata_check.FCheckOpinion, 审核意见
     * @mbg.generated
     */
    public String getFcheckopinion() {
        return fcheckopinion;
    }

    /**
     * 设置 审核意见 字段:t_subdata_check.FCheckOpinion
     * @param fcheckopinion the value for t_subdata_check.FCheckOpinion, 审核意见
     * @mbg.generated
     */
    public void setFcheckopinion(String fcheckopinion) {
        this.fcheckopinion = fcheckopinion == null ? null : fcheckopinion.trim();
    }

    /**
     * 获取 备注 字段:t_subdata_check.FNote
     * @return t_subdata_check.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_subdata_check.FNote
     * @param fnote the value for t_subdata_check.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_subdata_check
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
        sb.append(", fcheckstate=").append(fcheckstate);
        sb.append(", fsubdataid=").append(fsubdataid);
        sb.append(", fcheckflowid=").append(fcheckflowid);
        sb.append(", fnodeid=").append(fnodeid);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fcheckresult=").append(fcheckresult);
        sb.append(", fcheckopinion=").append(fcheckopinion);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_subdata_check
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
        TSubdataCheck other = (TSubdataCheck) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcheckstate() == null ? other.getFcheckstate() == null : this.getFcheckstate().equals(other.getFcheckstate()))
            && (this.getFsubdataid() == null ? other.getFsubdataid() == null : this.getFsubdataid().equals(other.getFsubdataid()))
            && (this.getFcheckflowid() == null ? other.getFcheckflowid() == null : this.getFcheckflowid().equals(other.getFcheckflowid()))
            && (this.getFnodeid() == null ? other.getFnodeid() == null : this.getFnodeid().equals(other.getFnodeid()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()))
            && (this.getFcheckresult() == null ? other.getFcheckresult() == null : this.getFcheckresult().equals(other.getFcheckresult()))
            && (this.getFcheckopinion() == null ? other.getFcheckopinion() == null : this.getFcheckopinion().equals(other.getFcheckopinion()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_subdata_check
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
        result = prime * result + ((getFcheckstate() == null) ? 0 : getFcheckstate().hashCode());
        result = prime * result + ((getFsubdataid() == null) ? 0 : getFsubdataid().hashCode());
        result = prime * result + ((getFcheckflowid() == null) ? 0 : getFcheckflowid().hashCode());
        result = prime * result + ((getFnodeid() == null) ? 0 : getFnodeid().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        result = prime * result + ((getFcheckresult() == null) ? 0 : getFcheckresult().hashCode());
        result = prime * result + ((getFcheckopinion() == null) ? 0 : getFcheckopinion().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}