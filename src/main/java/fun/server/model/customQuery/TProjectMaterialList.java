package fun.server.model.customQuery;

public class TProjectMaterialList {

    private Long fkeyid;//bomsid

    private Long FMID;//物料ID

    private String FNo;

    private String FName;


    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFNo() {
        return FNo;
    }

    public void setFNo(String FNo) {
        this.FNo = FNo;
    }

    public Long getFkeyid() {
        return fkeyid;
    }

    public void setFkeyid(Long fkeyid) {
        this.fkeyid = fkeyid;
    }

    public Long getFMID() {
        return FMID;
    }

    public void setFMID(Long FMID) {
        this.FMID = FMID;
    }
}
