package fun.server.service.impl;

import fun.server.model.TUser;
import fun.server.service.TUserService;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class TUserServiceImpl extends AbstractService<TUser> implements TUserService {}