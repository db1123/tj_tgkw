package fun.server.model.customQuery.coursestudent;

public class CoursestudentscoreData {

    private Long FKeyID; //考核方式ID

    private String FMethodName;//考核方式名称

    private String Conditions;// 考核条件+分数 Java基础语法 (20)，异常处理 (10)，数据库基础 (15)，集合框架 (15)，面向对象编程（OOP） (20)

    private String ConditionIDs;//考核条件ID 逗号间隔

    private String ConditionScores;//条件分数 逗号间隔

    private String TotalScore;//分数总和

    private float FMethodWeight;//权重

    private int FScoreMin;//能力等级最低分数(合格分数线)

    public int getFScoreMin() {
        return FScoreMin;
    }

    public void setFScoreMin(int FScoreMin) {
        this.FScoreMin = FScoreMin;
    }

    public float getFMethodWeight() {
        return FMethodWeight;
    }

    public void setFMethodWeight(float FMethodWeight) {
        this.FMethodWeight = FMethodWeight;
    }

    public Long getFKeyID() {
        return FKeyID;
    }

    public void setFKeyID(Long FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String getFMethodName() {
        return FMethodName;
    }

    public void setFMethodName(String FMethodName) {
        this.FMethodName = FMethodName;
    }

    public String getConditions() {
        return Conditions;
    }

    public void setConditions(String conditions) {
        Conditions = conditions;
    }

    public String getConditionIDs() {
        return ConditionIDs;
    }

    public void setConditionIDs(String conditionIDs) {
        ConditionIDs = conditionIDs;
    }

    public String getConditionScores() {
        return ConditionScores;
    }

    public void setConditionScores(String conditionScores) {
        ConditionScores = conditionScores;
    }

    public String getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(String totalScore) {
        TotalScore = totalScore;
    }
}
