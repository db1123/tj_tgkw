package fun.server.service.impl;


import fun.server.model.TPermission;
import fun.server.service.TPermissionService;
import org.springframework.stereotype.Service;

@Service(value = "tpermissionService")
public class TPermissionServiceImpl extends AbstractService<TPermission> implements TPermissionService {}