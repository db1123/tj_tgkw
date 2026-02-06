package fun.server.model.customQuery.coursestandard;

public class CourseStandardData {

    private Long FKeyID;//标准设置ID
    private Long FCourseID;
    private Long FAbilityID;
    private Long FAbilityLevelID;
    private Long FAbilityCAMID;

    private String FAbilityName;
    private String FCourseName;
    private String FAbilityLevelName;
    private String FAbilityCAMName;

    private int FQZType;

    private int FType;

    private Long FcalcType;

    private int FSZState;

    private float FConditionScore;//满足条件的分数

    public float getFConditionScore() {
        return FConditionScore;
    }

    public void setFConditionScore(float FConditionScore) {
        this.FConditionScore = FConditionScore;
    }

    public int getFQZType() {
        return FQZType;
    }

    public void setFQZType(int FQZType) {
        this.FQZType = FQZType;
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

    public Long getFAbilityLevelID() {
        return FAbilityLevelID;
    }

    public void setFAbilityLevelID(Long FAbilityLevelID) {
        this.FAbilityLevelID = FAbilityLevelID;
    }

    public Long getFAbilityCAMID() {
        return FAbilityCAMID;
    }

    public void setFAbilityCAMID(Long FAbilityCAMID) {
        this.FAbilityCAMID = FAbilityCAMID;
    }

    public String getFAbilityName() {
        return FAbilityName;
    }

    public void setFAbilityName(String FAbilityName) {
        this.FAbilityName = FAbilityName;
    }

    public String getFCourseName() {
        return FCourseName;
    }

    public void setFCourseName(String FCourseName) {
        this.FCourseName = FCourseName;
    }

    public String getFAbilityLevelName() {
        return FAbilityLevelName;
    }

    public void setFAbilityLevelName(String FAbilityLevelName) {
        this.FAbilityLevelName = FAbilityLevelName;
    }

    public String getFAbilityCAMName() {
        return FAbilityCAMName;
    }

    public void setFAbilityCAMName(String FAbilityCAMName) {
        this.FAbilityCAMName = FAbilityCAMName;
    }

    public int getFType() {
        return FType;
    }

    public void setFType(int FType) {
        this.FType = FType;
    }

    public Long getFcalcType() {
        return FcalcType;
    }

    public void setFcalcType(Long fcalcType) {
        FcalcType = fcalcType;
    }

    public int getFSZState() {
        return FSZState;
    }

    public void setFSZState(int FSZState) {
        this.FSZState = FSZState;
    }
}
