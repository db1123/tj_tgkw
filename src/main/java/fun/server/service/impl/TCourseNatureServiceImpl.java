package fun.server.service.impl;

import fun.server.mapper.TCourseNatureMapper;
import fun.server.model.TCourseNature;
import fun.server.model.customQuery.coursemb.CourseMbData;
import fun.server.service.TCourseNatureService;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcoursenatureService")
public class TCourseNatureServiceImpl extends AbstractService<TCourseNature> implements TCourseNatureService {

    private final TCourseNatureMapper tCourseNatureMapper;

    public TCourseNatureServiceImpl(TCourseNatureMapper tCourseNatureMapper) {
        this.tCourseNatureMapper = tCourseNatureMapper;
    }

    @Override
    public List<CourseMbData> getCourseModelData() {
        return tCourseNatureMapper.getCourseModelData();
    }
}