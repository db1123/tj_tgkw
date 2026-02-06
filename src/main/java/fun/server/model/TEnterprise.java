package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_enterprise
*/
@Table(name = "t_enterprise")
public class TEnterprise implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_enterprise.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_enterprise.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_enterprise.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_enterprise.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_enterprise.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_enterprise.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 方式：1-添加、2-注册
     * 表字段 : student_capability_evaluation..t_enterprise.FMode
     * @mbg.generated
     */
    private Integer fmode;

    /**
     * 名称
     * 表字段 : student_capability_evaluation..t_enterprise.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 类型
     * 表字段 : student_capability_evaluation..t_enterprise.FType
     * @mbg.generated
     */
    private String ftype;

    /**
     * 营业执照号
     * 表字段 : student_capability_evaluation..t_enterprise.FBLNo
     * @mbg.generated
     */
    private String fblno;

    /**
     * 法人（法定代表人）
     * 表字段 : student_capability_evaluation..t_enterprise.FLegalPerson
     * @mbg.generated
     */
    private String flegalperson;

    /**
     * 成立日期
     * 表字段 : student_capability_evaluation..t_enterprise.FCreateDate
     * @mbg.generated
     */
    private String fcreatedate;

    /**
     * 所属行业
     * 表字段 : student_capability_evaluation..t_enterprise.FIndustry
     * @mbg.generated
     */
    private String findustry;

    /**
     * 经营范围
     * 表字段 : student_capability_evaluation..t_enterprise.FScope
     * @mbg.generated
     */
    private String fscope;

    /**
     * 注册地址
     * 表字段 : student_capability_evaluation..t_enterprise.FRegisterAddr
     * @mbg.generated
     */
    private String fregisteraddr;

    /**
     * 工作地址
     * 表字段 : student_capability_evaluation..t_enterprise.FWorkAddr
     * @mbg.generated
     */
    private String fworkaddr;

    /**
     * 联系人
     * 表字段 : student_capability_evaluation..t_enterprise.FUser
     * @mbg.generated
     */
    private String fuser;

    /**
     * 联系电话
     * 表字段 : student_capability_evaluation..t_enterprise.FUserTel
     * @mbg.generated
     */
    private String fusertel;

    /**
     * 备注
     * 表字段 : student_capability_evaluation..t_enterprise.FNote
     * @mbg.generated
     */
    private String fnote;

    /**
     * 主要产品
     * 表字段 : student_capability_evaluation..t_enterprise.FProduct
     * @mbg.generated
     */
    private String fproduct;

    /**
     * 经营状态
     * 表字段 : student_capability_evaluation..t_enterprise.FOperateState
     * @mbg.generated
     */
    private String foperatestate;

    /**
     * 简介
     * 表字段 : student_capability_evaluation..t_enterprise.FIntroduction
     * @mbg.generated
     */
    private String fintroduction;

    /**
     * 用户ID，T_User表ID
     * 表字段 : student_capability_evaluation..t_enterprise.FUserID
     * @mbg.generated
     */
    private Long fuserid;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public TEnterprise(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, Integer fmode, String fname, String ftype, String fblno, String flegalperson, String fcreatedate, String findustry, String fscope, String fregisteraddr, String fworkaddr, String fuser, String fusertel, String fnote, String fproduct, String foperatestate, String fintroduction, Long fuserid) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fmode = fmode;
        this.fname = fname;
        this.ftype = ftype;
        this.fblno = fblno;
        this.flegalperson = flegalperson;
        this.fcreatedate = fcreatedate;
        this.findustry = findustry;
        this.fscope = fscope;
        this.fregisteraddr = fregisteraddr;
        this.fworkaddr = fworkaddr;
        this.fuser = fuser;
        this.fusertel = fusertel;
        this.fnote = fnote;
        this.fproduct = fproduct;
        this.foperatestate = foperatestate;
        this.fintroduction = fintroduction;
        this.fuserid = fuserid;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public TEnterprise() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_enterprise.FKeyID
     * @return student_capability_evaluation..t_enterprise.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_enterprise.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_enterprise.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_enterprise.FCID
     * @return student_capability_evaluation..t_enterprise.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_enterprise.FCID
     * @param fcid the value for student_capability_evaluation..t_enterprise.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_enterprise.FUID
     * @return student_capability_evaluation..t_enterprise.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_enterprise.FUID
     * @param fuid the value for student_capability_evaluation..t_enterprise.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_enterprise.FCDATE
     * @return student_capability_evaluation..t_enterprise.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_enterprise.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_enterprise.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_enterprise.FUDATE
     * @return student_capability_evaluation..t_enterprise.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_enterprise.FUDATE
     * @param fudate the value for student_capability_evaluation..t_enterprise.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_enterprise.FState
     * @return student_capability_evaluation..t_enterprise.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_enterprise.FState
     * @param fstate the value for student_capability_evaluation..t_enterprise.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 方式：1-添加、2-注册 字段:student_capability_evaluation..t_enterprise.FMode
     * @return student_capability_evaluation..t_enterprise.FMode, 方式：1-添加、2-注册
     * @mbg.generated
     */
    public Integer getFmode() {
        return fmode;
    }

    /**
     * 设置 方式：1-添加、2-注册 字段:student_capability_evaluation..t_enterprise.FMode
     * @param fmode the value for student_capability_evaluation..t_enterprise.FMode, 方式：1-添加、2-注册
     * @mbg.generated
     */
    public void setFmode(Integer fmode) {
        this.fmode = fmode;
    }

    /**
     * 获取 名称 字段:student_capability_evaluation..t_enterprise.FName
     * @return student_capability_evaluation..t_enterprise.FName, 名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 名称 字段:student_capability_evaluation..t_enterprise.FName
     * @param fname the value for student_capability_evaluation..t_enterprise.FName, 名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 类型 字段:student_capability_evaluation..t_enterprise.FType
     * @return student_capability_evaluation..t_enterprise.FType, 类型
     * @mbg.generated
     */
    public String getFtype() {
        return ftype;
    }

    /**
     * 设置 类型 字段:student_capability_evaluation..t_enterprise.FType
     * @param ftype the value for student_capability_evaluation..t_enterprise.FType, 类型
     * @mbg.generated
     */
    public void setFtype(String ftype) {
        this.ftype = ftype == null ? null : ftype.trim();
    }

    /**
     * 获取 营业执照号 字段:student_capability_evaluation..t_enterprise.FBLNo
     * @return student_capability_evaluation..t_enterprise.FBLNo, 营业执照号
     * @mbg.generated
     */
    public String getFblno() {
        return fblno;
    }

    /**
     * 设置 营业执照号 字段:student_capability_evaluation..t_enterprise.FBLNo
     * @param fblno the value for student_capability_evaluation..t_enterprise.FBLNo, 营业执照号
     * @mbg.generated
     */
    public void setFblno(String fblno) {
        this.fblno = fblno == null ? null : fblno.trim();
    }

    /**
     * 获取 法人（法定代表人） 字段:student_capability_evaluation..t_enterprise.FLegalPerson
     * @return student_capability_evaluation..t_enterprise.FLegalPerson, 法人（法定代表人）
     * @mbg.generated
     */
    public String getFlegalperson() {
        return flegalperson;
    }

    /**
     * 设置 法人（法定代表人） 字段:student_capability_evaluation..t_enterprise.FLegalPerson
     * @param flegalperson the value for student_capability_evaluation..t_enterprise.FLegalPerson, 法人（法定代表人）
     * @mbg.generated
     */
    public void setFlegalperson(String flegalperson) {
        this.flegalperson = flegalperson == null ? null : flegalperson.trim();
    }

    /**
     * 获取 成立日期 字段:student_capability_evaluation..t_enterprise.FCreateDate
     * @return student_capability_evaluation..t_enterprise.FCreateDate, 成立日期
     * @mbg.generated
     */
    public String getFcreatedate() {
        return fcreatedate;
    }

    /**
     * 设置 成立日期 字段:student_capability_evaluation..t_enterprise.FCreateDate
     * @param fcreatedate the value for student_capability_evaluation..t_enterprise.FCreateDate, 成立日期
     * @mbg.generated
     */
    public void setFcreatedate(String fcreatedate) {
        this.fcreatedate = fcreatedate == null ? null : fcreatedate.trim();
    }

    /**
     * 获取 所属行业 字段:student_capability_evaluation..t_enterprise.FIndustry
     * @return student_capability_evaluation..t_enterprise.FIndustry, 所属行业
     * @mbg.generated
     */
    public String getFindustry() {
        return findustry;
    }

    /**
     * 设置 所属行业 字段:student_capability_evaluation..t_enterprise.FIndustry
     * @param findustry the value for student_capability_evaluation..t_enterprise.FIndustry, 所属行业
     * @mbg.generated
     */
    public void setFindustry(String findustry) {
        this.findustry = findustry == null ? null : findustry.trim();
    }

    /**
     * 获取 经营范围 字段:student_capability_evaluation..t_enterprise.FScope
     * @return student_capability_evaluation..t_enterprise.FScope, 经营范围
     * @mbg.generated
     */
    public String getFscope() {
        return fscope;
    }

    /**
     * 设置 经营范围 字段:student_capability_evaluation..t_enterprise.FScope
     * @param fscope the value for student_capability_evaluation..t_enterprise.FScope, 经营范围
     * @mbg.generated
     */
    public void setFscope(String fscope) {
        this.fscope = fscope == null ? null : fscope.trim();
    }

    /**
     * 获取 注册地址 字段:student_capability_evaluation..t_enterprise.FRegisterAddr
     * @return student_capability_evaluation..t_enterprise.FRegisterAddr, 注册地址
     * @mbg.generated
     */
    public String getFregisteraddr() {
        return fregisteraddr;
    }

    /**
     * 设置 注册地址 字段:student_capability_evaluation..t_enterprise.FRegisterAddr
     * @param fregisteraddr the value for student_capability_evaluation..t_enterprise.FRegisterAddr, 注册地址
     * @mbg.generated
     */
    public void setFregisteraddr(String fregisteraddr) {
        this.fregisteraddr = fregisteraddr == null ? null : fregisteraddr.trim();
    }

    /**
     * 获取 工作地址 字段:student_capability_evaluation..t_enterprise.FWorkAddr
     * @return student_capability_evaluation..t_enterprise.FWorkAddr, 工作地址
     * @mbg.generated
     */
    public String getFworkaddr() {
        return fworkaddr;
    }

    /**
     * 设置 工作地址 字段:student_capability_evaluation..t_enterprise.FWorkAddr
     * @param fworkaddr the value for student_capability_evaluation..t_enterprise.FWorkAddr, 工作地址
     * @mbg.generated
     */
    public void setFworkaddr(String fworkaddr) {
        this.fworkaddr = fworkaddr == null ? null : fworkaddr.trim();
    }

    /**
     * 获取 联系人 字段:student_capability_evaluation..t_enterprise.FUser
     * @return student_capability_evaluation..t_enterprise.FUser, 联系人
     * @mbg.generated
     */
    public String getFuser() {
        return fuser;
    }

    /**
     * 设置 联系人 字段:student_capability_evaluation..t_enterprise.FUser
     * @param fuser the value for student_capability_evaluation..t_enterprise.FUser, 联系人
     * @mbg.generated
     */
    public void setFuser(String fuser) {
        this.fuser = fuser == null ? null : fuser.trim();
    }

    /**
     * 获取 联系电话 字段:student_capability_evaluation..t_enterprise.FUserTel
     * @return student_capability_evaluation..t_enterprise.FUserTel, 联系电话
     * @mbg.generated
     */
    public String getFusertel() {
        return fusertel;
    }

    /**
     * 设置 联系电话 字段:student_capability_evaluation..t_enterprise.FUserTel
     * @param fusertel the value for student_capability_evaluation..t_enterprise.FUserTel, 联系电话
     * @mbg.generated
     */
    public void setFusertel(String fusertel) {
        this.fusertel = fusertel == null ? null : fusertel.trim();
    }

    /**
     * 获取 备注 字段:student_capability_evaluation..t_enterprise.FNote
     * @return student_capability_evaluation..t_enterprise.FNote, 备注
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置 备注 字段:student_capability_evaluation..t_enterprise.FNote
     * @param fnote the value for student_capability_evaluation..t_enterprise.FNote, 备注
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 获取 主要产品 字段:student_capability_evaluation..t_enterprise.FProduct
     * @return student_capability_evaluation..t_enterprise.FProduct, 主要产品
     * @mbg.generated
     */
    public String getFproduct() {
        return fproduct;
    }

    /**
     * 设置 主要产品 字段:student_capability_evaluation..t_enterprise.FProduct
     * @param fproduct the value for student_capability_evaluation..t_enterprise.FProduct, 主要产品
     * @mbg.generated
     */
    public void setFproduct(String fproduct) {
        this.fproduct = fproduct == null ? null : fproduct.trim();
    }

    /**
     * 获取 经营状态 字段:student_capability_evaluation..t_enterprise.FOperateState
     * @return student_capability_evaluation..t_enterprise.FOperateState, 经营状态
     * @mbg.generated
     */
    public String getFoperatestate() {
        return foperatestate;
    }

    /**
     * 设置 经营状态 字段:student_capability_evaluation..t_enterprise.FOperateState
     * @param foperatestate the value for student_capability_evaluation..t_enterprise.FOperateState, 经营状态
     * @mbg.generated
     */
    public void setFoperatestate(String foperatestate) {
        this.foperatestate = foperatestate == null ? null : foperatestate.trim();
    }

    /**
     * 获取 简介 字段:student_capability_evaluation..t_enterprise.FIntroduction
     * @return student_capability_evaluation..t_enterprise.FIntroduction, 简介
     * @mbg.generated
     */
    public String getFintroduction() {
        return fintroduction;
    }

    /**
     * 设置 简介 字段:student_capability_evaluation..t_enterprise.FIntroduction
     * @param fintroduction the value for student_capability_evaluation..t_enterprise.FIntroduction, 简介
     * @mbg.generated
     */
    public void setFintroduction(String fintroduction) {
        this.fintroduction = fintroduction == null ? null : fintroduction.trim();
    }

    /**
     * 获取 用户ID，T_User表ID 字段:student_capability_evaluation..t_enterprise.FUserID
     * @return student_capability_evaluation..t_enterprise.FUserID, 用户ID，T_User表ID
     * @mbg.generated
     */
    public Long getFuserid() {
        return fuserid;
    }

    /**
     * 设置 用户ID，T_User表ID 字段:student_capability_evaluation..t_enterprise.FUserID
     * @param fuserid the value for student_capability_evaluation..t_enterprise.FUserID, 用户ID，T_User表ID
     * @mbg.generated
     */
    public void setFuserid(Long fuserid) {
        this.fuserid = fuserid;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_enterprise
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
        sb.append(", ftype=").append(ftype);
        sb.append(", fblno=").append(fblno);
        sb.append(", flegalperson=").append(flegalperson);
        sb.append(", fcreatedate=").append(fcreatedate);
        sb.append(", findustry=").append(findustry);
        sb.append(", fscope=").append(fscope);
        sb.append(", fregisteraddr=").append(fregisteraddr);
        sb.append(", fworkaddr=").append(fworkaddr);
        sb.append(", fuser=").append(fuser);
        sb.append(", fusertel=").append(fusertel);
        sb.append(", fnote=").append(fnote);
        sb.append(", fproduct=").append(fproduct);
        sb.append(", foperatestate=").append(foperatestate);
        sb.append(", fintroduction=").append(fintroduction);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_enterprise
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
        TEnterprise other = (TEnterprise) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFmode() == null ? other.getFmode() == null : this.getFmode().equals(other.getFmode()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getFblno() == null ? other.getFblno() == null : this.getFblno().equals(other.getFblno()))
            && (this.getFlegalperson() == null ? other.getFlegalperson() == null : this.getFlegalperson().equals(other.getFlegalperson()))
            && (this.getFcreatedate() == null ? other.getFcreatedate() == null : this.getFcreatedate().equals(other.getFcreatedate()))
            && (this.getFindustry() == null ? other.getFindustry() == null : this.getFindustry().equals(other.getFindustry()))
            && (this.getFscope() == null ? other.getFscope() == null : this.getFscope().equals(other.getFscope()))
            && (this.getFregisteraddr() == null ? other.getFregisteraddr() == null : this.getFregisteraddr().equals(other.getFregisteraddr()))
            && (this.getFworkaddr() == null ? other.getFworkaddr() == null : this.getFworkaddr().equals(other.getFworkaddr()))
            && (this.getFuser() == null ? other.getFuser() == null : this.getFuser().equals(other.getFuser()))
            && (this.getFusertel() == null ? other.getFusertel() == null : this.getFusertel().equals(other.getFusertel()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()))
            && (this.getFproduct() == null ? other.getFproduct() == null : this.getFproduct().equals(other.getFproduct()))
            && (this.getFoperatestate() == null ? other.getFoperatestate() == null : this.getFoperatestate().equals(other.getFoperatestate()))
            && (this.getFintroduction() == null ? other.getFintroduction() == null : this.getFintroduction().equals(other.getFintroduction()))
            && (this.getFuserid() == null ? other.getFuserid() == null : this.getFuserid().equals(other.getFuserid()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_enterprise
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
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getFblno() == null) ? 0 : getFblno().hashCode());
        result = prime * result + ((getFlegalperson() == null) ? 0 : getFlegalperson().hashCode());
        result = prime * result + ((getFcreatedate() == null) ? 0 : getFcreatedate().hashCode());
        result = prime * result + ((getFindustry() == null) ? 0 : getFindustry().hashCode());
        result = prime * result + ((getFscope() == null) ? 0 : getFscope().hashCode());
        result = prime * result + ((getFregisteraddr() == null) ? 0 : getFregisteraddr().hashCode());
        result = prime * result + ((getFworkaddr() == null) ? 0 : getFworkaddr().hashCode());
        result = prime * result + ((getFuser() == null) ? 0 : getFuser().hashCode());
        result = prime * result + ((getFusertel() == null) ? 0 : getFusertel().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        result = prime * result + ((getFproduct() == null) ? 0 : getFproduct().hashCode());
        result = prime * result + ((getFoperatestate() == null) ? 0 : getFoperatestate().hashCode());
        result = prime * result + ((getFintroduction() == null) ? 0 : getFintroduction().hashCode());
        result = prime * result + ((getFuserid() == null) ? 0 : getFuserid().hashCode());
        return result;
    }
}