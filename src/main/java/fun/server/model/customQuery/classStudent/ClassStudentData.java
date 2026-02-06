package fun.server.model.customQuery.classStudent;

public class ClassStudentData {

    private Long FKeyID;//学生ID

    private String FName;// 姓名

    private String FNo;//学号

    private String FIDNumber;//身份证号

    private String FTel;//联系电话

    private int FSex;//性别

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

    public String getFIDNumber() {
        return FIDNumber;
    }

    public void setFIDNumber(String FIDNumber) {
        this.FIDNumber = FIDNumber;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }

    public int getFSex() {
        return FSex;
    }

    public void setFSex(int FSex) {
        this.FSex = FSex;
    }
}
