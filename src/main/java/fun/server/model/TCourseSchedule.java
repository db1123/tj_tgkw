package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_schedule
*/
@Table(name = "t_course_schedule")
public class TCourseSchedule implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_schedule.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_schedule.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_schedule.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_schedule.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_course_schedule.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_course_schedule.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 开课表ID
     * 表字段 : student_capability_evaluation..t_course_schedule.FCOID
     * @mbg.generated
     */
    private Long fcoid;

    /**
     * 关联教室表ID
     * 表字段 : student_capability_evaluation..t_course_schedule.FCRMID
     * @mbg.generated
     */
    private Long fcrmid;

    /**
     * 星期几（周一至周日）数字1-7
     * 表字段 : student_capability_evaluation..t_course_schedule.FWeekday
     * @mbg.generated
     */
    private Integer fweekday;

    /**
     * 开始时间
     * 表字段 : student_capability_evaluation..t_course_schedule.FStartTime
     * @mbg.generated
     */
    private Date fstarttime;

    /**
     * 结束时间
     * 表字段 : student_capability_evaluation..t_course_schedule.FEndTime
     * @mbg.generated
     */
    private Date fendtime;

    /**
     * 节次（如1-2节）
     * 表字段 : student_capability_evaluation..t_course_schedule.FSession
     * @mbg.generated
     */
    private String fsession;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_course_schedule.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public TCourseSchedule(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcoid, Long fcrmid, Integer fweekday, Date fstarttime, Date fendtime, String fsession, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcoid = fcoid;
        this.fcrmid = fcrmid;
        this.fweekday = fweekday;
        this.fstarttime = fstarttime;
        this.fendtime = fendtime;
        this.fsession = fsession;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public TCourseSchedule() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_schedule.FKeyID
     * @return student_capability_evaluation..t_course_schedule.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_schedule.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_schedule.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_schedule.FCID
     * @return student_capability_evaluation..t_course_schedule.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_schedule.FCID
     * @param fcid the value for student_capability_evaluation..t_course_schedule.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_schedule.FUID
     * @return student_capability_evaluation..t_course_schedule.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_schedule.FUID
     * @param fuid the value for student_capability_evaluation..t_course_schedule.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_schedule.FCDATE
     * @return student_capability_evaluation..t_course_schedule.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_schedule.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_schedule.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_course_schedule.FUDATE
     * @return student_capability_evaluation..t_course_schedule.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_course_schedule.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_schedule.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_course_schedule.FState
     * @return student_capability_evaluation..t_course_schedule.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_course_schedule.FState
     * @param fstate the value for student_capability_evaluation..t_course_schedule.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 开课表ID 字段:student_capability_evaluation..t_course_schedule.FCOID
     * @return student_capability_evaluation..t_course_schedule.FCOID, 开课表ID
     * @mbg.generated
     */
    public Long getFcoid() {
        return fcoid;
    }

    /**
     * 设置 开课表ID 字段:student_capability_evaluation..t_course_schedule.FCOID
     * @param fcoid the value for student_capability_evaluation..t_course_schedule.FCOID, 开课表ID
     * @mbg.generated
     */
    public void setFcoid(Long fcoid) {
        this.fcoid = fcoid;
    }

    /**
     * 获取 关联教室表ID 字段:student_capability_evaluation..t_course_schedule.FCRMID
     * @return student_capability_evaluation..t_course_schedule.FCRMID, 关联教室表ID
     * @mbg.generated
     */
    public Long getFcrmid() {
        return fcrmid;
    }

    /**
     * 设置 关联教室表ID 字段:student_capability_evaluation..t_course_schedule.FCRMID
     * @param fcrmid the value for student_capability_evaluation..t_course_schedule.FCRMID, 关联教室表ID
     * @mbg.generated
     */
    public void setFcrmid(Long fcrmid) {
        this.fcrmid = fcrmid;
    }

    /**
     * 获取 星期几（周一至周日）数字1-7 字段:student_capability_evaluation..t_course_schedule.FWeekday
     * @return student_capability_evaluation..t_course_schedule.FWeekday, 星期几（周一至周日）数字1-7
     * @mbg.generated
     */
    public Integer getFweekday() {
        return fweekday;
    }

    /**
     * 设置 星期几（周一至周日）数字1-7 字段:student_capability_evaluation..t_course_schedule.FWeekday
     * @param fweekday the value for student_capability_evaluation..t_course_schedule.FWeekday, 星期几（周一至周日）数字1-7
     * @mbg.generated
     */
    public void setFweekday(Integer fweekday) {
        this.fweekday = fweekday;
    }

    /**
     * 获取 开始时间 字段:student_capability_evaluation..t_course_schedule.FStartTime
     * @return student_capability_evaluation..t_course_schedule.FStartTime, 开始时间
     * @mbg.generated
     */
    public Date getFstarttime() {
        return fstarttime;
    }

    /**
     * 设置 开始时间 字段:student_capability_evaluation..t_course_schedule.FStartTime
     * @param fstarttime the value for student_capability_evaluation..t_course_schedule.FStartTime, 开始时间
     * @mbg.generated
     */
    public void setFstarttime(Date fstarttime) {
        this.fstarttime = fstarttime;
    }

    /**
     * 获取 结束时间 字段:student_capability_evaluation..t_course_schedule.FEndTime
     * @return student_capability_evaluation..t_course_schedule.FEndTime, 结束时间
     * @mbg.generated
     */
    public Date getFendtime() {
        return fendtime;
    }

    /**
     * 设置 结束时间 字段:student_capability_evaluation..t_course_schedule.FEndTime
     * @param fendtime the value for student_capability_evaluation..t_course_schedule.FEndTime, 结束时间
     * @mbg.generated
     */
    public void setFendtime(Date fendtime) {
        this.fendtime = fendtime;
    }

    /**
     * 获取 节次（如1-2节） 字段:student_capability_evaluation..t_course_schedule.FSession
     * @return student_capability_evaluation..t_course_schedule.FSession, 节次（如1-2节）
     * @mbg.generated
     */
    public String getFsession() {
        return fsession;
    }

    /**
     * 设置 节次（如1-2节） 字段:student_capability_evaluation..t_course_schedule.FSession
     * @param fsession the value for student_capability_evaluation..t_course_schedule.FSession, 节次（如1-2节）
     * @mbg.generated
     */
    public void setFsession(String fsession) {
        this.fsession = fsession == null ? null : fsession.trim();
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_course_schedule.FNote
     * @return student_capability_evaluation..t_course_schedule.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_course_schedule.FNote
     * @param fnote the value for student_capability_evaluation..t_course_schedule.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_schedule
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
        sb.append(", fcoid=").append(fcoid);
        sb.append(", fcrmid=").append(fcrmid);
        sb.append(", fweekday=").append(fweekday);
        sb.append(", fstarttime=").append(fstarttime);
        sb.append(", fendtime=").append(fendtime);
        sb.append(", fsession=").append(fsession);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_schedule
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
        TCourseSchedule other = (TCourseSchedule) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcoid() == null ? other.getFcoid() == null : this.getFcoid().equals(other.getFcoid()))
            && (this.getFcrmid() == null ? other.getFcrmid() == null : this.getFcrmid().equals(other.getFcrmid()))
            && (this.getFweekday() == null ? other.getFweekday() == null : this.getFweekday().equals(other.getFweekday()))
            && (this.getFstarttime() == null ? other.getFstarttime() == null : this.getFstarttime().equals(other.getFstarttime()))
            && (this.getFendtime() == null ? other.getFendtime() == null : this.getFendtime().equals(other.getFendtime()))
            && (this.getFsession() == null ? other.getFsession() == null : this.getFsession().equals(other.getFsession()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_schedule
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
        result = prime * result + ((getFcoid() == null) ? 0 : getFcoid().hashCode());
        result = prime * result + ((getFcrmid() == null) ? 0 : getFcrmid().hashCode());
        result = prime * result + ((getFweekday() == null) ? 0 : getFweekday().hashCode());
        result = prime * result + ((getFstarttime() == null) ? 0 : getFstarttime().hashCode());
        result = prime * result + ((getFendtime() == null) ? 0 : getFendtime().hashCode());
        result = prime * result + ((getFsession() == null) ? 0 : getFsession().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}