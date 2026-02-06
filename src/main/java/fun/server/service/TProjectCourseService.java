package fun.server.service;

import fun.server.model.TProjectCourse;
import fun.server.model.customQuery.projectcourse.ProjectCourseCs;
import fun.server.model.customQuery.projectcourse.ProjectCoursedata;

import java.util.List;

public interface TProjectCourseService extends BaseService<TProjectCourse> {
    List<ProjectCoursedata> getProjectCourseSeelect(ProjectCourseCs projectCourseCs);
}