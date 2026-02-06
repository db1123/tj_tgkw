package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_user
*/
@Table(name = "t_user")
public class TUser implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_key_id
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fKeyId;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_c_id
     * @mbg.generated
     */
    private Long fCId;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_u_id
     * @mbg.generated
     */
    private Long fUId;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_c_date
     * @mbg.generated
     */
    private Date fCDate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_u_date
     * @mbg.generated
     */
    private Date fUDate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_state
     * @mbg.generated
     */
    private Integer fState;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_login
     * @mbg.generated
     */
    private String fLogin;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_pass
     * @mbg.generated
     */
    private String fPass;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_name
     * @mbg.generated
     */
    private String fName;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_tel
     * @mbg.generated
     */
    private String fTel;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_email
     * @mbg.generated
     */
    private String fEmail;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_is_admin
     * @mbg.generated
     */
    private Integer fIsAdmin;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_note
     * @mbg.generated
     */
    private String fNote;

    /**
     * 1系统用户
2企业用户3学生用户
     * 表字段 : student_capability_evaluation..t_user.f_type
     * @mbg.generated
     */
    private Integer fType;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_supplier_id
     * @mbg.generated
     */
    private Long fSupplierId;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_user.f_active
     * @mbg.generated
     */
    private Integer fActive;

    /**
     * 部门id
     * 表字段 : student_capability_evaluation..t_user.f_dept
     * @mbg.generated
     */
    private Long fDept;

    /**
     * 职务ID
     * 表字段 : student_capability_evaluation..t_user.f_post
     * @mbg.generated
     */
    private Long fPost;

    /**
     * 员工编号
     * 表字段 : student_capability_evaluation..t_user.f_userno
     * @mbg.generated
     */
    private String fUserno;

    /**
     * 系统用户为‘’；学生用户为学生表ID；企业用户为企业版ID
     * 表字段 : student_capability_evaluation..t_user.f_join_id
     * @mbg.generated
     */
    private Long fJoinId;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public TUser(Long fKeyId, Long fCId, Long fUId, Date fCDate, Date fUDate, Integer fState, String fLogin, String fPass, String fName, String fTel, String fEmail, Integer fIsAdmin, String fNote, Integer fType, Long fSupplierId, Integer fActive, Long fDept, Long fPost, String fUserno, Long fJoinId) {
        this.fKeyId = fKeyId;
        this.fCId = fCId;
        this.fUId = fUId;
        this.fCDate = fCDate;
        this.fUDate = fUDate;
        this.fState = fState;
        this.fLogin = fLogin;
        this.fPass = fPass;
        this.fName = fName;
        this.fTel = fTel;
        this.fEmail = fEmail;
        this.fIsAdmin = fIsAdmin;
        this.fNote = fNote;
        this.fType = fType;
        this.fSupplierId = fSupplierId;
        this.fActive = fActive;
        this.fDept = fDept;
        this.fPost = fPost;
        this.fUserno = fUserno;
        this.fJoinId = fJoinId;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public TUser() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_key_id
     * @return student_capability_evaluation..t_user.f_key_id, 
     * @mbg.generated
     */
    public Long getfKeyId() {
        return fKeyId;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_key_id
     * @param fKeyId the value for student_capability_evaluation..t_user.f_key_id, 
     * @mbg.generated
     */
    public void setfKeyId(Long fKeyId) {
        this.fKeyId = fKeyId;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_c_id
     * @return student_capability_evaluation..t_user.f_c_id, 
     * @mbg.generated
     */
    public Long getfCId() {
        return fCId;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_c_id
     * @param fCId the value for student_capability_evaluation..t_user.f_c_id, 
     * @mbg.generated
     */
    public void setfCId(Long fCId) {
        this.fCId = fCId;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_u_id
     * @return student_capability_evaluation..t_user.f_u_id, 
     * @mbg.generated
     */
    public Long getfUId() {
        return fUId;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_u_id
     * @param fUId the value for student_capability_evaluation..t_user.f_u_id, 
     * @mbg.generated
     */
    public void setfUId(Long fUId) {
        this.fUId = fUId;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_c_date
     * @return student_capability_evaluation..t_user.f_c_date, 
     * @mbg.generated
     */
    public Date getfCDate() {
        return fCDate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_c_date
     * @param fCDate the value for student_capability_evaluation..t_user.f_c_date, 
     * @mbg.generated
     */
    public void setfCDate(Date fCDate) {
        this.fCDate = fCDate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_u_date
     * @return student_capability_evaluation..t_user.f_u_date, 
     * @mbg.generated
     */
    public Date getfUDate() {
        return fUDate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_u_date
     * @param fUDate the value for student_capability_evaluation..t_user.f_u_date, 
     * @mbg.generated
     */
    public void setfUDate(Date fUDate) {
        this.fUDate = fUDate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_state
     * @return student_capability_evaluation..t_user.f_state, 
     * @mbg.generated
     */
    public Integer getfState() {
        return fState;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_state
     * @param fState the value for student_capability_evaluation..t_user.f_state, 
     * @mbg.generated
     */
    public void setfState(Integer fState) {
        this.fState = fState;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_login
     * @return student_capability_evaluation..t_user.f_login, 
     * @mbg.generated
     */
    public String getfLogin() {
        return fLogin;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_login
     * @param fLogin the value for student_capability_evaluation..t_user.f_login, 
     * @mbg.generated
     */
    public void setfLogin(String fLogin) {
        this.fLogin = fLogin == null ? null : fLogin.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_pass
     * @return student_capability_evaluation..t_user.f_pass, 
     * @mbg.generated
     */
    public String getfPass() {
        return fPass;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_pass
     * @param fPass the value for student_capability_evaluation..t_user.f_pass, 
     * @mbg.generated
     */
    public void setfPass(String fPass) {
        this.fPass = fPass == null ? null : fPass.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_name
     * @return student_capability_evaluation..t_user.f_name, 
     * @mbg.generated
     */
    public String getfName() {
        return fName;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_name
     * @param fName the value for student_capability_evaluation..t_user.f_name, 
     * @mbg.generated
     */
    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_tel
     * @return student_capability_evaluation..t_user.f_tel, 
     * @mbg.generated
     */
    public String getfTel() {
        return fTel;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_tel
     * @param fTel the value for student_capability_evaluation..t_user.f_tel, 
     * @mbg.generated
     */
    public void setfTel(String fTel) {
        this.fTel = fTel == null ? null : fTel.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_email
     * @return student_capability_evaluation..t_user.f_email, 
     * @mbg.generated
     */
    public String getfEmail() {
        return fEmail;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_email
     * @param fEmail the value for student_capability_evaluation..t_user.f_email, 
     * @mbg.generated
     */
    public void setfEmail(String fEmail) {
        this.fEmail = fEmail == null ? null : fEmail.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_is_admin
     * @return student_capability_evaluation..t_user.f_is_admin, 
     * @mbg.generated
     */
    public Integer getfIsAdmin() {
        return fIsAdmin;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_is_admin
     * @param fIsAdmin the value for student_capability_evaluation..t_user.f_is_admin, 
     * @mbg.generated
     */
    public void setfIsAdmin(Integer fIsAdmin) {
        this.fIsAdmin = fIsAdmin;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_note
     * @return student_capability_evaluation..t_user.f_note, 
     * @mbg.generated
     */
    public String getfNote() {
        return fNote;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_note
     * @param fNote the value for student_capability_evaluation..t_user.f_note, 
     * @mbg.generated
     */
    public void setfNote(String fNote) {
        this.fNote = fNote == null ? null : fNote.trim();
    }

    /**
     * 获取 1系统用户
2企业用户3学生用户 字段:student_capability_evaluation..t_user.f_type
     * @return student_capability_evaluation..t_user.f_type, 1系统用户
2企业用户3学生用户
     * @mbg.generated
     */
    public Integer getfType() {
        return fType;
    }

    /**
     * 设置 1系统用户
2企业用户3学生用户 字段:student_capability_evaluation..t_user.f_type
     * @param fType the value for student_capability_evaluation..t_user.f_type, 1系统用户
2企业用户3学生用户
     * @mbg.generated
     */
    public void setfType(Integer fType) {
        this.fType = fType;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_supplier_id
     * @return student_capability_evaluation..t_user.f_supplier_id, 
     * @mbg.generated
     */
    public Long getfSupplierId() {
        return fSupplierId;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_supplier_id
     * @param fSupplierId the value for student_capability_evaluation..t_user.f_supplier_id, 
     * @mbg.generated
     */
    public void setfSupplierId(Long fSupplierId) {
        this.fSupplierId = fSupplierId;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_user.f_active
     * @return student_capability_evaluation..t_user.f_active, 
     * @mbg.generated
     */
    public Integer getfActive() {
        return fActive;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_user.f_active
     * @param fActive the value for student_capability_evaluation..t_user.f_active, 
     * @mbg.generated
     */
    public void setfActive(Integer fActive) {
        this.fActive = fActive;
    }

    /**
     * 获取 部门id 字段:student_capability_evaluation..t_user.f_dept
     * @return student_capability_evaluation..t_user.f_dept, 部门id
     * @mbg.generated
     */
    public Long getfDept() {
        return fDept;
    }

    /**
     * 设置 部门id 字段:student_capability_evaluation..t_user.f_dept
     * @param fDept the value for student_capability_evaluation..t_user.f_dept, 部门id
     * @mbg.generated
     */
    public void setfDept(Long fDept) {
        this.fDept = fDept;
    }

    /**
     * 获取 职务ID 字段:student_capability_evaluation..t_user.f_post
     * @return student_capability_evaluation..t_user.f_post, 职务ID
     * @mbg.generated
     */
    public Long getfPost() {
        return fPost;
    }

    /**
     * 设置 职务ID 字段:student_capability_evaluation..t_user.f_post
     * @param fPost the value for student_capability_evaluation..t_user.f_post, 职务ID
     * @mbg.generated
     */
    public void setfPost(Long fPost) {
        this.fPost = fPost;
    }

    /**
     * 获取 员工编号 字段:student_capability_evaluation..t_user.f_userno
     * @return student_capability_evaluation..t_user.f_userno, 员工编号
     * @mbg.generated
     */
    public String getfUserno() {
        return fUserno;
    }

    /**
     * 设置 员工编号 字段:student_capability_evaluation..t_user.f_userno
     * @param fUserno the value for student_capability_evaluation..t_user.f_userno, 员工编号
     * @mbg.generated
     */
    public void setfUserno(String fUserno) {
        this.fUserno = fUserno == null ? null : fUserno.trim();
    }

    /**
     * 获取 系统用户为‘’；学生用户为学生表ID；企业用户为企业版ID 字段:student_capability_evaluation..t_user.f_join_id
     * @return student_capability_evaluation..t_user.f_join_id, 系统用户为‘’；学生用户为学生表ID；企业用户为企业版ID
     * @mbg.generated
     */
    public Long getfJoinId() {
        return fJoinId;
    }

    /**
     * 设置 系统用户为‘’；学生用户为学生表ID；企业用户为企业版ID 字段:student_capability_evaluation..t_user.f_join_id
     * @param fJoinId the value for student_capability_evaluation..t_user.f_join_id, 系统用户为‘’；学生用户为学生表ID；企业用户为企业版ID
     * @mbg.generated
     */
    public void setfJoinId(Long fJoinId) {
        this.fJoinId = fJoinId;
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fKeyId=").append(fKeyId);
        sb.append(", fCId=").append(fCId);
        sb.append(", fUId=").append(fUId);
        sb.append(", fCDate=").append(fCDate);
        sb.append(", fUDate=").append(fUDate);
        sb.append(", fState=").append(fState);
        sb.append(", fLogin=").append(fLogin);
        sb.append(", fPass=").append(fPass);
        sb.append(", fName=").append(fName);
        sb.append(", fTel=").append(fTel);
        sb.append(", fEmail=").append(fEmail);
        sb.append(", fIsAdmin=").append(fIsAdmin);
        sb.append(", fNote=").append(fNote);
        sb.append(", fType=").append(fType);
        sb.append(", fSupplierId=").append(fSupplierId);
        sb.append(", fActive=").append(fActive);
        sb.append(", fDept=").append(fDept);
        sb.append(", fPost=").append(fPost);
        sb.append(", fUserno=").append(fUserno);
        sb.append(", fJoinId=").append(fJoinId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_user
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
        TUser other = (TUser) that;
        return (this.getfKeyId() == null ? other.getfKeyId() == null : this.getfKeyId().equals(other.getfKeyId()))
            && (this.getfCId() == null ? other.getfCId() == null : this.getfCId().equals(other.getfCId()))
            && (this.getfUId() == null ? other.getfUId() == null : this.getfUId().equals(other.getfUId()))
            && (this.getfCDate() == null ? other.getfCDate() == null : this.getfCDate().equals(other.getfCDate()))
            && (this.getfUDate() == null ? other.getfUDate() == null : this.getfUDate().equals(other.getfUDate()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfLogin() == null ? other.getfLogin() == null : this.getfLogin().equals(other.getfLogin()))
            && (this.getfPass() == null ? other.getfPass() == null : this.getfPass().equals(other.getfPass()))
            && (this.getfName() == null ? other.getfName() == null : this.getfName().equals(other.getfName()))
            && (this.getfTel() == null ? other.getfTel() == null : this.getfTel().equals(other.getfTel()))
            && (this.getfEmail() == null ? other.getfEmail() == null : this.getfEmail().equals(other.getfEmail()))
            && (this.getfIsAdmin() == null ? other.getfIsAdmin() == null : this.getfIsAdmin().equals(other.getfIsAdmin()))
            && (this.getfNote() == null ? other.getfNote() == null : this.getfNote().equals(other.getfNote()))
            && (this.getfType() == null ? other.getfType() == null : this.getfType().equals(other.getfType()))
            && (this.getfSupplierId() == null ? other.getfSupplierId() == null : this.getfSupplierId().equals(other.getfSupplierId()))
            && (this.getfActive() == null ? other.getfActive() == null : this.getfActive().equals(other.getfActive()))
            && (this.getfDept() == null ? other.getfDept() == null : this.getfDept().equals(other.getfDept()))
            && (this.getfPost() == null ? other.getfPost() == null : this.getfPost().equals(other.getfPost()))
            && (this.getfUserno() == null ? other.getfUserno() == null : this.getfUserno().equals(other.getfUserno()))
            && (this.getfJoinId() == null ? other.getfJoinId() == null : this.getfJoinId().equals(other.getfJoinId()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfKeyId() == null) ? 0 : getfKeyId().hashCode());
        result = prime * result + ((getfCId() == null) ? 0 : getfCId().hashCode());
        result = prime * result + ((getfUId() == null) ? 0 : getfUId().hashCode());
        result = prime * result + ((getfCDate() == null) ? 0 : getfCDate().hashCode());
        result = prime * result + ((getfUDate() == null) ? 0 : getfUDate().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfLogin() == null) ? 0 : getfLogin().hashCode());
        result = prime * result + ((getfPass() == null) ? 0 : getfPass().hashCode());
        result = prime * result + ((getfName() == null) ? 0 : getfName().hashCode());
        result = prime * result + ((getfTel() == null) ? 0 : getfTel().hashCode());
        result = prime * result + ((getfEmail() == null) ? 0 : getfEmail().hashCode());
        result = prime * result + ((getfIsAdmin() == null) ? 0 : getfIsAdmin().hashCode());
        result = prime * result + ((getfNote() == null) ? 0 : getfNote().hashCode());
        result = prime * result + ((getfType() == null) ? 0 : getfType().hashCode());
        result = prime * result + ((getfSupplierId() == null) ? 0 : getfSupplierId().hashCode());
        result = prime * result + ((getfActive() == null) ? 0 : getfActive().hashCode());
        result = prime * result + ((getfDept() == null) ? 0 : getfDept().hashCode());
        result = prime * result + ((getfPost() == null) ? 0 : getfPost().hashCode());
        result = prime * result + ((getfUserno() == null) ? 0 : getfUserno().hashCode());
        result = prime * result + ((getfJoinId() == null) ? 0 : getfJoinId().hashCode());
        return result;
    }
}