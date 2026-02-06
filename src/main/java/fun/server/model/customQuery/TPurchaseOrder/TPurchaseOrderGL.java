package fun.server.model.customQuery.TPurchaseOrder;

//采购订单管理
public class TPurchaseOrderGL {

    private Long FKeyID;//采购订单ID
    private String FPOrderNo;//订单编号
    private String FProjectName;//所属项目
    private String FEndDate;//交期
    private String FSuppName;//供应商
    private String FSuppContact;//联系人
    private String FSuppNumber;//联系电话
    private Double FAllMoney;//总金额
    private String FLowsName;//工作流名称
    private Long FSupplierID;//供应商id
    private String FNote;//备注
    private int FAllMaterialNum;//物料总数
    private int FState;//状态

    private String FUserID;//订单负责人

    public String getFUserID() {
        return FUserID;
    }

    public void setFUserID(String FUserID) {
        this.FUserID = FUserID;
    }

    public String getFNote() {
        return FNote;
    }

    public void setFNote(String FNote) {
        this.FNote = FNote;
    }

    public int getFAllMaterialNum() {
        return FAllMaterialNum;
    }

    public void setFAllMaterialNum(int FAllMaterialNum) {
        this.FAllMaterialNum = FAllMaterialNum;
    }

    public Long getFSupplierID() {
        return FSupplierID;
    }

    public void setFSupplierID(Long FSupplierID) {
        this.FSupplierID = FSupplierID;
    }

    public int getFState() {
        return FState;
    }

    public void setFState(int FState) {
        this.FState = FState;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFPOrderNo() {
        return FPOrderNo;
    }

    public void setFPOrderNo(String FPOrderNo) {
        this.FPOrderNo = FPOrderNo;
    }

    public String getFProjectName() {
        return FProjectName;
    }

    public void setFProjectName(String FProjectName) {
        this.FProjectName = FProjectName;
    }

    public String getFEndDate() {
        return FEndDate;
    }

    public void setFEndDate(String FEndDate) {
        this.FEndDate = FEndDate;
    }

    public String getFSuppName() {
        return FSuppName;
    }

    public void setFSuppName(String FSuppName) {
        this.FSuppName = FSuppName;
    }

    public String getFSuppContact() {
        return FSuppContact;
    }

    public void setFSuppContact(String FSuppContact) {
        this.FSuppContact = FSuppContact;
    }

    public String getFSuppNumber() {
        return FSuppNumber;
    }

    public void setFSuppNumber(String FSuppNumber) {
        this.FSuppNumber = FSuppNumber;
    }

    public Double getFAllMoney() {
        return FAllMoney;
    }

    public void setFAllMoney(Double FAllMoney) {
        this.FAllMoney = FAllMoney;
    }

    public String getFLowsName() {
        return FLowsName;
    }

    public void setFLowsName(String FLowsName) {
        this.FLowsName = FLowsName;
    }


}
