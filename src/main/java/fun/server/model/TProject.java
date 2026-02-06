package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_project
*/
@Table(name = "t_project")
public class TProject implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_project.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_project.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_project.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 项目发布人
     * 表字段 : teaching_diversity..t_project.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    /**
     * 项目发布时间
     * 表字段 : teaching_diversity..t_project.FDate
     * @mbg.generated
     */
    private Date fdate;

    /**
     * 状态,0编辑,1在研,2完成,3关闭
     * 表字段 : teaching_diversity..t_project.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 项目名称
     * 表字段 : teaching_diversity..t_project.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 项目编码
     * 表字段 : teaching_diversity..t_project.FNo
     * @mbg.generated
     */
    private String fno;

    /**
     * 客户
     * 表字段 : teaching_diversity..t_project.FCustomerID
     * @mbg.generated
     */
    private Long fcustomerid;

    /**
     * 客户要求
     * 表字段 : teaching_diversity..t_project.FAsk
     * @mbg.generated
     */
    private String fask;

    /**
     * 描述
     * 表字段 : teaching_diversity..t_project.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 项目类型
     * 表字段 : teaching_diversity..t_project.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 项目等级
     * 表字段 : teaching_diversity..t_project.FLevelID
     * @mbg.generated
     */
    private Long flevelid;

    /**
     * 项目负责人
     * 表字段 : teaching_diversity..t_project.FLeadUserID
     * @mbg.generated
     */
    private Long fleaduserid;

    /**
     * 计划开始时间
     * 表字段 : teaching_diversity..t_project.FSDate
     * @mbg.generated
     */
    private Date fsdate;

    /**
     * 计划结束时间
     * 表字段 : teaching_diversity..t_project.FEDate
     * @mbg.generated
     */
    private Date fedate;

    /**
     * 实际开始时间
     * 表字段 : teaching_diversity..t_project.FStartDate
     * @mbg.generated
     */
    private Date fstartdate;

    /**
     * 实际完成时间
     * 表字段 : teaching_diversity..t_project.FEndDate
     * @mbg.generated
     */
    private Date fenddate;

    /**
     * 项目产品
     * 表字段 : teaching_diversity..t_project.FMaterialID
     * @mbg.generated
     */
    private Long fmaterialid;

    /**
     * 主管部门
     * 表字段 : teaching_diversity..t_project.FDeptID
     * @mbg.generated
     */
    private Long fdeptid;

    /**
     * 合同编号
     * 表字段 : teaching_diversity..t_project.FContractNo
     * @mbg.generated
     */
    private String fcontractno;

    /**
     * 密级
     * 表字段 : teaching_diversity..t_project.FSecret
     * @mbg.generated
     */
    private Long fsecret;

    /**
     * 来源
     * 表字段 : teaching_diversity..t_project.FFrom
     * @mbg.generated
     */
    private String ffrom;

    /**
     * 经费
     * 表字段 : teaching_diversity..t_project.FFunds
     * @mbg.generated
     */
    private String ffunds;

    /**
     * 系统编码,生成(P+年月+月流水3位)
     * 表字段 : teaching_diversity..t_project.FSysNo
     * @mbg.generated
     */
    private String fsysno;

    /**
     * 系统编码流水
     * 表字段 : teaching_diversity..t_project.FSysNoNum
     * @mbg.generated
     */
    private Integer fsysnonum;

    /**
     * 年月
     * 表字段 : teaching_diversity..t_project.FYYMMNum
     * @mbg.generated
     */
    private String fyymmnum;

    /**
     * 内部编码
     * 表字段 : teaching_diversity..t_project.FInsideNo
     * @mbg.generated
     */
    private String finsideno;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public TProject(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Long fuserid, Date fdate, Integer fstate, String fname, String fno, Long fcustomerid, String fask, String fnote, Long ftypeid, Long flevelid, Long fleaduserid, Date fsdate, Date fedate, Date fstartdate, Date fenddate, Long fmaterialid, Long fdeptid, String fcontractno, Long fsecret, String ffrom, String ffunds, String fsysno, Integer fsysnonum, String fyymmnum, String finsideno) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fuserid = fuserid;
        this.fdate = fdate;
        this.fstate = fstate;
        this.fname = fname;
        this.fno = fno;
        this.fcustomerid = fcustomerid;
        this.fask = fask;
        this.fnote = fnote;
        this.ftypeid = ftypeid;
        this.flevelid = flevelid;
        this.fleaduserid = fleaduserid;
        this.fsdate = fsdate;
        this.fedate = fedate;
        this.fstartdate = fstartdate;
        this.fenddate = fenddate;
        this.fmaterialid = fmaterialid;
        this.fdeptid = fdeptid;
        this.fcontractno = fcontractno;
        this.fsecret = fsecret;
        this.ffrom = ffrom;
        this.ffunds = ffunds;
        this.fsysno = fsysno;
        this.fsysnonum = fsysnonum;
        this.fyymmnum = fyymmnum;
        this.finsideno = finsideno;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public TProject() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_project.FKeyID
     * @return teaching_diversity..t_project.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project.FKeyID
     * @param fkeyid the value for teaching_diversity..t_project.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project.FCID
     * @return teaching_diversity..t_project.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project.FCID
     * @param fcid the value for teaching_diversity..t_project.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project.FUID
     * @return teaching_diversity..t_project.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_project.FUID
     * @param fuid the value for teaching_diversity..t_project.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_project.FCDATE
     * @return teaching_diversity..t_project.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_project.FCDATE
     * @param fcdate the value for teaching_diversity..t_project.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_project.FUDATE
     * @return teaching_diversity..t_project.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_project.FUDATE
     * @param fudate the value for teaching_diversity..t_project.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 项目发布人 字段:teaching_diversity..t_project.FUserID
     * @return teaching_diversity..t_project.FUserID, 项目发布人
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 项目发布人 字段:teaching_diversity..t_project.FUserID
     * @param fuserid the value for teaching_diversity..t_project.FUserID, 项目发布人
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 获取 项目发布时间 字段:teaching_diversity..t_project.FDate
     * @return teaching_diversity..t_project.FDate, 项目发布时间
     * @mbg.generated
     */
    public Date getFdate() {
        return fdate;
    }

    /**
     * 设置 项目发布时间 字段:teaching_diversity..t_project.FDate
     * @param fdate the value for teaching_diversity..t_project.FDate, 项目发布时间
     * @mbg.generated
     */
    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    /**
     * 获取 状态,0编辑,1在研,2完成,3关闭 字段:teaching_diversity..t_project.FState
     * @return teaching_diversity..t_project.FState, 状态,0编辑,1在研,2完成,3关闭
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态,0编辑,1在研,2完成,3关闭 字段:teaching_diversity..t_project.FState
     * @param fstate the value for teaching_diversity..t_project.FState, 状态,0编辑,1在研,2完成,3关闭
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 项目名称 字段:teaching_diversity..t_project.FName
     * @return teaching_diversity..t_project.FName, 项目名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 项目名称 字段:teaching_diversity..t_project.FName
     * @param fname the value for teaching_diversity..t_project.FName, 项目名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 项目编码 字段:teaching_diversity..t_project.FNo
     * @return teaching_diversity..t_project.FNo, 项目编码
     * @mbg.generated
     */
    public String getFno() {
        return fno;
    }

    /**
     * 设置 项目编码 字段:teaching_diversity..t_project.FNo
     * @param fno the value for teaching_diversity..t_project.FNo, 项目编码
     * @mbg.generated
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * 获取 客户 字段:teaching_diversity..t_project.FCustomerID
     * @return teaching_diversity..t_project.FCustomerID, 客户
     * @mbg.generated
     */
    public Long getFcustomerid() {
        return fcustomerid;
    }

    /**
     * 设置 客户 字段:teaching_diversity..t_project.FCustomerID
     * @param fcustomerid the value for teaching_diversity..t_project.FCustomerID, 客户
     * @mbg.generated
     */
    public void setFcustomerid(Long fcustomerid) {
        this.fcustomerid = fcustomerid;
    }

    /**
     * 获取 客户要求 字段:teaching_diversity..t_project.FAsk
     * @return teaching_diversity..t_project.FAsk, 客户要求
     * @mbg.generated
     */
    public String getFask() {
        return fask;
    }

    /**
     * 设置 客户要求 字段:teaching_diversity..t_project.FAsk
     * @param fask the value for teaching_diversity..t_project.FAsk, 客户要求
     * @mbg.generated
     */
    public void setFask(String fask) {
        this.fask = fask == null ? null : fask.trim();
    }

    /**
     * 获取 描述 字段:teaching_diversity..t_project.FNote
     * @return teaching_diversity..t_project.FNote, 描述
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 描述 字段:teaching_diversity..t_project.FNote
     * @param fnote the value for teaching_diversity..t_project.FNote, 描述
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 项目类型 字段:teaching_diversity..t_project.FTypeID
     * @return teaching_diversity..t_project.FTypeID, 项目类型
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 项目类型 字段:teaching_diversity..t_project.FTypeID
     * @param ftypeid the value for teaching_diversity..t_project.FTypeID, 项目类型
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 项目等级 字段:teaching_diversity..t_project.FLevelID
     * @return teaching_diversity..t_project.FLevelID, 项目等级
     * @mbg.generated
     */
    public Long getFlevelid() {
        return flevelid;
    }

    /**
     * 设置 项目等级 字段:teaching_diversity..t_project.FLevelID
     * @param flevelid the value for teaching_diversity..t_project.FLevelID, 项目等级
     * @mbg.generated
     */
    public void setFlevelid(Long flevelid) {
        this.flevelid = flevelid;
    }

    /**
     * 获取 项目负责人 字段:teaching_diversity..t_project.FLeadUserID
     * @return teaching_diversity..t_project.FLeadUserID, 项目负责人
     * @mbg.generated
     */
    public Long getFleaduserid() {
        return fleaduserid;
    }

    /**
     * 设置 项目负责人 字段:teaching_diversity..t_project.FLeadUserID
     * @param fleaduserid the value for teaching_diversity..t_project.FLeadUserID, 项目负责人
     * @mbg.generated
     */
    public void setFleaduserid(Long fleaduserid) {
        this.fleaduserid = fleaduserid;
    }

    /**
     * 获取 计划开始时间 字段:teaching_diversity..t_project.FSDate
     * @return teaching_diversity..t_project.FSDate, 计划开始时间
     * @mbg.generated
     */
    public Date getFsdate() {
        return fsdate;
    }

    /**
     * 设置 计划开始时间 字段:teaching_diversity..t_project.FSDate
     * @param fsdate the value for teaching_diversity..t_project.FSDate, 计划开始时间
     * @mbg.generated
     */
    public void setFsdate(Date fsdate) {
        this.fsdate = fsdate;
    }

    /**
     * 获取 计划结束时间 字段:teaching_diversity..t_project.FEDate
     * @return teaching_diversity..t_project.FEDate, 计划结束时间
     * @mbg.generated
     */
    public Date getFedate() {
        return fedate;
    }

    /**
     * 设置 计划结束时间 字段:teaching_diversity..t_project.FEDate
     * @param fedate the value for teaching_diversity..t_project.FEDate, 计划结束时间
     * @mbg.generated
     */
    public void setFedate(Date fedate) {
        this.fedate = fedate;
    }

    /**
     * 获取 实际开始时间 字段:teaching_diversity..t_project.FStartDate
     * @return teaching_diversity..t_project.FStartDate, 实际开始时间
     * @mbg.generated
     */
    public Date getFstartdate() {
        return fstartdate;
    }

    /**
     * 设置 实际开始时间 字段:teaching_diversity..t_project.FStartDate
     * @param fstartdate the value for teaching_diversity..t_project.FStartDate, 实际开始时间
     * @mbg.generated
     */
    public void setFstartdate(Date fstartdate) {
        this.fstartdate = fstartdate;
    }

    /**
     * 获取 实际完成时间 字段:teaching_diversity..t_project.FEndDate
     * @return teaching_diversity..t_project.FEndDate, 实际完成时间
     * @mbg.generated
     */
    public Date getFenddate() {
        return fenddate;
    }

    /**
     * 设置 实际完成时间 字段:teaching_diversity..t_project.FEndDate
     * @param fenddate the value for teaching_diversity..t_project.FEndDate, 实际完成时间
     * @mbg.generated
     */
    public void setFenddate(Date fenddate) {
        this.fenddate = fenddate;
    }

    /**
     * 获取 项目产品 字段:teaching_diversity..t_project.FMaterialID
     * @return teaching_diversity..t_project.FMaterialID, 项目产品
     * @mbg.generated
     */
    public Long getFmaterialid() {
        return fmaterialid;
    }

    /**
     * 设置 项目产品 字段:teaching_diversity..t_project.FMaterialID
     * @param fmaterialid the value for teaching_diversity..t_project.FMaterialID, 项目产品
     * @mbg.generated
     */
    public void setFmaterialid(Long fmaterialid) {
        this.fmaterialid = fmaterialid;
    }

    /**
     * 获取 主管部门 字段:teaching_diversity..t_project.FDeptID
     * @return teaching_diversity..t_project.FDeptID, 主管部门
     * @mbg.generated
     */
    public Long getFdeptid() {
        return fdeptid;
    }

    /**
     * 设置 主管部门 字段:teaching_diversity..t_project.FDeptID
     * @param fdeptid the value for teaching_diversity..t_project.FDeptID, 主管部门
     * @mbg.generated
     */
    public void setFdeptid(Long fdeptid) {
        this.fdeptid = fdeptid;
    }

    /**
     * 获取 合同编号 字段:teaching_diversity..t_project.FContractNo
     * @return teaching_diversity..t_project.FContractNo, 合同编号
     * @mbg.generated
     */
    public String getFcontractno() {
        return fcontractno;
    }

    /**
     * 设置 合同编号 字段:teaching_diversity..t_project.FContractNo
     * @param fcontractno the value for teaching_diversity..t_project.FContractNo, 合同编号
     * @mbg.generated
     */
    public void setFcontractno(String fcontractno) {
        this.fcontractno = fcontractno == null ? null : fcontractno.trim();
    }

    /**
     * 获取 密级 字段:teaching_diversity..t_project.FSecret
     * @return teaching_diversity..t_project.FSecret, 密级
     * @mbg.generated
     */
    public Long getFsecret() {
        return fsecret;
    }

    /**
     * 设置 密级 字段:teaching_diversity..t_project.FSecret
     * @param fsecret the value for teaching_diversity..t_project.FSecret, 密级
     * @mbg.generated
     */
    public void setFsecret(Long fsecret) {
        this.fsecret = fsecret;
    }

    /**
     * 获取 来源 字段:teaching_diversity..t_project.FFrom
     * @return teaching_diversity..t_project.FFrom, 来源
     * @mbg.generated
     */
    public String getFfrom() {
        return ffrom;
    }

    /**
     * 设置 来源 字段:teaching_diversity..t_project.FFrom
     * @param ffrom the value for teaching_diversity..t_project.FFrom, 来源
     * @mbg.generated
     */
    public void setFfrom(String ffrom) {
        this.ffrom = ffrom == null ? null : ffrom.trim();
    }

    /**
     * 获取 经费 字段:teaching_diversity..t_project.FFunds
     * @return teaching_diversity..t_project.FFunds, 经费
     * @mbg.generated
     */
    public String getFfunds() {
        return ffunds;
    }

    /**
     * 设置 经费 字段:teaching_diversity..t_project.FFunds
     * @param ffunds the value for teaching_diversity..t_project.FFunds, 经费
     * @mbg.generated
     */
    public void setFfunds(String ffunds) {
        this.ffunds = ffunds == null ? null : ffunds.trim();
    }

    /**
     * 获取 系统编码,生成(P+年月+月流水3位) 字段:teaching_diversity..t_project.FSysNo
     * @return teaching_diversity..t_project.FSysNo, 系统编码,生成(P+年月+月流水3位)
     * @mbg.generated
     */
    public String getFsysno() {
        return fsysno;
    }

    /**
     * 设置 系统编码,生成(P+年月+月流水3位) 字段:teaching_diversity..t_project.FSysNo
     * @param fsysno the value for teaching_diversity..t_project.FSysNo, 系统编码,生成(P+年月+月流水3位)
     * @mbg.generated
     */
    public void setFsysno(String fsysno) {
        this.fsysno = fsysno == null ? null : fsysno.trim();
    }

    /**
     * 获取 系统编码流水 字段:teaching_diversity..t_project.FSysNoNum
     * @return teaching_diversity..t_project.FSysNoNum, 系统编码流水
     * @mbg.generated
     */
    public Integer getFsysnonum() {
        return fsysnonum;
    }

    /**
     * 设置 系统编码流水 字段:teaching_diversity..t_project.FSysNoNum
     * @param fsysnonum the value for teaching_diversity..t_project.FSysNoNum, 系统编码流水
     * @mbg.generated
     */
    public void setFsysnonum(Integer fsysnonum) {
        this.fsysnonum = fsysnonum;
    }

    /**
     * 获取 年月 字段:teaching_diversity..t_project.FYYMMNum
     * @return teaching_diversity..t_project.FYYMMNum, 年月
     * @mbg.generated
     */
    public String getFyymmnum() {
        return fyymmnum;
    }

    /**
     * 设置 年月 字段:teaching_diversity..t_project.FYYMMNum
     * @param fyymmnum the value for teaching_diversity..t_project.FYYMMNum, 年月
     * @mbg.generated
     */
    public void setFyymmnum(String fyymmnum) {
        this.fyymmnum = fyymmnum == null ? null : fyymmnum.trim();
    }

    /**
     * 获取 内部编码 字段:teaching_diversity..t_project.FInsideNo
     * @return teaching_diversity..t_project.FInsideNo, 内部编码
     * @mbg.generated
     */
    public String getFinsideno() {
        return finsideno;
    }

    /**
     * 设置 内部编码 字段:teaching_diversity..t_project.FInsideNo
     * @param finsideno the value for teaching_diversity..t_project.FInsideNo, 内部编码
     * @mbg.generated
     */
    public void setFinsideno(String finsideno) {
        this.finsideno = finsideno == null ? null : finsideno.trim();
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_project
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
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fdate=").append(fdate);
        sb.append(", fstate=").append(fstate);
        sb.append(", fname=").append(fname);
        sb.append(", fno=").append(fno);
        sb.append(", fcustomerid=").append(fcustomerid);
        sb.append(", fask=").append(fask);
        sb.append(", fnote=").append(fnote);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", flevelid=").append(flevelid);
        sb.append(", fleaduserid=").append(fleaduserid);
        sb.append(", fsdate=").append(fsdate);
        sb.append(", fedate=").append(fedate);
        sb.append(", fstartdate=").append(fstartdate);
        sb.append(", fenddate=").append(fenddate);
        sb.append(", fmaterialid=").append(fmaterialid);
        sb.append(", fdeptid=").append(fdeptid);
        sb.append(", fcontractno=").append(fcontractno);
        sb.append(", fsecret=").append(fsecret);
        sb.append(", ffrom=").append(ffrom);
        sb.append(", ffunds=").append(ffunds);
        sb.append(", fsysno=").append(fsysno);
        sb.append(", fsysnonum=").append(fsysnonum);
        sb.append(", fyymmnum=").append(fyymmnum);
        sb.append(", finsideno=").append(finsideno);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_project
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
        TProject other = (TProject) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()))
            && (this.getFdate() == null ? other.getFdate() == null : this.getFdate().equals(other.getFdate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFno() == null ? other.getFno() == null : this.getFno().equals(other.getFno()))
            && (this.getFcustomerid() == null ? other.getFcustomerid() == null : this.getFcustomerid().equals(other.getFcustomerid()))
            && (this.getFask() == null ? other.getFask() == null : this.getFask().equals(other.getFask()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFlevelid() == null ? other.getFlevelid() == null : this.getFlevelid().equals(other.getFlevelid()))
            && (this.getFleaduserid() == null ? other.getFleaduserid() == null : this.getFleaduserid().equals(other.getFleaduserid()))
            && (this.getFsdate() == null ? other.getFsdate() == null : this.getFsdate().equals(other.getFsdate()))
            && (this.getFedate() == null ? other.getFedate() == null : this.getFedate().equals(other.getFedate()))
            && (this.getFstartdate() == null ? other.getFstartdate() == null : this.getFstartdate().equals(other.getFstartdate()))
            && (this.getFenddate() == null ? other.getFenddate() == null : this.getFenddate().equals(other.getFenddate()))
            && (this.getFmaterialid() == null ? other.getFmaterialid() == null : this.getFmaterialid().equals(other.getFmaterialid()))
            && (this.getFdeptid() == null ? other.getFdeptid() == null : this.getFdeptid().equals(other.getFdeptid()))
            && (this.getFcontractno() == null ? other.getFcontractno() == null : this.getFcontractno().equals(other.getFcontractno()))
            && (this.getFsecret() == null ? other.getFsecret() == null : this.getFsecret().equals(other.getFsecret()))
            && (this.getFfrom() == null ? other.getFfrom() == null : this.getFfrom().equals(other.getFfrom()))
            && (this.getFfunds() == null ? other.getFfunds() == null : this.getFfunds().equals(other.getFfunds()))
            && (this.getFsysno() == null ? other.getFsysno() == null : this.getFsysno().equals(other.getFsysno()))
            && (this.getFsysnonum() == null ? other.getFsysnonum() == null : this.getFsysnonum().equals(other.getFsysnonum()))
            && (this.getFyymmnum() == null ? other.getFyymmnum() == null : this.getFyymmnum().equals(other.getFyymmnum()))
            && (this.getFinsideno() == null ? other.getFinsideno() == null : this.getFinsideno().equals(other.getFinsideno()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_project
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
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        result = prime * result + ((getFdate() == null) ? 0 : getFdate().hashCode());
        result = prime * result + ((getFstate() == null) ? 0 : getFstate().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFno() == null) ? 0 : getFno().hashCode());
        result = prime * result + ((getFcustomerid() == null) ? 0 : getFcustomerid().hashCode());
        result = prime * result + ((getFask() == null) ? 0 : getFask().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFlevelid() == null) ? 0 : getFlevelid().hashCode());
        result = prime * result + ((getFleaduserid() == null) ? 0 : getFleaduserid().hashCode());
        result = prime * result + ((getFsdate() == null) ? 0 : getFsdate().hashCode());
        result = prime * result + ((getFedate() == null) ? 0 : getFedate().hashCode());
        result = prime * result + ((getFstartdate() == null) ? 0 : getFstartdate().hashCode());
        result = prime * result + ((getFenddate() == null) ? 0 : getFenddate().hashCode());
        result = prime * result + ((getFmaterialid() == null) ? 0 : getFmaterialid().hashCode());
        result = prime * result + ((getFdeptid() == null) ? 0 : getFdeptid().hashCode());
        result = prime * result + ((getFcontractno() == null) ? 0 : getFcontractno().hashCode());
        result = prime * result + ((getFsecret() == null) ? 0 : getFsecret().hashCode());
        result = prime * result + ((getFfrom() == null) ? 0 : getFfrom().hashCode());
        result = prime * result + ((getFfunds() == null) ? 0 : getFfunds().hashCode());
        result = prime * result + ((getFsysno() == null) ? 0 : getFsysno().hashCode());
        result = prime * result + ((getFsysnonum() == null) ? 0 : getFsysnonum().hashCode());
        result = prime * result + ((getFyymmnum() == null) ? 0 : getFyymmnum().hashCode());
        result = prime * result + ((getFinsideno() == null) ? 0 : getFinsideno().hashCode());
        return result;
    }
}