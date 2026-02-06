package fun.server.mapper;

import fun.server.model.TCourseNature;
import fun.server.model.customQuery.coursemb.CourseMbData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseNatureMapper extends Mapper<TCourseNature> {
    List<CourseMbData> getCourseModelData();
}