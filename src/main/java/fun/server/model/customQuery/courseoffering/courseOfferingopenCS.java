package fun.server.model.customQuery.courseoffering;

public class courseOfferingopenCS {

    private Long FKeyID;//

    private Long FLTypeID;//课程类别

    private Long FNatureID;//课程性质

    private String FCourseName;//课程名称

    private String FCourseNo;//课程编号

    private String orderBy;//排序字符串

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFLTypeID() {
        return FLTypeID;
    }

    public void setFLTypeID(Long FLTypeID) {
        this.FLTypeID = FLTypeID;
    }

    public Long getFNatureID() {
        return FNatureID;
    }

    public void setFNatureID(Long FNatureID) {
        this.FNatureID = FNatureID;
    }

    public String getFCourseName() {
        return FCourseName;
    }

    public void setFCourseName(String FCourseName) {
        this.FCourseName = FCourseName;
    }

    public String getFCourseNo() {
        return FCourseNo;
    }

    public void setFCourseNo(String FCourseNo) {
        this.FCourseNo = FCourseNo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
