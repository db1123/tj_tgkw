package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_grade
*/
@Table(name = "t_grade")
public class TGrade implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_grade.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_grade.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_grade.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_grade.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_grade.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_grade.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 年级名称，如“2024级”
     * 表字段 : student_capability_evaluation..t_grade.FGradeName
     * @mbg.generated
     */
    private String fgradename;

    /**
     * 入学年份，如2024
     * 表字段 : student_capability_evaluation..t_grade.FAdmissionYear
     * @mbg.generated
     */
    private Integer fadmissionyear;

    /**
     * 关联T_Major表
     * 表字段 : student_capability_evaluation..t_grade.FMajorID
     * @mbg.generated
     */
    private Long fmajorid;

    /**
     * 入学月份 1-12
     * 表字段 : student_capability_evaluation..t_grade.FMonth
     * @mbg.generated
     */
    private Integer fmonth;

    /**
     * 入学季节1=春2=秋
     * 表字段 : student_capability_evaluation..t_grade.FSeason
     * @mbg.generated
     */
    private Integer fseason;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_grade
     * @mbg.generated
     */
    public TGrade(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fgradename, Integer fadmissionyear, Long fmajorid, Integer fmonth, Integer fseason) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fgradename = fgradename;
        this.fadmissionyear = fadmissionyear;
        this.fmajorid = fmajorid;
        this.fmonth = fmonth;
        this.fseason = fseason;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_grade
     * @mbg.generated
     */
    public TGrade() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_grade.FKeyID
     * @return student_capability_evaluation..t_grade.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_grade.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_grade.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_grade.FCID
     * @return student_capability_evaluation..t_grade.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_grade.FCID
     * @param fcid the value for student_capability_evaluation..t_grade.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_grade.FUID
     * @return student_capability_evaluation..t_grade.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_grade.FUID
     * @param fuid the value for student_capability_evaluation..t_grade.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_grade.FCDATE
     * @return student_capability_evaluation..t_grade.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_grade.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_grade.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_grade.FUDATE
     * @return student_capability_evaluation..t_grade.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_grade.FUDATE
     * @param fudate the value for student_capability_evaluation..t_grade.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_grade.FState
     * @return student_capability_evaluation..t_grade.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_grade.FState
     * @param fstate the value for student_capability_evaluation..t_grade.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 年级名称，如“2024级” 字段:student_capability_evaluation..t_grade.FGradeName
     * @return student_capability_evaluation..t_grade.FGradeName, 年级名称，如“2024级”
     * @mbg.generated
     */
    public String getFgradename() {
        return fgradename;
    }

    /**
     * 设置 年级名称，如“2024级” 字段:student_capability_evaluation..t_grade.FGradeName
     * @param fgradename the value for student_capability_evaluation..t_grade.FGradeName, 年级名称，如“2024级”
     * @mbg.generated
     */
    public void setFgradename(String fgradename) {
        this.fgradename = fgradename == null ? null : fgradename.trim();
    }

    /**
     * 获取 入学年份，如2024 字段:student_capability_evaluation..t_grade.FAdmissionYear
     * @return student_capability_evaluation..t_grade.FAdmissionYear, 入学年份，如2024
     * @mbg.generated
     */
    public Integer getFadmissionyear() {
        return fadmissionyear;
    }

    /**
     * 设置 入学年份，如2024 字段:student_capability_evaluation..t_grade.FAdmissionYear
     * @param fadmissionyear the value for student_capability_evaluation..t_grade.FAdmissionYear, 入学年份，如2024
     * @mbg.generated
     */
    public void setFadmissionyear(Integer fadmissionyear) {
        this.fadmissionyear = fadmissionyear;
    }

    /**
     * 获取 关联T_Major表 字段:student_capability_evaluation..t_grade.FMajorID
     * @return student_capability_evaluation..t_grade.FMajorID, 关联T_Major表
     * @mbg.generated
     */
    public Long getFmajorid() {
        return fmajorid;
    }

    /**
     * 设置 关联T_Major表 字段:student_capability_evaluation..t_grade.FMajorID
     * @param fmajorid the value for student_capability_evaluation..t_grade.FMajorID, 关联T_Major表
     * @mbg.generated
     */
    public void setFmajorid(Long fmajorid) {
        this.fmajorid = fmajorid;
    }

    /**
     * 获取 入学月份 1-12 字段:student_capability_evaluation..t_grade.FMonth
     * @return student_capability_evaluation..t_grade.FMonth, 入学月份 1-12
     * @mbg.generated
     */
    public Integer getFmonth() {
        return fmonth;
    }

    /**
     * 设置 入学月份 1-12 字段:student_capability_evaluation..t_grade.FMonth
     * @param fmonth the value for student_capability_evaluation..t_grade.FMonth, 入学月份 1-12
     * @mbg.generated
     */
    public void setFmonth(Integer fmonth) {
        this.fmonth = fmonth;
    }

    /**
     * 获取 入学季节1=春2=秋 字段:student_capability_evaluation..t_grade.FSeason
     * @return student_capability_evaluation..t_grade.FSeason, 入学季节1=春2=秋
     * @mbg.generated
     */
    public Integer getFseason() {
        return fseason;
    }

    /**
     * 设置 入学季节1=春2=秋 字段:student_capability_evaluation..t_grade.FSeason
     * @param fseason the value for student_capability_evaluation..t_grade.FSeason, 入学季节1=春2=秋
     * @mbg.generated
     */
    public void setFseason(Integer fseason) {
        this.fseason = fseason;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_grade
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
        sb.append(", fgradename=").append(fgradename);
        sb.append(", fadmissionyear=").append(fadmissionyear);
        sb.append(", fmajorid=").append(fmajorid);
        sb.append(", fmonth=").append(fmonth);
        sb.append(", fseason=").append(fseason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_grade
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
        TGrade other = (TGrade) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFgradename() == null ? other.getFgradename() == null : this.getFgradename().equals(other.getFgradename()))
            && (this.getFadmissionyear() == null ? other.getFadmissionyear() == null : this.getFadmissionyear().equals(other.getFadmissionyear()))
            && (this.getFmajorid() == null ? other.getFmajorid() == null : this.getFmajorid().equals(other.getFmajorid()))
            && (this.getFmonth() == null ? other.getFmonth() == null : this.getFmonth().equals(other.getFmonth()))
            && (this.getFseason() == null ? other.getFseason() == null : this.getFseason().equals(other.getFseason()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_grade
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
        result = prime * result + ((getFgradename() == null) ? 0 : getFgradename().hashCode());
        result = prime * result + ((getFadmissionyear() == null) ? 0 : getFadmissionyear().hashCode());
        result = prime * result + ((getFmajorid() == null) ? 0 : getFmajorid().hashCode());
        result = prime * result + ((getFmonth() == null) ? 0 : getFmonth().hashCode());
        result = prime * result + ((getFseason() == null) ? 0 : getFseason().hashCode());
        return result;
    }
}