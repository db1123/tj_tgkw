package fun.server.model.customQuery.courseabilityCondition;

public class CourseabilityCondtionData {


    private Long FCourseAbilityConditionID; //考核条件ID
    private String FCourseAbilityConditionName;//考核条件名称


    private float FScore;//达标分数

    private String FNote;//描述

    public float getFScore() {
        return FScore;
    }

    public void setFScore(float FScore) {
        this.FScore = FScore;
    }

    public String getFNote() {
        return FNote;
    }

    public void setFNote(String FNote) {
        this.FNote = FNote;
    }



    public Long getFCourseAbilityConditionID() {
        return FCourseAbilityConditionID;
    }

    public void setFCourseAbilityConditionID(Long FCourseAbilityConditionID) {
        this.FCourseAbilityConditionID = FCourseAbilityConditionID;
    }

    public String getFCourseAbilityConditionName() {
        return FCourseAbilityConditionName;
    }

    public void setFCourseAbilityConditionName(String FCourseAbilityConditionName) {
        this.FCourseAbilityConditionName = FCourseAbilityConditionName;
    }


}
