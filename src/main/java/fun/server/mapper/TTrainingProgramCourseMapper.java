package fun.server.mapper;

import fun.server.model.TTrainingProgramCourse;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TTrainingProgramCourseMapper extends Mapper<TTrainingProgramCourse> {
    List<TrainingprogramkcData> getTrainingprogramCourseData(TrainingprogramkcCS trainingprogramkcCS);

    List<TrainingprogramkcData> getTrainingprogramCourseDayin(TrainingprogramkcCS trainingprogramkcCS);

    List<TrainingprogramkcData> getTrainingprogramCourseSelect(TrainingprogramkcCS trainingprogramkcCS);
}