package fun.server.model.customQuery.trainingprogram;

public class TrainingprogramkcstData {


     private Long FKeyID;//关联表ID

     private Long FTMID;//专业ID
     
     private Long FTPID;//培养方案ID

     private Long FCourseID;//课程ID

     private String FCourseName;//课程名称
     
     private int FOrder;//排序

     private float FXF;//学分

     private String FTotalHours;//总学时

     private String FWeeklyStudyHours;//周学时

     private String FTheoreticalStudyHours;//理论学时

     private String FPracticalStudyHours;//实践学时

     private int FPPState;//课程同步状态

     private int FYZState;//培养方案验证状态

     private String FMajorName;//专业名称

     private String FNo;//课程编号

     private String FYWName;//课程英文名称

     private String FTypeName;//课程类别

     private String FCNatureName;//课程性质

     private Long FType;//课程类别ID

     private Integer FCourseState;//课程状态

     private String FSemesterName;

     public String getFSemesterName() {
          return FSemesterName;
     }

     public void setFSemesterName(String FSemesterName) {
          this.FSemesterName = FSemesterName;
     }

     public Integer getFCourseState() {
          return FCourseState;
     }

     public void setFCourseState(Integer FCourseState) {
          this.FCourseState = FCourseState;
     }

     public Long getFType() {
          return FType;
     }

     public void setFType(Long FType) {
          this.FType = FType;
     }

     public Long getFKeyID() {
          return FKeyID;
     }

     public void setFKeyID(Long FKeyID) {
          this.FKeyID = FKeyID;
     }

     public Long getFTMID() {
          return FTMID;
     }

     public void setFTMID(Long FTMID) {
          this.FTMID = FTMID;
     }

     public Long getFTPID() {
          return FTPID;
     }

     public void setFTPID(Long FTPID) {
          this.FTPID = FTPID;
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

     public int getFOrder() {
          return FOrder;
     }

     public void setFOrder(int FOrder) {
          this.FOrder = FOrder;
     }

     public float getFXF() {
          return FXF;
     }

     public void setFXF(float FXF) {
          this.FXF = FXF;
     }

     public String getFTotalHours() {
          return FTotalHours;
     }

     public void setFTotalHours(String FTotalHours) {
          this.FTotalHours = FTotalHours;
     }

     public String getFWeeklyStudyHours() {
          return FWeeklyStudyHours;
     }

     public void setFWeeklyStudyHours(String FWeeklyStudyHours) {
          this.FWeeklyStudyHours = FWeeklyStudyHours;
     }

     public String getFTheoreticalStudyHours() {
          return FTheoreticalStudyHours;
     }

     public void setFTheoreticalStudyHours(String FTheoreticalStudyHours) {
          this.FTheoreticalStudyHours = FTheoreticalStudyHours;
     }

     public String getFPracticalStudyHours() {
          return FPracticalStudyHours;
     }

     public void setFPracticalStudyHours(String FPracticalStudyHours) {
          this.FPracticalStudyHours = FPracticalStudyHours;
     }

     public int getFPPState() {
          return FPPState;
     }

     public void setFPPState(int FPPState) {
          this.FPPState = FPPState;
     }

     public int getFYZState() {
          return FYZState;
     }

     public void setFYZState(int FYZState) {
          this.FYZState = FYZState;
     }

     public String getFMajorName() {
          return FMajorName;
     }

     public void setFMajorName(String FMajorName) {
          this.FMajorName = FMajorName;
     }

     public String getFNo() {
          return FNo;
     }

     public void setFNo(String FNo) {
          this.FNo = FNo;
     }

     public String getFYWName() {
          return FYWName;
     }

     public void setFYWName(String FYWName) {
          this.FYWName = FYWName;
     }

     public String getFTypeName() {
          return FTypeName;
     }

     public void setFTypeName(String FTypeName) {
          this.FTypeName = FTypeName;
     }

     public String getFCNatureName() {
          return FCNatureName;
     }

     public void setFCNatureName(String FCNatureName) {
          this.FCNatureName = FCNatureName;
     }
}
