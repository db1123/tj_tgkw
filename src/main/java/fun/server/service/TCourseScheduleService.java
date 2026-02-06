package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseSchedule;
import fun.server.model.customQuery.courseSchedule.courseScheduleCS;
import fun.server.model.customQuery.courseSchedule.courseScheduleData;

public interface TCourseScheduleService extends BaseService<TCourseSchedule> {

    PageInfo<courseScheduleData> getTCourseScheduleList(courseScheduleCS courseScheduleCS, int offset, int limit);

    courseScheduleData getTCourseScheduleInfo(courseScheduleCS courseScheduleCS);
}