package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseStudent;
import fun.server.model.customQuery.coursestudent.CourseStudendata;
import fun.server.model.customQuery.coursestudent.CourseStudentCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreData;

import java.util.List;

public interface TCourseStudentService extends BaseService<TCourseStudent> {
    PageInfo<CourseStudendata> getCourseStudentdata(CourseStudentCS courseStudentCS, int offset, int limit);

    List<CoursestudentscoreData> getCoursestudentscore(CoursestudentscoreCS coursestudentscoreCS);
}