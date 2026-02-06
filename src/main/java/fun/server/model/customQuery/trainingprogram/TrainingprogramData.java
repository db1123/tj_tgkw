package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramData {


    private Long FKeyID;

    private Long FMjorID;//专业ID

    private int FState;

    private String FName;//培养方案名称

    private String FJS;//培养方案介绍

    private String FZYJS;//专业介绍

    private int  FEditionNo;//版本号

    private String FEdition;//版本

    private int FValid;//是否有效

    private String FMjorName;//专业名称

    private String FCollegeName;//学院名称

    private String FCDATE;//
    
    private String FUDATE;//

    private Long FParentId;//历史版本父节点

    public Long getFParentId() {
        return FParentId;
    }

    public void setFParentId(Long FParentId) {
        this.FParentId = FParentId;
    }

    public String getFCDATE() {
        return FCDATE;
    }

    public void setFCDATE(String FCDATE) {
        this.FCDATE = FCDATE;
    }

    public String getFUDATE() {
        return FUDATE;
    }

    public void setFUDATE(String FUDATE) {
        this.FUDATE = FUDATE;
    }

    public int getFState() {
        return FState;
    }

    public void setFState(int FState) {
        this.FState = FState;
    }

    public Long getFMjorID() {
        return FMjorID;
    }

    public void setFMjorID(Long FMjorID) {
        this.FMjorID = FMjorID;
    }

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

    public String getFJS() {
        return FJS;
    }

    public void setFJS(String FJS) {
        this.FJS = FJS;
    }

    public String getFZYJS() {
        return FZYJS;
    }

    public void setFZYJS(String FZYJS) {
        this.FZYJS = FZYJS;
    }

    public int getFEditionNo() {
        return FEditionNo;
    }

    public void setFEditionNo(int FEditionNo) {
        this.FEditionNo = FEditionNo;
    }

    public String getFEdition() {
        return FEdition;
    }

    public void setFEdition(String FEdition) {
        this.FEdition = FEdition;
    }

    public int getFValid() {
        return FValid;
    }

    public void setFValid(int FValid) {
        this.FValid = FValid;
    }

    public String getFMjorName() {
        return FMjorName;
    }

    public void setFMjorName(String FMjorName) {
        this.FMjorName = FMjorName;
    }

    public String getFCollegeName() {
        return FCollegeName;
    }

    public void setFCollegeName(String FCollegeName) {
        this.FCollegeName = FCollegeName;
    }
}
