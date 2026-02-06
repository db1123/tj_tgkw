package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseStandardMapper;
import fun.server.model.TCourseStandard;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.coursestandard.CourseStandardCS;
import fun.server.model.customQuery.coursestandard.CourseStandardData;
import fun.server.service.TCourseStandardService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcoursestandardService")
public class TCourseStandardServiceImpl extends AbstractService<TCourseStandard> implements TCourseStandardService {

    private final TCourseStandardMapper tCourseStandardMapper;

    public TCourseStandardServiceImpl(TCourseStandardMapper tCourseStandardMapper) {
        this.tCourseStandardMapper = tCourseStandardMapper;
    }


    @Override
    public PageInfo<CourseStandardData> getCourseStandardDataList(CourseStandardCS courseStandardCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseStandardMapper.getCourseStandardDataList(courseStandardCS));
    }

    @Override
    public List<CourseStandardData> getCourseStandardDatainsert(CourseStandardCS courseStandardCS) {
        return tCourseStandardMapper.getCourseStandardDatainsert(courseStandardCS);
    }

    @Override
    public List<CourseStandardData> getTAbilityConditionSDatainsert(CourseStandardCS courseStandardCS) {
        return tCourseStandardMapper.getTAbilityConditionSDatainsert(courseStandardCS);
    }

    @Override
    public Integer getCourseabiltyCount(CourseabilityCS courseabilityCS) {
        return tCourseStandardMapper.getCourseabiltyCount(courseabilityCS);
    }
}