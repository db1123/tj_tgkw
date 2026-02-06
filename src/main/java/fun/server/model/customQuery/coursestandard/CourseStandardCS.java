package fun.server.model.customQuery.coursestandard;

public class CourseStandardCS {

    private String FTJName;//条件名称

    private String FNLName;//能力名称

    private String FNLDJName;//能力等级名称

    private int FSZState;//设置状态

    private String orderBy;//排序字符串

    private int FType;//类型

    private Long FCourseID;

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }

    public String getFTJName() {
        return FTJName;
    }

    public void setFTJName(String FTJName) {
        this.FTJName = FTJName;
    }

    public String getFNLName() {
        return FNLName;
    }

    public void setFNLName(String FNLName) {
        this.FNLName = FNLName;
    }

    public String getFNLDJName() {
        return FNLDJName;
    }

    public void setFNLDJName(String FNLDJName) {
        this.FNLDJName = FNLDJName;
    }

    public int getFSZState() {
        return FSZState;
    }

    public void setFSZState(int FSZState) {
        this.FSZState = FSZState;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getFType() {
        return FType;
    }

    public void setFType(int FType) {
        this.FType = FType;
    }
}
