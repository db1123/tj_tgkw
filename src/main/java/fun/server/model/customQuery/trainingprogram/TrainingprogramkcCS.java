package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramkcCS {

    private int FType;// 1=核心课程 2=课程安排

    private Long FTPID;//方案ID



    private String FName;//课程名称

    private String FNo;//课程编号

    private Long FLTypeID;//课程类别

    private Long FNatureID;//课程性质


    private String orderBy;//排序字符串

    private int FValid;//状态 0=无效 1=有效



    public int getFType() {
        return FType;
    }

    public void setFType(int FType) {
        this.FType = FType;
    }

    public Long getFTPID() {
        return FTPID;
    }

    public void setFTPID(Long FTPID) {
        this.FTPID = FTPID;
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

    public Long getFLTypeID() {
        return FLTypeID;
    }

    public void setFLTypeID(Long FLTypeID) {
        this.FLTypeID = FLTypeID;
    }

    public Long getFNatureID() {
        return FNatureID;
    }

    public void setFNatureID(Long FNatureID) {
        this.FNatureID = FNatureID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getFValid() {
        return FValid;
    }

    public void setFValid(int FValid) {
        this.FValid = FValid;
    }
}
