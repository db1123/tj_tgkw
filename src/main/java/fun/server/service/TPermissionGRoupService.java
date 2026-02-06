package fun.server.service;

import fun.server.model.TPermissiongroup;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserCs;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserQX;

import java.util.List;

public interface TPermissionGRoupService extends BaseService<TPermissiongroup> {
    List<TPermissionUserQX> getUserPermission(TPermissionUserCs tPermissionUserCs);
}