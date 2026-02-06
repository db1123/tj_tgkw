package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TTrainingProgramHxkcMapper;
import fun.server.model.TTrainingProgramHxkc;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import fun.server.service.TTrainingProgramHxkcService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "trainingprogramhxkcService")
public class TTrainingProgramHxkcServiceImpl extends AbstractService<TTrainingProgramHxkc> implements TTrainingProgramHxkcService {

    private final TTrainingProgramHxkcMapper trainingprogramhxkcMapper;

    public TTrainingProgramHxkcServiceImpl(TTrainingProgramHxkcMapper trainingprogramhxkcMapper) {
        this.trainingprogramhxkcMapper = trainingprogramhxkcMapper;
    }


    @Override
    public PageInfo<TrainingprogramkcData> getCourseListopen(TrainingprogramkcCS trainingprogramkcCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(trainingprogramhxkcMapper.getCourseListopen(trainingprogramkcCS));
    }
}