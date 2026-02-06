package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_interface
 * 表注释：数据接口表
*/
@Table(name = "t_course_interface")
public class TCourseInterface implements Serializable {
    /**
     * 系统唯一ID，系统生成
     * 表字段 : student_capability_evaluation..t_course_interface.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 创建者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_course_interface.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 最后修改者，来源T_User主键
     * 表字段 : student_capability_evaluation..t_course_interface.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 创建时间
     * 表字段 : student_capability_evaluation..t_course_interface.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 最后修改时间
     * 表字段 : student_capability_evaluation..t_course_interface.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态
     * 表字段 : student_capability_evaluation..t_course_interface.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * interface表FKeyID
     * 表字段 : student_capability_evaluation..t_course_interface.FIFID
     * @mbg.generated
     */
    private Long fifid;

    /**
     * t_course_enrollment表FKeyID
     * 表字段 : student_capability_evaluation..t_course_interface.FCEID
     * @mbg.generated
     */
    private Long fceid;

    /**
     * 课程ID
     * 表字段 : student_capability_evaluation..t_course_interface.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 学生ID
     * 表字段 : student_capability_evaluation..t_course_interface.FStudentID
     * @mbg.generated
     */
    private Long fstudentid;

    /**
     * 数据接口名称
     * 表字段 : student_capability_evaluation..t_course_interface.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 数据接口类型 T_Interface_Type
     * 表字段 : student_capability_evaluation..t_course_interface.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 接口调用地址
     * 表字段 : student_capability_evaluation..t_course_interface.FMaturity
     * @mbg.generated
     */
    private Float fmaturity;

    /**
     * 调用大模型返回的查询ID
     * 表字段 : student_capability_evaluation..t_course_interface.FInterfacellmID
     * @mbg.generated
     */
    private String finterfacellmid;

    /**
     * 调用大模型状态 0-未匹配 1-已匹配 2=赋值完成
     * 表字段 : student_capability_evaluation..t_course_interface.FInterfacellmState
     * @mbg.generated
     */
    private Integer finterfacellmstate;

    /**
     * 学生评价内容
     * 表字段 : student_capability_evaluation..t_course_interface.FPJContent
     * @mbg.generated
     */
    private String fpjcontent;

    /**
     * 输入参数内容
     * 表字段 : student_capability_evaluation..t_course_interface.FInputParameters
     * @mbg.generated
     */
    private String finputparameters;

    /**
     * 返回参数内容
     * 表字段 : student_capability_evaluation..t_course_interface.FReturnParameters
     * @mbg.generated
     */
    private String freturnparameters;

    /**
     * 要求
     * 表字段 : student_capability_evaluation..t_course_interface.FRequire
     * @mbg.generated
     */
    private String frequire;

    /**
     * 大模型返回值
     * 表字段 : student_capability_evaluation..t_course_interface.FInterfacellm_Return
     * @mbg.generated
     */
    private String finterfacellmReturn;

    /**
     * 拼接后的参数内容
     * 表字段 : student_capability_evaluation..t_course_interface.FLJParameters
     * @mbg.generated
     */
    private String fljparameters;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public TCourseInterface(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fifid, Long fceid, Long fcourseid, Long fstudentid, String fname, Long ftypeid, Float fmaturity, String finterfacellmid, Integer finterfacellmstate, String fpjcontent) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fifid = fifid;
        this.fceid = fceid;
        this.fcourseid = fcourseid;
        this.fstudentid = fstudentid;
        this.fname = fname;
        this.ftypeid = ftypeid;
        this.fmaturity = fmaturity;
        this.finterfacellmid = finterfacellmid;
        this.finterfacellmstate = finterfacellmstate;
        this.fpjcontent = fpjcontent;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public TCourseInterface(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fifid, Long fceid, Long fcourseid, Long fstudentid, String fname, Long ftypeid, Float fmaturity, String finterfacellmid, Integer finterfacellmstate, String fpjcontent, String finputparameters, String freturnparameters, String frequire, String finterfacellmReturn, String fljparameters) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fifid = fifid;
        this.fceid = fceid;
        this.fcourseid = fcourseid;
        this.fstudentid = fstudentid;
        this.fname = fname;
        this.ftypeid = ftypeid;
        this.fmaturity = fmaturity;
        this.finterfacellmid = finterfacellmid;
        this.finterfacellmstate = finterfacellmstate;
        this.fpjcontent = fpjcontent;
        this.finputparameters = finputparameters;
        this.freturnparameters = freturnparameters;
        this.frequire = frequire;
        this.finterfacellmReturn = finterfacellmReturn;
        this.fljparameters = fljparameters;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public TCourseInterface() {
        super();
    }

    /**
     * 获取 系统唯一ID，系统生成 字段:student_capability_evaluation..t_course_interface.FKeyID
     * @return student_capability_evaluation..t_course_interface.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置 系统唯一ID，系统生成 字段:student_capability_evaluation..t_course_interface.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_course_interface.FKeyID, 系统唯一ID，系统生成
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取 创建者，来源T_User主键 字段:student_capability_evaluation..t_course_interface.FCID
     * @return student_capability_evaluation..t_course_interface.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置 创建者，来源T_User主键 字段:student_capability_evaluation..t_course_interface.FCID
     * @param fcid the value for student_capability_evaluation..t_course_interface.FCID, 创建者，来源T_User主键
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_course_interface.FUID
     * @return student_capability_evaluation..t_course_interface.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置 最后修改者，来源T_User主键 字段:student_capability_evaluation..t_course_interface.FUID
     * @param fuid the value for student_capability_evaluation..t_course_interface.FUID, 最后修改者，来源T_User主键
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取 创建时间 字段:student_capability_evaluation..t_course_interface.FCDATE
     * @return student_capability_evaluation..t_course_interface.FCDATE, 创建时间
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置 创建时间 字段:student_capability_evaluation..t_course_interface.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_course_interface.FCDATE, 创建时间
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取 最后修改时间 字段:student_capability_evaluation..t_course_interface.FUDATE
     * @return student_capability_evaluation..t_course_interface.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置 最后修改时间 字段:student_capability_evaluation..t_course_interface.FUDATE
     * @param fudate the value for student_capability_evaluation..t_course_interface.FUDATE, 最后修改时间
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态 字段:student_capability_evaluation..t_course_interface.FState
     * @return student_capability_evaluation..t_course_interface.FState, 状态
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态 字段:student_capability_evaluation..t_course_interface.FState
     * @param fstate the value for student_capability_evaluation..t_course_interface.FState, 状态
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 interface表FKeyID 字段:student_capability_evaluation..t_course_interface.FIFID
     * @return student_capability_evaluation..t_course_interface.FIFID, interface表FKeyID
     * @mbg.generated
     */
    public Long getFifid() {
        return fifid;
    }

    /**
     * 设置 interface表FKeyID 字段:student_capability_evaluation..t_course_interface.FIFID
     * @param fifid the value for student_capability_evaluation..t_course_interface.FIFID, interface表FKeyID
     * @mbg.generated
     */
    public void setFifid(Long fifid) {
        this.fifid = fifid;
    }

    /**
     * 获取 t_course_enrollment表FKeyID 字段:student_capability_evaluation..t_course_interface.FCEID
     * @return student_capability_evaluation..t_course_interface.FCEID, t_course_enrollment表FKeyID
     * @mbg.generated
     */
    public Long getFceid() {
        return fceid;
    }

    /**
     * 设置 t_course_enrollment表FKeyID 字段:student_capability_evaluation..t_course_interface.FCEID
     * @param fceid the value for student_capability_evaluation..t_course_interface.FCEID, t_course_enrollment表FKeyID
     * @mbg.generated
     */
    public void setFceid(Long fceid) {
        this.fceid = fceid;
    }

    /**
     * 获取 课程ID 字段:student_capability_evaluation..t_course_interface.FCourseID
     * @return student_capability_evaluation..t_course_interface.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:student_capability_evaluation..t_course_interface.FCourseID
     * @param fcourseid the value for student_capability_evaluation..t_course_interface.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 学生ID 字段:student_capability_evaluation..t_course_interface.FStudentID
     * @return student_capability_evaluation..t_course_interface.FStudentID, 学生ID
     * @mbg.generated
     */
    public Long getFstudentid() {
        return fstudentid;
    }

    /**
     * 设置 学生ID 字段:student_capability_evaluation..t_course_interface.FStudentID
     * @param fstudentid the value for student_capability_evaluation..t_course_interface.FStudentID, 学生ID
     * @mbg.generated
     */
    public void setFstudentid(Long fstudentid) {
        this.fstudentid = fstudentid;
    }

    /**
     * 获取 数据接口名称 字段:student_capability_evaluation..t_course_interface.FName
     * @return student_capability_evaluation..t_course_interface.FName, 数据接口名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 数据接口名称 字段:student_capability_evaluation..t_course_interface.FName
     * @param fname the value for student_capability_evaluation..t_course_interface.FName, 数据接口名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 数据接口类型 T_Interface_Type 字段:student_capability_evaluation..t_course_interface.FTypeID
     * @return student_capability_evaluation..t_course_interface.FTypeID, 数据接口类型 T_Interface_Type
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 数据接口类型 T_Interface_Type 字段:student_capability_evaluation..t_course_interface.FTypeID
     * @param ftypeid the value for student_capability_evaluation..t_course_interface.FTypeID, 数据接口类型 T_Interface_Type
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 接口调用地址 字段:student_capability_evaluation..t_course_interface.FMaturity
     * @return student_capability_evaluation..t_course_interface.FMaturity, 接口调用地址
     * @mbg.generated
     */
    public Float getFmaturity() {
        return fmaturity;
    }

    /**
     * 设置 接口调用地址 字段:student_capability_evaluation..t_course_interface.FMaturity
     * @param fmaturity the value for student_capability_evaluation..t_course_interface.FMaturity, 接口调用地址
     * @mbg.generated
     */
    public void setFmaturity(Float fmaturity) {
        this.fmaturity = fmaturity;
    }

    /**
     * 获取 调用大模型返回的查询ID 字段:student_capability_evaluation..t_course_interface.FInterfacellmID
     * @return student_capability_evaluation..t_course_interface.FInterfacellmID, 调用大模型返回的查询ID
     * @mbg.generated
     */
    public String getFinterfacellmid() {
        return finterfacellmid;
    }

    /**
     * 设置 调用大模型返回的查询ID 字段:student_capability_evaluation..t_course_interface.FInterfacellmID
     * @param finterfacellmid the value for student_capability_evaluation..t_course_interface.FInterfacellmID, 调用大模型返回的查询ID
     * @mbg.generated
     */
    public void setFinterfacellmid(String finterfacellmid) {
        this.finterfacellmid = finterfacellmid == null ? null : finterfacellmid.trim();
    }

    /**
     * 获取 调用大模型状态 0-未匹配 1-已匹配 2=赋值完成 字段:student_capability_evaluation..t_course_interface.FInterfacellmState
     * @return student_capability_evaluation..t_course_interface.FInterfacellmState, 调用大模型状态 0-未匹配 1-已匹配 2=赋值完成
     * @mbg.generated
     */
    public Integer getFinterfacellmstate() {
        return finterfacellmstate;
    }

    /**
     * 设置 调用大模型状态 0-未匹配 1-已匹配 2=赋值完成 字段:student_capability_evaluation..t_course_interface.FInterfacellmState
     * @param finterfacellmstate the value for student_capability_evaluation..t_course_interface.FInterfacellmState, 调用大模型状态 0-未匹配 1-已匹配 2=赋值完成
     * @mbg.generated
     */
    public void setFinterfacellmstate(Integer finterfacellmstate) {
        this.finterfacellmstate = finterfacellmstate;
    }

    /**
     * 获取 学生评价内容 字段:student_capability_evaluation..t_course_interface.FPJContent
     * @return student_capability_evaluation..t_course_interface.FPJContent, 学生评价内容
     * @mbg.generated
     */
    public String getFpjcontent() {
        return fpjcontent;
    }

    /**
     * 设置 学生评价内容 字段:student_capability_evaluation..t_course_interface.FPJContent
     * @param fpjcontent the value for student_capability_evaluation..t_course_interface.FPJContent, 学生评价内容
     * @mbg.generated
     */
    public void setFpjcontent(String fpjcontent) {
        this.fpjcontent = fpjcontent == null ? null : fpjcontent.trim();
    }

    /**
     * 获取 输入参数内容 字段:student_capability_evaluation..t_course_interface.FInputParameters
     * @return student_capability_evaluation..t_course_interface.FInputParameters, 输入参数内容
     * @mbg.generated
     */
    public String getFinputparameters() {
        return finputparameters;
    }

    /**
     * 设置 输入参数内容 字段:student_capability_evaluation..t_course_interface.FInputParameters
     * @param finputparameters the value for student_capability_evaluation..t_course_interface.FInputParameters, 输入参数内容
     * @mbg.generated
     */
    public void setFinputparameters(String finputparameters) {
        this.finputparameters = finputparameters == null ? null : finputparameters.trim();
    }

    /**
     * 获取 返回参数内容 字段:student_capability_evaluation..t_course_interface.FReturnParameters
     * @return student_capability_evaluation..t_course_interface.FReturnParameters, 返回参数内容
     * @mbg.generated
     */
    public String getFreturnparameters() {
        return freturnparameters;
    }

    /**
     * 设置 返回参数内容 字段:student_capability_evaluation..t_course_interface.FReturnParameters
     * @param freturnparameters the value for student_capability_evaluation..t_course_interface.FReturnParameters, 返回参数内容
     * @mbg.generated
     */
    public void setFreturnparameters(String freturnparameters) {
        this.freturnparameters = freturnparameters == null ? null : freturnparameters.trim();
    }

    /**
     * 获取 要求 字段:student_capability_evaluation..t_course_interface.FRequire
     * @return student_capability_evaluation..t_course_interface.FRequire, 要求
     * @mbg.generated
     */
    public String getFrequire() {
        return frequire;
    }

    /**
     * 设置 要求 字段:student_capability_evaluation..t_course_interface.FRequire
     * @param frequire the value for student_capability_evaluation..t_course_interface.FRequire, 要求
     * @mbg.generated
     */
    public void setFrequire(String frequire) {
        this.frequire = frequire == null ? null : frequire.trim();
    }

    /**
     * 获取 大模型返回值 字段:student_capability_evaluation..t_course_interface.FInterfacellm_Return
     * @return student_capability_evaluation..t_course_interface.FInterfacellm_Return, 大模型返回值
     * @mbg.generated
     */
    public String getFinterfacellmReturn() {
        return finterfacellmReturn;
    }

    /**
     * 设置 大模型返回值 字段:student_capability_evaluation..t_course_interface.FInterfacellm_Return
     * @param finterfacellmReturn the value for student_capability_evaluation..t_course_interface.FInterfacellm_Return, 大模型返回值
     * @mbg.generated
     */
    public void setFinterfacellmReturn(String finterfacellmReturn) {
        this.finterfacellmReturn = finterfacellmReturn == null ? null : finterfacellmReturn.trim();
    }

    /**
     * 获取 拼接后的参数内容 字段:student_capability_evaluation..t_course_interface.FLJParameters
     * @return student_capability_evaluation..t_course_interface.FLJParameters, 拼接后的参数内容
     * @mbg.generated
     */
    public String getFljparameters() {
        return fljparameters;
    }

    /**
     * 设置 拼接后的参数内容 字段:student_capability_evaluation..t_course_interface.FLJParameters
     * @param fljparameters the value for student_capability_evaluation..t_course_interface.FLJParameters, 拼接后的参数内容
     * @mbg.generated
     */
    public void setFljparameters(String fljparameters) {
        this.fljparameters = fljparameters == null ? null : fljparameters.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_course_interface
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
        sb.append(", fifid=").append(fifid);
        sb.append(", fceid=").append(fceid);
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", fstudentid=").append(fstudentid);
        sb.append(", fname=").append(fname);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", fmaturity=").append(fmaturity);
        sb.append(", finterfacellmid=").append(finterfacellmid);
        sb.append(", finterfacellmstate=").append(finterfacellmstate);
        sb.append(", fpjcontent=").append(fpjcontent);
        sb.append(", finputparameters=").append(finputparameters);
        sb.append(", freturnparameters=").append(freturnparameters);
        sb.append(", frequire=").append(frequire);
        sb.append(", finterfacellmReturn=").append(finterfacellmReturn);
        sb.append(", fljparameters=").append(fljparameters);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_course_interface
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
        TCourseInterface other = (TCourseInterface) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFifid() == null ? other.getFifid() == null : this.getFifid().equals(other.getFifid()))
            && (this.getFceid() == null ? other.getFceid() == null : this.getFceid().equals(other.getFceid()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFstudentid() == null ? other.getFstudentid() == null : this.getFstudentid().equals(other.getFstudentid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFmaturity() == null ? other.getFmaturity() == null : this.getFmaturity().equals(other.getFmaturity()))
            && (this.getFinterfacellmid() == null ? other.getFinterfacellmid() == null : this.getFinterfacellmid().equals(other.getFinterfacellmid()))
            && (this.getFinterfacellmstate() == null ? other.getFinterfacellmstate() == null : this.getFinterfacellmstate().equals(other.getFinterfacellmstate()))
            && (this.getFpjcontent() == null ? other.getFpjcontent() == null : this.getFpjcontent().equals(other.getFpjcontent()))
            && (this.getFinputparameters() == null ? other.getFinputparameters() == null : this.getFinputparameters().equals(other.getFinputparameters()))
            && (this.getFreturnparameters() == null ? other.getFreturnparameters() == null : this.getFreturnparameters().equals(other.getFreturnparameters()))
            && (this.getFrequire() == null ? other.getFrequire() == null : this.getFrequire().equals(other.getFrequire()))
            && (this.getFinterfacellmReturn() == null ? other.getFinterfacellmReturn() == null : this.getFinterfacellmReturn().equals(other.getFinterfacellmReturn()))
            && (this.getFljparameters() == null ? other.getFljparameters() == null : this.getFljparameters().equals(other.getFljparameters()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_course_interface
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
        result = prime * result + ((getFifid() == null) ? 0 : getFifid().hashCode());
        result = prime * result + ((getFceid() == null) ? 0 : getFceid().hashCode());
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFstudentid() == null) ? 0 : getFstudentid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFmaturity() == null) ? 0 : getFmaturity().hashCode());
        result = prime * result + ((getFinterfacellmid() == null) ? 0 : getFinterfacellmid().hashCode());
        result = prime * result + ((getFinterfacellmstate() == null) ? 0 : getFinterfacellmstate().hashCode());
        result = prime * result + ((getFpjcontent() == null) ? 0 : getFpjcontent().hashCode());
        result = prime * result + ((getFinputparameters() == null) ? 0 : getFinputparameters().hashCode());
        result = prime * result + ((getFreturnparameters() == null) ? 0 : getFreturnparameters().hashCode());
        result = prime * result + ((getFrequire() == null) ? 0 : getFrequire().hashCode());
        result = prime * result + ((getFinterfacellmReturn() == null) ? 0 : getFinterfacellmReturn().hashCode());
        result = prime * result + ((getFljparameters() == null) ? 0 : getFljparameters().hashCode());
        return result;
    }
}