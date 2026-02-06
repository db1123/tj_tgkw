package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseAbilityMapper;
import fun.server.model.TCourseAbility;
import fun.server.model.customQuery.courseAbility.CourseabilityCS;
import fun.server.model.customQuery.courseAbility.CourseabilityData;
import fun.server.service.TCourseAbilityService;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcourseabilityService")
public class TCourseAbilityServiceImpl extends AbstractService<TCourseAbility> implements TCourseAbilityService {

    private final TCourseAbilityMapper tCourseAbilityMapper;

    public TCourseAbilityServiceImpl(TCourseAbilityMapper tCourseAbilityMapper) {
        this.tCourseAbilityMapper = tCourseAbilityMapper;
    }

    @Override
    public PageInfo<CourseabilityData> getCourseAbility(CourseabilityCS courseabilityCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseAbilityMapper.getCourseAbility(courseabilityCS));
    }

    @Override
    public PageInfo<CourseabilityData> getCourseAbilityList(CourseabilityCS courseabilityCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseAbilityMapper.getCourseAbilityList(courseabilityCS));
    }
}