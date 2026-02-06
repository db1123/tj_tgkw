package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_hire
*/
@Table(name = "t_hire")
public class THire implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_hire.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 状态：0-编辑，1-发布、2-完成
     * 表字段 : student_capability_evaluation..t_hire.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 发布时间
     * 表字段 : student_capability_evaluation..t_hire.FDate
     * @mbg.generated
     */
    private Date fdate;

    /**
     * 企业ID，T_Enterprise
     * 表字段 : student_capability_evaluation..t_hire.FEnterpriseID
     * @mbg.generated
     */
    private Long fenterpriseid;

    /**
     * 招聘职位名称
     * 表字段 : student_capability_evaluation..t_hire.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 招聘人数
     * 表字段 : student_capability_evaluation..t_hire.FNum
     * @mbg.generated
     */
    private Integer fnum;

    /**
     * 薪酬，如4000~5000元、面议
     * 表字段 : student_capability_evaluation..t_hire.FWages
     * @mbg.generated
     */
    private String fwages;

    /**
     * 福利，如包吃包住
     * 表字段 : student_capability_evaluation..t_hire.FBenefit
     * @mbg.generated
     */
    private String fbenefit;

    /**
     * 工作时间，如5天/周
     * 表字段 : student_capability_evaluation..t_hire.FWorkDate
     * @mbg.generated
     */
    private String fworkdate;

    /**
     * 工作地点
     * 表字段 : student_capability_evaluation..t_hire.FAddr
     * @mbg.generated
     */
    private String faddr;

    /**
     * 招聘描述
     * 表字段 : student_capability_evaluation..t_hire.FCon
     * @mbg.generated
     */
    private String fcon;

    /**
     * 招聘要求（自然语言解析匹配能力）
     * 表字段 : student_capability_evaluation..t_hire.FAsk
     * @mbg.generated
     */
    private String fask;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public THire(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Date fdate, Long fenterpriseid, String fname, Integer fnum, String fwages, String fbenefit, String fworkdate, String faddr, String fcon, String fask) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fdate = fdate;
        this.fenterpriseid = fenterpriseid;
        this.fname = fname;
        this.fnum = fnum;
        this.fwages = fwages;
        this.fbenefit = fbenefit;
        this.fworkdate = fworkdate;
        this.faddr = faddr;
        this.fcon = fcon;
        this.fask = fask;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public THire() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire.FKeyID
     * @return student_capability_evaluation..t_hire.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_hire.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire.FCID
     * @return student_capability_evaluation..t_hire.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire.FCID
     * @param fcid the value for student_capability_evaluation..t_hire.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire.FUID
     * @return student_capability_evaluation..t_hire.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire.FUID
     * @param fuid the value for student_capability_evaluation..t_hire.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire.FCDATE
     * @return student_capability_evaluation..t_hire.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_hire.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_hire.FUDATE
     * @return student_capability_evaluation..t_hire.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_hire.FUDATE
     * @param fudate the value for student_capability_evaluation..t_hire.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 状态：0-编辑，1-发布、2-完成 字段:student_capability_evaluation..t_hire.FState
     * @return student_capability_evaluation..t_hire.FState, 状态：0-编辑，1-发布、2-完成
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 状态：0-编辑，1-发布、2-完成 字段:student_capability_evaluation..t_hire.FState
     * @param fstate the value for student_capability_evaluation..t_hire.FState, 状态：0-编辑，1-发布、2-完成
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 发布时间 字段:student_capability_evaluation..t_hire.FDate
     * @return student_capability_evaluation..t_hire.FDate, 发布时间
     * @mbg.generated
     */
    public Date getFdate() {
        return fdate;
    }

    /**
     * 设置 发布时间 字段:student_capability_evaluation..t_hire.FDate
     * @param fdate the value for student_capability_evaluation..t_hire.FDate, 发布时间
     * @mbg.generated
     */
    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    /**
     * 获取 企业ID，T_Enterprise 字段:student_capability_evaluation..t_hire.FEnterpriseID
     * @return student_capability_evaluation..t_hire.FEnterpriseID, 企业ID，T_Enterprise
     * @mbg.generated
     */
    public Long getFenterpriseid() {
        return fenterpriseid;
    }

    /**
     * 设置 企业ID，T_Enterprise 字段:student_capability_evaluation..t_hire.FEnterpriseID
     * @param fenterpriseid the value for student_capability_evaluation..t_hire.FEnterpriseID, 企业ID，T_Enterprise
     * @mbg.generated
     */
    public void setFenterpriseid(Long fenterpriseid) {
        this.fenterpriseid = fenterpriseid;
    }

    /**
     * 获取 招聘职位名称 字段:student_capability_evaluation..t_hire.FName
     * @return student_capability_evaluation..t_hire.FName, 招聘职位名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 招聘职位名称 字段:student_capability_evaluation..t_hire.FName
     * @param fname the value for student_capability_evaluation..t_hire.FName, 招聘职位名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 招聘人数 字段:student_capability_evaluation..t_hire.FNum
     * @return student_capability_evaluation..t_hire.FNum, 招聘人数
     * @mbg.generated
     */
    public Integer getFnum() {
        return fnum;
    }

    /**
     * 设置 招聘人数 字段:student_capability_evaluation..t_hire.FNum
     * @param fnum the value for student_capability_evaluation..t_hire.FNum, 招聘人数
     * @mbg.generated
     */
    public void setFnum(Integer fnum) {
        this.fnum = fnum;
    }

    /**
     * 获取 薪酬，如4000~5000元、面议 字段:student_capability_evaluation..t_hire.FWages
     * @return student_capability_evaluation..t_hire.FWages, 薪酬，如4000~5000元、面议
     * @mbg.generated
     */
    public String getFwages() {
        return fwages;
    }

    /**
     * 设置 薪酬，如4000~5000元、面议 字段:student_capability_evaluation..t_hire.FWages
     * @param fwages the value for student_capability_evaluation..t_hire.FWages, 薪酬，如4000~5000元、面议
     * @mbg.generated
     */
    public void setFwages(String fwages) {
        this.fwages = fwages == null ? null : fwages.trim();
    }

    /**
     * 获取 福利，如包吃包住 字段:student_capability_evaluation..t_hire.FBenefit
     * @return student_capability_evaluation..t_hire.FBenefit, 福利，如包吃包住
     * @mbg.generated
     */
    public String getFbenefit() {
        return fbenefit;
    }

    /**
     * 设置 福利，如包吃包住 字段:student_capability_evaluation..t_hire.FBenefit
     * @param fbenefit the value for student_capability_evaluation..t_hire.FBenefit, 福利，如包吃包住
     * @mbg.generated
     */
    public void setFbenefit(String fbenefit) {
        this.fbenefit = fbenefit == null ? null : fbenefit.trim();
    }

    /**
     * 获取 工作时间，如5天/周 字段:student_capability_evaluation..t_hire.FWorkDate
     * @return student_capability_evaluation..t_hire.FWorkDate, 工作时间，如5天/周
     * @mbg.generated
     */
    public String getFworkdate() {
        return fworkdate;
    }

    /**
     * 设置 工作时间，如5天/周 字段:student_capability_evaluation..t_hire.FWorkDate
     * @param fworkdate the value for student_capability_evaluation..t_hire.FWorkDate, 工作时间，如5天/周
     * @mbg.generated
     */
    public void setFworkdate(String fworkdate) {
        this.fworkdate = fworkdate == null ? null : fworkdate.trim();
    }

    /**
     * 获取 工作地点 字段:student_capability_evaluation..t_hire.FAddr
     * @return student_capability_evaluation..t_hire.FAddr, 工作地点
     * @mbg.generated
     */
    public String getFaddr() {
        return faddr;
    }

    /**
     * 设置 工作地点 字段:student_capability_evaluation..t_hire.FAddr
     * @param faddr the value for student_capability_evaluation..t_hire.FAddr, 工作地点
     * @mbg.generated
     */
    public void setFaddr(String faddr) {
        this.faddr = faddr == null ? null : faddr.trim();
    }

    /**
     * 获取 招聘描述 字段:student_capability_evaluation..t_hire.FCon
     * @return student_capability_evaluation..t_hire.FCon, 招聘描述
     * @mbg.generated
     */
    public String getFcon() {
        return fcon;
    }

    /**
     * 设置 招聘描述 字段:student_capability_evaluation..t_hire.FCon
     * @param fcon the value for student_capability_evaluation..t_hire.FCon, 招聘描述
     * @mbg.generated
     */
    public void setFcon(String fcon) {
        this.fcon = fcon == null ? null : fcon.trim();
    }

    /**
     * 获取 招聘要求（自然语言解析匹配能力） 字段:student_capability_evaluation..t_hire.FAsk
     * @return student_capability_evaluation..t_hire.FAsk, 招聘要求（自然语言解析匹配能力）
     * @mbg.generated
     */
    public String getFask() {
        return fask;
    }

    /**
     * 设置 招聘要求（自然语言解析匹配能力） 字段:student_capability_evaluation..t_hire.FAsk
     * @param fask the value for student_capability_evaluation..t_hire.FAsk, 招聘要求（自然语言解析匹配能力）
     * @mbg.generated
     */
    public void setFask(String fask) {
        this.fask = fask == null ? null : fask.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_hire
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
        sb.append(", fdate=").append(fdate);
        sb.append(", fenterpriseid=").append(fenterpriseid);
        sb.append(", fname=").append(fname);
        sb.append(", fnum=").append(fnum);
        sb.append(", fwages=").append(fwages);
        sb.append(", fbenefit=").append(fbenefit);
        sb.append(", fworkdate=").append(fworkdate);
        sb.append(", faddr=").append(faddr);
        sb.append(", fcon=").append(fcon);
        sb.append(", fask=").append(fask);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_hire
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
        THire other = (THire) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFdate() == null ? other.getFdate() == null : this.getFdate().equals(other.getFdate()))
            && (this.getFenterpriseid() == null ? other.getFenterpriseid() == null : this.getFenterpriseid().equals(other.getFenterpriseid()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFnum() == null ? other.getFnum() == null : this.getFnum().equals(other.getFnum()))
            && (this.getFwages() == null ? other.getFwages() == null : this.getFwages().equals(other.getFwages()))
            && (this.getFbenefit() == null ? other.getFbenefit() == null : this.getFbenefit().equals(other.getFbenefit()))
            && (this.getFworkdate() == null ? other.getFworkdate() == null : this.getFworkdate().equals(other.getFworkdate()))
            && (this.getFaddr() == null ? other.getFaddr() == null : this.getFaddr().equals(other.getFaddr()))
            && (this.getFcon() == null ? other.getFcon() == null : this.getFcon().equals(other.getFcon()))
            && (this.getFask() == null ? other.getFask() == null : this.getFask().equals(other.getFask()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_hire
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
        result = prime * result + ((getFdate() == null) ? 0 : getFdate().hashCode());
        result = prime * result + ((getFenterpriseid() == null) ? 0 : getFenterpriseid().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFnum() == null) ? 0 : getFnum().hashCode());
        result = prime * result + ((getFwages() == null) ? 0 : getFwages().hashCode());
        result = prime * result + ((getFbenefit() == null) ? 0 : getFbenefit().hashCode());
        result = prime * result + ((getFworkdate() == null) ? 0 : getFworkdate().hashCode());
        result = prime * result + ((getFaddr() == null) ? 0 : getFaddr().hashCode());
        result = prime * result + ((getFcon() == null) ? 0 : getFcon().hashCode());
        result = prime * result + ((getFask() == null) ? 0 : getFask().hashCode());
        return result;
    }
}