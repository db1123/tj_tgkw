package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseStudentMapper;
import fun.server.model.TCourseStudent;
import fun.server.model.customQuery.coursestudent.CourseStudendata;
import fun.server.model.customQuery.coursestudent.CourseStudentCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreCS;
import fun.server.model.customQuery.coursestudent.CoursestudentscoreData;
import fun.server.service.TCourseStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcoursestudentService")
public class TCourseStudentServiceImpl extends AbstractService<TCourseStudent> implements TCourseStudentService {

    private final TCourseStudentMapper tCourseStudentMapper;


    public TCourseStudentServiceImpl(TCourseStudentMapper tCourseStudentMapper) {
        this.tCourseStudentMapper = tCourseStudentMapper;
    }


    @Override
    public PageInfo<CourseStudendata> getCourseStudentdata(CourseStudentCS courseStudentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseStudentMapper.getCourseStudentdata(courseStudentCS));
    }

    @Override
    public List<CoursestudentscoreData> getCoursestudentscore(CoursestudentscoreCS coursestudentscoreCS) {
        return tCourseStudentMapper.getCoursestudentscore(coursestudentscoreCS);
    }
}