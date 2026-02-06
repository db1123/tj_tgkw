package fun.server.mapper;

import fun.server.model.TCourseStudent;
import fun.server.model.customQuery.coursestudent.CourseStudendata;
import fun.server.model.customQuery.coursestudent.CourseStudentCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseStudentMapper extends Mapper<TCourseStudent> {


    List<CourseStudendata> getCourseStudentdata(CourseStudentCS courseStudentCS);

    List<CoursestudentscoreData> getCoursestudentscore(CoursestudentscoreCS coursestudentscoreCS);
}