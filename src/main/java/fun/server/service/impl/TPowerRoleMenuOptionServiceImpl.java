package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TPowerRoleMenuOption;
import fun.server.service.TPowerRoleMenuOptionService;

@Service(value = "powerRoleMenuOptionService")
public class TPowerRoleMenuOptionServiceImpl extends AbstractService<TPowerRoleMenuOption> implements TPowerRoleMenuOptionService {}