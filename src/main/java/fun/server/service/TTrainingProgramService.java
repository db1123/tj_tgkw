package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TTrainingProgram;
import fun.server.model.customQuery.trainingprogram.CouserVersionData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramversionCS;

public interface TTrainingProgramService extends BaseService<TTrainingProgram> {

    PageInfo<TrainingprogramData> getTrainingProgramDataList(TrainingprogramCS trainingprogramCS, int offset, int limit);

    TrainingprogramData getTrainingProgramDataInfo(TrainingprogramCS trainingprogramCS);


    PageInfo<TrainingprogramData> getTrainingVersionDataList(TrainingprogramversionCS trainingprogramversionCS, int offset, int limit);

    PageInfo<CouserVersionData> getCourseVersionDataList(TrainingprogramversionCS trainingprogramversionCS, int offset, int limit);
}