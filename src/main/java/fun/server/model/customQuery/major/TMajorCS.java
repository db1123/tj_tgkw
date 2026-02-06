package fun.server.model.customQuery.major;

public class TMajorCS {
    //参数类
    private Long FKeyID;//各种key

    private String FName;//各种名称

    private Long FMajorID;

    private String FNo ;

    private Integer FSex;

    private String FTel;

    private String FBirthday;

    private String orderBy;//排序字符串

    public String getFNo() {
        return FNo;
    }

    public void setFNo(String FNo) {
        this.FNo = FNo;
    }

    public Integer getFSex() {
        return FSex;
    }

    public void setFSex(Integer FSex) {
        this.FSex = FSex;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }

    public String getFBirthday() {
        return FBirthday;
    }

    public void setFBirthday(String FBirthday) {
        this.FBirthday = FBirthday;
    }

    public Long getFMajorID() {
        return FMajorID;
    }

    public void setFMajorID(Long FMajorID) {
        this.FMajorID = FMajorID;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
