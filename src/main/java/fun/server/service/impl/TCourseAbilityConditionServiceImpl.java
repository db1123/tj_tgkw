package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseAbilityConditionMapper;
import fun.server.model.TCourseAbilityCondition;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionCS;
import fun.server.model.customQuery.courseabilityCondition.CourseabilityCondtionData;
import fun.server.service.TCourseAbilityConditionService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcourseabilityconditionService")
public class TCourseAbilityConditionServiceImpl extends AbstractService<TCourseAbilityCondition> implements TCourseAbilityConditionService {

    private final TCourseAbilityConditionMapper tCourseAbilityConditionMapper;

    public TCourseAbilityConditionServiceImpl(TCourseAbilityConditionMapper tCourseAbilityConditionMapper) {
        this.tCourseAbilityConditionMapper = tCourseAbilityConditionMapper;
    }


    @Override
    public PageInfo<CourseabilityCondtionData> getCourseAbilityConditionList(CourseabilityCondtionCS courseabilityCondtionCS, int offset, int limit) {

        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseAbilityConditionMapper.getCourseAbilityConditionList(courseabilityCondtionCS));
    }

    @Override
    public List<Long> getLevelAbilityConditionIDList(CourseabilityCondtionCS courseabilityCondtionCS) {
        return tCourseAbilityConditionMapper.getLevelAbilityConditionIDList(courseabilityCondtionCS);
    }
}