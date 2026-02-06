package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TClassMapper;
import fun.server.model.TClass;
import fun.server.model.customQuery.tclass.tclassopenCS;
import fun.server.model.customQuery.tclass.tclassopenData;
import fun.server.service.TClassService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tclassService")
public class TClassServiceImpl extends AbstractService<TClass> implements TClassService {

    private final TClassMapper tclassMapper;

    public TClassServiceImpl(TClassMapper tclassMapper) {
        this.tclassMapper = tclassMapper;
    }

    @Override
    public PageInfo<tclassopenData> getClassopenList(tclassopenCS tclassopenCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tclassMapper.getClassopenList(tclassopenCS));
    }
}