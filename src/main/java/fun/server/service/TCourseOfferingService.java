package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TCourseOffering;
import fun.server.model.customQuery.courseoffering.courseOfferingCS;
import fun.server.model.customQuery.courseoffering.courseOfferingData;
import fun.server.model.customQuery.courseoffering.courseOfferingopenCS;

public interface TCourseOfferingService extends BaseService<TCourseOffering> {
    PageInfo<courseOfferingData> getTCourseOfferingList(courseOfferingCS courseOfferingCS, int offset, int limit);

    PageInfo<courseOfferingData> getTCourseOfferingListopen(courseOfferingopenCS courseOfferingopenCS, int offset, int limit);

    courseOfferingData getTCourseOfferingInfo(courseOfferingCS courseOfferingCS);
}
