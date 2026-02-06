package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseEnrollmentMapper;
import fun.server.model.TCourseEnrollment;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseCS;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentCourseData;
import fun.server.model.customQuery.courseEnrollment.courseEnrollmentData;
import fun.server.service.TCourseEnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcourseenrollmentService")
public class TCourseEnrollmentServiceImpl extends AbstractService<TCourseEnrollment> implements TCourseEnrollmentService {

    private final TCourseEnrollmentMapper tCourseEnrollmentMapper;

    public TCourseEnrollmentServiceImpl(TCourseEnrollmentMapper tCourseEnrollmentMapper) {
        this.tCourseEnrollmentMapper = tCourseEnrollmentMapper;
    }


    @Override
    public PageInfo<courseEnrollmentData> getCourseEnrollmentDataList(courseEnrollmentCS courseEnrollmentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseEnrollmentMapper.getCourseEnrollmentDataList(courseEnrollmentCS));
    }

    @Override
    public List<courseEnrollmentData> getCourseEnrollmentDataListmb(courseEnrollmentCS courseEnrollmentCS) {
        return tCourseEnrollmentMapper.getCourseEnrollmentDataListmb(courseEnrollmentCS);
    }

    @Override
    public courseEnrollmentData getCourseEnrollmentDataInfo(courseEnrollmentCS courseEnrollmentCS) {
        return tCourseEnrollmentMapper.getCourseEnrollmentDataInfo(courseEnrollmentCS);
    }

    @Override
    public List<courseEnrollmentCourseData> getcourseEnrollmentCourseSelect(courseEnrollmentCourseCS courseEnrollmentCourseCS) {
        return tCourseEnrollmentMapper.getcourseEnrollmentCourseSelect(courseEnrollmentCourseCS);
    }
}