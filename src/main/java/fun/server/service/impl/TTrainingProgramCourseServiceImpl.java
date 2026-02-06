package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TTrainingProgramCourseMapper;
import fun.server.model.TTrainingProgramCourse;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import fun.server.service.TTrainingProgramCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "trainingprogramcourseService")
public class TTrainingProgramCourseServiceImpl extends AbstractService<TTrainingProgramCourse> implements TTrainingProgramCourseService {

    private final TTrainingProgramCourseMapper trainingprogramcourseMapper;

    public TTrainingProgramCourseServiceImpl(TTrainingProgramCourseMapper trainingprogramcourseMapper) {
        this.trainingprogramcourseMapper = trainingprogramcourseMapper;
    }

    @Override
    public PageInfo<TrainingprogramkcData> getTrainingprogramCourseData(TrainingprogramkcCS trainingprogramkcCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingprogramcourseMapper.getTrainingprogramCourseData(trainingprogramkcCS));
    }

    @Override
    public List<TrainingprogramkcData> getTrainingprogramCourseDayin(TrainingprogramkcCS trainingprogramkcCS) {
        return trainingprogramcourseMapper.getTrainingprogramCourseDayin(trainingprogramkcCS);
    }

    @Override
    public List<TrainingprogramkcData> getTrainingprogramCourseSelect(TrainingprogramkcCS trainingprogramkcCS) {
        return trainingprogramcourseMapper.getTrainingprogramCourseSelect(trainingprogramkcCS);
    }
}