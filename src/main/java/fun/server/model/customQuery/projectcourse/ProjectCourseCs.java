package fun.server.model.customQuery.projectcourse;

public class ProjectCourseCs {
    //查询传参
    private String FName;//

    private Long FMajorId;

    private String orderBy;//排序字符串

    public Long getFMajorId() {
        return FMajorId;
    }

    public void setFMajorId(Long FMajorId) {
        this.FMajorId = FMajorId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
