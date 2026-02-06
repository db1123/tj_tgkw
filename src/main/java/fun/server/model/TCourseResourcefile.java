package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_course_resourcefile
 * 表注释：课程资源表
*/
@Table(name = "t_course_resourcefile")
public class TCourseResourcefile implements Serializable {
    /**
     * 
     * 表字段 : teaching_diversity..t_course_resourcefile.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_resourcefile.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_resourcefile.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_resourcefile.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : teaching_diversity..t_course_resourcefile.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : teaching_diversity..t_course_resourcefile.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 课程ID
     * 表字段 : teaching_diversity..t_course_resourcefile.FCourseID
     * @mbg.generated
     */
    private Long fcourseid;

    /**
     * 资源文件名称
     * 表字段 : teaching_diversity..t_course_resourcefile.FFName
     * @mbg.generated
     */
    private String ffname;

    /**
     * 资源文件文件路径
     * 表字段 : teaching_diversity..t_course_resourcefile.FFUrl
     * @mbg.generated
     */
    private String ffurl;

    /**
     * 文件后缀名
     * 表字段 : teaching_diversity..t_course_resourcefile.FHZ
     * @mbg.generated
     */
    private String fhz;

    /**
     * 原始文件名
     * 表字段 : teaching_diversity..t_course_resourcefile.FYSFIleName
     * @mbg.generated
     */
    private String fysfilename;

    /**
     * 系统文件名
     * 表字段 : teaching_diversity..t_course_resourcefile.FXTFIleName
     * @mbg.generated
     */
    private String fxtfilename;

    /**
     * 去掉前缀路径
     * 表字段 : teaching_diversity..t_course_resourcefile.FRelativePath1
     * @mbg.generated
     */
    private String frelativepath1;

    /**
     * 添加目录路径
     * 表字段 : teaching_diversity..t_course_resourcefile.FRelativePath2
     * @mbg.generated
     */
    private String frelativepath2;

    /**
     * solr处理状态，0：未处理；1：处理中；2：处理完成；9：处理错误
     * 表字段 : teaching_diversity..t_course_resourcefile.FSolrState
     * @mbg.generated
     */
    private Integer fsolrstate;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * teaching_diversity..t_course_resourcefile
     * @mbg.generated
     */
    public TCourseResourcefile(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long fcourseid, String ffname, String ffurl, String fhz, String fysfilename, String fxtfilename, String frelativepath1, String frelativepath2, Integer fsolrstate) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fcourseid = fcourseid;
        this.ffname = ffname;
        this.ffurl = ffurl;
        this.fhz = fhz;
        this.fysfilename = fysfilename;
        this.fxtfilename = fxtfilename;
        this.frelativepath1 = frelativepath1;
        this.frelativepath2 = frelativepath2;
        this.fsolrstate = fsolrstate;
    }

    /**
     * 构造查询条件
     * teaching_diversity..t_course_resourcefile
     * @mbg.generated
     */
    public TCourseResourcefile() {
        super();
    }

    /**
     * 获取  字段:teaching_diversity..t_course_resourcefile.FKeyID
     * @return teaching_diversity..t_course_resourcefile.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_resourcefile.FKeyID
     * @param fkeyid the value for teaching_diversity..t_course_resourcefile.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_resourcefile.FCID
     * @return teaching_diversity..t_course_resourcefile.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_resourcefile.FCID
     * @param fcid the value for teaching_diversity..t_course_resourcefile.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_resourcefile.FUID
     * @return teaching_diversity..t_course_resourcefile.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_resourcefile.FUID
     * @param fuid the value for teaching_diversity..t_course_resourcefile.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_resourcefile.FCDATE
     * @return teaching_diversity..t_course_resourcefile.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_resourcefile.FCDATE
     * @param fcdate the value for teaching_diversity..t_course_resourcefile.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:teaching_diversity..t_course_resourcefile.FUDATE
     * @return teaching_diversity..t_course_resourcefile.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:teaching_diversity..t_course_resourcefile.FUDATE
     * @param fudate the value for teaching_diversity..t_course_resourcefile.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:teaching_diversity..t_course_resourcefile.FState
     * @return teaching_diversity..t_course_resourcefile.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:teaching_diversity..t_course_resourcefile.FState
     * @param fstate the value for teaching_diversity..t_course_resourcefile.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 课程ID 字段:teaching_diversity..t_course_resourcefile.FCourseID
     * @return teaching_diversity..t_course_resourcefile.FCourseID, 课程ID
     * @mbg.generated
     */
    public Long getFcourseid() {
        return fcourseid;
    }

    /**
     * 设置 课程ID 字段:teaching_diversity..t_course_resourcefile.FCourseID
     * @param fcourseid the value for teaching_diversity..t_course_resourcefile.FCourseID, 课程ID
     * @mbg.generated
     */
    public void setFcourseid(Long fcourseid) {
        this.fcourseid = fcourseid;
    }

    /**
     * 获取 资源文件名称 字段:teaching_diversity..t_course_resourcefile.FFName
     * @return teaching_diversity..t_course_resourcefile.FFName, 资源文件名称
     * @mbg.generated
     */
    public String getFfname() {
        return ffname;
    }

    /**
     * 设置 资源文件名称 字段:teaching_diversity..t_course_resourcefile.FFName
     * @param ffname the value for teaching_diversity..t_course_resourcefile.FFName, 资源文件名称
     * @mbg.generated
     */
    public void setFfname(String ffname) {
        this.ffname = ffname == null ? null : ffname.trim();
    }

    /**
     * 获取 资源文件文件路径 字段:teaching_diversity..t_course_resourcefile.FFUrl
     * @return teaching_diversity..t_course_resourcefile.FFUrl, 资源文件文件路径
     * @mbg.generated
     */
    public String getFfurl() {
        return ffurl;
    }

    /**
     * 设置 资源文件文件路径 字段:teaching_diversity..t_course_resourcefile.FFUrl
     * @param ffurl the value for teaching_diversity..t_course_resourcefile.FFUrl, 资源文件文件路径
     * @mbg.generated
     */
    public void setFfurl(String ffurl) {
        this.ffurl = ffurl == null ? null : ffurl.trim();
    }

    /**
     * 获取 文件后缀名 字段:teaching_diversity..t_course_resourcefile.FHZ
     * @return teaching_diversity..t_course_resourcefile.FHZ, 文件后缀名
     * @mbg.generated
     */
    public String getFhz() {
        return fhz;
    }

    /**
     * 设置 文件后缀名 字段:teaching_diversity..t_course_resourcefile.FHZ
     * @param fhz the value for teaching_diversity..t_course_resourcefile.FHZ, 文件后缀名
     * @mbg.generated
     */
    public void setFhz(String fhz) {
        this.fhz = fhz == null ? null : fhz.trim();
    }

    /**
     * 获取 原始文件名 字段:teaching_diversity..t_course_resourcefile.FYSFIleName
     * @return teaching_diversity..t_course_resourcefile.FYSFIleName, 原始文件名
     * @mbg.generated
     */
    public String getFysfilename() {
        return fysfilename;
    }

    /**
     * 设置 原始文件名 字段:teaching_diversity..t_course_resourcefile.FYSFIleName
     * @param fysfilename the value for teaching_diversity..t_course_resourcefile.FYSFIleName, 原始文件名
     * @mbg.generated
     */
    public void setFysfilename(String fysfilename) {
        this.fysfilename = fysfilename == null ? null : fysfilename.trim();
    }

    /**
     * 获取 系统文件名 字段:teaching_diversity..t_course_resourcefile.FXTFIleName
     * @return teaching_diversity..t_course_resourcefile.FXTFIleName, 系统文件名
     * @mbg.generated
     */
    public String getFxtfilename() {
        return fxtfilename;
    }

    /**
     * 设置 系统文件名 字段:teaching_diversity..t_course_resourcefile.FXTFIleName
     * @param fxtfilename the value for teaching_diversity..t_course_resourcefile.FXTFIleName, 系统文件名
     * @mbg.generated
     */
    public void setFxtfilename(String fxtfilename) {
        this.fxtfilename = fxtfilename == null ? null : fxtfilename.trim();
    }

    /**
     * 获取 去掉前缀路径 字段:teaching_diversity..t_course_resourcefile.FRelativePath1
     * @return teaching_diversity..t_course_resourcefile.FRelativePath1, 去掉前缀路径
     * @mbg.generated
     */
    public String getFrelativepath1() {
        return frelativepath1;
    }

    /**
     * 设置 去掉前缀路径 字段:teaching_diversity..t_course_resourcefile.FRelativePath1
     * @param frelativepath1 the value for teaching_diversity..t_course_resourcefile.FRelativePath1, 去掉前缀路径
     * @mbg.generated
     */
    public void setFrelativepath1(String frelativepath1) {
        this.frelativepath1 = frelativepath1 == null ? null : frelativepath1.trim();
    }

    /**
     * 获取 添加目录路径 字段:teaching_diversity..t_course_resourcefile.FRelativePath2
     * @return teaching_diversity..t_course_resourcefile.FRelativePath2, 添加目录路径
     * @mbg.generated
     */
    public String getFrelativepath2() {
        return frelativepath2;
    }

    /**
     * 设置 添加目录路径 字段:teaching_diversity..t_course_resourcefile.FRelativePath2
     * @param frelativepath2 the value for teaching_diversity..t_course_resourcefile.FRelativePath2, 添加目录路径
     * @mbg.generated
     */
    public void setFrelativepath2(String frelativepath2) {
        this.frelativepath2 = frelativepath2 == null ? null : frelativepath2.trim();
    }

    /**
     * 获取 solr处理状态，0：未处理；1：处理中；2：处理完成；9：处理错误 字段:teaching_diversity..t_course_resourcefile.FSolrState
     * @return teaching_diversity..t_course_resourcefile.FSolrState, solr处理状态，0：未处理；1：处理中；2：处理完成；9：处理错误
     * @mbg.generated
     */
    public Integer getFsolrstate() {
        return fsolrstate;
    }

    /**
     * 设置 solr处理状态，0：未处理；1：处理中；2：处理完成；9：处理错误 字段:teaching_diversity..t_course_resourcefile.FSolrState
     * @param fsolrstate the value for teaching_diversity..t_course_resourcefile.FSolrState, solr处理状态，0：未处理；1：处理中；2：处理完成；9：处理错误
     * @mbg.generated
     */
    public void setFsolrstate(Integer fsolrstate) {
        this.fsolrstate = fsolrstate;
    }

    /**
     * 实例输出为字符串
     * teaching_diversity..t_course_resourcefile
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
        sb.append(", fcourseid=").append(fcourseid);
        sb.append(", ffname=").append(ffname);
        sb.append(", ffurl=").append(ffurl);
        sb.append(", fhz=").append(fhz);
        sb.append(", fysfilename=").append(fysfilename);
        sb.append(", fxtfilename=").append(fxtfilename);
        sb.append(", frelativepath1=").append(frelativepath1);
        sb.append(", frelativepath2=").append(frelativepath2);
        sb.append(", fsolrstate=").append(fsolrstate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * teaching_diversity..t_course_resourcefile
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
        TCourseResourcefile other = (TCourseResourcefile) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFcourseid() == null ? other.getFcourseid() == null : this.getFcourseid().equals(other.getFcourseid()))
            && (this.getFfname() == null ? other.getFfname() == null : this.getFfname().equals(other.getFfname()))
            && (this.getFfurl() == null ? other.getFfurl() == null : this.getFfurl().equals(other.getFfurl()))
            && (this.getFhz() == null ? other.getFhz() == null : this.getFhz().equals(other.getFhz()))
            && (this.getFysfilename() == null ? other.getFysfilename() == null : this.getFysfilename().equals(other.getFysfilename()))
            && (this.getFxtfilename() == null ? other.getFxtfilename() == null : this.getFxtfilename().equals(other.getFxtfilename()))
            && (this.getFrelativepath1() == null ? other.getFrelativepath1() == null : this.getFrelativepath1().equals(other.getFrelativepath1()))
            && (this.getFrelativepath2() == null ? other.getFrelativepath2() == null : this.getFrelativepath2().equals(other.getFrelativepath2()))
            && (this.getFsolrstate() == null ? other.getFsolrstate() == null : this.getFsolrstate().equals(other.getFsolrstate()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * teaching_diversity..t_course_resourcefile
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
        result = prime * result + ((getFcourseid() == null) ? 0 : getFcourseid().hashCode());
        result = prime * result + ((getFfname() == null) ? 0 : getFfname().hashCode());
        result = prime * result + ((getFfurl() == null) ? 0 : getFfurl().hashCode());
        result = prime * result + ((getFhz() == null) ? 0 : getFhz().hashCode());
        result = prime * result + ((getFysfilename() == null) ? 0 : getFysfilename().hashCode());
        result = prime * result + ((getFxtfilename() == null) ? 0 : getFxtfilename().hashCode());
        result = prime * result + ((getFrelativepath1() == null) ? 0 : getFrelativepath1().hashCode());
        result = prime * result + ((getFrelativepath2() == null) ? 0 : getFrelativepath2().hashCode());
        result = prime * result + ((getFsolrstate() == null) ? 0 : getFsolrstate().hashCode());
        return result;
    }
}