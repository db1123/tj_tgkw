package fun.server.mapper;

import fun.server.model.TCourseEnrollment;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseData;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseEnrollmentMapper extends Mapper<TCourseEnrollment> {
    List<courseEnrollmentData> getCourseEnrollmentDataList(courseEnrollmentCS courseEnrollmentCS);

    List<courseEnrollmentData> getCourseEnrollmentDataListmb(courseEnrollmentCS courseEnrollmentCS);

    courseEnrollmentData getCourseEnrollmentDataInfo(courseEnrollmentCS courseEnrollmentCS);

    List<courseEnrollmentCourseData> getcourseEnrollmentCourseSelect(courseEnrollmentCourseCS courseEnrollmentCourseCS);
}