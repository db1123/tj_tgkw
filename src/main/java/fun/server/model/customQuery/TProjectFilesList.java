package fun.server.model.customQuery;


public class TProjectFilesList {

    private Long fkeyid;
    private String FFileName;
    private String FEdition;
    private Integer FEditionNo;
    private String FValidName;
    private Integer FValid;
    private String FNote;
    private String FUrl;
    private Long FProjectID;
    private Long FTaskID;
    private Long FFileID;




    public String getFNote() {
        return FNote;
    }

    public void setFNote(String FNote) {
        this.FNote = FNote;
    }

    public Integer getFValid() {
        return FValid;
    }

    public void setFValid(Integer FValid) {
        this.FValid = FValid;
    }

    public String getFValidName() {
        return FValidName;
    }

    public void setFValidName(String FValidName) {
        this.FValidName = FValidName;
    }

    public Integer getFEditionNo() {
        return FEditionNo;
    }

    public void setFEditionNo(Integer FEditionNo) {
        this.FEditionNo = FEditionNo;
    }

    public String getFEdition() {
        return FEdition;
    }

    public void setFEdition(String FEdition) {
        this.FEdition = FEdition;
    }

    public String getFFileName() {
        return FFileName;
    }

    public void setFFileName(String FFileName) {
        this.FFileName = FFileName;
    }



    public String getFUrl() {
        return FUrl;
    }

    public void setFUrl(String FUrl) {
        this.FUrl = FUrl;
    }

    public Long getFTaskID() {
        return FTaskID;
    }

    public void setFTaskID(Long FTaskID) {
        this.FTaskID = FTaskID;
    }

    public Long getFProjectID() {
        return FProjectID;
    }

    public void setFProjectID(Long FProjectID) {
        this.FProjectID = FProjectID;
    }

    public Long getFFileID() {
        return FFileID;
    }

    public void setFFileID(Long FFileID) {
        this.FFileID = FFileID;
    }

    public Long getFkeyid() {
        return fkeyid;
    }

    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }
}
