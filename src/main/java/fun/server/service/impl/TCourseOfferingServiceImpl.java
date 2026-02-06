package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TCourseOfferingMapper;
import fun.server.model.TCourseOffering;
import fun.server.model.customQuery.courseoffering.courseOfferingCS;
import fun.server.model.customQuery.courseoffering.courseOfferingData;
import fun.server.model.customQuery.courseoffering.courseOfferingopenCS;
import fun.server.service.TCourseOfferingService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tcourseofferingService")
public class TCourseOfferingServiceImpl extends AbstractService<TCourseOffering> implements TCourseOfferingService {

    private final TCourseOfferingMapper tCourseOfferingMapper;

    public TCourseOfferingServiceImpl(TCourseOfferingMapper tCourseOfferingMapper) {
        this.tCourseOfferingMapper = tCourseOfferingMapper;
    }

    @Override
    public PageInfo<courseOfferingData> getTCourseOfferingList(courseOfferingCS courseOfferingCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseOfferingMapper.getTCourseOfferingList(courseOfferingCS));
    }

    @Override
    public PageInfo<courseOfferingData> getTCourseOfferingListopen(courseOfferingopenCS courseOfferingopenCS, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return new PageInfo<>(tCourseOfferingMapper.getTCourseOfferingListopen(courseOfferingopenCS));
    }

    @Override
    public courseOfferingData getTCourseOfferingInfo(courseOfferingCS courseOfferingCS) {
        return tCourseOfferingMapper.getTCourseOfferingInfo(courseOfferingCS);
    }
}