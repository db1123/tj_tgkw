package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TClassStudent;
import fun.server.model.customQuery.classStudent.ClassStudentCS;
import fun.server.model.customQuery.classStudent.ClassStudentData;

public interface TClassStudentService extends BaseService<TClassStudent> {


    PageInfo<ClassStudentData> getCLassStudentlist(ClassStudentCS classStudentCS, int offset, int limit);

    PageInfo<ClassStudentData> getCourseStudentlist(ClassStudentCS classStudentCS, int offset, int limit);

    PageInfo<ClassStudentData> getStudentabilitylist(ClassStudentCS classStudentCS, int offset, int limit);

    PageInfo<ClassStudentData> getStudentenrollmentlist(ClassStudentCS classStudentCS, int offset, int limit);
}