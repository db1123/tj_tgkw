package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TClassStudentMapper;
import fun.server.model.TClassStudent;
import fun.server.model.customQuery.classStudent.ClassStudentCS;
import fun.server.model.customQuery.classStudent.ClassStudentData;
import fun.server.service.TClassStudentService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tclassstudentService")
public class TClassStudentServiceImpl extends AbstractService<TClassStudent> implements TClassStudentService {


    private final TClassStudentMapper tClassStudentMapper;

    public TClassStudentServiceImpl(TClassStudentMapper tClassStudentMapper) {
        this.tClassStudentMapper = tClassStudentMapper;
    }

    @Override
    public PageInfo<ClassStudentData> getCLassStudentlist(ClassStudentCS classStudentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tClassStudentMapper.getCLassStudentlist(classStudentCS));
    }

    @Override
    public PageInfo<ClassStudentData> getCourseStudentlist(ClassStudentCS classStudentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tClassStudentMapper.getCourseStudentlist(classStudentCS));
    }

    @Override
    public PageInfo<ClassStudentData> getStudentabilitylist(ClassStudentCS classStudentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tClassStudentMapper.getStudentabilitylist(classStudentCS));
    }

    @Override
    public PageInfo<ClassStudentData> getStudentenrollmentlist(ClassStudentCS classStudentCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tClassStudentMapper.getStudentenrollmentlist(classStudentCS));
    }
}