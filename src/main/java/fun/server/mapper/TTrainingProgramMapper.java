package fun.server.mapper;

import fun.server.model.TTrainingProgram;
import fun.server.model.customQuery.trainingprogram.CouserVersionData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramCS;
import fun.server.model.customQuery.trainingprogram.TrainingprogramData;
import fun.server.model.customQuery.trainingprogram.TrainingprogramversionCS;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TTrainingProgramMapper extends Mapper<TTrainingProgram> {
    List<TrainingprogramData> getTrainingProgramDataList(TrainingprogramCS trainingprogramCS);

    TrainingprogramData getTrainingProgramDataInfo(TrainingprogramCS trainingprogramCS);


    List<TrainingprogramData> getTrainingVersionDataList(TrainingprogramversionCS trainingprogramversionCS);

    List<CouserVersionData> getCourseVersionDataList(TrainingprogramversionCS trainingprogramversionCS);
}