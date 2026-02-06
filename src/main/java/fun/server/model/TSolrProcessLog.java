package fun.server.model;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_solr_process_log
*/
@Table(name = "t_solr_process_log")
public class TSolrProcessLog implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_solr_process_log.FKeyID
     * @mbg.generated
     */
    private Long fkeyid;

    /**
     * 来源表名
     * 表字段 : teaching_diversity..t_solr_process_log.FSourceTable
     * @mbg.generated
     */
    private String fsourcetable;

    /**
     * 来源表中的主键ID
     * 表字段 : teaching_diversity..t_solr_process_log.FSourceID
     * @mbg.generated
     */
    private Long fsourceid;

    /**
     * 处理时间
     * 表字段 : teaching_diversity..t_solr_process_log.FProcessTime
     * @mbg.generated
     */
    private Date fprocesstime;

    /**
     * 处理状态：0-未处理；1-处理中；2-处理完成；9-处理错误
     * 表字段 : teaching_diversity..t_solr_process_log.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 处理详情（如错误信息）
     * 表字段 : teaching_diversity..t_solr_process_log.FMessage
     * @mbg.generated
     */
    private String fmessage;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public TSolrProcessLog(Long fkeyid, String fsourcetable, Long fsourceid, Date fprocesstime, Integer fstate) {
        this.fkeyid = fkeyid;
        this.fsourcetable = fsourcetable;
        this.fsourceid = fsourceid;
        this.fprocesstime = fprocesstime;
        this.fstate = fstate;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public TSolrProcessLog(Long fkeyid, String fsourcetable, Long fsourceid, Date fprocesstime, Integer fstate, String fmessage) {
        this.fkeyid = fkeyid;
        this.fsourcetable = fsourcetable;
        this.fsourceid = fsourceid;
        this.fprocesstime = fprocesstime;
        this.fstate = fstate;
        this.fmessage = fmessage;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public TSolrProcessLog() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_solr_process_log.FKeyID
     * @return teaching_diversity..t_solr_process_log.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_solr_process_log.FKeyID
     * @param fkeyid the value for teaching_diversity..t_solr_process_log.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取 来源表名 字段:teaching_diversity..t_solr_process_log.FSourceTable
     * @return teaching_diversity..t_solr_process_log.FSourceTable, 来源表名
     * @mbg.generated
     */
    public String getFsourcetable() {
        return fsourcetable;
    }

    /**
     * 设置 来源表名 字段:teaching_diversity..t_solr_process_log.FSourceTable
     * @param fsourcetable the value for teaching_diversity..t_solr_process_log.FSourceTable, 来源表名
     * @mbg.generated
     */
    public void setFsourcetable(String fsourcetable) {
        this.fsourcetable = fsourcetable == null ? null : fsourcetable.trim();
    }

    /**
     * 获取 来源表中的主键ID 字段:teaching_diversity..t_solr_process_log.FSourceID
     * @return teaching_diversity..t_solr_process_log.FSourceID, 来源表中的主键ID
     * @mbg.generated
     */
    public Long getFsourceid() {
        return fsourceid;
    }

    /**
     * 设置 来源表中的主键ID 字段:teaching_diversity..t_solr_process_log.FSourceID
     * @param fsourceid the value for teaching_diversity..t_solr_process_log.FSourceID, 来源表中的主键ID
     * @mbg.generated
     */
    public void setFsourceid(Long fsourceid) {
        this.fsourceid = fsourceid;
    }

    /**
     * 获取 处理时间 字段:teaching_diversity..t_solr_process_log.FProcessTime
     * @return teaching_diversity..t_solr_process_log.FProcessTime, 处理时间
     * @mbg.generated
     */
    public Date getFprocesstime() {
        return fprocesstime;
    }

    /**
     * 设置 处理时间 字段:teaching_diversity..t_solr_process_log.FProcessTime
     * @param fprocesstime the value for teaching_diversity..t_solr_process_log.FProcessTime, 处理时间
     * @mbg.generated
     */
    public void setFprocesstime(Date fprocesstime) {
        this.fprocesstime = fprocesstime;
    }

    /**
     * 获取 处理状态：0-未处理；1-处理中；2-处理完成；9-处理错误 字段:teaching_diversity..t_solr_process_log.FState
     * @return teaching_diversity..t_solr_process_log.FState, 处理状态：0-未处理；1-处理中；2-处理完成；9-处理错误
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 处理状态：0-未处理；1-处理中；2-处理完成；9-处理错误 字段:teaching_diversity..t_solr_process_log.FState
     * @param fstate the value for teaching_diversity..t_solr_process_log.FState, 处理状态：0-未处理；1-处理中；2-处理完成；9-处理错误
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 处理详情（如错误信息） 字段:teaching_diversity..t_solr_process_log.FMessage
     * @return teaching_diversity..t_solr_process_log.FMessage, 处理详情（如错误信息）
     * @mbg.generated
     */
    public String getFmessage() {
        return fmessage;
    }

    /**
     * 设置 处理详情（如错误信息） 字段:teaching_diversity..t_solr_process_log.FMessage
     * @param fmessage the value for teaching_diversity..t_solr_process_log.FMessage, 处理详情（如错误信息）
     * @mbg.generated
     */
    public void setFmessage(String fmessage) {
        this.fmessage = fmessage == null ? null : fmessage.trim();
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fkeyid=").append(fkeyid);
        sb.append(", fsourcetable=").append(fsourcetable);
        sb.append(", fsourceid=").append(fsourceid);
        sb.append(", fprocesstime=").append(fprocesstime);
        sb.append(", fstate=").append(fstate);
        sb.append(", fmessage=").append(fmessage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_solr_process_log
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
        TSolrProcessLog other = (TSolrProcessLog) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFsourcetable() == null ? other.getFsourcetable() == null : this.getFsourcetable().equals(other.getFsourcetable()))
            && (this.getFsourceid() == null ? other.getFsourceid() == null : this.getFsourceid().equals(other.getFsourceid()))
            && (this.getFprocesstime() == null ? other.getFprocesstime() == null : this.getFprocesstime().equals(other.getFprocesstime()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFmessage() == null ? other.getFmessage() == null : this.getFmessage().equals(other.getFmessage()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFsourcetable() == null) ? 0 : getFsourcetable().hashCode());
        result = prime * result + ((getFsourceid() == null) ? 0 : getFsourceid().hashCode());
        result = prime * result + ((getFprocesstime() == null) ? 0 : getFprocesstime().hashCode());
        result = prime * result + ((getFstate() == null) ? 0 : getFstate().hashCode());
        result = prime * result + ((getFmessage() == null) ? 0 : getFmessage().hashCode());
        return result;
    }
}