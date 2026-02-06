package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseScheduleMapper;
import fun.server.model.TCourseSchedule;
import fun.server.model.customQuery.courseSchedule.courseScheduleCS;
import fun.server.model.customQuery.courseSchedule.courseScheduleData;
import fun.server.service.TCourseScheduleService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcoursescheduleService")
public class TCourseScheduleServiceImpl extends AbstractService<TCourseSchedule> implements TCourseScheduleService {


    private final TCourseScheduleMapper tCourseScheduleMapper;

    public TCourseScheduleServiceImpl(TCourseScheduleMapper tCourseScheduleMapper) {
        this.tCourseScheduleMapper = tCourseScheduleMapper;
    }


    @Override
    public PageInfo<courseScheduleData> getTCourseScheduleList(courseScheduleCS courseScheduleCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseScheduleMapper.getTCourseScheduleList(courseScheduleCS));
    }

    @Override
    public courseScheduleData getTCourseScheduleInfo(courseScheduleCS courseScheduleCS) {
        return tCourseScheduleMapper.getTCourseScheduleInfo(courseScheduleCS);
    }
}