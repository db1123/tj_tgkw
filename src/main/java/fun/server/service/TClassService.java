package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TClass;
import fun.server.model.customQuery.tclass.tclassopenCS;
import fun.server.model.customQuery.tclass.tclassopenData;

public interface TClassService extends BaseService<TClass> {
    PageInfo<tclassopenData> getClassopenList(tclassopenCS tclassopenCS, int offset, int limit);
}