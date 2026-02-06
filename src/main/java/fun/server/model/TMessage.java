package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "t_message")
public class TMessage implements Serializable {
    /**
     * 
     * 表字段 : t_message.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_message.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : t_message.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : t_message.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : t_message.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    /**
     * 
     * 表字段 : t_message.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 1系统消息
       2用户消息
       3流程图消息
       4评价模块消息
     * 表字段 : t_message.f_type
     * @mbg.generated
     */
    private Integer fType;

    /**
     * 
     * 表字段 : t_message.f_from_user_id
     * @mbg.generated
     */
    private Long fFromUserId;

    /**
     * 
     * 表字段 : t_message.f_to_user_id
     * @mbg.generated
     */
    private Long fToUserId;

    /**
     * 
     * 表字段 : t_message.f_title
     * @mbg.generated
     */
    private String fTitle;

    /**
     * 
     * 表字段 : t_message.f_note
     * @mbg.generated
     */
    private String fNote;

    /**
     * 
     * 表字段 : t_message.f_is_read
     * @mbg.generated
     */
    private Integer fIsRead;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_message
     * @mbg.generated
     */
    public TMessage(Long fKeyId, Long fCId, Long fUId, Date fCDate, Date fUDate, Integer fState, Integer fType, Long fFromUserId, Long fToUserId, String fTitle, String fNote, Integer fIsRead) {
        this.fKeyId = fKeyId;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
        this.fState = fState;
        this.fType = fType;
        this.fFromUserId = fFromUserId;
        this.fToUserId = fToUserId;
        this.fTitle = fTitle;
        this.fNote = fNote;
        this.fIsRead = fIsRead;
    }

    /**
     * 构造查询条件
     * t_message
     * @mbg.generated
     */
    public TMessage() {
        super();
    }

    /**
     * 获取  字段:t_message.f_key_id
     * @return t_message.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_message.f_key_id
     * @param fKeyId the value for t_message.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_message.f_c_id
     * @return t_message.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:t_message.f_c_id
     * @param fCId the value for t_message.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:t_message.f_u_id
     * @return t_message.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:t_message.f_u_id
     * @param fUId the value for t_message.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:t_message.f_c_date
     * @return t_message.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:t_message.f_c_date
     * @param fCDate the value for t_message.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:t_message.f_u_date
     * @return t_message.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:t_message.f_u_date
     * @param fUDate the value for t_message.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 获取  字段:t_message.f_state
     * @return t_message.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:t_message.f_state
     * @param fState the value for t_message.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取 1系统消息
2用户消息
3流程图消息
4评价模块消息 字段:t_message.f_type
     * @return t_message.f_type, 1系统消息
2用户消息
3流程图消息
4评价模块消息
     * @mbg.generated
     */
    public Integer getfType() {
        return fType;
    }

    /**
     * 设置 1系统消息
2用户消息
3流程图消息
4评价模块消息 字段:t_message.f_type
     * @param fType the value for t_message.f_type, 1系统消息
2用户消息
3流程图消息
4评价模块消息
     * @mbg.generated
     */
    public void setfType(Integer fType) {
        this.fType = fType;
    }

    /**
     * 获取  字段:t_message.f_from_user_id
     * @return t_message.f_from_user_id, 
     * @mbg.generated
     */
    public Long getfFromUserId() {
        return fFromUserId;
    }

    /**
     * 设置  字段:t_message.f_from_user_id
     * @param fFromUserId the value for t_message.f_from_user_id, 
     * @mbg.generated
     */
    public void setfFromUserId(Long fFromUserId) {
        this.fFromUserId = fFromUserId;
    }

    /**
     * 获取  字段:t_message.f_to_user_id
     * @return t_message.f_to_user_id, 
     * @mbg.generated
     */
    public Long getfToUserId() {
        return fToUserId;
    }

    /**
     * 设置  字段:t_message.f_to_user_id
     * @param fToUserId the value for t_message.f_to_user_id, 
     * @mbg.generated
     */
    public void setfToUserId(Long fToUserId) {
        this.fToUserId = fToUserId;
    }

    /**
     * 获取  字段:t_message.f_title
     * @return t_message.f_title, 
     * @mbg.generated
     */
    public String getfTitle() {
        return fTitle;
    }

    /**
     * 设置  字段:t_message.f_title
     * @param fTitle the value for t_message.f_title, 
     * @mbg.generated
     */
    public void setfTitle(String fTitle) {
        this.fTitle = fTitle == null ? null : fTitle.trim();
    }

    /**
     * 获取  字段:t_message.f_note
     * @return t_message.f_note, 
     * @mbg.generated
     */
    public String getfNote() {
        return fNote;
    }

    /**
     * 设置  字段:t_message.f_note
     * @param fNote the value for t_message.f_note, 
     * @mbg.generated
     */
    public void setfNote(String fNote) {
        this.fNote = fNote == null ? null : fNote.trim();
    }

    /**
     * 获取  字段:t_message.f_is_read
     * @return t_message.f_is_read, 
     * @mbg.generated
     */
    public Integer getfIsRead() {
        return fIsRead;
    }

    /**
     * 设置  字段:t_message.f_is_read
     * @param fIsRead the value for t_message.f_is_read, 
     * @mbg.generated
     */
    public void setfIsRead(Integer fIsRead) {
        this.fIsRead = fIsRead;
    }

    /**
     * 实例输出为字符串
     * t_message
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fCId=").append(fCId);
        sb.append(", fUId=").append(fUId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUDate=").append(fUDate);
        sb.append(", fState=").append(fState);
        sb.append(", fType=").append(fType);
        sb.append(", fFromUserId=").append(fFromUserId);
        sb.append(", fToUserId=").append(fToUserId);
        sb.append(", fTitle=").append(fTitle);
        sb.append(", fNote=").append(fNote);
        sb.append(", fIsRead=").append(fIsRead);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_message
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
        TMessage other = (TMessage) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfType() == null ? other.getfType() == null : this.getfType().equals(other.getfType()))
            && (this.getfFromUserId() == null ? other.getfFromUserId() == null : this.getfFromUserId().equals(other.getfFromUserId()))
            && (this.getfToUserId() == null ? other.getfToUserId() == null : this.getfToUserId().equals(other.getfToUserId()))
            && (this.getfTitle() == null ? other.getfTitle() == null : this.getfTitle().equals(other.getfTitle()))
            && (this.getfNote() == null ? other.getfNote() == null : this.getfNote().equals(other.getfNote()))
            && (this.getfIsRead() == null ? other.getfIsRead() == null : this.getfIsRead().equals(other.getfIsRead()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_message
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfType() == null) ? 0 : getfType().hashCode());
        result = prime * result + ((getfFromUserId() == null) ? 0 : getfFromUserId().hashCode());
        result = prime * result + ((getfToUserId() == null) ? 0 : getfToUserId().hashCode());
        result = prime * result + ((getfTitle() == null) ? 0 : getfTitle().hashCode());
        result = prime * result + ((getfNote() == null) ? 0 : getfNote().hashCode());
        result = prime * result + ((getfIsRead() == null) ? 0 : getfIsRead().hashCode());
        return result;
    }
}