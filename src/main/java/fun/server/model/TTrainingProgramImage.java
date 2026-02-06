package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_training_program_image
*/
@Table(name = "t_training_program_image")
public class TTrainingProgramImage implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_image.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_image.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_image.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_image.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_training_program_image.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_training_program_image.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * T_Major表FKeyID
     * 表字段 : student_capability_evaluation..t_training_program_image.FTMID
     * @mbg.generated
     */
    private Long ftmid;

    /**
     * T_Training_Program表ID
     * 表字段 : student_capability_evaluation..t_training_program_image.FTPID
     * @mbg.generated
     */
    private Long ftpid;

    /**
     * 图片名称
     * 表字段 : student_capability_evaluation..t_training_program_image.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 上传的图片路径
     * 表字段 : student_capability_evaluation..t_training_program_image.FUrl
     * @mbg.generated
     */
    private String furl;

    /**
     * 上传图片转为base64
     * 表字段 : student_capability_evaluation..t_training_program_image.FBase
     * @mbg.generated
     */
    private String fbase;

    /**
     * 图片后缀名
     * 表字段 : student_capability_evaluation..t_training_program_image.FHZ
     * @mbg.generated
     */
    private String fhz;

    /**
     * 原始文件名
     * 表字段 : student_capability_evaluation..t_training_program_image.FYSFIleName
     * @mbg.generated
     */
    private String fysfilename;

    /**
     * 系统文件名
     * 表字段 : student_capability_evaluation..t_training_program_image.FXTFIleName
     * @mbg.generated
     */
    private String fxtfilename;

    /**
     * 去掉前缀路径
     * 表字段 : student_capability_evaluation..t_training_program_image.FRelativePath1
     * @mbg.generated
     */
    private String frelativepath1;

    /**
     * 添加目录路径
     * 表字段 : student_capability_evaluation..t_training_program_image.FRelativePath2
     * @mbg.generated
     */
    private String frelativepath2;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public TTrainingProgramImage(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Long ftmid, Long ftpid, String fname, String furl, String fbase, String fhz, String fysfilename, String fxtfilename, String frelativepath1, String frelativepath2) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.ftmid = ftmid;
        this.ftpid = ftpid;
        this.fname = fname;
        this.furl = furl;
        this.fbase = fbase;
        this.fhz = fhz;
        this.fysfilename = fysfilename;
        this.fxtfilename = fxtfilename;
        this.frelativepath1 = frelativepath1;
        this.frelativepath2 = frelativepath2;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public TTrainingProgramImage() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_image.FKeyID
     * @return student_capability_evaluation..t_training_program_image.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_image.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_training_program_image.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_image.FCID
     * @return student_capability_evaluation..t_training_program_image.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_image.FCID
     * @param fcid the value for student_capability_evaluation..t_training_program_image.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_image.FUID
     * @return student_capability_evaluation..t_training_program_image.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_image.FUID
     * @param fuid the value for student_capability_evaluation..t_training_program_image.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_image.FCDATE
     * @return student_capability_evaluation..t_training_program_image.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_image.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_training_program_image.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_training_program_image.FUDATE
     * @return student_capability_evaluation..t_training_program_image.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_training_program_image.FUDATE
     * @param fudate the value for student_capability_evaluation..t_training_program_image.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_image.FState
     * @return student_capability_evaluation..t_training_program_image.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_training_program_image.FState
     * @param fstate the value for student_capability_evaluation..t_training_program_image.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_image.FTMID
     * @return student_capability_evaluation..t_training_program_image.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public Long getFtmid() {
        return ftmid;
    }

    /**
     * 设置 T_Major表FKeyID 字段:student_capability_evaluation..t_training_program_image.FTMID
     * @param ftmid the value for student_capability_evaluation..t_training_program_image.FTMID, T_Major表FKeyID
     * @mbg.generated
     */
    public void setFtmid(Long ftmid) {
        this.ftmid = ftmid;
    }

    /**
     * 获取 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_image.FTPID
     * @return student_capability_evaluation..t_training_program_image.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public Long getFtpid() {
        return ftpid;
    }

    /**
     * 设置 T_Training_Program表ID 字段:student_capability_evaluation..t_training_program_image.FTPID
     * @param ftpid the value for student_capability_evaluation..t_training_program_image.FTPID, T_Training_Program表ID
     * @mbg.generated
     */
    public void setFtpid(Long ftpid) {
        this.ftpid = ftpid;
    }

    /**
     * 获取 图片名称 字段:student_capability_evaluation..t_training_program_image.FName
     * @return student_capability_evaluation..t_training_program_image.FName, 图片名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 图片名称 字段:student_capability_evaluation..t_training_program_image.FName
     * @param fname the value for student_capability_evaluation..t_training_program_image.FName, 图片名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 上传的图片路径 字段:student_capability_evaluation..t_training_program_image.FUrl
     * @return student_capability_evaluation..t_training_program_image.FUrl, 上传的图片路径
     * @mbg.generated
     */
    public String getFurl() {
        return furl;
    }

    /**
     * 设置 上传的图片路径 字段:student_capability_evaluation..t_training_program_image.FUrl
     * @param furl the value for student_capability_evaluation..t_training_program_image.FUrl, 上传的图片路径
     * @mbg.generated
     */
    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    /**
     * 获取 上传图片转为base64 字段:student_capability_evaluation..t_training_program_image.FBase
     * @return student_capability_evaluation..t_training_program_image.FBase, 上传图片转为base64
     * @mbg.generated
     */
    public String getFbase() {
        return fbase;
    }

    /**
     * 设置 上传图片转为base64 字段:student_capability_evaluation..t_training_program_image.FBase
     * @param fbase the value for student_capability_evaluation..t_training_program_image.FBase, 上传图片转为base64
     * @mbg.generated
     */
    public void setFbase(String fbase) {
        this.fbase = fbase == null ? null : fbase.trim();
    }

    /**
     * 获取 图片后缀名 字段:student_capability_evaluation..t_training_program_image.FHZ
     * @return student_capability_evaluation..t_training_program_image.FHZ, 图片后缀名
     * @mbg.generated
     */
    public String getFhz() {
        return fhz;
    }

    /**
     * 设置 图片后缀名 字段:student_capability_evaluation..t_training_program_image.FHZ
     * @param fhz the value for student_capability_evaluation..t_training_program_image.FHZ, 图片后缀名
     * @mbg.generated
     */
    public void setFhz(String fhz) {
        this.fhz = fhz == null ? null : fhz.trim();
    }

    /**
     * 获取 原始文件名 字段:student_capability_evaluation..t_training_program_image.FYSFIleName
     * @return student_capability_evaluation..t_training_program_image.FYSFIleName, 原始文件名
     * @mbg.generated
     */
    public String getFysfilename() {
        return fysfilename;
    }

    /**
     * 设置 原始文件名 字段:student_capability_evaluation..t_training_program_image.FYSFIleName
     * @param fysfilename the value for student_capability_evaluation..t_training_program_image.FYSFIleName, 原始文件名
     * @mbg.generated
     */
    public void setFysfilename(String fysfilename) {
        this.fysfilename = fysfilename == null ? null : fysfilename.trim();
    }

    /**
     * 获取 系统文件名 字段:student_capability_evaluation..t_training_program_image.FXTFIleName
     * @return student_capability_evaluation..t_training_program_image.FXTFIleName, 系统文件名
     * @mbg.generated
     */
    public String getFxtfilename() {
        return fxtfilename;
    }

    /**
     * 设置 系统文件名 字段:student_capability_evaluation..t_training_program_image.FXTFIleName
     * @param fxtfilename the value for student_capability_evaluation..t_training_program_image.FXTFIleName, 系统文件名
     * @mbg.generated
     */
    public void setFxtfilename(String fxtfilename) {
        this.fxtfilename = fxtfilename == null ? null : fxtfilename.trim();
    }

    /**
     * 获取 去掉前缀路径 字段:student_capability_evaluation..t_training_program_image.FRelativePath1
     * @return student_capability_evaluation..t_training_program_image.FRelativePath1, 去掉前缀路径
     * @mbg.generated
     */
    public String getFrelativepath1() {
        return frelativepath1;
    }

    /**
     * 设置 去掉前缀路径 字段:student_capability_evaluation..t_training_program_image.FRelativePath1
     * @param frelativepath1 the value for student_capability_evaluation..t_training_program_image.FRelativePath1, 去掉前缀路径
     * @mbg.generated
     */
    public void setFrelativepath1(String frelativepath1) {
        this.frelativepath1 = frelativepath1 == null ? null : frelativepath1.trim();
    }

    /**
     * 获取 添加目录路径 字段:student_capability_evaluation..t_training_program_image.FRelativePath2
     * @return student_capability_evaluation..t_training_program_image.FRelativePath2, 添加目录路径
     * @mbg.generated
     */
    public String getFrelativepath2() {
        return frelativepath2;
    }

    /**
     * 设置 添加目录路径 字段:student_capability_evaluation..t_training_program_image.FRelativePath2
     * @param frelativepath2 the value for student_capability_evaluation..t_training_program_image.FRelativePath2, 添加目录路径
     * @mbg.generated
     */
    public void setFrelativepath2(String frelativepath2) {
        this.frelativepath2 = frelativepath2 == null ? null : frelativepath2.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_training_program_image
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
        sb.append(", fname=").append(fname);
        sb.append(", furl=").append(furl);
        sb.append(", fbase=").append(fbase);
        sb.append(", fhz=").append(fhz);
        sb.append(", fysfilename=").append(fysfilename);
        sb.append(", fxtfilename=").append(fxtfilename);
        sb.append(", frelativepath1=").append(frelativepath1);
        sb.append(", frelativepath2=").append(frelativepath2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_training_program_image
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
        TTrainingProgramImage other = (TTrainingProgramImage) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFtmid() == null ? other.getFtmid() == null : this.getFtmid().equals(other.getFtmid()))
            && (this.getFtpid() == null ? other.getFtpid() == null : this.getFtpid().equals(other.getFtpid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFurl() == null ? other.getFurl() == null : this.getFurl().equals(other.getFurl()))
            && (this.getFbase() == null ? other.getFbase() == null : this.getFbase().equals(other.getFbase()))
            && (this.getFhz() == null ? other.getFhz() == null : this.getFhz().equals(other.getFhz()))
            && (this.getFysfilename() == null ? other.getFysfilename() == null : this.getFysfilename().equals(other.getFysfilename()))
            && (this.getFxtfilename() == null ? other.getFxtfilename() == null : this.getFxtfilename().equals(other.getFxtfilename()))
            && (this.getFrelativepath1() == null ? other.getFrelativepath1() == null : this.getFrelativepath1().equals(other.getFrelativepath1()))
            && (this.getFrelativepath2() == null ? other.getFrelativepath2() == null : this.getFrelativepath2().equals(other.getFrelativepath2()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_training_program_image
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
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFurl() == null) ? 0 : getFurl().hashCode());
        result = prime * result + ((getFbase() == null) ? 0 : getFbase().hashCode());
        result = prime * result + ((getFhz() == null) ? 0 : getFhz().hashCode());
        result = prime * result + ((getFysfilename() == null) ? 0 : getFysfilename().hashCode());
        result = prime * result + ((getFxtfilename() == null) ? 0 : getFxtfilename().hashCode());
        result = prime * result + ((getFrelativepath1() == null) ? 0 : getFrelativepath1().hashCode());
        result = prime * result + ((getFrelativepath2() == null) ? 0 : getFrelativepath2().hashCode());
        return result;
    }
}