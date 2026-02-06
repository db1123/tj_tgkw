package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseStandard;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.coursestandard.CourseStandardCS;
import fun.server.model.customQuery.coursestandard.CourseStandardData;

import java.util.List;

public interface TCourseStandardService extends BaseService<TCourseStandard> {
    PageInfo<CourseStandardData> getCourseStandardDataList(CourseStandardCS courseStandardCS,int offset, int limit);


    List<CourseStandardData> getCourseStandardDatainsert(CourseStandardCS courseStandardCS);

    List<CourseStandardData> getTAbilityConditionSDatainsert(CourseStandardCS courseStandardCS);

    Integer getCourseabiltyCount(CourseabilityCS courseabilityCS);
}