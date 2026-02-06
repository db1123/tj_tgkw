package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_class
*/
@Table(name = "t_class")
public class TClass implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_class.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_class.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_class.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 班级名称
     * 表字段 : student_capability_evaluation..t_class.FClassName
     * @mbg.generated
     */
    private String fclassname;

    /**
     * 关联T_Major表
     * 表字段 : student_capability_evaluation..t_class.FGradeID
     * @mbg.generated
     */
    private Long fgradeid;

    /**
     * 关联T_Grade表
     * 表字段 : student_capability_evaluation..t_class.FMajorID
     * @mbg.generated
     */
    private Long fmajorid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_class
     * @mbg.generated
     */
    public TClass(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fclassname, Long fgradeid, Long fmajorid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fclassname = fclassname;
        this.fgradeid = fgradeid;
        this.fmajorid = fmajorid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_class
     * @mbg.generated
     */
    public TClass() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class.FKeyID
     * @return student_capability_evaluation..t_class.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_class.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class.FCID
     * @return student_capability_evaluation..t_class.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class.FCID
     * @param fcid the value for student_capability_evaluation..t_class.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class.FUID
     * @return student_capability_evaluation..t_class.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class.FUID
     * @param fuid the value for student_capability_evaluation..t_class.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class.FCDATE
     * @return student_capability_evaluation..t_class.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_class.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_class.FUDATE
     * @return student_capability_evaluation..t_class.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_class.FUDATE
     * @param fudate the value for student_capability_evaluation..t_class.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_class.FState
     * @return student_capability_evaluation..t_class.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_class.FState
     * @param fstate the value for student_capability_evaluation..t_class.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 班级名称 字段:student_capability_evaluation..t_class.FClassName
     * @return student_capability_evaluation..t_class.FClassName, 班级名称
     * @mbg.generated
     */
    public String getFclassname() {
        return fclassname;
    }

    /**
     * 设置 班级名称 字段:student_capability_evaluation..t_class.FClassName
     * @param fclassname the value for student_capability_evaluation..t_class.FClassName, 班级名称
     * @mbg.generated
     */
    public void setFclassname(String fclassname) {
        this.fclassname = fclassname == null ? null : fclassname.trim();
    }

    /**
     * 获取 关联T_Major表 字段:student_capability_evaluation..t_class.FGradeID
     * @return student_capability_evaluation..t_class.FGradeID, 关联T_Major表
     * @mbg.generated
     */
    public Long getFgradeid() {
        return fgradeid;
    }

    /**
     * 设置 关联T_Major表 字段:student_capability_evaluation..t_class.FGradeID
     * @param fgradeid the value for student_capability_evaluation..t_class.FGradeID, 关联T_Major表
     * @mbg.generated
     */
    public void setFgradeid(Long fgradeid) {
        this.fgradeid = fgradeid;
    }

    /**
     * 获取 关联T_Grade表 字段:student_capability_evaluation..t_class.FMajorID
     * @return student_capability_evaluation..t_class.FMajorID, 关联T_Grade表
     * @mbg.generated
     */
    public Long getFmajorid() {
        return fmajorid;
    }

    /**
     * 设置 关联T_Grade表 字段:student_capability_evaluation..t_class.FMajorID
     * @param fmajorid the value for student_capability_evaluation..t_class.FMajorID, 关联T_Grade表
     * @mbg.generated
     */
    public void setFmajorid(Long fmajorid) {
        this.fmajorid = fmajorid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_class
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
        sb.append(", fclassname=").append(fclassname);
        sb.append(", fgradeid=").append(fgradeid);
        sb.append(", fmajorid=").append(fmajorid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_class
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
        TClass other = (TClass) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFclassname() == null ? other.getFclassname() == null : this.getFclassname().equals(other.getFclassname()))
            && (this.getFgradeid() == null ? other.getFgradeid() == null : this.getFgradeid().equals(other.getFgradeid()))
            && (this.getFmajorid() == null ? other.getFmajorid() == null : this.getFmajorid().equals(other.getFmajorid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_class
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
        result = prime * result + ((getFclassname() == null) ? 0 : getFclassname().hashCode());
        result = prime * result + ((getFgradeid() == null) ? 0 : getFgradeid().hashCode());
        result = prime * result + ((getFmajorid() == null) ? 0 : getFmajorid().hashCode());
        return result;
    }
}