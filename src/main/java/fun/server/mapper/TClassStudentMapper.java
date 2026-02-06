package fun.server.mapper;

import fun.server.model.TClassStudent;
import fun.server.model.customQuery.classStudent.ClassStudentCS;
import fun.server.model.customQuery.classStudent.ClassStudentData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TClassStudentMapper extends Mapper<TClassStudent> {
    List<ClassStudentData> getCLassStudentlist(ClassStudentCS classStudentCS);

    List<ClassStudentData> getCourseStudentlist(ClassStudentCS classStudentCS);

    List<ClassStudentData> getStudentabilitylist(ClassStudentCS classStudentCS);

    List<ClassStudentData> getStudentenrollmentlist(ClassStudentCS classStudentCS);
}