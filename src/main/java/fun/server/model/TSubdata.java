package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_subdata")
public class TSubdata implements Serializable {
    /**
     * 
     * 表字段 : t_subdata.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_subdata.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_subdata.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_subdata.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_subdata.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : t_subdata.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 类型,1项计,2项流,3任,4任流,5文件审核，6异常审核7，采购审核
     * 表字段 : t_subdata.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 项目ID,T_Project主键
     * 表字段 : t_subdata.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 项目计划ID,T_Project_Plan主键
     * 表字段 : t_subdata.FProjectPlanID
     * @mbg.generated
     */
    private Long fprojectplanid;

    /**
     * 任务ID,T_Task主键
     * 表字段 : t_subdata.FTaskID
     * @mbg.generated
     */
    private Long ftaskid;

    /**
     * 工作流ID
     * 表字段 : t_subdata.FWorkFlowID
     * @mbg.generated
     */
    private Long fworkflowid;

    /**
     * 工作流节点ID
     * 表字段 : t_subdata.FNodeID
     * @mbg.generated
     */
    private Long fnodeid;

    /**
     * 需要的交付物
     * 表字段 : t_subdata.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 工具,UG等
     * 表字段 : t_subdata.FTool
     * @mbg.generated
     */
    private String ftool;

    /**
     * 交付物类型,T_SubData_Type主键
     * 表字段 : t_subdata.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 交付物文件类型,后缀名
     * 表字段 : t_subdata.FFileType
     * @mbg.generated
     */
    private String ffiletype;

    /**
     * 审核流程
     * 表字段 : t_subdata.FCheckFlowID
     * @mbg.generated
     */
    private Long fcheckflowid;

    /**
     * 备注
     * 表字段 : t_subdata.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 存放地址
     * 表字段 : t_subdata.FUrl
     * @mbg.generated
     */
    private String furl;

    /**
     * 系统存放名
     * 表字段 : t_subdata.FSysName
     * @mbg.generated
     */
    private String fsysname;

    /**
     * 文件名
     * 表字段 : t_subdata.FFileName
     * @mbg.generated
     */
    private String ffilename;

    /**
     * 提交人,T_UserID主键
     * 表字段 : t_subdata.FSubUserID
     * @mbg.generated
     */
    private Long fsubuserid;

    /**
     * 提交时间
     * 表字段 : t_subdata.FSubDate
     * @mbg.generated
     */
    private Date fsubdate;

    /**
     * 提交方式,1上传,2-流程转,3任务转
     * 表字段 : t_subdata.FSubMode
     * @mbg.generated
     */
    private Integer fsubmode;

    /**
     * 审核状态,0未审,1审核中,2通过,3不通过
     * 表字段 : t_subdata.FCheckState
     * @mbg.generated
     */
    private Integer fcheckstate;

    /**
     * 原提交物ID
     * 表字段 : t_subdata.FSubDataID
     * @mbg.generated
     */
    private Long fsubdataid;

    /**
     * 第几次提交
     * 表字段 : t_subdata.FSubDataNum
     * @mbg.generated
     */
    private Integer fsubdatanum;

    /**
     * 需要或其他
     * 表字段 : t_subdata.FHaveType
     * @mbg.generated
     */
    private Integer fhavetype;

    /**
     * 是否原始版本,0-是，1-升级版本
     * 表字段 : t_subdata.FISInit
     * @mbg.generated
     */
    private Integer fisinit;

    /**
     * 原始版本ID,T_SubData主键
     * 表字段 : t_subdata.FInitFileID
     * @mbg.generated
     */
    private Long finitfileid;

    /**
     * 版本号
     * 表字段 : t_subdata.FEditionNo
     * @mbg.generated
     */
    private Integer feditionno;

    /**
     * 版本,V1(V+FEditionNo)
     * 表字段 : t_subdata.FEdition
     * @mbg.generated
     */
    private String fedition;

    /**
     * 有效版本,0-无效,1-有（只一个）
     * 表字段 : t_subdata.FValid
     * @mbg.generated
     */
    private Integer fvalid;

    /**
     * 流程转任务交付物，交付物ID
     * 表字段 : t_subdata.FZSubDataID
     * @mbg.generated
     */
    private Long fzsubdataid;

    /**
     * 
     * 表字段 : t_subdata.FXml
     * @mbg.generated
     */
    private String fxml;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_subdata
     * @mbg.generated
     */
    public TSubdata(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer ftype, Long fprojectid, Long fprojectplanid, Long ftaskid, Long fworkflowid, Long fnodeid, String fname, String ftool, Long ftypeid, String ffiletype, Long fcheckflowid, String fnote, String furl, String fsysname, String ffilename, Long fsubuserid, Date fsubdate, Integer fsubmode, Integer fcheckstate, Long fsubdataid, Integer fsubdatanum, Integer fhavetype, Integer fisinit, Long finitfileid, Integer feditionno, String fedition, Integer fvalid, Long fzsubdataid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftype = ftype;
        this.fprojectid = fprojectid;
        this.fprojectplanid = fprojectplanid;
        this.ftaskid = ftaskid;
        this.fworkflowid = fworkflowid;
        this.fnodeid = fnodeid;
        this.fname = fname;
        this.ftool = ftool;
        this.ftypeid = ftypeid;
        this.ffiletype = ffiletype;
        this.fcheckflowid = fcheckflowid;
        this.fnote = fnote;
        this.furl = furl;
        this.fsysname = fsysname;
        this.ffilename = ffilename;
        this.fsubuserid = fsubuserid;
        this.fsubdate = fsubdate;
        this.fsubmode = fsubmode;
        this.fcheckstate = fcheckstate;
        this.fsubdataid = fsubdataid;
        this.fsubdatanum = fsubdatanum;
        this.fhavetype = fhavetype;
        this.fisinit = fisinit;
        this.finitfileid = finitfileid;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.fzsubdataid = fzsubdataid;
    }

    /**
     * 构造查询条件
     * t_subdata
     * @mbg.generated
     */
    public TSubdata(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer ftype, Long fprojectid, Long fprojectplanid, Long ftaskid, Long fworkflowid, Long fnodeid, String fname, String ftool, Long ftypeid, String ffiletype, Long fcheckflowid, String fnote, String furl, String fsysname, String ffilename, Long fsubuserid, Date fsubdate, Integer fsubmode, Integer fcheckstate, Long fsubdataid, Integer fsubdatanum, Integer fhavetype, Integer fisinit, Long finitfileid, Integer feditionno, String fedition, Integer fvalid, Long fzsubdataid, String fxml) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftype = ftype;
        this.fprojectid = fprojectid;
        this.fprojectplanid = fprojectplanid;
        this.ftaskid = ftaskid;
        this.fworkflowid = fworkflowid;
        this.fnodeid = fnodeid;
        this.fname = fname;
        this.ftool = ftool;
        this.ftypeid = ftypeid;
        this.ffiletype = ffiletype;
        this.fcheckflowid = fcheckflowid;
        this.fnote = fnote;
        this.furl = furl;
        this.fsysname = fsysname;
        this.ffilename = ffilename;
        this.fsubuserid = fsubuserid;
        this.fsubdate = fsubdate;
        this.fsubmode = fsubmode;
        this.fcheckstate = fcheckstate;
        this.fsubdataid = fsubdataid;
        this.fsubdatanum = fsubdatanum;
        this.fhavetype = fhavetype;
        this.fisinit = fisinit;
        this.finitfileid = finitfileid;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.fzsubdataid = fzsubdataid;
        this.fxml = fxml;
    }

    /**
     * 构造查询条件
     * t_subdata
     * @mbg.generated
     */
    public TSubdata() {
        super();
    }

    /**
     * 获取  字段:t_subdata.FKeyID
     * @return t_subdata.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_subdata.FKeyID
     * @param fkeyid the value for t_subdata.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_subdata.FCID
     * @return t_subdata.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_subdata.FCID
     * @param fcid the value for t_subdata.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_subdata.FUID
     * @return t_subdata.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_subdata.FUID
     * @param fuid the value for t_subdata.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_subdata.FCDATE
     * @return t_subdata.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_subdata.FCDATE
     * @param fcdate the value for t_subdata.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_subdata.FUDATE
     * @return t_subdata.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_subdata.FUDATE
     * @param fudate the value for t_subdata.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:t_subdata.FState
     * @return t_subdata.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:t_subdata.FState
     * @param fstate the value for t_subdata.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 类型,1项计,2项流,3任,4任流,5文件审核，6异常审核7，采购审核 字段:t_subdata.FType
     * @return t_subdata.FType, 类型,1项计,2项流,3任,4任流,5文件审核，6异常审核7，采购审核
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 类型,1项计,2项流,3任,4任流,5文件审核，6异常审核7，采购审核 字段:t_subdata.FType
     * @param ftype the value for t_subdata.FType, 类型,1项计,2项流,3任,4任流,5文件审核，6异常审核7，采购审核
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 项目ID,T_Project主键 字段:t_subdata.FProjectID
     * @return t_subdata.FProjectID, 项目ID,T_Project主键
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID,T_Project主键 字段:t_subdata.FProjectID
     * @param fprojectid the value for t_subdata.FProjectID, 项目ID,T_Project主键
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 项目计划ID,T_Project_Plan主键 字段:t_subdata.FProjectPlanID
     * @return t_subdata.FProjectPlanID, 项目计划ID,T_Project_Plan主键
     * @mbg.generated
     */
    public Long getFprojectplanid() {
        return fprojectplanid;
    }

    /**
     * 设置 项目计划ID,T_Project_Plan主键 字段:t_subdata.FProjectPlanID
     * @param fprojectplanid the value for t_subdata.FProjectPlanID, 项目计划ID,T_Project_Plan主键
     * @mbg.generated
     */
    public void setFprojectplanid(Long fprojectplanid) {
        this.fprojectplanid = fprojectplanid;
    }

    /**
     * 获取 任务ID,T_Task主键 字段:t_subdata.FTaskID
     * @return t_subdata.FTaskID, 任务ID,T_Task主键
     * @mbg.generated
     */
    public Long getFtaskid() {
        return ftaskid;
    }

    /**
     * 设置 任务ID,T_Task主键 字段:t_subdata.FTaskID
     * @param ftaskid the value for t_subdata.FTaskID, 任务ID,T_Task主键
     * @mbg.generated
     */
    public void setFtaskid(Long ftaskid) {
        this.ftaskid = ftaskid;
    }

    /**
     * 获取 工作流ID 字段:t_subdata.FWorkFlowID
     * @return t_subdata.FWorkFlowID, 工作流ID
     * @mbg.generated
     */
    public Long getFworkflowid() {
        return fworkflowid;
    }

    /**
     * 设置 工作流ID 字段:t_subdata.FWorkFlowID
     * @param fworkflowid the value for t_subdata.FWorkFlowID, 工作流ID
     * @mbg.generated
     */
    public void setFworkflowid(Long fworkflowid) {
        this.fworkflowid = fworkflowid;
    }

    /**
     * 获取 工作流节点ID 字段:t_subdata.FNodeID
     * @return t_subdata.FNodeID, 工作流节点ID
     * @mbg.generated
     */
    public Long getFnodeid() {
        return fnodeid;
    }

    /**
     * 设置 工作流节点ID 字段:t_subdata.FNodeID
     * @param fnodeid the value for t_subdata.FNodeID, 工作流节点ID
     * @mbg.generated
     */
    public void setFnodeid(Long fnodeid) {
        this.fnodeid = fnodeid;
    }

    /**
     * 获取 需要的交付物 字段:t_subdata.FName
     * @return t_subdata.FName, 需要的交付物
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 需要的交付物 字段:t_subdata.FName
     * @param fname the value for t_subdata.FName, 需要的交付物
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 工具,UG等 字段:t_subdata.FTool
     * @return t_subdata.FTool, 工具,UG等
     * @mbg.generated
     */
    public String getFtool() {
        return ftool;
    }

    /**
     * 设置 工具,UG等 字段:t_subdata.FTool
     * @param ftool the value for t_subdata.FTool, 工具,UG等
     * @mbg.generated
     */
    public void setFtool(String ftool) {
        this.ftool = ftool == null ? null : ftool.trim();
    }

    /**
     * 获取 交付物类型,T_SubData_Type主键 字段:t_subdata.FTypeID
     * @return t_subdata.FTypeID, 交付物类型,T_SubData_Type主键
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 交付物类型,T_SubData_Type主键 字段:t_subdata.FTypeID
     * @param ftypeid the value for t_subdata.FTypeID, 交付物类型,T_SubData_Type主键
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 交付物文件类型,后缀名 字段:t_subdata.FFileType
     * @return t_subdata.FFileType, 交付物文件类型,后缀名
     * @mbg.generated
     */
    public String getFfiletype() {
        return ffiletype;
    }

    /**
     * 设置 交付物文件类型,后缀名 字段:t_subdata.FFileType
     * @param ffiletype the value for t_subdata.FFileType, 交付物文件类型,后缀名
     * @mbg.generated
     */
    public void setFfiletype(String ffiletype) {
        this.ffiletype = ffiletype == null ? null : ffiletype.trim();
    }

    /**
     * 获取 审核流程 字段:t_subdata.FCheckFlowID
     * @return t_subdata.FCheckFlowID, 审核流程
     * @mbg.generated
     */
    public Long getFcheckflowid() {
        return fcheckflowid;
    }

    /**
     * 设置 审核流程 字段:t_subdata.FCheckFlowID
     * @param fcheckflowid the value for t_subdata.FCheckFlowID, 审核流程
     * @mbg.generated
     */
    public void setFcheckflowid(Long fcheckflowid) {
        this.fcheckflowid = fcheckflowid;
    }

    /**
     * 获取 备注 字段:t_subdata.FNote
     * @return t_subdata.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_subdata.FNote
     * @param fnote the value for t_subdata.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 存放地址 字段:t_subdata.FUrl
     * @return t_subdata.FUrl, 存放地址
     * @mbg.generated
     */
    public String getFurl() {
        return furl;
    }

    /**
     * 设置 存放地址 字段:t_subdata.FUrl
     * @param furl the value for t_subdata.FUrl, 存放地址
     * @mbg.generated
     */
    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    /**
     * 获取 系统存放名 字段:t_subdata.FSysName
     * @return t_subdata.FSysName, 系统存放名
     * @mbg.generated
     */
    public String getFsysname() {
        return fsysname;
    }

    /**
     * 设置 系统存放名 字段:t_subdata.FSysName
     * @param fsysname the value for t_subdata.FSysName, 系统存放名
     * @mbg.generated
     */
    public void setFsysname(String fsysname) {
        this.fsysname = fsysname == null ? null : fsysname.trim();
    }

    /**
     * 获取 文件名 字段:t_subdata.FFileName
     * @return t_subdata.FFileName, 文件名
     * @mbg.generated
     */
    public String getFfilename() {
        return ffilename;
    }

    /**
     * 设置 文件名 字段:t_subdata.FFileName
     * @param ffilename the value for t_subdata.FFileName, 文件名
     * @mbg.generated
     */
    public void setFfilename(String ffilename) {
        this.ffilename = ffilename == null ? null : ffilename.trim();
    }

    /**
     * 获取 提交人,T_UserID主键 字段:t_subdata.FSubUserID
     * @return t_subdata.FSubUserID, 提交人,T_UserID主键
     * @mbg.generated
     */
    public Long getFsubuserid() {
        return fsubuserid;
    }

    /**
     * 设置 提交人,T_UserID主键 字段:t_subdata.FSubUserID
     * @param fsubuserid the value for t_subdata.FSubUserID, 提交人,T_UserID主键
     * @mbg.generated
     */
    public void setFsubuserid(Long fsubuserid) {
        this.fsubuserid = fsubuserid;
    }

    /**
     * 获取 提交时间 字段:t_subdata.FSubDate
     * @return t_subdata.FSubDate, 提交时间
     * @mbg.generated
     */
    public Date getFsubdate() {
        return fsubdate;
    }

    /**
     * 设置 提交时间 字段:t_subdata.FSubDate
     * @param fsubdate the value for t_subdata.FSubDate, 提交时间
     * @mbg.generated
     */
    public void setFsubdate(Date fsubdate) {
        this.fsubdate = fsubdate;
    }

    /**
     * 获取 提交方式,1上传,2-流程转,3任务转 字段:t_subdata.FSubMode
     * @return t_subdata.FSubMode, 提交方式,1上传,2-流程转,3任务转
     * @mbg.generated
     */
    public Integer getFsubmode() {
        return fsubmode;
    }

    /**
     * 设置 提交方式,1上传,2-流程转,3任务转 字段:t_subdata.FSubMode
     * @param fsubmode the value for t_subdata.FSubMode, 提交方式,1上传,2-流程转,3任务转
     * @mbg.generated
     */
    public void setFsubmode(Integer fsubmode) {
        this.fsubmode = fsubmode;
    }

    /**
     * 获取 审核状态,0未审,1审核中,2通过,3不通过 字段:t_subdata.FCheckState
     * @return t_subdata.FCheckState, 审核状态,0未审,1审核中,2通过,3不通过
     * @mbg.generated
     */
    public Integer getFcheckstate() {
        return fcheckstate;
    }

    /**
     * 设置 审核状态,0未审,1审核中,2通过,3不通过 字段:t_subdata.FCheckState
     * @param fcheckstate the value for t_subdata.FCheckState, 审核状态,0未审,1审核中,2通过,3不通过
     * @mbg.generated
     */
    public void setFcheckstate(Integer fcheckstate) {
        this.fcheckstate = fcheckstate;
    }

    /**
     * 获取 原提交物ID 字段:t_subdata.FSubDataID
     * @return t_subdata.FSubDataID, 原提交物ID
     * @mbg.generated
     */
    public Long getFsubdataid() {
        return fsubdataid;
    }

    /**
     * 设置 原提交物ID 字段:t_subdata.FSubDataID
     * @param fsubdataid the value for t_subdata.FSubDataID, 原提交物ID
     * @mbg.generated
     */
    public void setFsubdataid(Long fsubdataid) {
        this.fsubdataid = fsubdataid;
    }

    /**
     * 获取 第几次提交 字段:t_subdata.FSubDataNum
     * @return t_subdata.FSubDataNum, 第几次提交
     * @mbg.generated
     */
    public Integer getFsubdatanum() {
        return fsubdatanum;
    }

    /**
     * 设置 第几次提交 字段:t_subdata.FSubDataNum
     * @param fsubdatanum the value for t_subdata.FSubDataNum, 第几次提交
     * @mbg.generated
     */
    public void setFsubdatanum(Integer fsubdatanum) {
        this.fsubdatanum = fsubdatanum;
    }

    /**
     * 获取 需要或其他 字段:t_subdata.FHaveType
     * @return t_subdata.FHaveType, 需要或其他
     * @mbg.generated
     */
    public Integer getFhavetype() {
        return fhavetype;
    }

    /**
     * 设置 需要或其他 字段:t_subdata.FHaveType
     * @param fhavetype the value for t_subdata.FHaveType, 需要或其他
     * @mbg.generated
     */
    public void setFhavetype(Integer fhavetype) {
        this.fhavetype = fhavetype;
    }

    /**
     * 获取 是否原始版本,0-是，1-升级版本 字段:t_subdata.FISInit
     * @return t_subdata.FISInit, 是否原始版本,0-是，1-升级版本
     * @mbg.generated
     */
    public Integer getFisinit() {
        return fisinit;
    }

    /**
     * 设置 是否原始版本,0-是，1-升级版本 字段:t_subdata.FISInit
     * @param fisinit the value for t_subdata.FISInit, 是否原始版本,0-是，1-升级版本
     * @mbg.generated
     */
    public void setFisinit(Integer fisinit) {
        this.fisinit = fisinit;
    }

    /**
     * 获取 原始版本ID,T_SubData主键 字段:t_subdata.FInitFileID
     * @return t_subdata.FInitFileID, 原始版本ID,T_SubData主键
     * @mbg.generated
     */
    public Long getFinitfileid() {
        return finitfileid;
    }

    /**
     * 设置 原始版本ID,T_SubData主键 字段:t_subdata.FInitFileID
     * @param finitfileid the value for t_subdata.FInitFileID, 原始版本ID,T_SubData主键
     * @mbg.generated
     */
    public void setFinitfileid(Long finitfileid) {
        this.finitfileid = finitfileid;
    }

    /**
     * 获取 版本号 字段:t_subdata.FEditionNo
     * @return t_subdata.FEditionNo, 版本号
     * @mbg.generated
     */
    public Integer getFeditionno() {
        return feditionno;
    }

    /**
     * 设置 版本号 字段:t_subdata.FEditionNo
     * @param feditionno the value for t_subdata.FEditionNo, 版本号
     * @mbg.generated
     */
    public void setFeditionno(Integer feditionno) {
        this.feditionno = feditionno;
    }

    /**
     * 获取 版本,V1(V+FEditionNo) 字段:t_subdata.FEdition
     * @return t_subdata.FEdition, 版本,V1(V+FEditionNo)
     * @mbg.generated
     */
    public String getFedition() {
        return fedition;
    }

    /**
     * 设置 版本,V1(V+FEditionNo) 字段:t_subdata.FEdition
     * @param fedition the value for t_subdata.FEdition, 版本,V1(V+FEditionNo)
     * @mbg.generated
     */
    public void setFedition(String fedition) {
        this.fedition = fedition == null ? null : fedition.trim();
    }

    /**
     * 获取 有效版本,0-无效,1-有（只一个） 字段:t_subdata.FValid
     * @return t_subdata.FValid, 有效版本,0-无效,1-有（只一个）
     * @mbg.generated
     */
    public Integer getFvalid() {
        return fvalid;
    }

    /**
     * 设置 有效版本,0-无效,1-有（只一个） 字段:t_subdata.FValid
     * @param fvalid the value for t_subdata.FValid, 有效版本,0-无效,1-有（只一个）
     * @mbg.generated
     */
    public void setFvalid(Integer fvalid) {
        this.fvalid = fvalid;
    }

    /**
     * 获取 流程转任务交付物，交付物ID 字段:t_subdata.FZSubDataID
     * @return t_subdata.FZSubDataID, 流程转任务交付物，交付物ID
     * @mbg.generated
     */
    public Long getFzsubdataid() {
        return fzsubdataid;
    }

    /**
     * 设置 流程转任务交付物，交付物ID 字段:t_subdata.FZSubDataID
     * @param fzsubdataid the value for t_subdata.FZSubDataID, 流程转任务交付物，交付物ID
     * @mbg.generated
     */
    public void setFzsubdataid(Long fzsubdataid) {
        this.fzsubdataid = fzsubdataid;
    }

    /**
     * 获取  字段:t_subdata.FXml
     * @return t_subdata.FXml, 
     * @mbg.generated
     */
    public String getFxml() {
        return fxml;
    }

    /**
     * 设置  字段:t_subdata.FXml
     * @param fxml the value for t_subdata.FXml, 
     * @mbg.generated
     */
    public void setFxml(String fxml) {
        this.fxml = fxml == null ? null : fxml.trim();
    }

    /**
     * 实例输出为字符串
     * t_subdata
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
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", fprojectplanid=").append(fprojectplanid);
        sb.append(", ftaskid=").append(ftaskid);
        sb.append(", fworkflowid=").append(fworkflowid);
        sb.append(", fnodeid=").append(fnodeid);
        sb.append(", fname=").append(fname);
        sb.append(", ftool=").append(ftool);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", ffiletype=").append(ffiletype);
        sb.append(", fcheckflowid=").append(fcheckflowid);
        sb.append(", fnote=").append(fnote);
        sb.append(", furl=").append(furl);
        sb.append(", fsysname=").append(fsysname);
        sb.append(", ffilename=").append(ffilename);
        sb.append(", fsubuserid=").append(fsubuserid);
        sb.append(", fsubdate=").append(fsubdate);
        sb.append(", fsubmode=").append(fsubmode);
        sb.append(", fcheckstate=").append(fcheckstate);
        sb.append(", fsubdataid=").append(fsubdataid);
        sb.append(", fsubdatanum=").append(fsubdatanum);
        sb.append(", fhavetype=").append(fhavetype);
        sb.append(", fisinit=").append(fisinit);
        sb.append(", finitfileid=").append(finitfileid);
        sb.append(", feditionno=").append(feditionno);
        sb.append(", fedition=").append(fedition);
        sb.append(", fvalid=").append(fvalid);
        sb.append(", fzsubdataid=").append(fzsubdataid);
        sb.append(", fxml=").append(fxml);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_subdata
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
        TSubdata other = (TSubdata) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFprojectplanid() == null ? other.getFprojectplanid() == null : this.getFprojectplanid().equals(other.getFprojectplanid()))
            && (this.getFtaskid() == null ? other.getFtaskid() == null : this.getFtaskid().equals(other.getFtaskid()))
            && (this.getFworkflowid() == null ? other.getFworkflowid() == null : this.getFworkflowid().equals(other.getFworkflowid()))
            && (this.getFnodeid() == null ? other.getFnodeid() == null : this.getFnodeid().equals(other.getFnodeid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtool() == null ? other.getFtool() == null : this.getFtool().equals(other.getFtool()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFfiletype() == null ? other.getFfiletype() == null : this.getFfiletype().equals(other.getFfiletype()))
            && (this.getFcheckflowid() == null ? other.getFcheckflowid() == null : this.getFcheckflowid().equals(other.getFcheckflowid()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFurl() == null ? other.getFurl() == null : this.getFurl().equals(other.getFurl()))
            && (this.getFsysname() == null ? other.getFsysname() == null : this.getFsysname().equals(other.getFsysname()))
            && (this.getFfilename() == null ? other.getFfilename() == null : this.getFfilename().equals(other.getFfilename()))
            && (this.getFsubuserid() == null ? other.getFsubuserid() == null : this.getFsubuserid().equals(other.getFsubuserid()))
            && (this.getFsubdate() == null ? other.getFsubdate() == null : this.getFsubdate().equals(other.getFsubdate()))
            && (this.getFsubmode() == null ? other.getFsubmode() == null : this.getFsubmode().equals(other.getFsubmode()))
            && (this.getFcheckstate() == null ? other.getFcheckstate() == null : this.getFcheckstate().equals(other.getFcheckstate()))
            && (this.getFsubdataid() == null ? other.getFsubdataid() == null : this.getFsubdataid().equals(other.getFsubdataid()))
            && (this.getFsubdatanum() == null ? other.getFsubdatanum() == null : this.getFsubdatanum().equals(other.getFsubdatanum()))
            && (this.getFhavetype() == null ? other.getFhavetype() == null : this.getFhavetype().equals(other.getFhavetype()))
            && (this.getFisinit() == null ? other.getFisinit() == null : this.getFisinit().equals(other.getFisinit()))
            && (this.getFinitfileid() == null ? other.getFinitfileid() == null : this.getFinitfileid().equals(other.getFinitfileid()))
            && (this.getFeditionno() == null ? other.getFeditionno() == null : this.getFeditionno().equals(other.getFeditionno()))
            && (this.getFedition() == null ? other.getFedition() == null : this.getFedition().equals(other.getFedition()))
            && (this.getFvalid() == null ? other.getFvalid() == null : this.getFvalid().equals(other.getFvalid()))
            && (this.getFzsubdataid() == null ? other.getFzsubdataid() == null : this.getFzsubdataid().equals(other.getFzsubdataid()))
            && (this.getFxml() == null ? other.getFxml() == null : this.getFxml().equals(other.getFxml()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_subdata
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
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFprojectplanid() == null) ? 0 : getFprojectplanid().hashCode());
        result = prime * result + ((getFtaskid() == null) ? 0 : getFtaskid().hashCode());
        result = prime * result + ((getFworkflowid() == null) ? 0 : getFworkflowid().hashCode());
        result = prime * result + ((getFnodeid() == null) ? 0 : getFnodeid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFtool() == null) ? 0 : getFtool().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFfiletype() == null) ? 0 : getFfiletype().hashCode());
        result = prime * result + ((getFcheckflowid() == null) ? 0 : getFcheckflowid().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFurl() == null) ? 0 : getFurl().hashCode());
        result = prime * result + ((getFsysname() == null) ? 0 : getFsysname().hashCode());
        result = prime * result + ((getFfilename() == null) ? 0 : getFfilename().hashCode());
        result = prime * result + ((getFsubuserid() == null) ? 0 : getFsubuserid().hashCode());
        result = prime * result + ((getFsubdate() == null) ? 0 : getFsubdate().hashCode());
        result = prime * result + ((getFsubmode() == null) ? 0 : getFsubmode().hashCode());
        result = prime * result + ((getFcheckstate() == null) ? 0 : getFcheckstate().hashCode());
        result = prime * result + ((getFsubdataid() == null) ? 0 : getFsubdataid().hashCode());
        result = prime * result + ((getFsubdatanum() == null) ? 0 : getFsubdatanum().hashCode());
        result = prime * result + ((getFhavetype() == null) ? 0 : getFhavetype().hashCode());
        result = prime * result + ((getFisinit() == null) ? 0 : getFisinit().hashCode());
        result = prime * result + ((getFinitfileid() == null) ? 0 : getFinitfileid().hashCode());
        result = prime * result + ((getFeditionno() == null) ? 0 : getFeditionno().hashCode());
        result = prime * result + ((getFedition() == null) ? 0 : getFedition().hashCode());
        result = prime * result + ((getFvalid() == null) ? 0 : getFvalid().hashCode());
        result = prime * result + ((getFzsubdataid() == null) ? 0 : getFzsubdataid().hashCode());
        result = prime * result + ((getFxml() == null) ? 0 : getFxml().hashCode());
        return result;
    }
}