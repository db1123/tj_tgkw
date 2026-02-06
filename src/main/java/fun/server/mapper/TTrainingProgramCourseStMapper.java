package fun.server.mapper;

import fun.server.model.TTrainingProgramCourseSt;
import fun.server.model.customQuery.trainingprogram.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TTrainingProgramCourseStMapper extends Mapper<TTrainingProgramCourseSt> {
    List<TrainingprogramkcstData> getTrainingprogramCourseStData(TrainingprogramkcCS trainingprogramkcCS);


    List<TrainingprogramcourseStyData> getCourseYanZhengst(TrainingprogramcourseStyCS trainingprogramcourseStyCS);//手填

    List<TrainingprogramcourseStyData> getCourseYanZhengxt(TrainingprogramcourseStyCS trainingprogramcourseStyCS);//系统

    
    List<TrainingprogramcourseStyData> getnatureList();//获取课程性质

    List<TTrainingprogramcourseGxData> getTTrainingprogramCourseGx(TTrainingprogramcourseGxCS tTrainingprogramcourseGxCS);

}