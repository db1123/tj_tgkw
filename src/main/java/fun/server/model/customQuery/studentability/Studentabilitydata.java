package fun.server.model.customQuery.studentability;

public class Studentabilitydata {

    private String FSName; //学生名称
    private String FSNo;//学生编号
    private int FSex;//性别
    private String FBirthday;//出生日期
    private String FTel;//联系电话
    private Long FKeyID;//学生能力ID
    private Long FStudentID; //学生ID
    private String FDate;//获取时间
    private int FMode;//获取方式
    private Long FAbilityID;//能力ID
    private Long FAbilityLevelID;//能力等级ID
    private Long FConditionID;//考核条件ID
    private String FAbilityInf;//学生能力描述
    private String FUrl;//学生材料链接
    private String FTABName;//能力名称
    private String FTABLName;//能力等级名称
    private String FTATBTName;//能力类型名称
    private String FConditionName;//能力条件
    private int FSState;//学生能力状态

    public String getFConditionName() {
        return FConditionName;
    }

    public void setFConditionName(String FConditionName) {
        this.FConditionName = FConditionName;
    }

    public Long getFConditionID() {
        return FConditionID;
    }

    public void setFConditionID(Long FConditionID) {
        this.FConditionID = FConditionID;
    }

    public int getFSState() {
        return FSState;
    }

    public void setFSState(int FSState) {
        this.FSState = FSState;
    }

    public String getFSName() {
        return FSName;
    }

    public void setFSName(String FSName) {
        this.FSName = FSName;
    }

    public String getFSNo() {
        return FSNo;
    }

    public void setFSNo(String FSNo) {
        this.FSNo = FSNo;
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

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFStudentID() {
        return FStudentID;
    }

    public void setFStudentID(Long FStudentID) {
        this.FStudentID = FStudentID;
    }

    public String getFDate() {
        return FDate;
    }

    public void setFDate(String FDate) {
        this.FDate = FDate;
    }

    public int getFMode() {
        return FMode;
    }

    public void setFMode(int FMode) {
        this.FMode = FMode;
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

    public String getFAbilityInf() {
        return FAbilityInf;
    }

    public void setFAbilityInf(String FAbilityInf) {
        this.FAbilityInf = FAbilityInf;
    }

    public String getFUrl() {
        return FUrl;
    }

    public void setFUrl(String FUrl) {
        this.FUrl = FUrl;
    }

    public String getFTABName() {
        return FTABName;
    }

    public void setFTABName(String FTABName) {
        this.FTABName = FTABName;
    }

    public String getFTABLName() {
        return FTABLName;
    }

    public void setFTABLName(String FTABLName) {
        this.FTABLName = FTABLName;
    }

    public String getFTATBTName() {
        return FTATBTName;
    }

    public void setFTATBTName(String FTATBTName) {
        this.FTATBTName = FTATBTName;
    }
}
