package fun.server.model.customQuery.studentability;

public class StudentabilityCs {
    //查询传参

    private String FName;//学生名称

    private String FTel;//联系电话

    private String FAbilityName;//能力名称

    private int FState;//状态，0-申请，1-获取，2-注销

    private String orderBy;//排序字符串

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }

    public String getFAbilityName() {
        return FAbilityName;
    }

    public void setFAbilityName(String FAbilityName) {
        this.FAbilityName = FAbilityName;
    }

    public int getFState() {
        return FState;
    }

    public void setFState(int FState) {
        this.FState = FState;
    }
}
