package fun.server.model.customQuery.tclass;

public class tclassopenCS {

    private String FCollegeName;

    private String FMajorName;

    private Long FGradeID;

    private String FClassName;

    private Long courseID;//开课申请ID

    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getFCollegeName() {
        return FCollegeName;
    }

    public void setFCollegeName(String FCollegeName) {
        this.FCollegeName = FCollegeName;
    }

    public String getFMajorName() {
        return FMajorName;
    }

    public void setFMajorName(String FMajorName) {
        this.FMajorName = FMajorName;
    }

    public Long getFGradeID() {
        return FGradeID;
    }

    public void setFGradeID(Long FGradeID) {
        this.FGradeID = FGradeID;
    }

    public String getFClassName() {
        return FClassName;
    }

    public void setFClassName(String FClassName) {
        this.FClassName = FClassName;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}
