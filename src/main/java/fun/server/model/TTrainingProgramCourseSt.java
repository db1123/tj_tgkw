package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_course_st
 * 表注释：专业培养方案课程安排新表
*/
@Table(name = "t_training_program_course_st")
public class TTrainingProgramCourseSt implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_st.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_st.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_st.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_st.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_training_program_course_st.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_training_program_course_st.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : teaching_diversity..t_training_program_course_st.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : teaching_diversity..t_training_program_course_st.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 课程ID,可为空
     * 表字段 : teaching_diversity..t_training_program_course_st.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 课程名称，手填或代入
     * 表字段 : teaching_diversity..t_training_program_course_st.FCourseName
     * @mbg.generated
     */
    private String fcoursename;

    /**
     * 顺序
     * 表字段 : teaching_diversity..t_training_program_course_st.FOrder
     * @mbg.generated
     */
    private Integer forder;

    /**
     * 学分
     * 表字段 : teaching_diversity..t_training_program_course_st.FXF
     * @mbg.generated
     */
    private Float fxf;

    /**
     * 总学时
     * 表字段 : teaching_diversity..t_training_program_course_st.FTotalHours
     * @mbg.generated
     */
    private String ftotalhours;

    /**
     * 周学时
     * 表字段 : teaching_diversity..t_training_program_course_st.FWeeklyStudyHours
     * @mbg.generated
     */
    private String fweeklystudyhours;

    /**
     * 理论学时
     * 表字段 : teaching_diversity..t_training_program_course_st.FTheoreticalStudyHours
     * @mbg.generated
     */
    private String ftheoreticalstudyhours;

    /**
     * 实践学时
     * 表字段 : teaching_diversity..t_training_program_course_st.FPracticalStudyHours
     * @mbg.generated
     */
    private String fpracticalstudyhours;

    /**
     * 是否匹配课程1=未匹配 2-已匹配
     * 表字段 : teaching_diversity..t_training_program_course_st.FPPState
     * @mbg.generated
     */
    private Integer fppstate;

    /**
     * 是否验证，1-未验证 2-通过验证 3-未通过验证
     * 表字段 : teaching_diversity..t_training_program_course_st.FYZState
     * @mbg.generated
     */
    private Integer fyzstate;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public TTrainingProgramCourseSt(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, Long fcourseid, String fcoursename, Integer forder, Float fxf, String ftotalhours, String fweeklystudyhours, String ftheoreticalstudyhours, String fpracticalstudyhours, Integer fppstate, Integer fyzstate) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fcourseid = fcourseid;
        this.fcoursename = fcoursename;
        this.forder = forder;
        this.fxf = fxf;
        this.ftotalhours = ftotalhours;
        this.fweeklystudyhours = fweeklystudyhours;
        this.ftheoreticalstudyhours = ftheoreticalstudyhours;
        this.fpracticalstudyhours = fpracticalstudyhours;
        this.fppstate = fppstate;
        this.fyzstate = fyzstate;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public TTrainingProgramCourseSt() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_st.FKeyID
     * @return teaching_diversity..t_training_program_course_st.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_st.FKeyID
     * @param fkeyid the value for teaching_diversity..t_training_program_course_st.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_st.FCID
     * @return teaching_diversity..t_training_program_course_st.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_st.FCID
     * @param fcid the value for teaching_diversity..t_training_program_course_st.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_st.FUID
     * @return teaching_diversity..t_training_program_course_st.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_st.FUID
     * @param fuid the value for teaching_diversity..t_training_program_course_st.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_st.FCDATE
     * @return teaching_diversity..t_training_program_course_st.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_st.FCDATE
     * @param fcdate the value for teaching_diversity..t_training_program_course_st.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_training_program_course_st.FUDATE
     * @return teaching_diversity..t_training_program_course_st.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_training_program_course_st.FUDATE
     * @param fudate the value for teaching_diversity..t_training_program_course_st.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_training_program_course_st.FState
     * @return teaching_diversity..t_training_program_course_st.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_training_program_course_st.FState
     * @param fstate the value for teaching_diversity..t_training_program_course_st.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:teaching_diversity..t_training_program_course_st.FTMID
     * @return teaching_diversity..t_training_program_course_st.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:teaching_diversity..t_training_program_course_st.FTMID
     * @param ftmid the value for teaching_diversity..t_training_program_course_st.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:teaching_diversity..t_training_program_course_st.FTPID
     * @return teaching_diversity..t_training_program_course_st.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:teaching_diversity..t_training_program_course_st.FTPID
     * @param ftpid the value for teaching_diversity..t_training_program_course_st.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 课程ID,可为空 字段:teaching_diversity..t_training_program_course_st.FCourseID
     * @return teaching_diversity..t_training_program_course_st.FCourseID, 课程ID,可为空
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID,可为空 字段:teaching_diversity..t_training_program_course_st.FCourseID
     * @param fcourseid the value for teaching_diversity..t_training_program_course_st.FCourseID, 课程ID,可为空
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 课程名称，手填或代入 字段:teaching_diversity..t_training_program_course_st.FCourseName
     * @return teaching_diversity..t_training_program_course_st.FCourseName, 课程名称，手填或代入
     * @mbg.generated
     */
    public String getFcoursename() {
        return fcoursename;
    }

    /**
     * 设置 课程名称，手填或代入 字段:teaching_diversity..t_training_program_course_st.FCourseName
     * @param fcoursename the value for teaching_diversity..t_training_program_course_st.FCourseName, 课程名称，手填或代入
     * @mbg.generated
     */
    public void setFcoursename(String fcoursename) {
        this.fcoursename = fcoursename == null ? null : fcoursename.trim();
    }

    /**
     * 获取 顺序 字段:teaching_diversity..t_training_program_course_st.FOrder
     * @return teaching_diversity..t_training_program_course_st.FOrder, 顺序
     * @mbg.generated
     */
    public Integer getForder() {
        return forder;
    }

    /**
     * 设置 顺序 字段:teaching_diversity..t_training_program_course_st.FOrder
     * @param forder the value for teaching_diversity..t_training_program_course_st.FOrder, 顺序
     * @mbg.generated
     */
    public void setForder(Integer forder) {
        this.forder = forder;
    }

    /**
     * 获取 学分 字段:teaching_diversity..t_training_program_course_st.FXF
     * @return teaching_diversity..t_training_program_course_st.FXF, 学分
     * @mbg.generated
     */
    public Float getFxf() {
        return fxf;
    }

    /**
     * 设置 学分 字段:teaching_diversity..t_training_program_course_st.FXF
     * @param fxf the value for teaching_diversity..t_training_program_course_st.FXF, 学分
     * @mbg.generated
     */
    public void setFxf(Float fxf) {
        this.fxf = fxf;
    }

    /**
     * 获取 总学时 字段:teaching_diversity..t_training_program_course_st.FTotalHours
     * @return teaching_diversity..t_training_program_course_st.FTotalHours, 总学时
     * @mbg.generated
     */
    public String getFtotalhours() {
        return ftotalhours;
    }

    /**
     * 设置 总学时 字段:teaching_diversity..t_training_program_course_st.FTotalHours
     * @param ftotalhours the value for teaching_diversity..t_training_program_course_st.FTotalHours, 总学时
     * @mbg.generated
     */
    public void setFtotalhours(String ftotalhours) {
        this.ftotalhours = ftotalhours == null ? null : ftotalhours.trim();
    }

    /**
     * 获取 周学时 字段:teaching_diversity..t_training_program_course_st.FWeeklyStudyHours
     * @return teaching_diversity..t_training_program_course_st.FWeeklyStudyHours, 周学时
     * @mbg.generated
     */
    public String getFweeklystudyhours() {
        return fweeklystudyhours;
    }

    /**
     * 设置 周学时 字段:teaching_diversity..t_training_program_course_st.FWeeklyStudyHours
     * @param fweeklystudyhours the value for teaching_diversity..t_training_program_course_st.FWeeklyStudyHours, 周学时
     * @mbg.generated
     */
    public void setFweeklystudyhours(String fweeklystudyhours) {
        this.fweeklystudyhours = fweeklystudyhours == null ? null : fweeklystudyhours.trim();
    }

    /**
     * 获取 理论学时 字段:teaching_diversity..t_training_program_course_st.FTheoreticalStudyHours
     * @return teaching_diversity..t_training_program_course_st.FTheoreticalStudyHours, 理论学时
     * @mbg.generated
     */
    public String getFtheoreticalstudyhours() {
        return ftheoreticalstudyhours;
    }

    /**
     * 设置 理论学时 字段:teaching_diversity..t_training_program_course_st.FTheoreticalStudyHours
     * @param ftheoreticalstudyhours the value for teaching_diversity..t_training_program_course_st.FTheoreticalStudyHours, 理论学时
     * @mbg.generated
     */
    public void setFtheoreticalstudyhours(String ftheoreticalstudyhours) {
        this.ftheoreticalstudyhours = ftheoreticalstudyhours == null ? null : ftheoreticalstudyhours.trim();
    }

    /**
     * 获取 实践学时 字段:teaching_diversity..t_training_program_course_st.FPracticalStudyHours
     * @return teaching_diversity..t_training_program_course_st.FPracticalStudyHours, 实践学时
     * @mbg.generated
     */
    public String getFpracticalstudyhours() {
        return fpracticalstudyhours;
    }

    /**
     * 设置 实践学时 字段:teaching_diversity..t_training_program_course_st.FPracticalStudyHours
     * @param fpracticalstudyhours the value for teaching_diversity..t_training_program_course_st.FPracticalStudyHours, 实践学时
     * @mbg.generated
     */
    public void setFpracticalstudyhours(String fpracticalstudyhours) {
        this.fpracticalstudyhours = fpracticalstudyhours == null ? null : fpracticalstudyhours.trim();
    }

    /**
     * 获取 是否匹配课程1=未匹配 2-已匹配 字段:teaching_diversity..t_training_program_course_st.FPPState
     * @return teaching_diversity..t_training_program_course_st.FPPState, 是否匹配课程1=未匹配 2-已匹配
     * @mbg.generated
     */
    public Integer getFppstate() {
        return fppstate;
    }

    /**
     * 设置 是否匹配课程1=未匹配 2-已匹配 字段:teaching_diversity..t_training_program_course_st.FPPState
     * @param fppstate the value for teaching_diversity..t_training_program_course_st.FPPState, 是否匹配课程1=未匹配 2-已匹配
     * @mbg.generated
     */
    public void setFppstate(Integer fppstate) {
        this.fppstate = fppstate;
    }

    /**
     * 获取 是否验证，1-未验证 2-通过验证 3-未通过验证 字段:teaching_diversity..t_training_program_course_st.FYZState
     * @return teaching_diversity..t_training_program_course_st.FYZState, 是否验证，1-未验证 2-通过验证 3-未通过验证
     * @mbg.generated
     */
    public Integer getFyzstate() {
        return fyzstate;
    }

    /**
     * 设置 是否验证，1-未验证 2-通过验证 3-未通过验证 字段:teaching_diversity..t_training_program_course_st.FYZState
     * @param fyzstate the value for teaching_diversity..t_training_program_course_st.FYZState, 是否验证，1-未验证 2-通过验证 3-未通过验证
     * @mbg.generated
     */
    public void setFyzstate(Integer fyzstate) {
        this.fyzstate = fyzstate;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_training_program_course_st
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
        sb.append(", ftmid=").append(ftmid);
        sb.append(", ftpid=").append(ftpid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fcoursename=").append(fcoursename);
        sb.append(", forder=").append(forder);
        sb.append(", fxf=").append(fxf);
        sb.append(", ftotalhours=").append(ftotalhours);
        sb.append(", fweeklystudyhours=").append(fweeklystudyhours);
        sb.append(", ftheoreticalstudyhours=").append(ftheoreticalstudyhours);
        sb.append(", fpracticalstudyhours=").append(fpracticalstudyhours);
        sb.append(", fppstate=").append(fppstate);
        sb.append(", fyzstate=").append(fyzstate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_training_program_course_st
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
        TTrainingProgramCourseSt other = (TTrainingProgramCourseSt) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFcoursename() == null ? other.getFcoursename() == null : this.getFcoursename().equals(other.getFcoursename()))
            && (this.getForder() == null ? other.getForder() == null : this.getForder().equals(other.getForder()))
            && (this.getFxf() == null ? other.getFxf() == null : this.getFxf().equals(other.getFxf()))
            && (this.getFtotalhours() == null ? other.getFtotalhours() == null : this.getFtotalhours().equals(other.getFtotalhours()))
            && (this.getFweeklystudyhours() == null ? other.getFweeklystudyhours() == null : this.getFweeklystudyhours().equals(other.getFweeklystudyhours()))
            && (this.getFtheoreticalstudyhours() == null ? other.getFtheoreticalstudyhours() == null : this.getFtheoreticalstudyhours().equals(other.getFtheoreticalstudyhours()))
            && (this.getFpracticalstudyhours() == null ? other.getFpracticalstudyhours() == null : this.getFpracticalstudyhours().equals(other.getFpracticalstudyhours()))
            && (this.getFppstate() == null ? other.getFppstate() == null : this.getFppstate().equals(other.getFppstate()))
            && (this.getFyzstate() == null ? other.getFyzstate() == null : this.getFyzstate().equals(other.getFyzstate()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_training_program_course_st
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
        result = prime * result + ((getFtmid() == null) ? 0 : getFtmid().hashCode());
        result = prime * result + ((getFtpid() == null) ? 0 : getFtpid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFcoursename() == null) ? 0 : getFcoursename().hashCode());
        result = prime * result + ((getForder() == null) ? 0 : getForder().hashCode());
        result = prime * result + ((getFxf() == null) ? 0 : getFxf().hashCode());
        result = prime * result + ((getFtotalhours() == null) ? 0 : getFtotalhours().hashCode());
        result = prime * result + ((getFweeklystudyhours() == null) ? 0 : getFweeklystudyhours().hashCode());
        result = prime * result + ((getFtheoreticalstudyhours() == null) ? 0 : getFtheoreticalstudyhours().hashCode());
        result = prime * result + ((getFpracticalstudyhours() == null) ? 0 : getFpracticalstudyhours().hashCode());
        result = prime * result + ((getFppstate() == null) ? 0 : getFppstate().hashCode());
        result = prime * result + ((getFyzstate() == null) ? 0 : getFyzstate().hashCode());
        return result;
    }
}