package fun.server.model.customQuery.coursestudent;

public class CourseStudendata {


    private Long FCourseID;//课程ID

    private Long FCourseStudentID;//课程报名学生表ID

    private String FCourseName;//课程名称

    private String FYWName;//英文名称

    private String FCourseNo;//课程编号

    private String FStudentName;//学生名称

    private Long FStudentID;//学生ID

    private String FStudentNo;//学生编号

    private int FStudentSex;//学生性别

    private float FScore;//分数

    private int FIfPass ;//是否通过

    private String FTel;//电话

    private Long FCourseFType;//课程类别ID

    private Long FCourseNatureID;//课程性质ID

    private String FEdition;//课程版本号

    private String FClassName;//班级名称

    public String getFClassName() {
        return FClassName;
    }

    public void setFClassName(String FClassName) {
        this.FClassName = FClassName;
    }

    public String getFStudentNo() {
        return FStudentNo;
    }

    public void setFStudentNo(String FStudentNo) {
        this.FStudentNo = FStudentNo;
    }

    public int getFStudentSex() {
        return FStudentSex;
    }

    public void setFStudentSex(int FStudentSex) {
        this.FStudentSex = FStudentSex;
    }

    public String getFYWName() {
        return FYWName;
    }

    public void setFYWName(String FYWName) {
        this.FYWName = FYWName;
    }

    public String getFEdition() {
        return FEdition;
    }

    public void setFEdition(String FEdition) {
        this.FEdition = FEdition;
    }

    public Long getFCourseFType() {
        return FCourseFType;
    }

    public void setFCourseFType(Long FCourseFType) {
        this.FCourseFType = FCourseFType;
    }

    public Long getFCourseNatureID() {
        return FCourseNatureID;
    }

    public void setFCourseNatureID(Long FCourseNatureID) {
        this.FCourseNatureID = FCourseNatureID;
    }

    public String getFTel() {
        return FTel;
    }

    public void setFTel(String FTel) {
        this.FTel = FTel;
    }

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }

    public Long getFCourseStudentID() {
        return FCourseStudentID;
    }

    public void setFCourseStudentID(Long FCourseStudentID) {
        this.FCourseStudentID = FCourseStudentID;
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

    public String getFStudentName() {
        return FStudentName;
    }

    public void setFStudentName(String FStudentName) {
        this.FStudentName = FStudentName;
    }

    public Long getFStudentID() {
        return FStudentID;
    }

    public void setFStudentID(Long FStudentID) {
        this.FStudentID = FStudentID;
    }

    public float getFScore() {
        return FScore;
    }

    public void setFScore(float FScore) {
        this.FScore = FScore;
    }

    public int getFIfPass() {
        return FIfPass;
    }

    public void setFIfPass(int FIfPass) {
        this.FIfPass = FIfPass;
    }
}
