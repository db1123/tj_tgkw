package fun.server.model.customQuery.classStudent;

public class ClassStudentCS {

    private String FName;//姓名

    private String FNo;//学号

    private Integer FSex;//性别

    private String FIDNumber;//身份证号

    private String FTel;//联系电话

    private Long CLassID;//班级ID

    private String orderBy;//排序字符串


    private Long FAbilityID;

    private Long FAbilityLevelID;
    private Long FConditionID;

    public Long getFConditionID() {
        return FConditionID;
    }

    public void setFConditionID(Long FConditionID) {
        this.FConditionID = FConditionID;
    }

    public Long getFAbilityID() {
        return FAbilityID;
    }

    public void setFAbilityID(Long FAbilityID) {
        this.FAbilityID = FAbilityID;
    }

    public Long getFAbilityLevelID() {
        return FAbilityLevelID;
    }

    public void setFAbilityLevelID(Long FAbilityLevelID) {
        this.FAbilityLevelID = FAbilityLevelID;
    }

    public Long getCLassID() {
        return CLassID;
    }

    public void setCLassID(Long CLassID) {
        this.CLassID = CLassID;
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

    public Integer getFSex() {
        return FSex;
    }

    public void setFSex(Integer FSex) {
        this.FSex = FSex;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
