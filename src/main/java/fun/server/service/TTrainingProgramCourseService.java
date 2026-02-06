package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TTrainingProgramCourse;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramkcData;

import java.util.List;

public interface TTrainingProgramCourseService extends BaseService<TTrainingProgramCourse> {
    PageInfo<TrainingprogramkcData> getTrainingprogramCourseData(TrainingprogramkcCS trainingprogramkcCS, int offset, int limit);

    List<TrainingprogramkcData> getTrainingprogramCourseDayin(TrainingprogramkcCS trainingprogramkcCS);

    List<TrainingprogramkcData> getTrainingprogramCourseSelect(TrainingprogramkcCS trainingprogramkcCS);

}