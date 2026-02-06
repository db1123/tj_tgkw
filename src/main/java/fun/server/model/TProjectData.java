package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_project_data")
public class TProjectData implements Serializable {
    /**
     * 
     * 表字段 : t_project_data.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : t_project_data.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : t_project_data.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : t_project_data.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : t_project_data.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : t_project_data.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 项目ID
     * 表字段 : t_project_data.FProjectID
     * @mbg.generated
     */
    private Long fprojectid;

    /**
     * 资料名称
     * 表字段 : t_project_data.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 资料类型
     * 表字段 : t_project_data.FTypeID
     * @mbg.generated
     */
    private Long ftypeid;

    /**
     * 来源
     * 表字段 : t_project_data.FFrom
     * @mbg.generated
     */
    private String ffrom;

    /**
     * 类型
     * 表字段 : t_project_data.FType
     * @mbg.generated
     */
    private String ftype;

    /**
     * 存放地址
     * 表字段 : t_project_data.FUrl
     * @mbg.generated
     */
    private String furl;

    /**
     * 系统存放名
     * 表字段 : t_project_data.FSysName
     * @mbg.generated
     */
    private String fsysname;

    /**
     * 文件名
     * 表字段 : t_project_data.FFileName
     * @mbg.generated
     */
    private String ffilename;

    /**
     * 浏览次数
     * 表字段 : t_project_data.FBrowseNum
     * @mbg.generated
     */
    private Integer fbrowsenum;

    /**
     * 下载次数
     * 表字段 : t_project_data.FDownloadNum
     * @mbg.generated
     */
    private Integer fdownloadnum;

    /**
     * 备注
     * 表字段 : t_project_data.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * t_project_data
     * @mbg.generated
     */
    public TProjectData(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fprojectid, String fname, Long ftypeid, String ffrom, String ftype, String furl, String fsysname, String ffilename, Integer fbrowsenum, Integer fdownloadnum, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fprojectid = fprojectid;
        this.fname = fname;
        this.ftypeid = ftypeid;
        this.ffrom = ffrom;
        this.ftype = ftype;
        this.furl = furl;
        this.fsysname = fsysname;
        this.ffilename = ffilename;
        this.fbrowsenum = fbrowsenum;
        this.fdownloadnum = fdownloadnum;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * t_project_data
     * @mbg.generated
     */
    public TProjectData() {
        super();
    }

    /**
     * 获取  字段:t_project_data.FKeyID
     * @return t_project_data.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:t_project_data.FKeyID
     * @param fkeyid the value for t_project_data.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:t_project_data.FCID
     * @return t_project_data.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:t_project_data.FCID
     * @param fcid the value for t_project_data.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:t_project_data.FUID
     * @return t_project_data.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:t_project_data.FUID
     * @param fuid the value for t_project_data.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:t_project_data.FCDATE
     * @return t_project_data.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:t_project_data.FCDATE
     * @param fcdate the value for t_project_data.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:t_project_data.FUDATE
     * @return t_project_data.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:t_project_data.FUDATE
     * @param fudate the value for t_project_data.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:t_project_data.FState
     * @return t_project_data.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:t_project_data.FState
     * @param fstate the value for t_project_data.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 项目ID 字段:t_project_data.FProjectID
     * @return t_project_data.FProjectID, 项目ID
     * @mbg.generated
     */
    public Long getFprojectid() {
        return fprojectid;
    }

    /**
     * 设置 项目ID 字段:t_project_data.FProjectID
     * @param fprojectid the value for t_project_data.FProjectID, 项目ID
     * @mbg.generated
     */
    public void setFprojectid(Long fprojectid) {
        this.fprojectid = fprojectid;
    }

    /**
     * 获取 资料名称 字段:t_project_data.FName
     * @return t_project_data.FName, 资料名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 资料名称 字段:t_project_data.FName
     * @param fname the value for t_project_data.FName, 资料名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 资料类型 字段:t_project_data.FTypeID
     * @return t_project_data.FTypeID, 资料类型
     * @mbg.generated
     */
    public Long getFtypeid() {
        return ftypeid;
    }

    /**
     * 设置 资料类型 字段:t_project_data.FTypeID
     * @param ftypeid the value for t_project_data.FTypeID, 资料类型
     * @mbg.generated
     */
    public void setFtypeid(Long ftypeid) {
        this.ftypeid = ftypeid;
    }

    /**
     * 获取 来源 字段:t_project_data.FFrom
     * @return t_project_data.FFrom, 来源
     * @mbg.generated
     */
    public String getFfrom() {
        return ffrom;
    }

    /**
     * 设置 来源 字段:t_project_data.FFrom
     * @param ffrom the value for t_project_data.FFrom, 来源
     * @mbg.generated
     */
    public void setFfrom(String ffrom) {
        this.ffrom = ffrom == null ? null : ffrom.trim();
    }

    /**
     * 获取 类型 字段:t_project_data.FType
     * @return t_project_data.FType, 类型
     * @mbg.generated
     */
    public String getFtype() {
        return ftype;
    }

    /**
     * 设置 类型 字段:t_project_data.FType
     * @param ftype the value for t_project_data.FType, 类型
     * @mbg.generated
     */
    public void setFtype(String ftype) {
        this.ftype = ftype == null ? null : ftype.trim();
    }

    /**
     * 获取 存放地址 字段:t_project_data.FUrl
     * @return t_project_data.FUrl, 存放地址
     * @mbg.generated
     */
    public String getFurl() {
        return furl;
    }

    /**
     * 设置 存放地址 字段:t_project_data.FUrl
     * @param furl the value for t_project_data.FUrl, 存放地址
     * @mbg.generated
     */
    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    /**
     * 获取 系统存放名 字段:t_project_data.FSysName
     * @return t_project_data.FSysName, 系统存放名
     * @mbg.generated
     */
    public String getFsysname() {
        return fsysname;
    }

    /**
     * 设置 系统存放名 字段:t_project_data.FSysName
     * @param fsysname the value for t_project_data.FSysName, 系统存放名
     * @mbg.generated
     */
    public void setFsysname(String fsysname) {
        this.fsysname = fsysname == null ? null : fsysname.trim();
    }

    /**
     * 获取 文件名 字段:t_project_data.FFileName
     * @return t_project_data.FFileName, 文件名
     * @mbg.generated
     */
    public String getFfilename() {
        return ffilename;
    }

    /**
     * 设置 文件名 字段:t_project_data.FFileName
     * @param ffilename the value for t_project_data.FFileName, 文件名
     * @mbg.generated
     */
    public void setFfilename(String ffilename) {
        this.ffilename = ffilename == null ? null : ffilename.trim();
    }

    /**
     * 获取 浏览次数 字段:t_project_data.FBrowseNum
     * @return t_project_data.FBrowseNum, 浏览次数
     * @mbg.generated
     */
    public Integer getFbrowsenum() {
        return fbrowsenum;
    }

    /**
     * 设置 浏览次数 字段:t_project_data.FBrowseNum
     * @param fbrowsenum the value for t_project_data.FBrowseNum, 浏览次数
     * @mbg.generated
     */
    public void setFbrowsenum(Integer fbrowsenum) {
        this.fbrowsenum = fbrowsenum;
    }

    /**
     * 获取 下载次数 字段:t_project_data.FDownloadNum
     * @return t_project_data.FDownloadNum, 下载次数
     * @mbg.generated
     */
    public Integer getFdownloadnum() {
        return fdownloadnum;
    }

    /**
     * 设置 下载次数 字段:t_project_data.FDownloadNum
     * @param fdownloadnum the value for t_project_data.FDownloadNum, 下载次数
     * @mbg.generated
     */
    public void setFdownloadnum(Integer fdownloadnum) {
        this.fdownloadnum = fdownloadnum;
    }

    /**
     * 获取 备注 字段:t_project_data.FNote
     * @return t_project_data.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:t_project_data.FNote
     * @param fnote the value for t_project_data.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * t_project_data
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
        sb.append(", fname=").append(fname);
        sb.append(", ftypeid=").append(ftypeid);
        sb.append(", ffrom=").append(ffrom);
        sb.append(", ftype=").append(ftype);
        sb.append(", furl=").append(furl);
        sb.append(", fsysname=").append(fsysname);
        sb.append(", ffilename=").append(ffilename);
        sb.append(", fbrowsenum=").append(fbrowsenum);
        sb.append(", fdownloadnum=").append(fdownloadnum);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * t_project_data
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
        TProjectData other = (TProjectData) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFprojectid() == null ? other.getFprojectid() == null : this.getFprojectid().equals(other.getFprojectid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtypeid() == null ? other.getFtypeid() == null : this.getFtypeid().equals(other.getFtypeid()))
            && (this.getFfrom() == null ? other.getFfrom() == null : this.getFfrom().equals(other.getFfrom()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFurl() == null ? other.getFurl() == null : this.getFurl().equals(other.getFurl()))
            && (this.getFsysname() == null ? other.getFsysname() == null : this.getFsysname().equals(other.getFsysname()))
            && (this.getFfilename() == null ? other.getFfilename() == null : this.getFfilename().equals(other.getFfilename()))
            && (this.getFbrowsenum() == null ? other.getFbrowsenum() == null : this.getFbrowsenum().equals(other.getFbrowsenum()))
            && (this.getFdownloadnum() == null ? other.getFdownloadnum() == null : this.getFdownloadnum().equals(other.getFdownloadnum()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * t_project_data
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFtypeid() == null) ? 0 : getFtypeid().hashCode());
        result = prime * result + ((getFfrom() == null) ? 0 : getFfrom().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFurl() == null) ? 0 : getFurl().hashCode());
        result = prime * result + ((getFsysname() == null) ? 0 : getFsysname().hashCode());
        result = prime * result + ((getFfilename() == null) ? 0 : getFfilename().hashCode());
        result = prime * result + ((getFbrowsenum() == null) ? 0 : getFbrowsenum().hashCode());
        result = prime * result + ((getFdownloadnum() == null) ? 0 : getFdownloadnum().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}