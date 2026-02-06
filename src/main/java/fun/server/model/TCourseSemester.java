package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_semester
*/
@Table(name = "t_course_semester")
public class TCourseSemester implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_course_semester.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_semester.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_semester.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_semester.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_semester.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_course_semester.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 名称
     * 表字段 : teaching_diversity..t_course_semester.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 学年
     * 表字段 : teaching_diversity..t_course_semester.FXN
     * @mbg.generated
     */
    private Integer fxn;

    /**
     * 学期
     * 表字段 : teaching_diversity..t_course_semester.FXQ
     * @mbg.generated
     */
    private Integer fxq;

    /**
     * 简称
     * 表字段 : teaching_diversity..t_course_semester.FJName
     * @mbg.generated
     */
    private String fjname;

    /**
     * 开课学期（1-8）
     * 表字段 : teaching_diversity..t_course_semester.FKKXQ
     * @mbg.generated
     */
    private Integer fkkxq;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public TCourseSemester(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, Integer fxn, Integer fxq, String fjname, Integer fkkxq) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fxn = fxn;
        this.fxq = fxq;
        this.fjname = fjname;
        this.fkkxq = fkkxq;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public TCourseSemester() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_course_semester.FKeyID
     * @return teaching_diversity..t_course_semester.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_semester.FKeyID
     * @param fkeyid the value for teaching_diversity..t_course_semester.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_semester.FCID
     * @return teaching_diversity..t_course_semester.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_semester.FCID
     * @param fcid the value for teaching_diversity..t_course_semester.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_semester.FUID
     * @return teaching_diversity..t_course_semester.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_semester.FUID
     * @param fuid the value for teaching_diversity..t_course_semester.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_semester.FCDATE
     * @return teaching_diversity..t_course_semester.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_semester.FCDATE
     * @param fcdate the value for teaching_diversity..t_course_semester.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_semester.FUDATE
     * @return teaching_diversity..t_course_semester.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_semester.FUDATE
     * @param fudate the value for teaching_diversity..t_course_semester.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_course_semester.FState
     * @return teaching_diversity..t_course_semester.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_course_semester.FState
     * @param fstate the value for teaching_diversity..t_course_semester.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 名称 字段:teaching_diversity..t_course_semester.FName
     * @return teaching_diversity..t_course_semester.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:teaching_diversity..t_course_semester.FName
     * @param fname the value for teaching_diversity..t_course_semester.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 学年 字段:teaching_diversity..t_course_semester.FXN
     * @return teaching_diversity..t_course_semester.FXN, 学年
     * @mbg.generated
     */
    public Integer getFxn() {
        return fxn;
    }

    /**
     * 设置 学年 字段:teaching_diversity..t_course_semester.FXN
     * @param fxn the value for teaching_diversity..t_course_semester.FXN, 学年
     * @mbg.generated
     */
    public void setFxn(Integer fxn) {
        this.fxn = fxn;
    }

    /**
     * 获取 学期 字段:teaching_diversity..t_course_semester.FXQ
     * @return teaching_diversity..t_course_semester.FXQ, 学期
     * @mbg.generated
     */
    public Integer getFxq() {
        return fxq;
    }

    /**
     * 设置 学期 字段:teaching_diversity..t_course_semester.FXQ
     * @param fxq the value for teaching_diversity..t_course_semester.FXQ, 学期
     * @mbg.generated
     */
    public void setFxq(Integer fxq) {
        this.fxq = fxq;
    }

    /**
     * 获取 简称 字段:teaching_diversity..t_course_semester.FJName
     * @return teaching_diversity..t_course_semester.FJName, 简称
     * @mbg.generated
     */
    public String getFjname() {
        return fjname;
    }

    /**
     * 设置 简称 字段:teaching_diversity..t_course_semester.FJName
     * @param fjname the value for teaching_diversity..t_course_semester.FJName, 简称
     * @mbg.generated
     */
    public void setFjname(String fjname) {
        this.fjname = fjname == null ? null : fjname.trim();
    }

    /**
     * 获取 开课学期（1-8） 字段:teaching_diversity..t_course_semester.FKKXQ
     * @return teaching_diversity..t_course_semester.FKKXQ, 开课学期（1-8）
     * @mbg.generated
     */
    public Integer getFkkxq() {
        return fkkxq;
    }

    /**
     * 设置 开课学期（1-8） 字段:teaching_diversity..t_course_semester.FKKXQ
     * @param fkkxq the value for teaching_diversity..t_course_semester.FKKXQ, 开课学期（1-8）
     * @mbg.generated
     */
    public void setFkkxq(Integer fkkxq) {
        this.fkkxq = fkkxq;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_course_semester
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
        sb.append(", fname=").append(fname);
        sb.append(", fxn=").append(fxn);
        sb.append(", fxq=").append(fxq);
        sb.append(", fjname=").append(fjname);
        sb.append(", fkkxq=").append(fkkxq);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_course_semester
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
        TCourseSemester other = (TCourseSemester) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFxn() == null ? other.getFxn() == null : this.getFxn().equals(other.getFxn()))
            && (this.getFxq() == null ? other.getFxq() == null : this.getFxq().equals(other.getFxq()))
            && (this.getFjname() == null ? other.getFjname() == null : this.getFjname().equals(other.getFjname()))
            && (this.getFkkxq() == null ? other.getFkkxq() == null : this.getFkkxq().equals(other.getFkkxq()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_course_semester
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFxn() == null) ? 0 : getFxn().hashCode());
        result = prime * result + ((getFxq() == null) ? 0 : getFxq().hashCode());
        result = prime * result + ((getFjname() == null) ? 0 : getFjname().hashCode());
        result = prime * result + ((getFkkxq() == null) ? 0 : getFkkxq().hashCode());
        return result;
    }
}