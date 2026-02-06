package fun.server.mapper;

import fun.server.model.TCourseStandard;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.coursestandard.CourseStandardCS;
import fun.server.model.customQuery.coursestandard.CourseStandardData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseStandardMapper extends Mapper<TCourseStandard> {
    List<CourseStandardData> getCourseStandardDataList(CourseStandardCS courseStandardCS);


    List<CourseStandardData> getCourseStandardDatainsert(CourseStandardCS courseStandardCS);

    List<CourseStandardData> getTAbilityConditionSDatainsert(CourseStandardCS courseStandardCS);


    Integer getCourseabiltyCount(CourseabilityCS courseabilityCS);
}