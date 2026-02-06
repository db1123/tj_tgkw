package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_project_log")
public class TProjectLog implements Serializable {
    /**
     * 
     * 表字段 : t_project_log.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_project_log.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_project_log.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 项目ID
     * 表字段 : t_project_log.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 功能
     * 表字段 : t_project_log.FFunction
     * @mbg.generated
     */
    private String ffunction;

    /**
     * 操作
     * 表字段 : t_project_log.FOperation
     * @mbg.generated
     */
    private String foperation;

    /**
     * 有可以链接跳转的输入
     * 表字段 : t_project_log.FLink
     * @mbg.generated
     */
    private String flink;

    /**
     * 描述
     * 表字段 : t_project_log.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_log
     * @mbg.generated
     */
    public TProjectLog(Long fkeyid, Long fcid, Date fcdate, Long fprojectid, String ffunction, String foperation, String flink, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fcdate = fcdate;
        this.fprojectid = fprojectid;
        this.ffunction = ffunction;
        this.foperation = foperation;
        this.flink = flink;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_project_log
     * @mbg.generated
     */
    public TProjectLog() {
        super();
    }

    /**
     * 获取  字段:t_project_log.FKeyID
     * @return t_project_log.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_log.FKeyID
     * @param fkeyid the value for t_project_log.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_project_log.FCID
     * @return t_project_log.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_project_log.FCID
     * @param fcid the value for t_project_log.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_project_log.FCDATE
     * @return t_project_log.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_project_log.FCDATE
     * @param fcdate the value for t_project_log.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取 项目ID 字段:t_project_log.FProjectID
     * @return t_project_log.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:t_project_log.FProjectID
     * @param fprojectid the value for t_project_log.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 功能 字段:t_project_log.FFunction
     * @return t_project_log.FFunction, 功能
     * @mbg.generated
     */
    public String getFfunction() {
        return ffunction;
    }

    /**
     * 设置 功能 字段:t_project_log.FFunction
     * @param ffunction the value for t_project_log.FFunction, 功能
     * @mbg.generated
     */
    public void setFfunction(String ffunction) {
        this.ffunction = ffunction == null ? null : ffunction.trim();
    }

    /**
     * 获取 操作 字段:t_project_log.FOperation
     * @return t_project_log.FOperation, 操作
     * @mbg.generated
     */
    public String getFoperation() {
        return foperation;
    }

    /**
     * 设置 操作 字段:t_project_log.FOperation
     * @param foperation the value for t_project_log.FOperation, 操作
     * @mbg.generated
     */
    public void setFoperation(String foperation) {
        this.foperation = foperation == null ? null : foperation.trim();
    }

    /**
     * 获取 有可以链接跳转的输入 字段:t_project_log.FLink
     * @return t_project_log.FLink, 有可以链接跳转的输入
     * @mbg.generated
     */
    public String getFlink() {
        return flink;
    }

    /**
     * 设置 有可以链接跳转的输入 字段:t_project_log.FLink
     * @param flink the value for t_project_log.FLink, 有可以链接跳转的输入
     * @mbg.generated
     */
    public void setFlink(String flink) {
        this.flink = flink == null ? null : flink.trim();
    }

    /**
     * 获取 描述 字段:t_project_log.FNote
     * @return t_project_log.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:t_project_log.FNote
     * @param fnote the value for t_project_log.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_project_log
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
        sb.append(", fcdate=").append(fcdate);
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", ffunction=").append(ffunction);
        sb.append(", foperation=").append(foperation);
        sb.append(", flink=").append(flink);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_log
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
        TProjectLog other = (TProjectLog) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFfunction() == null ? other.getFfunction() == null : this.getFfunction().equals(other.getFfunction()))
            && (this.getFoperation() == null ? other.getFoperation() == null : this.getFoperation().equals(other.getFoperation()))
            && (this.getFlink() == null ? other.getFlink() == null : this.getFlink().equals(other.getFlink()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_log
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFkeyid() == null) ? 0 : getFkeyid().hashCode());
        result = prime * result + ((getFcid() == null) ? 0 : getFcid().hashCode());
        result = prime * result + ((getFcdate() == null) ? 0 : getFcdate().hashCode());
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFfunction() == null) ? 0 : getFfunction().hashCode());
        result = prime * result + ((getFoperation() == null) ? 0 : getFoperation().hashCode());
        result = prime * result + ((getFlink() == null) ? 0 : getFlink().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}