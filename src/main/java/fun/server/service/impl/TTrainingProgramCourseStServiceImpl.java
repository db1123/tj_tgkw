package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TTrainingProgramCourseStMapper;
import fun.server.model.TTrainingProgramCourseSt;
import fun.server.model.customQuery.trainingprogram.*;
import fun.server.service.TTrainingProgramCourseStService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "trainingprogramcoursestService")
public class TTrainingProgramCourseStServiceImpl extends AbstractService<TTrainingProgramCourseSt> implements TTrainingProgramCourseStService {

    private final TTrainingProgramCourseStMapper trainingProgramCourseStMapper;

    public TTrainingProgramCourseStServiceImpl(TTrainingProgramCourseStMapper trainingProgramCourseStMapper) {
        this.trainingProgramCourseStMapper = trainingProgramCourseStMapper;
    }

    @Override
    public PageInfo<TrainingprogramkcstData> getTrainingprogramCourseStData(TrainingprogramkcCS trainingprogramkcCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingProgramCourseStMapper.getTrainingprogramCourseStData(trainingprogramkcCS));
    }

    @Override
    public List<TrainingprogramcourseStyData> getCourseYanZhengst(TrainingprogramcourseStyCS trainingprogramcourseStyCS) {
        return trainingProgramCourseStMapper.getCourseYanZhengst(trainingprogramcourseStyCS);
    }

    @Override
    public List<TrainingprogramcourseStyData> getCourseYanZhengxt(TrainingprogramcourseStyCS trainingprogramcourseStyCS) {
        return trainingProgramCourseStMapper.getCourseYanZhengxt(trainingprogramcourseStyCS);
    }

    @Override
    public List<TrainingprogramcourseStyData> getnatureList() {
        return trainingProgramCourseStMapper.getnatureList();
    }

    @Override
    public List<TTrainingprogramcourseGxData> getTTrainingprogramCourseGx(TTrainingprogramcourseGxCS tTrainingprogramcourseGxCS) {
        return trainingProgramCourseStMapper.getTTrainingprogramCourseGx(tTrainingprogramcourseGxCS);
    }
}