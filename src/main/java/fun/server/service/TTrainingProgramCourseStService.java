package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TTrainingProgramCourseSt;
import fun.server.model.customQuery.trainingprogram.*;

import java.util.List;

public interface TTrainingProgramCourseStService extends BaseService<TTrainingProgramCourseSt> {

    PageInfo<TrainingprogramkcstData> getTrainingprogramCourseStData(TrainingprogramkcCS trainingprogramkcCS, int offset, int limit);


    List<TrainingprogramcourseStyData> getCourseYanZhengst(TrainingprogramcourseStyCS trainingprogramcourseStyCS);//手填

    List<TrainingprogramcourseStyData> getCourseYanZhengxt(TrainingprogramcourseStyCS trainingprogramcourseStyCS);//系统


    List<TrainingprogramcourseStyData> getnatureList();

    List<TTrainingprogramcourseGxData> getTTrainingprogramCourseGx(TTrainingprogramcourseGxCS tTrainingprogramcourseGxCS);
}