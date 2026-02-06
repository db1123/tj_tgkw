package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_project_files
*/
@Table(name = "t_project_files")
public class TProjectFiles implements Serializable {
    /**
     * 
     * 表字段 : pdm_server..t_project_files.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : pdm_server..t_project_files.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : pdm_server..t_project_files.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : pdm_server..t_project_files.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : pdm_server..t_project_files.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : pdm_server..t_project_files.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 项目ID
     * 表字段 : pdm_server..t_project_files.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 任务ID
     * 表字段 : pdm_server..t_project_files.FTaskID
     * @mbg.generated
     */
    private Long ftaskid;

    /**
     * 类型 1-项目 2-任务
     * 表字段 : pdm_server..t_project_files.FType
     * @mbg.generated
     */
    private Integer ftype;

    /**
     * 文件名称
     * 表字段 : pdm_server..t_project_files.FFileName
     * @mbg.generated
     */
    private String ffilename;

    /**
     * 交付物类型表ID
     * 表字段 : pdm_server..t_project_files.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 版本号
     * 表字段 : pdm_server..t_project_files.FEditionNo
     * @mbg.generated
     */
    private Integer feditionno;

    /**
     * 版本,V1(V+FEditionNo)
     * 表字段 : pdm_server..t_project_files.FEdition
     * @mbg.generated
     */
    private String fedition;

    /**
     * 有效版本,0-无效,1-有（只一个）
     * 表字段 : pdm_server..t_project_files.FValid
     * @mbg.generated
     */
    private Integer fvalid;

    /**
     * 后缀名
     * 表字段 : pdm_server..t_project_files.FFileType
     * @mbg.generated
     */
    private String ffiletype;

    /**
     * 系统文件名
     * 表字段 : pdm_server..t_project_files.FSysName
     * @mbg.generated
     */
    private String fsysname;

    /**
     * 原始ID，默认-1
     * 表字段 : pdm_server..t_project_files.FFileID
     * @mbg.generated
     */
    private Long ffileid;

    /**
     * 存放地址
     * 表字段 : pdm_server..t_project_files.FUrl
     * @mbg.generated
     */
    private String furl;

    /**
     * 备注
     * 表字段 : pdm_server..t_project_files.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public TProjectFiles(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fprojectid, Long ftaskid, Integer ftype, String ffilename, Long ftypeid, Integer feditionno, String fedition, Integer fvalid, String ffiletype, String fsysname, Long ffileid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fprojectid = fprojectid;
        this.ftaskid = ftaskid;
        this.ftype = ftype;
        this.ffilename = ffilename;
        this.ftypeid = ftypeid;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.ffiletype = ffiletype;
        this.fsysname = fsysname;
        this.ffileid = ffileid;
    }

    /**
     * 构造查询条件
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public TProjectFiles(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fprojectid, Long ftaskid, Integer ftype, String ffilename, Long ftypeid, Integer feditionno, String fedition, Integer fvalid, String ffiletype, String fsysname, Long ffileid, String furl, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fprojectid = fprojectid;
        this.ftaskid = ftaskid;
        this.ftype = ftype;
        this.ffilename = ffilename;
        this.ftypeid = ftypeid;
        this.feditionno = feditionno;
        this.fedition = fedition;
        this.fvalid = fvalid;
        this.ffiletype = ffiletype;
        this.fsysname = fsysname;
        this.ffileid = ffileid;
        this.furl = furl;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public TProjectFiles() {
        super();
    }

    /**
     * 获取  字段:pdm_server..t_project_files.FKeyID
     * @return pdm_server..t_project_files.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:pdm_server..t_project_files.FKeyID
     * @param fkeyid the value for pdm_server..t_project_files.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:pdm_server..t_project_files.FCID
     * @return pdm_server..t_project_files.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:pdm_server..t_project_files.FCID
     * @param fcid the value for pdm_server..t_project_files.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:pdm_server..t_project_files.FUID
     * @return pdm_server..t_project_files.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:pdm_server..t_project_files.FUID
     * @param fuid the value for pdm_server..t_project_files.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:pdm_server..t_project_files.FCDATE
     * @return pdm_server..t_project_files.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:pdm_server..t_project_files.FCDATE
     * @param fcdate the value for pdm_server..t_project_files.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:pdm_server..t_project_files.FUDATE
     * @return pdm_server..t_project_files.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:pdm_server..t_project_files.FUDATE
     * @param fudate the value for pdm_server..t_project_files.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:pdm_server..t_project_files.FState
     * @return pdm_server..t_project_files.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:pdm_server..t_project_files.FState
     * @param fstate the value for pdm_server..t_project_files.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 项目ID 字段:pdm_server..t_project_files.FProjectID
     * @return pdm_server..t_project_files.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:pdm_server..t_project_files.FProjectID
     * @param fprojectid the value for pdm_server..t_project_files.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 任务ID 字段:pdm_server..t_project_files.FTaskID
     * @return pdm_server..t_project_files.FTaskID, 任务ID
     * @mbg.generated
     */
    public Long getFtaskid() {
        return ftaskid;
    }

    /**
     * 设置 任务ID 字段:pdm_server..t_project_files.FTaskID
     * @param ftaskid the value for pdm_server..t_project_files.FTaskID, 任务ID
     * @mbg.generated
     */
    public void setFtaskid(Long ftaskid) {
        this.ftaskid = ftaskid;
    }

    /**
     * 获取 类型 1-项目 2-任务 字段:pdm_server..t_project_files.FType
     * @return pdm_server..t_project_files.FType, 类型 1-项目 2-任务
     * @mbg.generated
     */
    public Integer getFtype() {
        return ftype;
    }

    /**
     * 设置 类型 1-项目 2-任务 字段:pdm_server..t_project_files.FType
     * @param ftype the value for pdm_server..t_project_files.FType, 类型 1-项目 2-任务
     * @mbg.generated
     */
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    /**
     * 获取 文件名称 字段:pdm_server..t_project_files.FFileName
     * @return pdm_server..t_project_files.FFileName, 文件名称
     * @mbg.generated
     */
    public String getFfilename() {
        return ffilename;
    }

    /**
     * 设置 文件名称 字段:pdm_server..t_project_files.FFileName
     * @param ffilename the value for pdm_server..t_project_files.FFileName, 文件名称
     * @mbg.generated
     */
    public void setFfilename(String ffilename) {
        this.ffilename = ffilename == null ? null : ffilename.trim();
    }

    /**
     * 获取 交付物类型表ID 字段:pdm_server..t_project_files.FTypeID
     * @return pdm_server..t_project_files.FTypeID, 交付物类型表ID
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 交付物类型表ID 字段:pdm_server..t_project_files.FTypeID
     * @param ftypeid the value for pdm_server..t_project_files.FTypeID, 交付物类型表ID
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 版本号 字段:pdm_server..t_project_files.FEditionNo
     * @return pdm_server..t_project_files.FEditionNo, 版本号
     * @mbg.generated
     */
    public Integer getFeditionno() {
        return feditionno;
    }

    /**
     * 设置 版本号 字段:pdm_server..t_project_files.FEditionNo
     * @param feditionno the value for pdm_server..t_project_files.FEditionNo, 版本号
     * @mbg.generated
     */
    public void setFeditionno(Integer feditionno) {
        this.feditionno = feditionno;
    }

    /**
     * 获取 版本,V1(V+FEditionNo) 字段:pdm_server..t_project_files.FEdition
     * @return pdm_server..t_project_files.FEdition, 版本,V1(V+FEditionNo)
     * @mbg.generated
     */
    public String getFedition() {
        return fedition;
    }

    /**
     * 设置 版本,V1(V+FEditionNo) 字段:pdm_server..t_project_files.FEdition
     * @param fedition the value for pdm_server..t_project_files.FEdition, 版本,V1(V+FEditionNo)
     * @mbg.generated
     */
    public void setFedition(String fedition) {
        this.fedition = fedition == null ? null : fedition.trim();
    }

    /**
     * 获取 有效版本,0-无效,1-有（只一个） 字段:pdm_server..t_project_files.FValid
     * @return pdm_server..t_project_files.FValid, 有效版本,0-无效,1-有（只一个）
     * @mbg.generated
     */
    public Integer getFvalid() {
        return fvalid;
    }

    /**
     * 设置 有效版本,0-无效,1-有（只一个） 字段:pdm_server..t_project_files.FValid
     * @param fvalid the value for pdm_server..t_project_files.FValid, 有效版本,0-无效,1-有（只一个）
     * @mbg.generated
     */
    public void setFvalid(Integer fvalid) {
        this.fvalid = fvalid;
    }

    /**
     * 获取 后缀名 字段:pdm_server..t_project_files.FFileType
     * @return pdm_server..t_project_files.FFileType, 后缀名
     * @mbg.generated
     */
    public String getFfiletype() {
        return ffiletype;
    }

    /**
     * 设置 后缀名 字段:pdm_server..t_project_files.FFileType
     * @param ffiletype the value for pdm_server..t_project_files.FFileType, 后缀名
     * @mbg.generated
     */
    public void setFfiletype(String ffiletype) {
        this.ffiletype = ffiletype == null ? null : ffiletype.trim();
    }

    /**
     * 获取 系统文件名 字段:pdm_server..t_project_files.FSysName
     * @return pdm_server..t_project_files.FSysName, 系统文件名
     * @mbg.generated
     */
    public String getFsysname() {
        return fsysname;
    }

    /**
     * 设置 系统文件名 字段:pdm_server..t_project_files.FSysName
     * @param fsysname the value for pdm_server..t_project_files.FSysName, 系统文件名
     * @mbg.generated
     */
    public void setFsysname(String fsysname) {
        this.fsysname = fsysname == null ? null : fsysname.trim();
    }

    /**
     * 获取 原始ID，默认-1 字段:pdm_server..t_project_files.FFileID
     * @return pdm_server..t_project_files.FFileID, 原始ID，默认-1
     * @mbg.generated
     */
    public Long getFfileid() {
        return ffileid;
    }

    /**
     * 设置 原始ID，默认-1 字段:pdm_server..t_project_files.FFileID
     * @param ffileid the value for pdm_server..t_project_files.FFileID, 原始ID，默认-1
     * @mbg.generated
     */
    public void setFfileid(Long ffileid) {
        this.ffileid = ffileid;
    }

    /**
     * 获取 存放地址 字段:pdm_server..t_project_files.FUrl
     * @return pdm_server..t_project_files.FUrl, 存放地址
     * @mbg.generated
     */
    public String getFurl() {
        return furl;
    }

    /**
     * 设置 存放地址 字段:pdm_server..t_project_files.FUrl
     * @param furl the value for pdm_server..t_project_files.FUrl, 存放地址
     * @mbg.generated
     */
    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    /**
     * 获取 备注 字段:pdm_server..t_project_files.FNote
     * @return pdm_server..t_project_files.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:pdm_server..t_project_files.FNote
     * @param fnote the value for pdm_server..t_project_files.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * pdm_server..t_project_files
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
        sb.append(", fprojectid=").append(fprojectid);
        sb.append(", ftaskid=").append(ftaskid);
        sb.append(", ftype=").append(ftype);
        sb.append(", ffilename=").append(ffilename);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", feditionno=").append(feditionno);
        sb.append(", fedition=").append(fedition);
        sb.append(", fvalid=").append(fvalid);
        sb.append(", ffiletype=").append(ffiletype);
        sb.append(", fsysname=").append(fsysname);
        sb.append(", ffileid=").append(ffileid);
        sb.append(", furl=").append(furl);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * pdm_server..t_project_files
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
        TProjectFiles other = (TProjectFiles) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFtaskid() == null ? other.getFtaskid() == null : this.getFtaskid().equals(other.getFtaskid()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFfilename() == null ? other.getFfilename() == null : this.getFfilename().equals(other.getFfilename()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFeditionno() == null ? other.getFeditionno() == null : this.getFeditionno().equals(other.getFeditionno()))
            && (this.getFedition() == null ? other.getFedition() == null : this.getFedition().equals(other.getFedition()))
            && (this.getFvalid() == null ? other.getFvalid() == null : this.getFvalid().equals(other.getFvalid()))
            && (this.getFfiletype() == null ? other.getFfiletype() == null : this.getFfiletype().equals(other.getFfiletype()))
            && (this.getFsysname() == null ? other.getFsysname() == null : this.getFsysname().equals(other.getFsysname()))
            && (this.getFfileid() == null ? other.getFfileid() == null : this.getFfileid().equals(other.getFfileid()))
            && (this.getFurl() == null ? other.getFurl() == null : this.getFurl().equals(other.getFurl()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * pdm_server..t_project_files
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
        result = prime * result + ((getFprojectid() == null) ? 0 : getFprojectid().hashCode());
        result = prime * result + ((getFtaskid() == null) ? 0 : getFtaskid().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFfilename() == null) ? 0 : getFfilename().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFeditionno() == null) ? 0 : getFeditionno().hashCode());
        result = prime * result + ((getFedition() == null) ? 0 : getFedition().hashCode());
        result = prime * result + ((getFvalid() == null) ? 0 : getFvalid().hashCode());
        result = prime * result + ((getFfiletype() == null) ? 0 : getFfiletype().hashCode());
        result = prime * result + ((getFsysname() == null) ? 0 : getFsysname().hashCode());
        result = prime * result + ((getFfileid() == null) ? 0 : getFfileid().hashCode());
        result = prime * result + ((getFurl() == null) ? 0 : getFurl().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}