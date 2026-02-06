package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TMajor;
import fun.server.model.customQuery.major.*;

import java.util.List;

public interface TMajorService extends BaseService<TMajor> {
    List<TMajorData> selectTGrade(TMajorCS tMajorCS);


    PageInfo<TClassStudentData> selectclassstudent(TMajorCS tMajorCS,int offset, int limit);


    List<TClassStudentData> classstudentSelect(TClassStudentCS tClassStudentCS);

    List<TCLassData> selectClassInfo(TClassCS tClassCS);

    List<TMajorData> queryDatamajorSelect(TMajorCS tMajorCS);
}