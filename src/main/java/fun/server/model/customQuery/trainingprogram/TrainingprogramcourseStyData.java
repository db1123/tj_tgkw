package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramcourseStyData {

    private Long FKeyID;//

    private String FName;

    private Long FPID;

    private String FCNatureName;
    
    private Float FXF;

    private Integer FTotalHours;

    private Integer FWeeklyStudyHours;

    private Integer FTheoreticalStudyHours;

    private Integer FPracticalStudyHours;

    private Integer TotalAllHours;//总计


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

    public Long getFPID() {
        return FPID;
    }

    public void setFPID(Long FPID) {
        this.FPID = FPID;
    }

    public String getFCNatureName() {
        return FCNatureName;
    }

    public void setFCNatureName(String FCNatureName) {
        this.FCNatureName = FCNatureName;
    }

    public Float getFXF() {
        return FXF;
    }

    public void setFXF(Float FXF) {
        this.FXF = FXF;
    }

    public Integer getFTotalHours() {
        return FTotalHours;
    }

    public void setFTotalHours(Integer FTotalHours) {
        this.FTotalHours = FTotalHours;
    }

    public Integer getFWeeklyStudyHours() {
        return FWeeklyStudyHours;
    }

    public void setFWeeklyStudyHours(Integer FWeeklyStudyHours) {
        this.FWeeklyStudyHours = FWeeklyStudyHours;
    }

    public Integer getFTheoreticalStudyHours() {
        return FTheoreticalStudyHours;
    }

    public void setFTheoreticalStudyHours(Integer FTheoreticalStudyHours) {
        this.FTheoreticalStudyHours = FTheoreticalStudyHours;
    }

    public Integer getFPracticalStudyHours() {
        return FPracticalStudyHours;
    }

    public void setFPracticalStudyHours(Integer FPracticalStudyHours) {
        this.FPracticalStudyHours = FPracticalStudyHours;
    }

    public Integer getTotalAllHours() {
        return TotalAllHours;
    }

    public void setTotalAllHours(Integer totalAllHours) {
        TotalAllHours = totalAllHours;
    }
}
