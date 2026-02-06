package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TPowerRoleUser;
import fun.server.service.TPowerRoleUserService;

@Service(value = "powerRoleUserService")
public class TPowerRoleUserServiceImpl extends AbstractService<TPowerRoleUser> implements TPowerRoleUserService {}