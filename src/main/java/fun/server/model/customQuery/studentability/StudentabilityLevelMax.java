package fun.server.model.customQuery.studentability;

public class StudentabilityLevelMax {

    private Long FKeyID;//学生能力表ID

    private Long FAbilityID;//能力ID
    private Long FTypeID;//能力类型ID
    private String FAbilityName;//能力名称

    private String FAbilityLevelName;//能力等级
    private Long FAbilityLevelID;//能力等级ID

    public Long getFTypeID() {
        return FTypeID;
    }

    public void setFTypeID(Long FTypeID) {
        this.FTypeID = FTypeID;
    }

    public String getFAbilityName() {
        return FAbilityName;
    }

    public void setFAbilityName(String FAbilityName) {
        this.FAbilityName = FAbilityName;
    }

    public Long getFAbilityLevelID() {
        return FAbilityLevelID;
    }

    public void setFAbilityLevelID(Long FAbilityLevelID) {
        this.FAbilityLevelID = FAbilityLevelID;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFAbilityID() {
        return FAbilityID;
    }

    public void setFAbilityID(Long FAbilityID) {
        this.FAbilityID = FAbilityID;
    }

    public String getFAbilityLevelName() {
        return FAbilityLevelName;
    }

    public void setFAbilityLevelName(String FAbilityLevelName) {
        this.FAbilityLevelName = FAbilityLevelName;
    }
}
