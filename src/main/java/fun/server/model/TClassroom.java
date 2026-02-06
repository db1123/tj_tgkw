package fun.server.model;

import fun.server.SnowflakeGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名：t_classroom
*/
@Table(name = "t_classroom")
public class TClassroom implements Serializable {
    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FKeyID
     * @mbg.generated
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    private Long fkeyid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FCID
     * @mbg.generated
     */
    private Long fcid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FUID
     * @mbg.generated
     */
    private Long fuid;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FCDATE
     * @mbg.generated
     */
    private Date fcdate;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FUDATE
     * @mbg.generated
     */
    private Date fudate;

    /**
     * 0-禁用；1-可用
     * 表字段 : student_capability_evaluation..t_classroom.FState
     * @mbg.generated
     */
    private Integer fstate;

    /**
     * 教室编号
     * 表字段 : student_capability_evaluation..t_classroom.FNo
     * @mbg.generated
     */
    private String fno;

    /**
     * 楼栋名称
     * 表字段 : student_capability_evaluation..t_classroom.FName
     * @mbg.generated
     */
    private String fname;

    /**
     * 房间号
     * 表字段 : student_capability_evaluation..t_classroom.FClassroom
     * @mbg.generated
     */
    private String fclassroom;

    /**
     * 教室容量
     * 表字段 : student_capability_evaluation..t_classroom.FCapacity
     * @mbg.generated
     */
    private Integer fcapacity;

    /**
     * 教室设备信息（如投影仪、实验室设备）
     * 表字段 : student_capability_evaluation..t_classroom.FEquipment
     * @mbg.generated
     */
    private String fequipment;

    /**
     * 
     * 表字段 : student_capability_evaluation..t_classroom.FNote
     * @mbg.generated
     */
    private String fnote;

    private static final long serialVersionUID = 1L;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_classroom
     * @mbg.generated
     */
    public TClassroom(Long fkeyid, Long fcid, Long fuid, Date fcdate, Date fudate, Integer fstate, String fno, String fname, String fclassroom, Integer fcapacity, String fequipment, String fnote) {
        this.fkeyid = fkeyid;
        this.fcid = fcid;
        this.fuid = fuid;
        this.fcdate = fcdate;
        this.fudate = fudate;
        this.fstate = fstate;
        this.fno = fno;
        this.fname = fname;
        this.fclassroom = fclassroom;
        this.fcapacity = fcapacity;
        this.fequipment = fequipment;
        this.fnote = fnote;
    }

    /**
     * 构造查询条件
     * student_capability_evaluation..t_classroom
     * @mbg.generated
     */
    public TClassroom() {
        super();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FKeyID
     * @return student_capability_evaluation..t_classroom.FKeyID, 
     * @mbg.generated
     */
    public Long getFkeyid() {
        return fkeyid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FKeyID
     * @param fkeyid the value for student_capability_evaluation..t_classroom.FKeyID, 
     * @mbg.generated
     */
    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FCID
     * @return student_capability_evaluation..t_classroom.FCID, 
     * @mbg.generated
     */
    public Long getFcid() {
        return fcid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FCID
     * @param fcid the value for student_capability_evaluation..t_classroom.FCID, 
     * @mbg.generated
     */
    public void setFcid(Long fcid) {
        this.fcid = fcid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FUID
     * @return student_capability_evaluation..t_classroom.FUID, 
     * @mbg.generated
     */
    public Long getFuid() {
        return fuid;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FUID
     * @param fuid the value for student_capability_evaluation..t_classroom.FUID, 
     * @mbg.generated
     */
    public void setFuid(Long fuid) {
        this.fuid = fuid;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FCDATE
     * @return student_capability_evaluation..t_classroom.FCDATE, 
     * @mbg.generated
     */
    public Date getFcdate() {
        return fcdate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FCDATE
     * @param fcdate the value for student_capability_evaluation..t_classroom.FCDATE, 
     * @mbg.generated
     */
    public void setFcdate(Date fcdate) {
        this.fcdate = fcdate;
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FUDATE
     * @return student_capability_evaluation..t_classroom.FUDATE, 
     * @mbg.generated
     */
    public Date getFudate() {
        return fudate;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FUDATE
     * @param fudate the value for student_capability_evaluation..t_classroom.FUDATE, 
     * @mbg.generated
     */
    public void setFudate(Date fudate) {
        this.fudate = fudate;
    }

    /**
     * 获取 0-禁用；1-可用 字段:student_capability_evaluation..t_classroom.FState
     * @return student_capability_evaluation..t_classroom.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public Integer getFstate() {
        return fstate;
    }

    /**
     * 设置 0-禁用；1-可用 字段:student_capability_evaluation..t_classroom.FState
     * @param fstate the value for student_capability_evaluation..t_classroom.FState, 0-禁用；1-可用
     * @mbg.generated
     */
    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    /**
     * 获取 教室编号 字段:student_capability_evaluation..t_classroom.FNo
     * @return student_capability_evaluation..t_classroom.FNo, 教室编号
     * @mbg.generated
     */
    public String getFno() {
        return fno;
    }

    /**
     * 设置 教室编号 字段:student_capability_evaluation..t_classroom.FNo
     * @param fno the value for student_capability_evaluation..t_classroom.FNo, 教室编号
     * @mbg.generated
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * 获取 楼栋名称 字段:student_capability_evaluation..t_classroom.FName
     * @return student_capability_evaluation..t_classroom.FName, 楼栋名称
     * @mbg.generated
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置 楼栋名称 字段:student_capability_evaluation..t_classroom.FName
     * @param fname the value for student_capability_evaluation..t_classroom.FName, 楼栋名称
     * @mbg.generated
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取 房间号 字段:student_capability_evaluation..t_classroom.FClassroom
     * @return student_capability_evaluation..t_classroom.FClassroom, 房间号
     * @mbg.generated
     */
    public String getFclassroom() {
        return fclassroom;
    }

    /**
     * 设置 房间号 字段:student_capability_evaluation..t_classroom.FClassroom
     * @param fclassroom the value for student_capability_evaluation..t_classroom.FClassroom, 房间号
     * @mbg.generated
     */
    public void setFclassroom(String fclassroom) {
        this.fclassroom = fclassroom == null ? null : fclassroom.trim();
    }

    /**
     * 获取 教室容量 字段:student_capability_evaluation..t_classroom.FCapacity
     * @return student_capability_evaluation..t_classroom.FCapacity, 教室容量
     * @mbg.generated
     */
    public Integer getFcapacity() {
        return fcapacity;
    }

    /**
     * 设置 教室容量 字段:student_capability_evaluation..t_classroom.FCapacity
     * @param fcapacity the value for student_capability_evaluation..t_classroom.FCapacity, 教室容量
     * @mbg.generated
     */
    public void setFcapacity(Integer fcapacity) {
        this.fcapacity = fcapacity;
    }

    /**
     * 获取 教室设备信息（如投影仪、实验室设备） 字段:student_capability_evaluation..t_classroom.FEquipment
     * @return student_capability_evaluation..t_classroom.FEquipment, 教室设备信息（如投影仪、实验室设备）
     * @mbg.generated
     */
    public String getFequipment() {
        return fequipment;
    }

    /**
     * 设置 教室设备信息（如投影仪、实验室设备） 字段:student_capability_evaluation..t_classroom.FEquipment
     * @param fequipment the value for student_capability_evaluation..t_classroom.FEquipment, 教室设备信息（如投影仪、实验室设备）
     * @mbg.generated
     */
    public void setFequipment(String fequipment) {
        this.fequipment = fequipment == null ? null : fequipment.trim();
    }

    /**
     * 获取  字段:student_capability_evaluation..t_classroom.FNote
     * @return student_capability_evaluation..t_classroom.FNote, 
     * @mbg.generated
     */
    public String getFnote() {
        return fnote;
    }

    /**
     * 设置  字段:student_capability_evaluation..t_classroom.FNote
     * @param fnote the value for student_capability_evaluation..t_classroom.FNote, 
     * @mbg.generated
     */
    public void setFnote(String fnote) {
        this.fnote = fnote == null ? null : fnote.trim();
    }

    /**
     * 实例输出为字符串
     * student_capability_evaluation..t_classroom
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
        sb.append(", fno=").append(fno);
        sb.append(", fname=").append(fname);
        sb.append(", fclassroom=").append(fclassroom);
        sb.append(", fcapacity=").append(fcapacity);
        sb.append(", fequipment=").append(fequipment);
        sb.append(", fnote=").append(fnote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 实例比较方法
     * student_capability_evaluation..t_classroom
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
        TClassroom other = (TClassroom) that;
        return (this.getFkeyid() == null ? other.getFkeyid() == null : this.getFkeyid().equals(other.getFkeyid()))
            && (this.getFcid() == null ? other.getFcid() == null : this.getFcid().equals(other.getFcid()))
            && (this.getFuid() == null ? other.getFuid() == null : this.getFuid().equals(other.getFuid()))
            && (this.getFcdate() == null ? other.getFcdate() == null : this.getFcdate().equals(other.getFcdate()))
            && (this.getFudate() == null ? other.getFudate() == null : this.getFudate().equals(other.getFudate()))
            && (this.getFstate() == null ? other.getFstate() == null : this.getFstate().equals(other.getFstate()))
            && (this.getFno() == null ? other.getFno() == null : this.getFno().equals(other.getFno()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFclassroom() == null ? other.getFclassroom() == null : this.getFclassroom().equals(other.getFclassroom()))
            && (this.getFcapacity() == null ? other.getFcapacity() == null : this.getFcapacity().equals(other.getFcapacity()))
            && (this.getFequipment() == null ? other.getFequipment() == null : this.getFequipment().equals(other.getFequipment()))
            && (this.getFnote() == null ? other.getFnote() == null : this.getFnote().equals(other.getFnote()));
    }

    /**
     * 根据对象的地址或者字符串或者数字算出来的int类型的数值
     * student_capability_evaluation..t_classroom
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
        result = prime * result + ((getFno() == null) ? 0 : getFno().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFclassroom() == null) ? 0 : getFclassroom().hashCode());
        result = prime * result + ((getFcapacity() == null) ? 0 : getFcapacity().hashCode());
        result = prime * result + ((getFequipment() == null) ? 0 : getFequipment().hashCode());
        result = prime * result + ((getFnote() == null) ? 0 : getFnote().hashCode());
        return result;
    }
}