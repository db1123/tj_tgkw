package fun.server.model.customQuery.major;

public class TMajorData {
    //返回值

    private Long FKeyID;//自身KeyID

    private String FName;//名称

    private Long FMajorID;//关联ID

    private Long FCLassID;//班级ID

    private Long FGradeID;//年级ID

    //自定义属性
    private int FAdmissionYear; //入学年份

    public Long getFCLassID() {
        return FCLassID;
    }

    public void setFCLassID(Long FCLassID) {
        this.FCLassID = FCLassID;
    }

    public Long getFGradeID() {
        return FGradeID;
    }

    public void setFGradeID(Long FGradeID) {
        this.FGradeID = FGradeID;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public Long getFMajorID() {
        return FMajorID;
    }

    public void setFMajorID(Long FMajorID) {
        this.FMajorID = FMajorID;
    }

    public int getFAdmissionYear() {
        return FAdmissionYear;
    }

    public void setFAdmissionYear(int FAdmissionYear) {
        this.FAdmissionYear = FAdmissionYear;
    }
}
