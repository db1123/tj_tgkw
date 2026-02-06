package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TTrainingProgramHxkc;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;

public interface TTrainingProgramHxkcService extends BaseService<TTrainingProgramHxkc> {
    PageInfo<TrainingprogramkcData> getCourseListopen(TrainingprogramkcCS trainingprogramkcCS,int offset, int limit);
}