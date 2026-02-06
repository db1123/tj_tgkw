package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TTrainingProgramMapper;
import fun.server.model.TTrainingProgram;
import fun.server.model.customQuery.trainingprogram.CouserVersionData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramversionCS;
import fun.server.service.TTrainingProgramService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "trainingprogramService")
public class TTrainingProgramServiceImpl extends AbstractService<TTrainingProgram> implements TTrainingProgramService {

    private final TTrainingProgramMapper trainingprogramMapper;

    public TTrainingProgramServiceImpl(TTrainingProgramMapper trainingprogramMapper) {
        this.trainingprogramMapper = trainingprogramMapper;
    }

    @Override
    public PageInfo<TrainingprogramData> getTrainingProgramDataList(TrainingprogramCS trainingprogramCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingprogramMapper.getTrainingProgramDataList(trainingprogramCS));
    }

    @Override
    public TrainingprogramData getTrainingProgramDataInfo(TrainingprogramCS trainingprogramCS) {
        return trainingprogramMapper.getTrainingProgramDataInfo(trainingprogramCS);
    }

    @Override
    public PageInfo<TrainingprogramData> getTrainingVersionDataList(TrainingprogramversionCS trainingprogramversionCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingprogramMapper.getTrainingVersionDataList(trainingprogramversionCS));
    }

    @Override
    public PageInfo<CouserVersionData> getCourseVersionDataList(TrainingprogramversionCS trainingprogramversionCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingprogramMapper.getCourseVersionDataList(trainingprogramversionCS));
    }
}