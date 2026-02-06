package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_student
*/
@Table(name = "t_student")
public class TStudent implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_student.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 方式：1-添加、2-注册
     * 表字段 : student_capability_evaluation..t_student.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_student.FNo
     * @mbg.generated
     */
    private String fno;

    /**
     * 性别，{-1-未选择。0-男，1-女}
     * 表字段 : student_capability_evaluation..t_student.FSex
     * @mbg.generated
     */
    private Integer fsex;

    /**
     * 出生日期
     * 表字段 : student_capability_evaluation..t_student.FBirthday
     * @mbg.generated
     */
    private String fbirthday;

    /**
     * 身份证号
     * 表字段 : student_capability_evaluation..t_student.FIDNumber
     * @mbg.generated
     */
    private String fidnumber;

    /**
     * 联系电话
     * 表字段 : student_capability_evaluation..t_student.FTel
     * @mbg.generated
     */
    private String ftel;

    /**
     * 电子邮箱
     * 表字段 : student_capability_evaluation..t_student.FEmail
     * @mbg.generated
     */
    private String femail;

    /**
     * 入学时间
     * 表字段 : student_capability_evaluation..t_student.FStartSchoolDate
     * @mbg.generated
     */
    private String fstartschooldate;

    /**
     * 毕业时间
     * 表字段 : student_capability_evaluation..t_student.FEndSchoolDate
     * @mbg.generated
     */
    private String fendschooldate;

    /**
     * 专业
     * 表字段 : student_capability_evaluation..t_student.FMajor
     * @mbg.generated
     */
    private String fmajor;

    /**
     * 学历
     * 表字段 : student_capability_evaluation..t_student.FEducation
     * @mbg.generated
     */
    private String feducation;

    /**
     * 政治面目
     * 表字段 : student_capability_evaluation..t_student.FPolitical
     * @mbg.generated
     */
    private String fpolitical;

    /**
     * 住址
     * 表字段 : student_capability_evaluation..t_student.FAddr
     * @mbg.generated
     */
    private String faddr;

    /**
     * 奖励荣誉
     * 表字段 : student_capability_evaluation..t_student.FHonor
     * @mbg.generated
     */
    private String fhonor;

    /**
     * 惩罚记录
     * 表字段 : student_capability_evaluation..t_student.FPunish
     * @mbg.generated
     */
    private String fpunish;

    /**
     * 健康记录
     * 表字段 : student_capability_evaluation..t_student.FHealth
     * @mbg.generated
     */
    private String fhealth;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_student.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 能力积分（获得能力的得分）
     * 表字段 : student_capability_evaluation..t_student.FPoints
     * @mbg.generated
     */
    private Integer fpoints;

    /**
     * 用户ID，T_User表ID
     * 表字段 : student_capability_evaluation..t_student.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    /**
     * 工作状态，0-未工作，1-已工作
     * 表字段 : student_capability_evaluation..t_student.FWorkState
     * @mbg.generated
     */
    private Integer fworkstate;

    /**
     * 当前年级
     * 表字段 : student_capability_evaluation..t_student.FGradeLevel
     * @mbg.generated
     */
    private Integer fgradelevel;

    /**
     * 班级名称
     * 表字段 : student_capability_evaluation..t_student.FClassName
     * @mbg.generated
     */
    private String fclassname;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public TStudent(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer fmode, String fname, String fno, Integer fsex, String fbirthday, String fidnumber, String ftel, String femail, String fstartschooldate, String fendschooldate, String fmajor, String feducation, String fpolitical, String faddr, String fhonor, String fpunish, String fhealth, String fnote, Integer fpoints, Long fuserid, Integer fworkstate, Integer fgradelevel, String fclassname) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fmode = fmode;
        this.fname = fname;
        this.fno = fno;
        this.fsex = fsex;
        this.fbirthday = fbirthday;
        this.fidnumber = fidnumber;
        this.ftel = ftel;
        this.femail = femail;
        this.fstartschooldate = fstartschooldate;
        this.fendschooldate = fendschooldate;
        this.fmajor = fmajor;
        this.feducation = feducation;
        this.fpolitical = fpolitical;
        this.faddr = faddr;
        this.fhonor = fhonor;
        this.fpunish = fpunish;
        this.fhealth = fhealth;
        this.fnote = fnote;
        this.fpoints = fpoints;
        this.fuserid = fuserid;
        this.fworkstate = fworkstate;
        this.fgradelevel = fgradelevel;
        this.fclassname = fclassname;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public TStudent() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FKeyID
     * @return student_capability_evaluation..t_student.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_student.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FCID
     * @return student_capability_evaluation..t_student.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FCID
     * @param fcid the value for student_capability_evaluation..t_student.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FUID
     * @return student_capability_evaluation..t_student.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FUID
     * @param fuid the value for student_capability_evaluation..t_student.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FCDATE
     * @return student_capability_evaluation..t_student.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_student.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FUDATE
     * @return student_capability_evaluation..t_student.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FUDATE
     * @param fudate the value for student_capability_evaluation..t_student.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_student.FState
     * @return student_capability_evaluation..t_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_student.FState
     * @param fstate the value for student_capability_evaluation..t_student.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 方式：1-添加、2-注册 字段:student_capability_evaluation..t_student.FMode
     * @return student_capability_evaluation..t_student.FMode, 方式：1-添加、2-注册
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式：1-添加、2-注册 字段:student_capability_evaluation..t_student.FMode
     * @param fmode the value for student_capability_evaluation..t_student.FMode, 方式：1-添加、2-注册
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FName
     * @return student_capability_evaluation..t_student.FName, 
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FName
     * @param fname the value for student_capability_evaluation..t_student.FName, 
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_student.FNo
     * @return student_capability_evaluation..t_student.FNo, 
     * @mbg.generated
     */
    public String getFno() {
        return fno;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_student.FNo
     * @param fno the value for student_capability_evaluation..t_student.FNo, 
     * @mbg.generated
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * 获取 性别，{-1-未选择。0-男，1-女} 字段:student_capability_evaluation..t_student.FSex
     * @return student_capability_evaluation..t_student.FSex, 性别，{-1-未选择。0-男，1-女}
     * @mbg.generated
     */
    public Integer getFsex() {
        return fsex;
    }

    /**
     * 设置 性别，{-1-未选择。0-男，1-女} 字段:student_capability_evaluation..t_student.FSex
     * @param fsex the value for student_capability_evaluation..t_student.FSex, 性别，{-1-未选择。0-男，1-女}
     * @mbg.generated
     */
    public void setFsex(Integer fsex) {
        this.fsex = fsex;
    }

    /**
     * 获取 出生日期 字段:student_capability_evaluation..t_student.FBirthday
     * @return student_capability_evaluation..t_student.FBirthday, 出生日期
     * @mbg.generated
     */
    public String getFbirthday() {
        return fbirthday;
    }

    /**
     * 设置 出生日期 字段:student_capability_evaluation..t_student.FBirthday
     * @param fbirthday the value for student_capability_evaluation..t_student.FBirthday, 出生日期
     * @mbg.generated
     */
    public void setFbirthday(String fbirthday) {
        this.fbirthday = fbirthday == null ? null : fbirthday.trim();
    }

    /**
     * 获取 身份证号 字段:student_capability_evaluation..t_student.FIDNumber
     * @return student_capability_evaluation..t_student.FIDNumber, 身份证号
     * @mbg.generated
     */
    public String getFidnumber() {
        return fidnumber;
    }

    /**
     * 设置 身份证号 字段:student_capability_evaluation..t_student.FIDNumber
     * @param fidnumber the value for student_capability_evaluation..t_student.FIDNumber, 身份证号
     * @mbg.generated
     */
    public void setFidnumber(String fidnumber) {
        this.fidnumber = fidnumber == null ? null : fidnumber.trim();
    }

    /**
     * 获取 联系电话 字段:student_capability_evaluation..t_student.FTel
     * @return student_capability_evaluation..t_student.FTel, 联系电话
     * @mbg.generated
     */
    public String getFtel() {
        return ftel;
    }

    /**
     * 设置 联系电话 字段:student_capability_evaluation..t_student.FTel
     * @param ftel the value for student_capability_evaluation..t_student.FTel, 联系电话
     * @mbg.generated
     */
    public void setFtel(String ftel) {
        this.ftel = ftel == null ? null : ftel.trim();
    }

    /**
     * 获取 电子邮箱 字段:student_capability_evaluation..t_student.FEmail
     * @return student_capability_evaluation..t_student.FEmail, 电子邮箱
     * @mbg.generated
     */
    public String getFemail() {
        return femail;
    }

    /**
     * 设置 电子邮箱 字段:student_capability_evaluation..t_student.FEmail
     * @param femail the value for student_capability_evaluation..t_student.FEmail, 电子邮箱
     * @mbg.generated
     */
    public void setFemail(String femail) {
        this.femail = femail == null ? null : femail.trim();
    }

    /**
     * 获取 入学时间 字段:student_capability_evaluation..t_student.FStartSchoolDate
     * @return student_capability_evaluation..t_student.FStartSchoolDate, 入学时间
     * @mbg.generated
     */
    public String getFstartschooldate() {
        return fstartschooldate;
    }

    /**
     * 设置 入学时间 字段:student_capability_evaluation..t_student.FStartSchoolDate
     * @param fstartschooldate the value for student_capability_evaluation..t_student.FStartSchoolDate, 入学时间
     * @mbg.generated
     */
    public void setFstartschooldate(String fstartschooldate) {
        this.fstartschooldate = fstartschooldate == null ? null : fstartschooldate.trim();
    }

    /**
     * 获取 毕业时间 字段:student_capability_evaluation..t_student.FEndSchoolDate
     * @return student_capability_evaluation..t_student.FEndSchoolDate, 毕业时间
     * @mbg.generated
     */
    public String getFendschooldate() {
        return fendschooldate;
    }

    /**
     * 设置 毕业时间 字段:student_capability_evaluation..t_student.FEndSchoolDate
     * @param fendschooldate the value for student_capability_evaluation..t_student.FEndSchoolDate, 毕业时间
     * @mbg.generated
     */
    public void setFendschooldate(String fendschooldate) {
        this.fendschooldate = fendschooldate == null ? null : fendschooldate.trim();
    }

    /**
     * 获取 专业 字段:student_capability_evaluation..t_student.FMajor
     * @return student_capability_evaluation..t_student.FMajor, 专业
     * @mbg.generated
     */
    public String getFmajor() {
        return fmajor;
    }

    /**
     * 设置 专业 字段:student_capability_evaluation..t_student.FMajor
     * @param fmajor the value for student_capability_evaluation..t_student.FMajor, 专业
     * @mbg.generated
     */
    public void setFmajor(String fmajor) {
        this.fmajor = fmajor == null ? null : fmajor.trim();
    }

    /**
     * 获取 学历 字段:student_capability_evaluation..t_student.FEducation
     * @return student_capability_evaluation..t_student.FEducation, 学历
     * @mbg.generated
     */
    public String getFeducation() {
        return feducation;
    }

    /**
     * 设置 学历 字段:student_capability_evaluation..t_student.FEducation
     * @param feducation the value for student_capability_evaluation..t_student.FEducation, 学历
     * @mbg.generated
     */
    public void setFeducation(String feducation) {
        this.feducation = feducation == null ? null : feducation.trim();
    }

    /**
     * 获取 政治面目 字段:student_capability_evaluation..t_student.FPolitical
     * @return student_capability_evaluation..t_student.FPolitical, 政治面目
     * @mbg.generated
     */
    public String getFpolitical() {
        return fpolitical;
    }

    /**
     * 设置 政治面目 字段:student_capability_evaluation..t_student.FPolitical
     * @param fpolitical the value for student_capability_evaluation..t_student.FPolitical, 政治面目
     * @mbg.generated
     */
    public void setFpolitical(String fpolitical) {
        this.fpolitical = fpolitical == null ? null : fpolitical.trim();
    }

    /**
     * 获取 住址 字段:student_capability_evaluation..t_student.FAddr
     * @return student_capability_evaluation..t_student.FAddr, 住址
     * @mbg.generated
     */
    public String getFaddr() {
        return faddr;
    }

    /**
     * 设置 住址 字段:student_capability_evaluation..t_student.FAddr
     * @param faddr the value for student_capability_evaluation..t_student.FAddr, 住址
     * @mbg.generated
     */
    public void setFaddr(String faddr) {
        this.faddr = faddr == null ? null : faddr.trim();
    }

    /**
     * 获取 奖励荣誉 字段:student_capability_evaluation..t_student.FHonor
     * @return student_capability_evaluation..t_student.FHonor, 奖励荣誉
     * @mbg.generated
     */
    public String getFhonor() {
        return fhonor;
    }

    /**
     * 设置 奖励荣誉 字段:student_capability_evaluation..t_student.FHonor
     * @param fhonor the value for student_capability_evaluation..t_student.FHonor, 奖励荣誉
     * @mbg.generated
     */
    public void setFhonor(String fhonor) {
        this.fhonor = fhonor == null ? null : fhonor.trim();
    }

    /**
     * 获取 惩罚记录 字段:student_capability_evaluation..t_student.FPunish
     * @return student_capability_evaluation..t_student.FPunish, 惩罚记录
     * @mbg.generated
     */
    public String getFpunish() {
        return fpunish;
    }

    /**
     * 设置 惩罚记录 字段:student_capability_evaluation..t_student.FPunish
     * @param fpunish the value for student_capability_evaluation..t_student.FPunish, 惩罚记录
     * @mbg.generated
     */
    public void setFpunish(String fpunish) {
        this.fpunish = fpunish == null ? null : fpunish.trim();
    }

    /**
     * 获取 健康记录 字段:student_capability_evaluation..t_student.FHealth
     * @return student_capability_evaluation..t_student.FHealth, 健康记录
     * @mbg.generated
     */
    public String getFhealth() {
        return fhealth;
    }

    /**
     * 设置 健康记录 字段:student_capability_evaluation..t_student.FHealth
     * @param fhealth the value for student_capability_evaluation..t_student.FHealth, 健康记录
     * @mbg.generated
     */
    public void setFhealth(String fhealth) {
        this.fhealth = fhealth == null ? null : fhealth.trim();
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_student.FNote
     * @return student_capability_evaluation..t_student.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_student.FNote
     * @param fnote the value for student_capability_evaluation..t_student.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 能力积分（获得能力的得分） 字段:student_capability_evaluation..t_student.FPoints
     * @return student_capability_evaluation..t_student.FPoints, 能力积分（获得能力的得分）
     * @mbg.generated
     */
    public Integer getFpoints() {
        return fpoints;
    }

    /**
     * 设置 能力积分（获得能力的得分） 字段:student_capability_evaluation..t_student.FPoints
     * @param fpoints the value for student_capability_evaluation..t_student.FPoints, 能力积分（获得能力的得分）
     * @mbg.generated
     */
    public void setFpoints(Integer fpoints) {
        this.fpoints = fpoints;
    }

    /**
     * 获取 用户ID，T_User表ID 字段:student_capability_evaluation..t_student.FUserID
     * @return student_capability_evaluation..t_student.FUserID, 用户ID，T_User表ID
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 用户ID，T_User表ID 字段:student_capability_evaluation..t_student.FUserID
     * @param fuserid the value for student_capability_evaluation..t_student.FUserID, 用户ID，T_User表ID
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 获取 工作状态，0-未工作，1-已工作 字段:student_capability_evaluation..t_student.FWorkState
     * @return student_capability_evaluation..t_student.FWorkState, 工作状态，0-未工作，1-已工作
     * @mbg.generated
     */
    public Integer getFworkstate() {
        return fworkstate;
    }

    /**
     * 设置 工作状态，0-未工作，1-已工作 字段:student_capability_evaluation..t_student.FWorkState
     * @param fworkstate the value for student_capability_evaluation..t_student.FWorkState, 工作状态，0-未工作，1-已工作
     * @mbg.generated
     */
    public void setFworkstate(Integer fworkstate) {
        this.fworkstate = fworkstate;
    }

    /**
     * 获取 当前年级 字段:student_capability_evaluation..t_student.FGradeLevel
     * @return student_capability_evaluation..t_student.FGradeLevel, 当前年级
     * @mbg.generated
     */
    public Integer getFgradelevel() {
        return fgradelevel;
    }

    /**
     * 设置 当前年级 字段:student_capability_evaluation..t_student.FGradeLevel
     * @param fgradelevel the value for student_capability_evaluation..t_student.FGradeLevel, 当前年级
     * @mbg.generated
     */
    public void setFgradelevel(Integer fgradelevel) {
        this.fgradelevel = fgradelevel;
    }

    /**
     * 获取 班级名称 字段:student_capability_evaluation..t_student.FClassName
     * @return student_capability_evaluation..t_student.FClassName, 班级名称
     * @mbg.generated
     */
    public String getFclassname() {
        return fclassname;
    }

    /**
     * 设置 班级名称 字段:student_capability_evaluation..t_student.FClassName
     * @param fclassname the value for student_capability_evaluation..t_student.FClassName, 班级名称
     * @mbg.generated
     */
    public void setFclassname(String fclassname) {
        this.fclassname = fclassname == null ? null : fclassname.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_student
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
        sb.append(", fmode=").append(fmode);
        sb.append(", fname=").append(fname);
        sb.append(", fno=").append(fno);
        sb.append(", fsex=").append(fsex);
        sb.append(", fbirthday=").append(fbirthday);
        sb.append(", fidnumber=").append(fidnumber);
        sb.append(", ftel=").append(ftel);
        sb.append(", femail=").append(femail);
        sb.append(", fstartschooldate=").append(fstartschooldate);
        sb.append(", fendschooldate=").append(fendschooldate);
        sb.append(", fmajor=").append(fmajor);
        sb.append(", feducation=").append(feducation);
        sb.append(", fpolitical=").append(fpolitical);
        sb.append(", faddr=").append(faddr);
        sb.append(", fhonor=").append(fhonor);
        sb.append(", fpunish=").append(fpunish);
        sb.append(", fhealth=").append(fhealth);
        sb.append(", fnote=").append(fnote);
        sb.append(", fpoints=").append(fpoints);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fworkstate=").append(fworkstate);
        sb.append(", fgradelevel=").append(fgradelevel);
        sb.append(", fclassname=").append(fclassname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_student
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
        TStudent other = (TStudent) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFno() == null ? other.getFno() == null : this.getFno().equals(other.getFno()))
            && (this.getFsex() == null ? other.getFsex() == null : this.getFsex().equals(other.getFsex()))
            && (this.getFbirthday() == null ? other.getFbirthday() == null : this.getFbirthday().equals(other.getFbirthday()))
            && (this.getFidnumber() == null ? other.getFidnumber() == null : this.getFidnumber().equals(other.getFidnumber()))
            && (this.getFtel() == null ? other.getFtel() == null : this.getFtel().equals(other.getFtel()))
            && (this.getFemail() == null ? other.getFemail() == null : this.getFemail().equals(other.getFemail()))
            && (this.getFstartschooldate() == null ? other.getFstartschooldate() == null : this.getFstartschooldate().equals(other.getFstartschooldate()))
            && (this.getFendschooldate() == null ? other.getFendschooldate() == null : this.getFendschooldate().equals(other.getFendschooldate()))
            && (this.getFmajor() == null ? other.getFmajor() == null : this.getFmajor().equals(other.getFmajor()))
            && (this.getFeducation() == null ? other.getFeducation() == null : this.getFeducation().equals(other.getFeducation()))
            && (this.getFpolitical() == null ? other.getFpolitical() == null : this.getFpolitical().equals(other.getFpolitical()))
            && (this.getFaddr() == null ? other.getFaddr() == null : this.getFaddr().equals(other.getFaddr()))
            && (this.getFhonor() == null ? other.getFhonor() == null : this.getFhonor().equals(other.getFhonor()))
            && (this.getFpunish() == null ? other.getFpunish() == null : this.getFpunish().equals(other.getFpunish()))
            && (this.getFhealth() == null ? other.getFhealth() == null : this.getFhealth().equals(other.getFhealth()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFpoints() == null ? other.getFpoints() == null : this.getFpoints().equals(other.getFpoints()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()))
            && (this.getFworkstate() == null ? other.getFworkstate() == null : this.getFworkstate().equals(other.getFworkstate()))
            && (this.getFgradelevel() == null ? other.getFgradelevel() == null : this.getFgradelevel().equals(other.getFgradelevel()))
            && (this.getFclassname() == null ? other.getFclassname() == null : this.getFclassname().equals(other.getFclassname()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_student
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
        result = prime * result + ((getFmode() == null) ? 0 : getFmode().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFno() == null) ? 0 : getFno().hashCode());
        result = prime * result + ((getFsex() == null) ? 0 : getFsex().hashCode());
        result = prime * result + ((getFbirthday() == null) ? 0 : getFbirthday().hashCode());
        result = prime * result + ((getFidnumber() == null) ? 0 : getFidnumber().hashCode());
        result = prime * result + ((getFtel() == null) ? 0 : getFtel().hashCode());
        result = prime * result + ((getFemail() == null) ? 0 : getFemail().hashCode());
        result = prime * result + ((getFstartschooldate() == null) ? 0 : getFstartschooldate().hashCode());
        result = prime * result + ((getFendschooldate() == null) ? 0 : getFendschooldate().hashCode());
        result = prime * result + ((getFmajor() == null) ? 0 : getFmajor().hashCode());
        result = prime * result + ((getFeducation() == null) ? 0 : getFeducation().hashCode());
        result = prime * result + ((getFpolitical() == null) ? 0 : getFpolitical().hashCode());
        result = prime * result + ((getFaddr() == null) ? 0 : getFaddr().hashCode());
        result = prime * result + ((getFhonor() == null) ? 0 : getFhonor().hashCode());
        result = prime * result + ((getFpunish() == null) ? 0 : getFpunish().hashCode());
        result = prime * result + ((getFhealth() == null) ? 0 : getFhealth().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFpoints() == null) ? 0 : getFpoints().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        result = prime * result + ((getFworkstate() == null) ? 0 : getFworkstate().hashCode());
        result = prime * result + ((getFgradelevel() == null) ? 0 : getFgradelevel().hashCode());
        result = prime * result + ((getFclassname() == null) ? 0 : getFclassname().hashCode());
        return result;
    }
}