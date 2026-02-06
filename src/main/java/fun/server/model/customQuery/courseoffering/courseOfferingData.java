package fun.server.model.customQuery.courseoffering;

public class courseOfferingData {

    private Long FKeyID;//开课ID

    private Long FTPID;

    private String FTPName;

    private Long FCourseID;

    private String FCourseName;

    private String FCourseNo;

    private Long FSemester;

    private String FSemesterName;

    private Long FTeacher;

    private String FTeacherName;

    private int FMaxCapacity;

    private int FCurrentEnrollment;

    private int FStatus;

    private String FNote;

    private String FCDATE;

    private String FUDATE;

    private Long FCID;

    private Long FUID;

    private int FState;

    private Long FCourseFType;//课程类别ID

    private Long FCourseNatureID;//课程性质ID

    private String FEdition;//课程版本号

    public Long getFCourseNatureID() {
        return FCourseNatureID;
    }

    public void setFCourseNatureID(Long FCourseNatureID) {
        this.FCourseNatureID = FCourseNatureID;
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

    public String getFCourseNo() {
        return FCourseNo;
    }

    public void setFCourseNo(String FCourseNo) {
        this.FCourseNo = FCourseNo;
    }

    public int getFState() {
        return FState;
    }

    public void setFState(int FState) {
        this.FState = FState;
    }

    public String getFCDATE() {
        return FCDATE;
    }

    public void setFCDATE(String FCDATE) {
        this.FCDATE = FCDATE;
    }

    public String getFUDATE() {
        return FUDATE;
    }

    public void setFUDATE(String FUDATE) {
        this.FUDATE = FUDATE;
    }

    public Long getFCID() {
        return FCID;
    }

    public void setFCID(Long FCID) {
        this.FCID = FCID;
    }

    public Long getFUID() {
        return FUID;
    }

    public void setFUID(Long FUID) {
        this.FUID = FUID;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFTPID() {
        return FTPID;
    }

    public void setFTPID(Long FTPID) {
        this.FTPID = FTPID;
    }

    public String getFTPName() {
        return FTPName;
    }

    public void setFTPName(String FTPName) {
        this.FTPName = FTPName;
    }

    public Long getFCourseID() {
        return FCourseID;
    }

    public void setFCourseID(Long FCourseID) {
        this.FCourseID = FCourseID;
    }

    public String getFCourseName() {
        return FCourseName;
    }

    public void setFCourseName(String FCourseName) {
        this.FCourseName = FCourseName;
    }

    public Long getFSemester() {
        return FSemester;
    }

    public void setFSemester(Long FSemester) {
        this.FSemester = FSemester;
    }

    public String getFSemesterName() {
        return FSemesterName;
    }

    public void setFSemesterName(String FSemesterName) {
        this.FSemesterName = FSemesterName;
    }

    public Long getFTeacher() {
        return FTeacher;
    }

    public void setFTeacher(Long FTeacher) {
        this.FTeacher = FTeacher;
    }

    public String getFTeacherName() {
        return FTeacherName;
    }

    public void setFTeacherName(String FTeacherName) {
        this.FTeacherName = FTeacherName;
    }

    public int getFMaxCapacity() {
        return FMaxCapacity;
    }

    public void setFMaxCapacity(int FMaxCapacity) {
        this.FMaxCapacity = FMaxCapacity;
    }

    public int getFCurrentEnrollment() {
        return FCurrentEnrollment;
    }

    public void setFCurrentEnrollment(int FCurrentEnrollment) {
        this.FCurrentEnrollment = FCurrentEnrollment;
    }

    public int getFStatus() {
        return FStatus;
    }

    public void setFStatus(int FStatus) {
        this.FStatus = FStatus;
    }

    public String getFNote() {
        return FNote;
    }

    public void setFNote(String FNote) {
        this.FNote = FNote;
    }
}
