package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseEnrollment;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseData;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentData;

import java.util.List;

public interface TCourseEnrollmentService extends BaseService<TCourseEnrollment> {
    PageInfo<courseEnrollmentData> getCourseEnrollmentDataList(courseEnrollmentCS courseEnrollmentCS, int offset, int limit);
    List<courseEnrollmentData> getCourseEnrollmentDataListmb(courseEnrollmentCS courseEnrollmentCS);

    courseEnrollmentData getCourseEnrollmentDataInfo(courseEnrollmentCS courseEnrollmentCS);

    List<courseEnrollmentCourseData> getcourseEnrollmentCourseSelect(courseEnrollmentCourseCS courseEnrollmentCourseCS);

}