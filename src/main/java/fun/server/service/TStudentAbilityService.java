package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TStudentAbility;
import fun.server.model.customQuery.studentability.StudentabilityCs;
import fun.server.model.customQuery.studentability.StudentabilityLevelMax;
import fun.server.model.customQuery.studentability.StudentabilityMB;
import fun.server.model.customQuery.studentability.Studentabilitydata;

import java.util.List;

public interface TStudentAbilityService extends BaseService<TStudentAbility> {
    PageInfo<Studentabilitydata> getStudentAbilitylist(StudentabilityCs studentabilityCs,int offset, int limit);

    int getStudentFPoints(Long FStudentID);

    List<StudentabilityMB> getStudentAbilityMB();

    List<StudentabilityLevelMax> getStudentAbilityLevelMax(Long FStudentID);
}