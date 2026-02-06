package fun.server.model.customQuery.courseoffering;

public class courseOfferingCS {

    private Long FKeyID;//

    private String FTPName;//培养方案名称

    private String FCourseName;//课程名称

    private String FCourseNo;//课程编号

    private String FSemesterName;//学期名称

    private String FTeacherName;//授课教师姓名

    private String orderBy;//排序字符串

    public String getFCourseNo() {
        return FCourseNo;
    }

    public void setFCourseNo(String FCourseNo) {
        this.FCourseNo = FCourseNo;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFTPName() {
        return FTPName;
    }

    public void setFTPName(String FTPName) {
        this.FTPName = FTPName;
    }

    public String getFCourseName() {
        return FCourseName;
    }

    public void setFCourseName(String FCourseName) {
        this.FCourseName = FCourseName;
    }

    public String getFSemesterName() {
        return FSemesterName;
    }

    public void setFSemesterName(String FSemesterName) {
        this.FSemesterName = FSemesterName;
    }

    public String getFTeacherName() {
        return FTeacherName;
    }

    public void setFTeacherName(String FTeacherName) {
        this.FTeacherName = FTeacherName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
