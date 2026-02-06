package fun.server.model.customQuery.courseAbility;

public class CourseabilityData {

    private Long FKeyID;//课程能力ID
    private Long FAbilityID; //能力ID
    private String FAbilityName;//能力名称
    private String FLevelName;//能力等级名称
    private String FTypeName;//能力类型名称
    private Long FLevelID;//能力等级ID
    private Long FTypeID;//能力类型ID

    private Long FCourseID;//课程ID

    private int fdardnum ;//需要标准设置的数量

    private int fstatedardnum;//已完成设置的数量

    private float FMethodWeight;//权重

    public float getFMethodWeight() {
        return FMethodWeight;
    }

    public void setFMethodWeight(float FMethodWeight) {
        this.FMethodWeight = FMethodWeight;
    }

    public int getFdardnum() {
        return fdardnum;
    }

    public void setFdardnum(int fdardnum) {
        this.fdardnum = fdardnum;
    }

    public int getFstatedardnum() {
        return fstatedardnum;
    }

    public void setFstatedardnum(int fstatedardnum) {
        this.fstatedardnum = fstatedardnum;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }

    public Long getFAbilityID() {
        return FAbilityID;
    }

    public void setFAbilityID(Long FAbilityID) {
        this.FAbilityID = FAbilityID;
    }

    public String getFAbilityName() {
        return FAbilityName;
    }

    public void setFAbilityName(String FAbilityName) {
        this.FAbilityName = FAbilityName;
    }

    public String getFLevelName() {
        return FLevelName;
    }

    public void setFLevelName(String FLevelName) {
        this.FLevelName = FLevelName;
    }

    public String getFTypeName() {
        return FTypeName;
    }

    public void setFTypeName(String FTypeName) {
        this.FTypeName = FTypeName;
    }

    public Long getFLevelID() {
        return FLevelID;
    }

    public void setFLevelID(Long FLevelID) {
        this.FLevelID = FLevelID;
    }

    public Long getFTypeID() {
        return FTypeID;
    }

    public void setFTypeID(Long FTypeID) {
        this.FTypeID = FTypeID;
    }
}
