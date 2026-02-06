package fun.server.model.customQuery.coursemb;

public class CourseMbData {

    //模版中添加数据信息
    //包含 课程类别与课程性质的字段

    private Long FKeyID;//类别ID或者性质ID

    private String FName;//名称

    private String FMName;//带层数的名称

    public String getFMName() {
        return FMName;
    }

    public void setFMName(String FMName) {
        this.FMName = FMName;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }
}
