package fun.server.model.customQuery.projectcourse;

public class ProjectCoursedata {

    private Long FKeyID;// t_training_program_course fkeyid

    private String FTrainingProgramName;//培养方案名称

    private String FMajorName;//专业名称

    private String FCourseName;//课程名称

    private String FCourseNo;//课程编号

    private String FCourseEdition;//课程版本

    private String FName;//拼接后的名称

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFTrainingProgramName() {
        return FTrainingProgramName;
    }

    public void setFTrainingProgramName(String FTrainingProgramName) {
        this.FTrainingProgramName = FTrainingProgramName;
    }

    public String getFMajorName() {
        return FMajorName;
    }

    public void setFMajorName(String FMajorName) {
        this.FMajorName = FMajorName;
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

    public String getFCourseEdition() {
        return FCourseEdition;
    }

    public void setFCourseEdition(String FCourseEdition) {
        this.FCourseEdition = FCourseEdition;
    }
}
