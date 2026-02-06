package fun.server.service.impl;


import fun.server.mapper.TPermissiongroupMapper;
import fun.server.model.TPermissiongroup;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserCs;
import fun.server.model.customQuery.TPermissionYZ.TPermissionUserQX;
import fun.server.service.TPermissionGRoupService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "tpermissiongroupService")
public class TPermissionGRoupServiceImpl extends AbstractService<TPermissiongroup> implements TPermissionGRoupService {

    private final TPermissiongroupMapper tPermissiongroupMapper;

    public TPermissionGRoupServiceImpl(TPermissiongroupMapper tPermissiongroupMapper) {
        this.tPermissiongroupMapper = tPermissiongroupMapper;
    }

    @Override
    public List<TPermissionUserQX> getUserPermission(TPermissionUserCs tPermissionUserCs) {
        return tPermissiongroupMapper.getUserPermission(tPermissionUserCs);
    }
}