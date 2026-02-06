package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_funcunit_file")
public class TFuncunitFile implements Serializable {
    /**
     * 
     * 表字段 : t_funcunit_file.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : t_funcunit_file.f_script_id
     * @mbg.generated
     */
    private Long fScriptId;

    /**
     * 
     * 表字段 : t_funcunit_file.f_path
     * @mbg.generated
     */
    private String fPath;

    /**
     * 
     * 表字段 : t_funcunit_file.f_filename
     * @mbg.generated
     */
    private String fFilename;

    /**
     * 
     * 表字段 : t_funcunit_file.f_ext
     * @mbg.generated
     */
    private String fExt;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_funcunit_file
     * @mbg.generated
     */
    public TFuncunitFile(Long fKeyId, Long fScriptId, String fPath, String fFilename, String fExt) {
        this.fKeyId = fKeyId;
        this.fScriptId = fScriptId;
        this.fPath = fPath;
        this.fFilename = fFilename;
        this.fExt = fExt;
    }

    /**
     * 构造查询条件
     * t_funcunit_file
     * @mbg.generated
     */
    public TFuncunitFile() {
        super();
    }

    /**
     * 获取  字段:t_funcunit_file.f_key_id
     * @return t_funcunit_file.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:t_funcunit_file.f_key_id
     * @param fKeyId the value for t_funcunit_file.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:t_funcunit_file.f_script_id
     * @return t_funcunit_file.f_script_id, 
     * @mbg.generated
     */
    public Long getfScriptId() {
        return fScriptId;
    }

    /**
     * 设置  字段:t_funcunit_file.f_script_id
     * @param fScriptId the value for t_funcunit_file.f_script_id, 
     * @mbg.generated
     */
    public void setfScriptId(Long fScriptId) {
        this.fScriptId = fScriptId;
    }

    /**
     * 获取  字段:t_funcunit_file.f_path
     * @return t_funcunit_file.f_path, 
     * @mbg.generated
     */
    public String getfPath() {
        return fPath;
    }

    /**
     * 设置  字段:t_funcunit_file.f_path
     * @param fPath the value for t_funcunit_file.f_path, 
     * @mbg.generated
     */
    public void setfPath(String fPath) {
        this.fPath = fPath == null ? null : fPath.trim();
    }

    /**
     * 获取  字段:t_funcunit_file.f_filename
     * @return t_funcunit_file.f_filename, 
     * @mbg.generated
     */
    public String getfFilename() {
        return fFilename;
    }

    /**
     * 设置  字段:t_funcunit_file.f_filename
     * @param fFilename the value for t_funcunit_file.f_filename, 
     * @mbg.generated
     */
    public void setfFilename(String fFilename) {
        this.fFilename = fFilename == null ? null : fFilename.trim();
    }

    /**
     * 获取  字段:t_funcunit_file.f_ext
     * @return t_funcunit_file.f_ext, 
     * @mbg.generated
     */
    public String getfExt() {
        return fExt;
    }

    /**
     * 设置  字段:t_funcunit_file.f_ext
     * @param fExt the value for t_funcunit_file.f_ext, 
     * @mbg.generated
     */
    public void setfExt(String fExt) {
        this.fExt = fExt == null ? null : fExt.trim();
    }

    /**
     * 实例输出为字符串
     * t_funcunit_file
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fScriptId=").append(fScriptId);
        sb.append(", fPath=").append(fPath);
        sb.append(", fFilename=").append(fFilename);
        sb.append(", fExt=").append(fExt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_funcunit_file
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
        TFuncunitFile other = (TFuncunitFile) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfScriptId() == null ? other.getfScriptId() == null : this.getfScriptId().equals(other.getfScriptId()))
            && (this.getfPath() == null ? other.getfPath() == null : this.getfPath().equals(other.getfPath()))
            && (this.getfFilename() == null ? other.getfFilename() == null : this.getfFilename().equals(other.getfFilename()))
            && (this.getfExt() == null ? other.getfExt() == null : this.getfExt().equals(other.getfExt()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_funcunit_file
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfScriptId() == null) ? 0 : getfScriptId().hashCode());
        result = prime * result + ((getfPath() == null) ? 0 : getfPath().hashCode());
        result = prime * result + ((getfFilename() == null) ? 0 : getfFilename().hashCode());
        result = prime * result + ((getfExt() == null) ? 0 : getfExt().hashCode());
        return result;
    }
}