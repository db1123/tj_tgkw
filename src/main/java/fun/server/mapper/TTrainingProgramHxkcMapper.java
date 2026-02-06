package fun.server.mapper;

import fun.server.model.TTrainingProgramHxkc;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TTrainingProgramHxkcMapper extends Mapper<TTrainingProgramHxkc> {
    List<TrainingprogramkcData> getCourseListopen(TrainingprogramkcCS trainingprogramkcCS);
}