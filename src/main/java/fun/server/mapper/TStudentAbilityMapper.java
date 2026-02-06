package fun.server.mapper;

import fun.server.model.TStudentAbility;
import fun.server.model.customQuery.studentability.StudentabilityCs;
import fun.server.model.customQuery.studentability.StudentabilityLevelMax;
import fun.server.model.customQuery.studentability.StudentabilityMB;
import fun.server.model.customQuery.studentability.Studentabilitydata;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TStudentAbilityMapper extends Mapper<TStudentAbility> {

    List<Studentabilitydata> getStudentAbilitylist(StudentabilityCs studentability);

    int getStudentFPoints(@Param("FStudentID") Long FStudentID);

    List<StudentabilityMB> getStudentAbilityMB();

    List<StudentabilityLevelMax> getStudentAbilityLevelMax(@Param("FStudentID") Long FStudentID);
}