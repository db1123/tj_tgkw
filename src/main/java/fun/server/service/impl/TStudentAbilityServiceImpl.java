package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TStudentAbilityMapper;
import fun.server.model.TStudentAbility;
import fun.server.model.customQuery.studentability.StudentabilityCs;
import fun.server.model.customQuery.studentability.StudentabilityLevelMax;
import fun.server.model.customQuery.studentability.StudentabilityMB;
import fun.server.model.customQuery.studentability.Studentabilitydata;
import fun.server.service.TStudentAbilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "studentabilityService")
public class TStudentAbilityServiceImpl extends AbstractService<TStudentAbility> implements TStudentAbilityService {
    private final TStudentAbilityMapper tStudentAbilityMapper;

    public TStudentAbilityServiceImpl(TStudentAbilityMapper tStudentAbilityMapper) {
        this.tStudentAbilityMapper = tStudentAbilityMapper;
    }


    @Override
    public PageInfo<Studentabilitydata> getStudentAbilitylist(StudentabilityCs studentabilityCs, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tStudentAbilityMapper.getStudentAbilitylist(studentabilityCs));
    }

    @Override
    public int getStudentFPoints(Long FStudentID) {
        return tStudentAbilityMapper.getStudentFPoints(FStudentID);
    }

    @Override
    public List<StudentabilityMB> getStudentAbilityMB() {
        return tStudentAbilityMapper.getStudentAbilityMB();
    }

    @Override
    public List<StudentabilityLevelMax> getStudentAbilityLevelMax(Long FStudentID) {
        return tStudentAbilityMapper.getStudentAbilityLevelMax(FStudentID);
    }


}