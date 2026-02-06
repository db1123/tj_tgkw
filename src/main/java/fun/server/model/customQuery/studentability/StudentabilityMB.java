package fun.server.model.customQuery.studentability;

public class StudentabilityMB {

    private Long FTabKeyID;

    private Long FTLevelID;

    private String FTABName;

    private String FTLevelName;

    private String FTypeName;

    public String getFTypeName() {
        return FTypeName;
    }

    public void setFTypeName(String FTypeName) {
        this.FTypeName = FTypeName;
    }

    public Long getFTabKeyID() {
        return FTabKeyID;
    }

    public void setFTabKeyID(Long FTabKeyID) {
        this.FTabKeyID = FTabKeyID;
    }

    public Long getFTLevelID() {
        return FTLevelID;
    }

    public void setFTLevelID(Long FTLevelID) {
        this.FTLevelID = FTLevelID;
    }

    public String getFTABName() {
        return FTABName;
    }

    public void setFTABName(String FTABName) {
        this.FTABName = FTABName;
    }

    public String getFTLevelName() {
        return FTLevelName;
    }

    public void setFTLevelName(String FTLevelName) {
        this.FTLevelName = FTLevelName;
    }
}
