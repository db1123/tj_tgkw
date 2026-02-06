package fun.server.model.customQuery.courseSchedule;

public class courseScheduleCS {

    private Long FKeyID;//开课时间表ID

    private String orderBy;//排序字符串

    private Long FCOID;//开课表ID

    private String FCRMName;//教室

    private int FWeekday;//星期


    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getFCOID() {
        return FCOID;
    }

    public void setFCOID(Long FCOID) {
        this.FCOID = FCOID;
    }

    public String getFCRMName() {
        return FCRMName;
    }

    public void setFCRMName(String FCRMName) {
        this.FCRMName = FCRMName;
    }

    public int getFWeekday() {
        return FWeekday;
    }

    public void setFWeekday(int FWeekday) {
        this.FWeekday = FWeekday;
    }
}
