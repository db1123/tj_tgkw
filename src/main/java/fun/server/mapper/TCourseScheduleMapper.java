package fun.server.mapper;

import fun.server.model.TCourseSchedule;
import fun.server.model.customQuery.courseSchedule.courseScheduleCS;
import fun.server.model.customQuery.courseSchedule.courseScheduleData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseScheduleMapper extends Mapper<TCourseSchedule> {
    List<courseScheduleData> getTCourseScheduleList(courseScheduleCS courseScheduleCS);

    courseScheduleData getTCourseScheduleInfo(courseScheduleCS courseScheduleCS);
}