package fun.server.mapper;

import fun.server.model.TMajor;
import fun.server.model.customQuery.major.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TMajorMapper extends Mapper<TMajor> {
    List<TMajorData> selectTGrade(TMajorCS tMajorCS);

    List<TClassStudentData> selectclassstudent(TMajorCS tMajorCS);


    List<TClassStudentData> classstudentSelect(TClassStudentCS tClassStudentCS);

    List<TCLassData> selectClassInfo(TClassCS tClassCS);

    List<TMajorData> queryDatamajorSelect(TMajorCS tMajorCS);

}