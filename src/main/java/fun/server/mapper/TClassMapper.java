package fun.server.mapper;

import fun.server.model.TClass;
import fun.server.model.customQuery.tclass.tclassopenCS;
import fun.server.model.customQuery.tclass.tclassopenData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TClassMapper extends Mapper<TClass> {

    List<tclassopenData> getClassopenList(tclassopenCS tclassopenCS);

}