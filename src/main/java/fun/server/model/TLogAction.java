package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 表名：t_log_action
*/
@Table(name = "t_log_action")
public class TLogAction implements Serializable {
    /**
     * 
     * 表字段 : zsysj_server_220310..t_log_action.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : zsysj_server_220310..t_log_action.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 1普通用户 2供应商
     * 表字段 : zsysj_server_220310..t_log_action.f_user_type
     * @mbg.generated
     */
    private Integer fUserType;

    /**
     * 
     * 表字段 : zsysj_server_220310..t_log_action.f_user_id
     * @mbg.generated
     */
    private Long fUserId;

    /**
     * 
     * 表字段 : zsysj_server_220310..t_log_action.f_user_name
     * @mbg.generated
     */
    private String fUserName;

    /**
     * 1访问地址\\n2服务请求(执行方法)\n3其它
     * 表字段 : zsysj_server_220310..t_log_action.f_type
     * @mbg.generated
     */
    private Integer fType;

    /**
     * URL或类路径
     * 表字段 : zsysj_server_220310..t_log_action.f_path
     * @mbg.generated
     */
    private String fPath;

    /**
     * 
     * 表字段 : zsysj_server_220310..t_log_action.f_memo
     * @mbg.generated
     */
    private String fMemo;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public TLogAction(Long fKeyId, Date fCDate, Integer fUserType, Long fUserId, String fUserName, Integer fType, String fPath, String fMemo) {
        this.fKeyId = fKeyId;
        this.fCDate = fCDate;
        this.fUserType = fUserType;
        this.fUserId = fUserId;
        this.fUserName = fUserName;
        this.fType = fType;
        this.fPath = fPath;
        this.fMemo = fMemo;
    }

    /**
     * 构造查询条件
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public TLogAction() {
        super();
    }

    /**
     * 获取  字段:zsysj_server_220310..t_log_action.f_key_id
     * @return zsysj_server_220310..t_log_action.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:zsysj_server_220310..t_log_action.f_key_id
     * @param fKeyId the value for zsysj_server_220310..t_log_action.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:zsysj_server_220310..t_log_action.f_c_date
     * @return zsysj_server_220310..t_log_action.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:zsysj_server_220310..t_log_action.f_c_date
     * @param fCDate the value for zsysj_server_220310..t_log_action.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取 1普通用户 2供应商 字段:zsysj_server_220310..t_log_action.f_user_type
     * @return zsysj_server_220310..t_log_action.f_user_type, 1普通用户 2供应商
     * @mbg.generated
     */
    public Integer getfUserType() {
        return fUserType;
    }

    /**
     * 设置 1普通用户 2供应商 字段:zsysj_server_220310..t_log_action.f_user_type
     * @param fUserType the value for zsysj_server_220310..t_log_action.f_user_type, 1普通用户 2供应商
     * @mbg.generated
     */
    public void setfUserType(Integer fUserType) {
        this.fUserType = fUserType;
    }

    /**
     * 获取  字段:zsysj_server_220310..t_log_action.f_user_id
     * @return zsysj_server_220310..t_log_action.f_user_id, 
     * @mbg.generated
     */
    public Long getfUserId() {
        return fUserId;
    }

    /**
     * 设置  字段:zsysj_server_220310..t_log_action.f_user_id
     * @param fUserId the value for zsysj_server_220310..t_log_action.f_user_id, 
     * @mbg.generated
     */
    public void setfUserId(Long fUserId) {
        this.fUserId = fUserId;
    }

    /**
     * 获取  字段:zsysj_server_220310..t_log_action.f_user_name
     * @return zsysj_server_220310..t_log_action.f_user_name, 
     * @mbg.generated
     */
    public String getfUserName() {
        return fUserName;
    }

    /**
     * 设置  字段:zsysj_server_220310..t_log_action.f_user_name
     * @param fUserName the value for zsysj_server_220310..t_log_action.f_user_name, 
     * @mbg.generated
     */
    public void setfUserName(String fUserName) {
        this.fUserName = fUserName == null ? null : fUserName.trim();
    }

    /**
     * 获取 1访问地址\\n2服务请求(执行方法)\n3其它 字段:zsysj_server_220310..t_log_action.f_type
     * @return zsysj_server_220310..t_log_action.f_type, 1访问地址\\n2服务请求(执行方法)\n3其它
     * @mbg.generated
     */
    public Integer getfType() {
        return fType;
    }

    /**
     * 设置 1访问地址\\n2服务请求(执行方法)\n3其它 字段:zsysj_server_220310..t_log_action.f_type
     * @param fType the value for zsysj_server_220310..t_log_action.f_type, 1访问地址\\n2服务请求(执行方法)\n3其它
     * @mbg.generated
     */
    public void setfType(Integer fType) {
        this.fType = fType;
    }

    /**
     * 获取 URL或类路径 字段:zsysj_server_220310..t_log_action.f_path
     * @return zsysj_server_220310..t_log_action.f_path, URL或类路径
     * @mbg.generated
     */
    public String getfPath() {
        return fPath;
    }

    /**
     * 设置 URL或类路径 字段:zsysj_server_220310..t_log_action.f_path
     * @param fPath the value for zsysj_server_220310..t_log_action.f_path, URL或类路径
     * @mbg.generated
     */
    public void setfPath(String fPath) {
        this.fPath = fPath == null ? null : fPath.trim();
    }

    /**
     * 获取  字段:zsysj_server_220310..t_log_action.f_memo
     * @return zsysj_server_220310..t_log_action.f_memo, 
     * @mbg.generated
     */
    public String getfMemo() {
        return fMemo;
    }

    /**
     * 设置  字段:zsysj_server_220310..t_log_action.f_memo
     * @param fMemo the value for zsysj_server_220310..t_log_action.f_memo, 
     * @mbg.generated
     */
    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }

    /**
     * 实例输出为字符串
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUserType=").append(fUserType);
        sb.append(", fUserId=").append(fUserId);
        sb.append(", fUserName=").append(fUserName);
        sb.append(", fType=").append(fType);
        sb.append(", fPath=").append(fPath);
        sb.append(", fMemo=").append(fMemo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * zsysj_server_220310..t_log_action
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
        TLogAction other = (TLogAction) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUserType() == null ? other.getfUserType() == null : this.getfUserType().equals(other.getfUserType()))
            && (this.getfUserId() == null ? other.getfUserId() == null : this.getfUserId().equals(other.getfUserId()))
            && (this.getfUserName() == null ? other.getfUserName() == null : this.getfUserName().equals(other.getfUserName()))
            && (this.getfType() == null ? other.getfType() == null : this.getfType().equals(other.getfType()))
            && (this.getfPath() == null ? other.getfPath() == null : this.getfPath().equals(other.getfPath()))
            && (this.getfMemo() == null ? other.getfMemo() == null : this.getfMemo().equals(other.getfMemo()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUserType() == null) ? 0 : getfUserType().hashCode());
        result = prime * result + ((getfUserId() == null) ? 0 : getfUserId().hashCode());
        result = prime * result + ((getfUserName() == null) ? 0 : getfUserName().hashCode());
        result = prime * result + ((getfType() == null) ? 0 : getfType().hashCode());
        result = prime * result + ((getfPath() == null) ? 0 : getfPath().hashCode());
        result = prime * result + ((getfMemo() == null) ? 0 : getfMemo().hashCode());
        return result;
    }
}