package fun.server.mapper;

import fun.server.model.TProjectCourse;
import fun.server.model.customQuery.projectcourse.ProjectCourseCs;
import fun.server.model.customQuery.projectcourse.ProjectCoursedata;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TProjectCourseMapper extends Mapper<TProjectCourse> {

    List<ProjectCoursedata> getProjectCourseSeelect(ProjectCourseCs projectCourseCs);
}