package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_course
*/
@Table(name = "t_course")
public class TCourse implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_course.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_course.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_course.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程名称
     * 表字段 : teaching_diversity..t_course.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 课程编号
     * 表字段 : teaching_diversity..t_course.FNo
     * @mbg.generated
     */
    private String fno;

    /**
     * 课程描述
     * 表字段 : teaching_diversity..t_course.FCon
     * @mbg.generated
     */
    private String fcon;

    /**
     * 合格分数
     * 表字段 : teaching_diversity..t_course.FPassScore
     * @mbg.generated
     */
    private BigDecimal fpassscore;

    /**
     * 满分
     * 表字段 : teaching_diversity..t_course.FFullScore
     * @mbg.generated
     */
    private BigDecimal ffullscore;

    /**
     * 获取的能力ID，T_Ability
     * 表字段 : teaching_diversity..t_course.FAbilityID
     * @mbg.generated
     */
    private Long fabilityid;

    /**
     * 获取的能力等级ID，T_Ability_Level
     * 表字段 : teaching_diversity..t_course.FAbilityLevelID
     * @mbg.generated
     */
    private Long fabilitylevelid;

    /**
     * 学时
     * 表字段 : teaching_diversity..t_course.FTotalHours
     * @mbg.generated
     */
    private String ftotalhours;

    /**
     * 学分
     * 表字段 : teaching_diversity..t_course.FCredits
     * @mbg.generated
     */
    private Float fcredits;

    /**
     * 适用专业
     * 表字段 : teaching_diversity..t_course.FMajor
     * @mbg.generated
     */
    private String fmajor;

    /**
     * 先修课程
     * 表字段 : teaching_diversity..t_course.FPrerequisites
     * @mbg.generated
     */
    private String fprerequisites;

    /**
     * 教学基本要求
     * 表字段 : teaching_diversity..t_course.FTeachingRequirements
     * @mbg.generated
     */
    private String fteachingrequirements;

    /**
     * 课程类别
     * 表字段 : teaching_diversity..t_course.FType
     * @mbg.generated
     */
    private Long ftype;

    /**
     * 课程性质
     * 表字段 : teaching_diversity..t_course.FCNature
     * @mbg.generated
     */
    private Long fcnature;

    /**
     * 周学时
     * 表字段 : teaching_diversity..t_course.FWeeklyStudyHours
     * @mbg.generated
     */
    private String fweeklystudyhours;

    /**
     * 理论学时
     * 表字段 : teaching_diversity..t_course.FTheoreticalStudyHours
     * @mbg.generated
     */
    private String ftheoreticalstudyhours;

    /**
     * 实践学时
     * 表字段 : teaching_diversity..t_course.FPracticalStudyHours
     * @mbg.generated
     */
    private String fpracticalstudyhours;

    /**
     * 开课秋季 0 -无 1-有
     * 表字段 : teaching_diversity..t_course.FKKQJ
     * @mbg.generated
     */
    private Integer fkkqj;

    /**
     * 开课春季0 -无 1-有
     * 表字段 : teaching_diversity..t_course.FKKCJ
     * @mbg.generated
     */
    private Integer fkkcj;

    /**
     * 短学期 0 -无 1-有
     * 表字段 : teaching_diversity..t_course.FDXQ
     * @mbg.generated
     */
    private Integer fdxq;

    /**
     * 建议学年学期
     * 表字段 : teaching_diversity..t_course.FJYXNXQ
     * @mbg.generated
     */
    private String fjyxnxq;

    /**
     * 课程英文名称
     * 表字段 : teaching_diversity..t_course.FYWName
     * @mbg.generated
     */
    private String fywname;

    /**
     * 版本号 默认1
     * 表字段 : teaching_diversity..t_course.FEditionNo
     * @mbg.generated
     */
    private Integer feditionno;

    /**
     * 版本,V1(V+FEditionNo) 默认V1
     * 表字段 : teaching_diversity..t_course.FEdition
     * @mbg.generated
     */
    private String fedition;

    /**
     * 有效版本,0-无效,1-有（只一个）默认1
     * 表字段 : teaching_diversity..t_course.FValid
     * @mbg.generated
     */
    private Integer fvalid;

    /**
     * 实践周数
     * 表字段 : teaching_diversity..t_course.FSJZS
     * @mbg.generated
     */
    private String fsjzs;

    /**
     * 是否停课 1=否 =2是
     * 表字段 : teaching_diversity..t_course.FIstk
     * @mbg.generated
     */
    private Integer fistk;

    /**
     * 历史记录父ID
     * 表字段 : teaching_diversity..t_course.FParentId
     * @mbg.generated
     */
    private Long fparentid;

    /**
     * 建议学期
     * 表字段 : teaching_diversity..t_course.FJYXQ
     * @mbg.generated
     */
    private Integer fjyxq;

    /**
     * 1= 基础课  2= 专业课
     * 表字段 : teaching_diversity..t_course.FJCZYK
     * @mbg.generated
     */
    private Integer fjczyk;

    /**
     * 开课单位
     * 表字段 : teaching_diversity..t_course.FKKDW
     * @mbg.generated
     */
    private Long fkkdw;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public TCourse(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fname, String fno, String fcon, BigDecimal fpassscore, BigDecimal ffullscore, Long fabilityid, Long fabilitylevelid, String ftotalhours, Float fcredits, String fmajor, String fprerequisites, String fteachingrequirements, Long ftype, Long fcnature, String fweeklystudyhours, String ftheoreticalstudyhours, String fpracticalstudyhours, Integer fkkqj, Integer fkkcj, Integer fdxq, String fjyxnxq, String fywname, Integer feditionno, String fedition, Integer fvalid, String fsjzs, Integer fistk, Long fparentid, Integer fjyxq, Integer fjczyk, Long fkkdw) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fname = fname;
        this.fno = fno;
        this.fcon = fcon;
        this.fpassscore = fpassscore;
        this.ffullscore = ffullscore;
        this.fabilityid = fabilityid;
        this.fabilitylevelid = fabilitylevelid;
        this.ftotalhours = ftotalhours;
        this.fcredits = fcredits;
        this.fmajor = fmajor;
        this.fprerequisites = fprerequisites;
        this.fteachingrequirements = fteachingrequirements;
        this.ftype = ftype;
        this.fcnature = fcnature;
        this.fweeklystudyhours = fweeklystudyhours;
        this.ftheoreticalstudyhours = ftheoreticalstudyhours;
        this.fpracticalstudyhours = fpracticalstudyhours;
        this.fkkqj = fkkqj;
        this.fkkcj = fkkcj;
        this.fdxq = fdxq;
        this.fjyxnxq = fjyxnxq;
        this.fywname = fywname;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.fsjzs = fsjzs;
        this.fistk = fistk;
        this.fparentid = fparentid;
        this.fjyxq = fjyxq;
        this.fjczyk = fjczyk;
        this.fkkdw = fkkdw;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public TCourse() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_course.FKeyID
     * @return teaching_diversity..t_course.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course.FKeyID
     * @param fkeyid the value for teaching_diversity..t_course.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course.FCID
     * @return teaching_diversity..t_course.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course.FCID
     * @param fcid the value for teaching_diversity..t_course.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course.FUID
     * @return teaching_diversity..t_course.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course.FUID
     * @param fuid the value for teaching_diversity..t_course.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course.FCDATE
     * @return teaching_diversity..t_course.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course.FCDATE
     * @param fcdate the value for teaching_diversity..t_course.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_course.FUDATE
     * @return teaching_diversity..t_course.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course.FUDATE
     * @param fudate the value for teaching_diversity..t_course.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_course.FState
     * @return teaching_diversity..t_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_course.FState
     * @param fstate the value for teaching_diversity..t_course.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程名称 字段:teaching_diversity..t_course.FName
     * @return teaching_diversity..t_course.FName, 课程名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 课程名称 字段:teaching_diversity..t_course.FName
     * @param fname the value for teaching_diversity..t_course.FName, 课程名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 课程编号 字段:teaching_diversity..t_course.FNo
     * @return teaching_diversity..t_course.FNo, 课程编号
     * @mbg.generated
     */
    public String getFno() {
        return fno;
    }

    /**
     * 设置 课程编号 字段:teaching_diversity..t_course.FNo
     * @param fno the value for teaching_diversity..t_course.FNo, 课程编号
     * @mbg.generated
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * 获取 课程描述 字段:teaching_diversity..t_course.FCon
     * @return teaching_diversity..t_course.FCon, 课程描述
     * @mbg.generated
     */
    public String getFcon() {
        return fcon;
    }

    /**
     * 设置 课程描述 字段:teaching_diversity..t_course.FCon
     * @param fcon the value for teaching_diversity..t_course.FCon, 课程描述
     * @mbg.generated
     */
    public void setFcon(String fcon) {
        this.fcon = fcon == null ? null : fcon.trim();
    }

    /**
     * 获取 合格分数 字段:teaching_diversity..t_course.FPassScore
     * @return teaching_diversity..t_course.FPassScore, 合格分数
     * @mbg.generated
     */
    public BigDecimal getFpassscore() {
        return fpassscore;
    }

    /**
     * 设置 合格分数 字段:teaching_diversity..t_course.FPassScore
     * @param fpassscore the value for teaching_diversity..t_course.FPassScore, 合格分数
     * @mbg.generated
     */
    public void setFpassscore(BigDecimal fpassscore) {
        this.fpassscore = fpassscore;
    }

    /**
     * 获取 满分 字段:teaching_diversity..t_course.FFullScore
     * @return teaching_diversity..t_course.FFullScore, 满分
     * @mbg.generated
     */
    public BigDecimal getFfullscore() {
        return ffullscore;
    }

    /**
     * 设置 满分 字段:teaching_diversity..t_course.FFullScore
     * @param ffullscore the value for teaching_diversity..t_course.FFullScore, 满分
     * @mbg.generated
     */
    public void setFfullscore(BigDecimal ffullscore) {
        this.ffullscore = ffullscore;
    }

    /**
     * 获取 获取的能力ID，T_Ability 字段:teaching_diversity..t_course.FAbilityID
     * @return teaching_diversity..t_course.FAbilityID, 获取的能力ID，T_Ability
     * @mbg.generated
     */
    public Long getFabilityid() {
        return fabilityid;
    }

    /**
     * 设置 获取的能力ID，T_Ability 字段:teaching_diversity..t_course.FAbilityID
     * @param fabilityid the value for teaching_diversity..t_course.FAbilityID, 获取的能力ID，T_Ability
     * @mbg.generated
     */
    public void setFabilityid(Long fabilityid) {
        this.fabilityid = fabilityid;
    }

    /**
     * 获取 获取的能力等级ID，T_Ability_Level 字段:teaching_diversity..t_course.FAbilityLevelID
     * @return teaching_diversity..t_course.FAbilityLevelID, 获取的能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public Long getFabilitylevelid() {
        return fabilitylevelid;
    }

    /**
     * 设置 获取的能力等级ID，T_Ability_Level 字段:teaching_diversity..t_course.FAbilityLevelID
     * @param fabilitylevelid the value for teaching_diversity..t_course.FAbilityLevelID, 获取的能力等级ID，T_Ability_Level
     * @mbg.generated
     */
    public void setFabilitylevelid(Long fabilitylevelid) {
        this.fabilitylevelid = fabilitylevelid;
    }

    /**
     * 获取 学时 字段:teaching_diversity..t_course.FTotalHours
     * @return teaching_diversity..t_course.FTotalHours, 学时
     * @mbg.generated
     */
    public String getFtotalhours() {
        return ftotalhours;
    }

    /**
     * 设置 学时 字段:teaching_diversity..t_course.FTotalHours
     * @param ftotalhours the value for teaching_diversity..t_course.FTotalHours, 学时
     * @mbg.generated
     */
    public void setFtotalhours(String ftotalhours) {
        this.ftotalhours = ftotalhours == null ? null : ftotalhours.trim();
    }

    /**
     * 获取 学分 字段:teaching_diversity..t_course.FCredits
     * @return teaching_diversity..t_course.FCredits, 学分
     * @mbg.generated
     */
    public Float getFcredits() {
        return fcredits;
    }

    /**
     * 设置 学分 字段:teaching_diversity..t_course.FCredits
     * @param fcredits the value for teaching_diversity..t_course.FCredits, 学分
     * @mbg.generated
     */
    public void setFcredits(Float fcredits) {
        this.fcredits = fcredits;
    }

    /**
     * 获取 适用专业 字段:teaching_diversity..t_course.FMajor
     * @return teaching_diversity..t_course.FMajor, 适用专业
     * @mbg.generated
     */
    public String getFmajor() {
        return fmajor;
    }

    /**
     * 设置 适用专业 字段:teaching_diversity..t_course.FMajor
     * @param fmajor the value for teaching_diversity..t_course.FMajor, 适用专业
     * @mbg.generated
     */
    public void setFmajor(String fmajor) {
        this.fmajor = fmajor == null ? null : fmajor.trim();
    }

    /**
     * 获取 先修课程 字段:teaching_diversity..t_course.FPrerequisites
     * @return teaching_diversity..t_course.FPrerequisites, 先修课程
     * @mbg.generated
     */
    public String getFprerequisites() {
        return fprerequisites;
    }

    /**
     * 设置 先修课程 字段:teaching_diversity..t_course.FPrerequisites
     * @param fprerequisites the value for teaching_diversity..t_course.FPrerequisites, 先修课程
     * @mbg.generated
     */
    public void setFprerequisites(String fprerequisites) {
        this.fprerequisites = fprerequisites == null ? null : fprerequisites.trim();
    }

    /**
     * 获取 教学基本要求 字段:teaching_diversity..t_course.FTeachingRequirements
     * @return teaching_diversity..t_course.FTeachingRequirements, 教学基本要求
     * @mbg.generated
     */
    public String getFteachingrequirements() {
        return fteachingrequirements;
    }

    /**
     * 设置 教学基本要求 字段:teaching_diversity..t_course.FTeachingRequirements
     * @param fteachingrequirements the value for teaching_diversity..t_course.FTeachingRequirements, 教学基本要求
     * @mbg.generated
     */
    public void setFteachingrequirements(String fteachingrequirements) {
        this.fteachingrequirements = fteachingrequirements == null ? null : fteachingrequirements.trim();
    }

    /**
     * 获取 课程类别 字段:teaching_diversity..t_course.FType
     * @return teaching_diversity..t_course.FType, 课程类别
     * @mbg.generated
     */
    public Long getFtype() {
        return ftype;
    }

    /**
     * 设置 课程类别 字段:teaching_diversity..t_course.FType
     * @param ftype the value for teaching_diversity..t_course.FType, 课程类别
     * @mbg.generated
     */
    public void setFtype(Long ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 课程性质 字段:teaching_diversity..t_course.FCNature
     * @return teaching_diversity..t_course.FCNature, 课程性质
     * @mbg.generated
     */
    public Long getFcnature() {
        return fcnature;
    }

    /**
     * 设置 课程性质 字段:teaching_diversity..t_course.FCNature
     * @param fcnature the value for teaching_diversity..t_course.FCNature, 课程性质
     * @mbg.generated
     */
    public void setFcnature(Long fcnature) {
        this.fcnature = fcnature;
    }

    /**
     * 获取 周学时 字段:teaching_diversity..t_course.FWeeklyStudyHours
     * @return teaching_diversity..t_course.FWeeklyStudyHours, 周学时
     * @mbg.generated
     */
    public String getFweeklystudyhours() {
        return fweeklystudyhours;
    }

    /**
     * 设置 周学时 字段:teaching_diversity..t_course.FWeeklyStudyHours
     * @param fweeklystudyhours the value for teaching_diversity..t_course.FWeeklyStudyHours, 周学时
     * @mbg.generated
     */
    public void setFweeklystudyhours(String fweeklystudyhours) {
        this.fweeklystudyhours = fweeklystudyhours == null ? null : fweeklystudyhours.trim();
    }

    /**
     * 获取 理论学时 字段:teaching_diversity..t_course.FTheoreticalStudyHours
     * @return teaching_diversity..t_course.FTheoreticalStudyHours, 理论学时
     * @mbg.generated
     */
    public String getFtheoreticalstudyhours() {
        return ftheoreticalstudyhours;
    }

    /**
     * 设置 理论学时 字段:teaching_diversity..t_course.FTheoreticalStudyHours
     * @param ftheoreticalstudyhours the value for teaching_diversity..t_course.FTheoreticalStudyHours, 理论学时
     * @mbg.generated
     */
    public void setFtheoreticalstudyhours(String ftheoreticalstudyhours) {
        this.ftheoreticalstudyhours = ftheoreticalstudyhours == null ? null : ftheoreticalstudyhours.trim();
    }

    /**
     * 获取 实践学时 字段:teaching_diversity..t_course.FPracticalStudyHours
     * @return teaching_diversity..t_course.FPracticalStudyHours, 实践学时
     * @mbg.generated
     */
    public String getFpracticalstudyhours() {
        return fpracticalstudyhours;
    }

    /**
     * 设置 实践学时 字段:teaching_diversity..t_course.FPracticalStudyHours
     * @param fpracticalstudyhours the value for teaching_diversity..t_course.FPracticalStudyHours, 实践学时
     * @mbg.generated
     */
    public void setFpracticalstudyhours(String fpracticalstudyhours) {
        this.fpracticalstudyhours = fpracticalstudyhours == null ? null : fpracticalstudyhours.trim();
    }

    /**
     * 获取 开课秋季 0 -无 1-有 字段:teaching_diversity..t_course.FKKQJ
     * @return teaching_diversity..t_course.FKKQJ, 开课秋季 0 -无 1-有
     * @mbg.generated
     */
    public Integer getFkkqj() {
        return fkkqj;
    }

    /**
     * 设置 开课秋季 0 -无 1-有 字段:teaching_diversity..t_course.FKKQJ
     * @param fkkqj the value for teaching_diversity..t_course.FKKQJ, 开课秋季 0 -无 1-有
     * @mbg.generated
     */
    public void setFkkqj(Integer fkkqj) {
        this.fkkqj = fkkqj;
    }

    /**
     * 获取 开课春季0 -无 1-有 字段:teaching_diversity..t_course.FKKCJ
     * @return teaching_diversity..t_course.FKKCJ, 开课春季0 -无 1-有
     * @mbg.generated
     */
    public Integer getFkkcj() {
        return fkkcj;
    }

    /**
     * 设置 开课春季0 -无 1-有 字段:teaching_diversity..t_course.FKKCJ
     * @param fkkcj the value for teaching_diversity..t_course.FKKCJ, 开课春季0 -无 1-有
     * @mbg.generated
     */
    public void setFkkcj(Integer fkkcj) {
        this.fkkcj = fkkcj;
    }

    /**
     * 获取 短学期 0 -无 1-有 字段:teaching_diversity..t_course.FDXQ
     * @return teaching_diversity..t_course.FDXQ, 短学期 0 -无 1-有
     * @mbg.generated
     */
    public Integer getFdxq() {
        return fdxq;
    }

    /**
     * 设置 短学期 0 -无 1-有 字段:teaching_diversity..t_course.FDXQ
     * @param fdxq the value for teaching_diversity..t_course.FDXQ, 短学期 0 -无 1-有
     * @mbg.generated
     */
    public void setFdxq(Integer fdxq) {
        this.fdxq = fdxq;
    }

    /**
     * 获取 建议学年学期 字段:teaching_diversity..t_course.FJYXNXQ
     * @return teaching_diversity..t_course.FJYXNXQ, 建议学年学期
     * @mbg.generated
     */
    public String getFjyxnxq() {
        return fjyxnxq;
    }

    /**
     * 设置 建议学年学期 字段:teaching_diversity..t_course.FJYXNXQ
     * @param fjyxnxq the value for teaching_diversity..t_course.FJYXNXQ, 建议学年学期
     * @mbg.generated
     */
    public void setFjyxnxq(String fjyxnxq) {
        this.fjyxnxq = fjyxnxq == null ? null : fjyxnxq.trim();
    }

    /**
     * 获取 课程英文名称 字段:teaching_diversity..t_course.FYWName
     * @return teaching_diversity..t_course.FYWName, 课程英文名称
     * @mbg.generated
     */
    public String getFywname() {
        return fywname;
    }

    /**
     * 设置 课程英文名称 字段:teaching_diversity..t_course.FYWName
     * @param fywname the value for teaching_diversity..t_course.FYWName, 课程英文名称
     * @mbg.generated
     */
    public void setFywname(String fywname) {
        this.fywname = fywname == null ? null : fywname.trim();
    }

    /**
     * 获取 版本号 默认1 字段:teaching_diversity..t_course.FEditionNo
     * @return teaching_diversity..t_course.FEditionNo, 版本号 默认1
     * @mbg.generated
     */
    public Integer getFeditionno() {
        return feditionno;
    }

    /**
     * 设置 版本号 默认1 字段:teaching_diversity..t_course.FEditionNo
     * @param feditionno the value for teaching_diversity..t_course.FEditionNo, 版本号 默认1
     * @mbg.generated
     */
    public void setFeditionno(Integer feditionno) {
        this.feditionno = feditionno;
    }

    /**
     * 获取 版本,V1(V+FEditionNo) 默认V1 字段:teaching_diversity..t_course.FEdition
     * @return teaching_diversity..t_course.FEdition, 版本,V1(V+FEditionNo) 默认V1
     * @mbg.generated
     */
    public String getFedition() {
        return fedition;
    }

    /**
     * 设置 版本,V1(V+FEditionNo) 默认V1 字段:teaching_diversity..t_course.FEdition
     * @param fedition the value for teaching_diversity..t_course.FEdition, 版本,V1(V+FEditionNo) 默认V1
     * @mbg.generated
     */
    public void setFedition(String fedition) {
        this.fedition = fedition == null ? null : fedition.trim();
    }

    /**
     * 获取 有效版本,0-无效,1-有（只一个）默认1 字段:teaching_diversity..t_course.FValid
     * @return teaching_diversity..t_course.FValid, 有效版本,0-无效,1-有（只一个）默认1
     * @mbg.generated
     */
    public Integer getFvalid() {
        return fvalid;
    }

    /**
     * 设置 有效版本,0-无效,1-有（只一个）默认1 字段:teaching_diversity..t_course.FValid
     * @param fvalid the value for teaching_diversity..t_course.FValid, 有效版本,0-无效,1-有（只一个）默认1
     * @mbg.generated
     */
    public void setFvalid(Integer fvalid) {
        this.fvalid = fvalid;
    }

    /**
     * 获取 实践周数 字段:teaching_diversity..t_course.FSJZS
     * @return teaching_diversity..t_course.FSJZS, 实践周数
     * @mbg.generated
     */
    public String getFsjzs() {
        return fsjzs;
    }

    /**
     * 设置 实践周数 字段:teaching_diversity..t_course.FSJZS
     * @param fsjzs the value for teaching_diversity..t_course.FSJZS, 实践周数
     * @mbg.generated
     */
    public void setFsjzs(String fsjzs) {
        this.fsjzs = fsjzs == null ? null : fsjzs.trim();
    }

    /**
     * 获取 是否停课 1=否 =2是 字段:teaching_diversity..t_course.FIstk
     * @return teaching_diversity..t_course.FIstk, 是否停课 1=否 =2是
     * @mbg.generated
     */
    public Integer getFistk() {
        return fistk;
    }

    /**
     * 设置 是否停课 1=否 =2是 字段:teaching_diversity..t_course.FIstk
     * @param fistk the value for teaching_diversity..t_course.FIstk, 是否停课 1=否 =2是
     * @mbg.generated
     */
    public void setFistk(Integer fistk) {
        this.fistk = fistk;
    }

    /**
     * 获取 历史记录父ID 字段:teaching_diversity..t_course.FParentId
     * @return teaching_diversity..t_course.FParentId, 历史记录父ID
     * @mbg.generated
     */
    public Long getFparentid() {
        return fparentid;
    }

    /**
     * 设置 历史记录父ID 字段:teaching_diversity..t_course.FParentId
     * @param fparentid the value for teaching_diversity..t_course.FParentId, 历史记录父ID
     * @mbg.generated
     */
    public void setFparentid(Long fparentid) {
        this.fparentid = fparentid;
    }

    /**
     * 获取 建议学期 字段:teaching_diversity..t_course.FJYXQ
     * @return teaching_diversity..t_course.FJYXQ, 建议学期
     * @mbg.generated
     */
    public Integer getFjyxq() {
        return fjyxq;
    }

    /**
     * 设置 建议学期 字段:teaching_diversity..t_course.FJYXQ
     * @param fjyxq the value for teaching_diversity..t_course.FJYXQ, 建议学期
     * @mbg.generated
     */
    public void setFjyxq(Integer fjyxq) {
        this.fjyxq = fjyxq;
    }

    /**
     * 获取 1= 基础课  2= 专业课 字段:teaching_diversity..t_course.FJCZYK
     * @return teaching_diversity..t_course.FJCZYK, 1= 基础课  2= 专业课
     * @mbg.generated
     */
    public Integer getFjczyk() {
        return fjczyk;
    }

    /**
     * 设置 1= 基础课  2= 专业课 字段:teaching_diversity..t_course.FJCZYK
     * @param fjczyk the value for teaching_diversity..t_course.FJCZYK, 1= 基础课  2= 专业课
     * @mbg.generated
     */
    public void setFjczyk(Integer fjczyk) {
        this.fjczyk = fjczyk;
    }

    /**
     * 获取 开课单位 字段:teaching_diversity..t_course.FKKDW
     * @return teaching_diversity..t_course.FKKDW, 开课单位
     * @mbg.generated
     */
    public Long getFkkdw() {
        return fkkdw;
    }

    /**
     * 设置 开课单位 字段:teaching_diversity..t_course.FKKDW
     * @param fkkdw the value for teaching_diversity..t_course.FKKDW, 开课单位
     * @mbg.generated
     */
    public void setFkkdw(Long fkkdw) {
        this.fkkdw = fkkdw;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_course
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
        sb.append(", fno=").append(fno);
        sb.append(", fcon=").append(fcon);
        sb.append(", fpassscore=").append(fpassscore);
        sb.append(", ffullscore=").append(ffullscore);
        sb.append(", fabilityid=").append(fabilityid);
        sb.append(", fabilitylevelid=").append(fabilitylevelid);
        sb.append(", ftotalhours=").append(ftotalhours);
        sb.append(", fcredits=").append(fcredits);
        sb.append(", fmajor=").append(fmajor);
        sb.append(", fprerequisites=").append(fprerequisites);
        sb.append(", fteachingrequirements=").append(fteachingrequirements);
        sb.append(", ftype=").append(ftype);
        sb.append(", fcnature=").append(fcnature);
        sb.append(", fweeklystudyhours=").append(fweeklystudyhours);
        sb.append(", ftheoreticalstudyhours=").append(ftheoreticalstudyhours);
        sb.append(", fpracticalstudyhours=").append(fpracticalstudyhours);
        sb.append(", fkkqj=").append(fkkqj);
        sb.append(", fkkcj=").append(fkkcj);
        sb.append(", fdxq=").append(fdxq);
        sb.append(", fjyxnxq=").append(fjyxnxq);
        sb.append(", fywname=").append(fywname);
        sb.append(", feditionno=").append(feditionno);
        sb.append(", fedition=").append(fedition);
        sb.append(", fvalid=").append(fvalid);
        sb.append(", fsjzs=").append(fsjzs);
        sb.append(", fistk=").append(fistk);
        sb.append(", fparentid=").append(fparentid);
        sb.append(", fjyxq=").append(fjyxq);
        sb.append(", fjczyk=").append(fjczyk);
        sb.append(", fkkdw=").append(fkkdw);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_course
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
        TCourse other = (TCourse) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFno() == null ? other.getFno() == null : this.getFno().equals(other.getFno()))
            && (this.getFcon() == null ? other.getFcon() == null : this.getFcon().equals(other.getFcon()))
            && (this.getFpassscore() == null ? other.getFpassscore() == null : this.getFpassscore().equals(other.getFpassscore()))
            && (this.getFfullscore() == null ? other.getFfullscore() == null : this.getFfullscore().equals(other.getFfullscore()))
            && (this.getFabilityid() == null ? other.getFabilityid() == null : this.getFabilityid().equals(other.getFabilityid()))
            && (this.getFabilitylevelid() == null ? other.getFabilitylevelid() == null : this.getFabilitylevelid().equals(other.getFabilitylevelid()))
            && (this.getFtotalhours() == null ? other.getFtotalhours() == null : this.getFtotalhours().equals(other.getFtotalhours()))
            && (this.getFcredits() == null ? other.getFcredits() == null : this.getFcredits().equals(other.getFcredits()))
            && (this.getFmajor() == null ? other.getFmajor() == null : this.getFmajor().equals(other.getFmajor()))
            && (this.getFprerequisites() == null ? other.getFprerequisites() == null : this.getFprerequisites().equals(other.getFprerequisites()))
            && (this.getFteachingrequirements() == null ? other.getFteachingrequirements() == null : this.getFteachingrequirements().equals(other.getFteachingrequirements()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFcnature() == null ? other.getFcnature() == null : this.getFcnature().equals(other.getFcnature()))
            && (this.getFweeklystudyhours() == null ? other.getFweeklystudyhours() == null : this.getFweeklystudyhours().equals(other.getFweeklystudyhours()))
            && (this.getFtheoreticalstudyhours() == null ? other.getFtheoreticalstudyhours() == null : this.getFtheoreticalstudyhours().equals(other.getFtheoreticalstudyhours()))
            && (this.getFpracticalstudyhours() == null ? other.getFpracticalstudyhours() == null : this.getFpracticalstudyhours().equals(other.getFpracticalstudyhours()))
            && (this.getFkkqj() == null ? other.getFkkqj() == null : this.getFkkqj().equals(other.getFkkqj()))
            && (this.getFkkcj() == null ? other.getFkkcj() == null : this.getFkkcj().equals(other.getFkkcj()))
            && (this.getFdxq() == null ? other.getFdxq() == null : this.getFdxq().equals(other.getFdxq()))
            && (this.getFjyxnxq() == null ? other.getFjyxnxq() == null : this.getFjyxnxq().equals(other.getFjyxnxq()))
            && (this.getFywname() == null ? other.getFywname() == null : this.getFywname().equals(other.getFywname()))
            && (this.getFeditionno() == null ? other.getFeditionno() == null : this.getFeditionno().equals(other.getFeditionno()))
            && (this.getFedition() == null ? other.getFedition() == null : this.getFedition().equals(other.getFedition()))
            && (this.getFvalid() == null ? other.getFvalid() == null : this.getFvalid().equals(other.getFvalid()))
            && (this.getFsjzs() == null ? other.getFsjzs() == null : this.getFsjzs().equals(other.getFsjzs()))
            && (this.getFistk() == null ? other.getFistk() == null : this.getFistk().equals(other.getFistk()))
            && (this.getFparentid() == null ? other.getFparentid() == null : this.getFparentid().equals(other.getFparentid()))
            && (this.getFjyxq() == null ? other.getFjyxq() == null : this.getFjyxq().equals(other.getFjyxq()))
            && (this.getFjczyk() == null ? other.getFjczyk() == null : this.getFjczyk().equals(other.getFjczyk()))
            && (this.getFkkdw() == null ? other.getFkkdw() == null : this.getFkkdw().equals(other.getFkkdw()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_course
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
        result = prime * result + ((getFno() == null) ? 0 : getFno().hashCode());
        result = prime * result + ((getFcon() == null) ? 0 : getFcon().hashCode());
        result = prime * result + ((getFpassscore() == null) ? 0 : getFpassscore().hashCode());
        result = prime * result + ((getFfullscore() == null) ? 0 : getFfullscore().hashCode());
        result = prime * result + ((getFabilityid() == null) ? 0 : getFabilityid().hashCode());
        result = prime * result + ((getFabilitylevelid() == null) ? 0 : getFabilitylevelid().hashCode());
        result = prime * result + ((getFtotalhours() == null) ? 0 : getFtotalhours().hashCode());
        result = prime * result + ((getFcredits() == null) ? 0 : getFcredits().hashCode());
        result = prime * result + ((getFmajor() == null) ? 0 : getFmajor().hashCode());
        result = prime * result + ((getFprerequisites() == null) ? 0 : getFprerequisites().hashCode());
        result = prime * result + ((getFteachingrequirements() == null) ? 0 : getFteachingrequirements().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFcnature() == null) ? 0 : getFcnature().hashCode());
        result = prime * result + ((getFweeklystudyhours() == null) ? 0 : getFweeklystudyhours().hashCode());
        result = prime * result + ((getFtheoreticalstudyhours() == null) ? 0 : getFtheoreticalstudyhours().hashCode());
        result = prime * result + ((getFpracticalstudyhours() == null) ? 0 : getFpracticalstudyhours().hashCode());
        result = prime * result + ((getFkkqj() == null) ? 0 : getFkkqj().hashCode());
        result = prime * result + ((getFkkcj() == null) ? 0 : getFkkcj().hashCode());
        result = prime * result + ((getFdxq() == null) ? 0 : getFdxq().hashCode());
        result = prime * result + ((getFjyxnxq() == null) ? 0 : getFjyxnxq().hashCode());
        result = prime * result + ((getFywname() == null) ? 0 : getFywname().hashCode());
        result = prime * result + ((getFeditionno() == null) ? 0 : getFeditionno().hashCode());
        result = prime * result + ((getFedition() == null) ? 0 : getFedition().hashCode());
        result = prime * result + ((getFvalid() == null) ? 0 : getFvalid().hashCode());
        result = prime * result + ((getFsjzs() == null) ? 0 : getFsjzs().hashCode());
        result = prime * result + ((getFistk() == null) ? 0 : getFistk().hashCode());
        result = prime * result + ((getFparentid() == null) ? 0 : getFparentid().hashCode());
        result = prime * result + ((getFjyxq() == null) ? 0 : getFjyxq().hashCode());
        result = prime * result + ((getFjczyk() == null) ? 0 : getFjczyk().hashCode());
        result = prime * result + ((getFkkdw() == null) ? 0 : getFkkdw().hashCode());
        return result;
    }
}