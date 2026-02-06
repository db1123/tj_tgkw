package fun.server.model.customQuery.major;

public class TClassStudentData {

    private Long FKeyID;//
    private String FName;//姓名
    private String FNo;//学号
    private int FSex;//性别
    private String FBirthday;//出生日期
    private String FTel;//电话
    private String FStudentID;

    public String getFStudentID() {
        return FStudentID;
    }

    public void setFStudentID(String FStudentID) {
        this.FStudentID = FStudentID;
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

    public String getFNo() {
        return FNo;
    }

    public void setFNo(String FNo) {
        this.FNo = FNo;
    }

    public int getFSex() {
        return FSex;
    }

    public void setFSex(int FSex) {
        this.FSex = FSex;
    }

    public String getFBirthday() {
        return FBirthday;
    }

    public void setFBirthday(String FBirthday) {
        this.FBirthday = FBirthday;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }
}
