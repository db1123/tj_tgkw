package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramkcData {


    private Long FKeyID;//课程ID
    private String FNo;//课程名称
    private String FName;//课程名称

    private String FYWName;//课程英文名称

    private Long FTypeID;//类别ID

    private Long FTypeName;//类别名称

    private Long FNatureID;//性质ID

    private String FNatureName;//性质名称

    private String FCDATE;//
    
    private String FUDATE;//

    private String FCredits;//学分

    private String FSJZS;//实践周数

    private String FJYXNXQ;//学年学期

    private int FIstk;//是否停课

    private String FEdition;

    private String FCon;

    private String FTTPName;//培养方案名称


    private String FXN;

    private Integer FXQ;

    private Integer FXF;

    public String getFXN() {
        return FXN;
    }

    public void setFXN(String FXN) {
        this.FXN = FXN;
    }

    public Integer getFXQ() {
        return FXQ;
    }

    public void setFXQ(Integer FXQ) {
        this.FXQ = FXQ;
    }

    public Integer getFXF() {
        return FXF;
    }

    public void setFXF(Integer FXF) {
        this.FXF = FXF;
    }

    public String getFTTPName() {
        return FTTPName;
    }

    public void setFTTPName(String FTTPName) {
        this.FTTPName = FTTPName;
    }

    public String getFCon() {
        return FCon;
    }

    public void setFCon(String FCon) {
        this.FCon = FCon;
    }

    public String getFEdition() {
        return FEdition;
    }

    public void setFEdition(String FEdition) {
        this.FEdition = FEdition;
    }

    private Long FTrainingCourseID;//培养方案 课程安排ID

    public Long getFTrainingCourseID() {
        return FTrainingCourseID;
    }

    public void setFTrainingCourseID(Long FTrainingCourseID) {
        this.FTrainingCourseID = FTrainingCourseID;
    }

    public int getFIstk() {
        return FIstk;
    }

    public void setFIstk(int FIstk) {
        this.FIstk = FIstk;
    }

    public String getFCredits() {
        return FCredits;
    }

    public void setFCredits(String FCredits) {
        this.FCredits = FCredits;
    }

    public String getFSJZS() {
        return FSJZS;
    }

    public void setFSJZS(String FSJZS) {
        this.FSJZS = FSJZS;
    }

    public String getFJYXNXQ() {
        return FJYXNXQ;
    }

    public void setFJYXNXQ(String FJYXNXQ) {
        this.FJYXNXQ = FJYXNXQ;
    }

    public String getFNo() {
        return FNo;
    }

    public void setFNo(String FNo) {
        this.FNo = FNo;
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

    public String getFYWName() {
        return FYWName;
    }

    public void setFYWName(String FYWName) {
        this.FYWName = FYWName;
    }

    public Long getFTypeID() {
        return FTypeID;
    }

    public void setFTypeID(Long FTypeID) {
        this.FTypeID = FTypeID;
    }

    public Long getFTypeName() {
        return FTypeName;
    }

    public void setFTypeName(Long FTypeName) {
        this.FTypeName = FTypeName;
    }

    public Long getFNatureID() {
        return FNatureID;
    }

    public void setFNatureID(Long FNatureID) {
        this.FNatureID = FNatureID;
    }

    public String getFNatureName() {
        return FNatureName;
    }

    public void setFNatureName(String FNatureName) {
        this.FNatureName = FNatureName;
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
}
