package fun.server.model.customQuery.courseabilityCondition;

public class CourseabilityCondtionCS {

    private String name;//考核条件名称

    private Long FCourseID;//课程ID
    private String orderBy;//排序字符串

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
