package fun.server.model.customQuery.trainingprogram;

public class TTrainingprogramcourseGxData {
    private Long FKeyID;

    private Long FCourseID;

    private String FName;

    private String FNo;

    private Integer FKKXQ;//学期

    private Long FCNature;

    public Long getFCNature() {
        return FCNature;
    }

    public void setFCNature(Long FCNature) {
        this.FCNature = FCNature;
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

    public Integer getFKKXQ() {
        return FKKXQ;
    }

    public void setFKKXQ(Integer FKKXQ) {
        this.FKKXQ = FKKXQ;
    }
}
