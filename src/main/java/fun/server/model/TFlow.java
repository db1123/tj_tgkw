package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 表名：t_flow
*/
@Table(name = "t_flow")
public class TFlow implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_tp_id
     * @mbg.generated
     */
    private Long fTpId;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_explain
     * @mbg.generated
     */
    private String fExplain;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_xml
     * @mbg.generated
     */
    private String fXml;

    /**
     * 
     * 表字段 : teaching_diversity..t_flow.f_json
     * @mbg.generated
     */
    private String fJson;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_flow
     * @mbg.generated
     */
    public TFlow(Long fKeyId, Long fTpId, String fName, String fExplain, Long fCId, Long fUId, Date fCDate, Date fUDate) {
        this.fKeyId = fKeyId;
        this.fTpId = fTpId;
        this.fName = fName;
        this.fExplain = fExplain;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_flow
     * @mbg.generated
     */
    public TFlow(Long fKeyId, Long fTpId, String fName, String fExplain, Long fCId, Long fUId, Date fCDate, Date fUDate, String fXml, String fJson) {
        this.fKeyId = fKeyId;
        this.fTpId = fTpId;
        this.fName = fName;
        this.fExplain = fExplain;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
        this.fXml = fXml;
        this.fJson = fJson;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_flow
     * @mbg.generated
     */
    public TFlow() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_key_id
     * @return teaching_diversity..t_flow.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_key_id
     * @param fKeyId the value for teaching_diversity..t_flow.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_tp_id
     * @return teaching_diversity..t_flow.f_tp_id, 
     * @mbg.generated
     */
    public Long getfTpId() {
        return fTpId;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_tp_id
     * @param fTpId the value for teaching_diversity..t_flow.f_tp_id, 
     * @mbg.generated
     */
    public void setfTpId(Long fTpId) {
        this.fTpId = fTpId;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_name
     * @return teaching_diversity..t_flow.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_name
     * @param fName the value for teaching_diversity..t_flow.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_explain
     * @return teaching_diversity..t_flow.f_explain, 
     * @mbg.generated
     */
    public String getfExplain() {
        return fExplain;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_explain
     * @param fExplain the value for teaching_diversity..t_flow.f_explain, 
     * @mbg.generated
     */
    public void setfExplain(String fExplain) {
        this.fExplain = fExplain == null ? null : fExplain.trim();
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_c_id
     * @return teaching_diversity..t_flow.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_c_id
     * @param fCId the value for teaching_diversity..t_flow.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_u_id
     * @return teaching_diversity..t_flow.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_u_id
     * @param fUId the value for teaching_diversity..t_flow.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_c_date
     * @return teaching_diversity..t_flow.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_c_date
     * @param fCDate the value for teaching_diversity..t_flow.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_u_date
     * @return teaching_diversity..t_flow.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_u_date
     * @param fUDate the value for teaching_diversity..t_flow.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_xml
     * @return teaching_diversity..t_flow.f_xml, 
     * @mbg.generated
     */
    public String getfXml() {
        return fXml;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_xml
     * @param fXml the value for teaching_diversity..t_flow.f_xml, 
     * @mbg.generated
     */
    public void setfXml(String fXml) {
        this.fXml = fXml == null ? null : fXml.trim();
    }

    /**
     * 获取  字段:teaching_diversity..t_flow.f_json
     * @return teaching_diversity..t_flow.f_json, 
     * @mbg.generated
     */
    public String getfJson() {
        return fJson;
    }

    /**
     * 设置  字段:teaching_diversity..t_flow.f_json
     * @param fJson the value for teaching_diversity..t_flow.f_json, 
     * @mbg.generated
     */
    public void setfJson(String fJson) {
        this.fJson = fJson == null ? null : fJson.trim();
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_flow
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fTpId=").append(fTpId);
        sb.append(", fName=").append(fName);
        sb.append(", fExplain=").append(fExplain);
        sb.append(", fCId=").append(fCId);
        sb.append(", fUId=").append(fUId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUDate=").append(fUDate);
        sb.append(", fXml=").append(fXml);
        sb.append(", fJson=").append(fJson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_flow
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
        TFlow other = (TFlow) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfTpId() == null ? other.getfTpId() == null : this.getfTpId().equals(other.getfTpId()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfExplain() == null ? other.getfExplain() == null : this.getfExplain().equals(other.getfExplain()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()))
            && (this.getfXml() == null ? other.getfXml() == null : this.getfXml().equals(other.getfXml()))
            && (this.getfJson() == null ? other.getfJson() == null : this.getfJson().equals(other.getfJson()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_flow
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfTpId() == null) ? 0 : getfTpId().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfExplain() == null) ? 0 : getfExplain().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        result = prime * result + ((getfXml() == null) ? 0 : getfXml().hashCode());
        result = prime * result + ((getfJson() == null) ? 0 : getfJson().hashCode());
        return result;
    }
}