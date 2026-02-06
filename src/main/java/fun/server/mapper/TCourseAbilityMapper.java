package fun.server.mapper;

import fun.server.model.TCourseAbility;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.courseAbility.CourseabilityData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseAbilityMapper extends Mapper<TCourseAbility> {
    List<CourseabilityData> getCourseAbility(CourseabilityCS courseabilityCS);

    List<CourseabilityData> getCourseAbilityList(CourseabilityCS courseabilityCS);
}