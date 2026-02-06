package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseAbilityCondition;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionCS;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionData;

import java.util.List;

public interface TCourseAbilityConditionService extends BaseService<TCourseAbilityCondition> {


    PageInfo<CourseabilityCondtionData> getCourseAbilityConditionList(CourseabilityCondtionCS courseabilityCondtionCS, int offset, int limit);

    List<Long> getLevelAbilityConditionIDList(CourseabilityCondtionCS courseabilityCondtionCS);
}