package fun.server.model.customQuery.major;

public class TClassCS {

    private Long FClassID;

    private Long FStudentID;

    private String orderBy;//排序字符串

    private String FClassName;

    private String FStudentName;

    public String getFClassName() {
        return FClassName;
    }

    public void setFClassName(String FClassName) {
        this.FClassName = FClassName;
    }

    public String getFStudentName() {
        return FStudentName;
    }

    public void setFStudentName(String FStudentName) {
        this.FStudentName = FStudentName;
    }

    public Long getFClassID() {
        return FClassID;
    }

    public void setFClassID(Long FClassID) {
        this.FClassID = FClassID;
    }

    public Long getFStudentID() {
        return FStudentID;
    }

    public void setFStudentID(Long FStudentID) {
        this.FStudentID = FStudentID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
