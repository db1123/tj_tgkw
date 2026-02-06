package fun.server.mapper;

import fun.server.model.TCourseAbilityCondition;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionCS;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseAbilityConditionMapper extends Mapper<TCourseAbilityCondition> {
    List<CourseabilityCondtionData> getCourseAbilityConditionList(CourseabilityCondtionCS courseabilityCondtionCS);
    List<Long> getLevelAbilityConditionIDList(CourseabilityCondtionCS courseabilityCondtionCS);
}