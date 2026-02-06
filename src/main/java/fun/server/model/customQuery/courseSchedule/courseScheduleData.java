package fun.server.model.customQuery.courseSchedule;

public class courseScheduleData {


    private Long FKeyID;

    private Long FCOID;

    private Long FCRMID;

    private String FCRMName;

    private int FWeekday;

    private String FStartTime;

    private String FEndTime;

    private String FSession;

    private String FNote;

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public Long getFCOID() {
        return FCOID;
    }

    public void setFCOID(Long FCOID) {
        this.FCOID = FCOID;
    }

    public Long getFCRMID() {
        return FCRMID;
    }

    public void setFCRMID(Long FCRMID) {
        this.FCRMID = FCRMID;
    }

    public String getFCRMName() {
        return FCRMName;
    }

    public void setFCRMName(String FCRMName) {
        this.FCRMName = FCRMName;
    }

    public int getFWeekday() {
        return FWeekday;
    }

    public void setFWeekday(int FWeekday) {
        this.FWeekday = FWeekday;
    }

    public String getFStartTime() {
        return FStartTime;
    }

    public void setFStartTime(String FStartTime) {
        this.FStartTime = FStartTime;
    }

    public String getFEndTime() {
        return FEndTime;
    }

    public void setFEndTime(String FEndTime) {
        this.FEndTime = FEndTime;
    }

    public String getFSession() {
        return FSession;
    }

    public void setFSession(String FSession) {
        this.FSession = FSession;
    }

    public String getFNote() {
        return FNote;
    }

    public void setFNote(String FNote) {
        this.FNote = FNote;
    }
}
