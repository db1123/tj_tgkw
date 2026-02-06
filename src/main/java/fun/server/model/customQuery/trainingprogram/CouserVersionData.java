package fun.server.model.customQuery.trainingprogram;

public class CouserVersionData {


    private Long FKeyID;//课程ID
    private String FNo;//课程名称
    private String FName;//课程名称

    private String FYWName;//课程英文名称

    private Long FTypeID;//类别ID

    private String FTypeName;//类别名称

    private Long FNatureID;//性质ID

    private String FNatureName;//性质名称

    private String FCDATE;//

    private String FUDATE;//

    private String FCredits;//学分

    private String FSJZS;//实践周数

    private String FJYXNXQ;//学年学期

    private int FIstk;//是否停课

    private int  FEditionNo;//版本号

    private String FEdition;//版本

    private int FValid;//是否有效

    private Long FParentId;//历史版本父节点

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFNo() {
        return FNo;
    }

    public void setFNo(String FNo) {
        this.FNo = FNo;
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

    public String getFTypeName() {
        return FTypeName;
    }

    public void setFTypeName(String FTypeName) {
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

    public int getFIstk() {
        return FIstk;
    }

    public void setFIstk(int FIstk) {
        this.FIstk = FIstk;
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

    public Long getFParentId() {
        return FParentId;
    }

    public void setFParentId(Long FParentId) {
        this.FParentId = FParentId;
    }
}
