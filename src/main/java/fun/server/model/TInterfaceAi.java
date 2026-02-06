package fun.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 表名：t_interface_ai
 * 表注释：数据接口表
*/
@Table(name = "t_interface_ai")
public class TInterfaceAi implements Serializable {
    /**
     * 系统唯一ID，系统生成
     * 表字段 : student_capability_evaluation..t_interface_ai.FKeyID
     * @mbg.generated
     */
	@Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 创建者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_interface_ai.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 最后修改者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_interface_ai.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 创建时间
     * 表字段 : student_capability_evaluation..t_interface_ai.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 最后修改时间
     * 表字段 : student_capability_evaluation..t_interface_ai.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态
     * 表字段 : student_capability_evaluation..t_interface_ai.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 接口类型1:招聘能力 2:招聘人员 3:招聘企业...
     * 表字段 : student_capability_evaluation..t_interface_ai.Ftype
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 接口类型所属表的fkeyid
     * 表字段 : student_capability_evaluation..t_interface_ai.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 调用大模型返回的查询ID
     * 表字段 : student_capability_evaluation..t_interface_ai.FInterfacellmID
     * @mbg.generated
     */
    private String finterfacellmid;

    /**
     * 调用大模型状态 0-未匹配 1-已匹配
     * 表字段 : student_capability_evaluation..t_interface_ai.FInterfacellmState
     * @mbg.generated
     */
    private Integer finterfacellmstate;

    /**
     * 大模型返回值
     * 表字段 : student_capability_evaluation..t_interface_ai.FInterfacellm_Return
     * @mbg.generated
     */
    private String finterfacellmReturn;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_interface_ai
     * @mbg.generated
     */
    public TInterfaceAi(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer ftype, Long ftypeid, String finterfacellmid, Integer finterfacellmstate) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftype = ftype;
        this.ftypeid = ftypeid;
        this.finterfacellmid = finterfacellmid;
        this.finterfacellmstate = finterfacellmstate;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_interface_ai
     * @mbg.generated
     */
    public TInterfaceAi(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer ftype, Long ftypeid, String finterfacellmid, Integer finterfacellmstate, String finterfacellmReturn) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftype = ftype;
        this.ftypeid = ftypeid;
        this.finterfacellmid = finterfacellmid;
        this.finterfacellmstate = finterfacellmstate;
        this.finterfacellmReturn = finterfacellmReturn;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_interface_ai
     * @mbg.generated
     */
    public TInterfaceAi() {
        super();
    }

    /**
     * 获取 系统唯一ID，系统生成 字段:student_capability_evaluation..t_interface_ai.FKeyID
     * @return student_capability_evaluation..t_interface_ai.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置 系统唯一ID，系统生成 字段:student_capability_evaluation..t_interface_ai.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_interface_ai.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取 创建者，来源T_User主键 字段:student_capability_evaluation..t_interface_ai.FCID
     * @return student_capability_evaluation..t_interface_ai.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置 创建者，来源T_User主键 字段:student_capability_evaluation..t_interface_ai.FCID
     * @param fcid the value for student_capability_evaluation..t_interface_ai.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_interface_ai.FUID
     * @return student_capability_evaluation..t_interface_ai.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_interface_ai.FUID
     * @param fuid the value for student_capability_evaluation..t_interface_ai.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取 创建时间 字段:student_capability_evaluation..t_interface_ai.FCDATE
     * @return student_capability_evaluation..t_interface_ai.FCDATE, 创建时间
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置 创建时间 字段:student_capability_evaluation..t_interface_ai.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_interface_ai.FCDATE, 创建时间
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取 最后修改时间 字段:student_capability_evaluation..t_interface_ai.FUDATE
     * @return student_capability_evaluation..t_interface_ai.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置 最后修改时间 字段:student_capability_evaluation..t_interface_ai.FUDATE
     * @param fudate the value for student_capability_evaluation..t_interface_ai.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态 字段:student_capability_evaluation..t_interface_ai.FState
     * @return student_capability_evaluation..t_interface_ai.FState, 状态
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态 字段:student_capability_evaluation..t_interface_ai.FState
     * @param fstate the value for student_capability_evaluation..t_interface_ai.FState, 状态
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 接口类型1:招聘能力 2:招聘人员 3:招聘企业... 字段:student_capability_evaluation..t_interface_ai.Ftype
     * @return student_capability_evaluation..t_interface_ai.Ftype, 接口类型1:招聘能力 2:招聘人员 3:招聘企业...
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 接口类型1:招聘能力 2:招聘人员 3:招聘企业... 字段:student_capability_evaluation..t_interface_ai.Ftype
     * @param ftype the value for student_capability_evaluation..t_interface_ai.Ftype, 接口类型1:招聘能力 2:招聘人员 3:招聘企业...
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 接口类型所属表的fkeyid 字段:student_capability_evaluation..t_interface_ai.FTypeID
     * @return student_capability_evaluation..t_interface_ai.FTypeID, 接口类型所属表的fkeyid
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 接口类型所属表的fkeyid 字段:student_capability_evaluation..t_interface_ai.FTypeID
     * @param ftypeid the value for student_capability_evaluation..t_interface_ai.FTypeID, 接口类型所属表的fkeyid
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 调用大模型返回的查询ID 字段:student_capability_evaluation..t_interface_ai.FInterfacellmID
     * @return student_capability_evaluation..t_interface_ai.FInterfacellmID, 调用大模型返回的查询ID
     * @mbg.generated
     */
    public String getFinterfacellmid() {
        return finterfacellmid;
    }

    /**
     * 设置 调用大模型返回的查询ID 字段:student_capability_evaluation..t_interface_ai.FInterfacellmID
     * @param finterfacellmid the value for student_capability_evaluation..t_interface_ai.FInterfacellmID, 调用大模型返回的查询ID
     * @mbg.generated
     */
    public void setFinterfacellmid(String finterfacellmid) {
        this.finterfacellmid = finterfacellmid == null ? null : finterfacellmid.trim();
    }

    /**
     * 获取 调用大模型状态 0-未匹配 1-已匹配 字段:student_capability_evaluation..t_interface_ai.FInterfacellmState
     * @return student_capability_evaluation..t_interface_ai.FInterfacellmState, 调用大模型状态 0-未匹配 1-已匹配
     * @mbg.generated
     */
    public Integer getFinterfacellmstate() {
        return finterfacellmstate;
    }

    /**
     * 设置 调用大模型状态 0-未匹配 1-已匹配 字段:student_capability_evaluation..t_interface_ai.FInterfacellmState
     * @param finterfacellmstate the value for student_capability_evaluation..t_interface_ai.FInterfacellmState, 调用大模型状态 0-未匹配 1-已匹配
     * @mbg.generated
     */
    public void setFinterfacellmstate(Integer finterfacellmstate) {
        this.finterfacellmstate = finterfacellmstate;
    }

    /**
     * 获取 大模型返回值 字段:student_capability_evaluation..t_interface_ai.FInterfacellm_Return
     * @return student_capability_evaluation..t_interface_ai.FInterfacellm_Return, 大模型返回值
     * @mbg.generated
     */
    public String getFinterfacellmReturn() {
        return finterfacellmReturn;
    }

    /**
     * 设置 大模型返回值 字段:student_capability_evaluation..t_interface_ai.FInterfacellm_Return
     * @param finterfacellmReturn the value for student_capability_evaluation..t_interface_ai.FInterfacellm_Return, 大模型返回值
     * @mbg.generated
     */
    public void setFinterfacellmReturn(String finterfacellmReturn) {
        this.finterfacellmReturn = finterfacellmReturn == null ? null : finterfacellmReturn.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_interface_ai
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
        sb.append(", ftype=").append(ftype);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", finterfacellmid=").append(finterfacellmid);
        sb.append(", finterfacellmstate=").append(finterfacellmstate);
        sb.append(", finterfacellmReturn=").append(finterfacellmReturn);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_interface_ai
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
        TInterfaceAi other = (TInterfaceAi) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFinterfacellmid() == null ? other.getFinterfacellmid() == null : this.getFinterfacellmid().equals(other.getFinterfacellmid()))
            && (this.getFinterfacellmstate() == null ? other.getFinterfacellmstate() == null : this.getFinterfacellmstate().equals(other.getFinterfacellmstate()))
            && (this.getFinterfacellmReturn() == null ? other.getFinterfacellmReturn() == null : this.getFinterfacellmReturn().equals(other.getFinterfacellmReturn()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_interface_ai
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
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFinterfacellmid() == null) ? 0 : getFinterfacellmid().hashCode());
        result = prime * result + ((getFinterfacellmstate() == null) ? 0 : getFinterfacellmstate().hashCode());
        result = prime * result + ((getFinterfacellmReturn() == null) ? 0 : getFinterfacellmReturn().hashCode());
        return result;
    }
}