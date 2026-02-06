package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TMajorMapper;
import fun.server.model.TMajor;
import fun.server.model.customQuery.major.*;
import fun.server.service.TMajorService;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tmajorService")
public class TMajorServiceImpl extends AbstractService<TMajor> implements TMajorService {

    private final TMajorMapper  tMajorMapper;

    public TMajorServiceImpl(TMajorMapper tMajorMapper) {
        this.tMajorMapper = tMajorMapper;
    }

    @Override
    public List<TMajorData> selectTGrade(TMajorCS tMajorCS) {
        return tMajorMapper.selectTGrade(tMajorCS);
    }

    @Override
    public PageInfo<TClassStudentData> selectclassstudent(TMajorCS tMajorCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tMajorMapper.selectclassstudent(tMajorCS));
    }

    @Override
    public List<TClassStudentData> classstudentSelect(TClassStudentCS tClassStudentCS) {
        return tMajorMapper.classstudentSelect(tClassStudentCS);
    }

    @Override
    public List<TCLassData> selectClassInfo(TClassCS tClassCS) {
        return tMajorMapper.selectClassInfo(tClassCS);
    }

    @Override
    public List<TMajorData> queryDatamajorSelect(TMajorCS tMajorCS) {
        return tMajorMapper.queryDatamajorSelect(tMajorCS);
    }
}