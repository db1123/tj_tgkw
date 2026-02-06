package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TRole;
import fun.server.service.TRoleService;

@Service(value = "roleService")
public class TRoleServiceImpl extends AbstractService<TRole> implements TRoleService {}