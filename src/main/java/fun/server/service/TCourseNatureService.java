package fun.server.service;

import fun.server.model.TCourseNature;
import fun.server.model.customQuery.coursemb.CourseMbData;

import java.util.List;

public interface TCourseNatureService extends BaseService<TCourseNature> {
    List<CourseMbData> getCourseModelData();
}