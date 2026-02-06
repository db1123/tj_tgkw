package fun.server.model.customQuery.courseAbility;

public class CourseabilityCS {

    private String name;//能力名称

    private Long FTypeID;//能力类型ID

    private Long FCourseID;//课程ID

    private String orderBy;//排序字符串

    public Long getFTypeID() {
        return FTypeID;
    }

    public void setFTypeID(Long FTypeID) {
        this.FTypeID = FTypeID;
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

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }
}
