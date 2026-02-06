package fun.server.mapper;

import fun.server.model.TCourseOffering;
import fun.server.model.customQuery.courseoffering.courseOfferingCS;
import fun.server.model.customQuery.courseoffering.courseOfferingData;
import fun.server.model.customQuery.courseoffering.courseOfferingopenCS;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCourseOfferingMapper extends Mapper<TCourseOffering> {

    List<courseOfferingData> getTCourseOfferingList(courseOfferingCS courseOfferingCS);

    List<courseOfferingData> getTCourseOfferingListopen(courseOfferingopenCS courseOfferingopenCS);

    courseOfferingData getTCourseOfferingInfo(courseOfferingCS courseOfferingCS);
}