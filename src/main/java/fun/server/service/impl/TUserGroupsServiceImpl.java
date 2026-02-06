package fun.server.service.impl;


import fun.server.model.TUsergroups;
import fun.server.service.TUserGroupsService;
import org.springframework.stereotype.Service;

@Service(value = "tusergroupsService")
public class TUserGroupsServiceImpl extends AbstractService<TUsergroups> implements TUserGroupsService {}