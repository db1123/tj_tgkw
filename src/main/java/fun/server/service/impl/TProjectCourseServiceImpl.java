package fun.server.service.impl;

import fun.server.mapper.TProjectCourseMapper;
import fun.server.model.TProjectCourse;
import fun.server.model.customQuery.projectcourse.ProjectCourseCs;
import fun.server.model.customQuery.projectcourse.ProjectCoursedata;
import fun.server.service.TProjectCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "projectcourseService")
public class TProjectCourseServiceImpl extends AbstractService<TProjectCourse> implements TProjectCourseService {

    private final TProjectCourseMapper tProjectCourseMapper;

    public TProjectCourseServiceImpl(TProjectCourseMapper tProjectCourseMapper) {
        this.tProjectCourseMapper = tProjectCourseMapper;
    }


    @Override
    public List<ProjectCoursedata> getProjectCourseSeelect(ProjectCourseCs projectCourseCs) {
        return tProjectCourseMapper.getProjectCourseSeelect(projectCourseCs);
    }
}