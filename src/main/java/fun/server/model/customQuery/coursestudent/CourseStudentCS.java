package fun.server.model.customQuery.coursestudent;

public class CourseStudentCS {


    private String FCourseName;//课程名称

    private String FStudentName;//学生名称

    private String FStudentNo;//学生编号

    private String FTel;//学生电话

    private Long FLTypeID;//课程类别

    private Long FNatureID;//课程性质

    private String orderBy;//排序字符串

    private int FSex; //性别

    public String getFStudentNo() {
        return FStudentNo;
    }

    public void setFStudentNo(String FStudentNo) {
        this.FStudentNo = FStudentNo;
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

    public int getFSex() {
        return FSex;
    }

    public void setFSex(int FSex) {
        this.FSex = FSex;
    }

    public String getFCourseName() {
        return FCourseName;
    }

    public void setFCourseName(String FCourseName) {
        this.FCourseName = FCourseName;
    }

    public String getFStudentName() {
        return FStudentName;
    }

    public void setFStudentName(String FStudentName) {
        this.FStudentName = FStudentName;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
