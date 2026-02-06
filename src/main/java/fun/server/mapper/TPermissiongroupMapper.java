package fun.server.mapper;

import fun.server.model.TPermissiongroup;
import fun.server.model.TPermissiongroupExample;
import java.util.List;

import fun.server.model.customQuery.TPermissionYZ.TPermissionUserCs;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserQX;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TPermissiongroupMapper extends Mapper<TPermissiongroup> {

    List<TPermissionUserQX> getUserPermission(TPermissionUserCs tPermissionUserCs);
}