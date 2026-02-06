package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramCS {

    private Long FKeyID;//

    private String FMjorName;//专业名称

    private String FName;//培养方案名称

    private String FCollegeName;//学院名称

    private Long FMjorID;//专业ID

    private Long FCollegeID;//学院ID

    private String orderBy;//排序字符串

    private int FValid;//状态 0=无效 1=有效

    public int getFValid() {
        return FValid;
    }

    public void setFValid(int FValid) {
        this.FValid = FValid;
    }

    public String getFMjorName() {

        return FMjorName;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public void setFMjorName(String FMjorName) {
        this.FMjorName = FMjorName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFCollegeName() {
        return FCollegeName;
    }

    public void setFCollegeName(String FCollegeName) {
        this.FCollegeName = FCollegeName;
    }

    public Long getFMjorID() {
        return FMjorID;
    }

    public void setFMjorID(Long FMjorID) {
        this.FMjorID = FMjorID;
    }

    public Long getFCollegeID() {
        return FCollegeID;
    }

    public void setFCollegeID(Long FCollegeID) {
        this.FCollegeID = FCollegeID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
