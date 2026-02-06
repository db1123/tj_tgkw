package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseAbility;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.courseAbility.CourseabilityData;

public interface TCourseAbilityService extends BaseService<TCourseAbility> {
    PageInfo<CourseabilityData> getCourseAbility(CourseabilityCS courseabilityCS, int offset, int limit);

    PageInfo<CourseabilityData> getCourseAbilityList(CourseabilityCS courseabilityCS, int offset, int limit);
}