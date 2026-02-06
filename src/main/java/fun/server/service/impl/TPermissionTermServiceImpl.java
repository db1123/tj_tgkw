package fun.server.service.impl;


import fun.server.model.TPermissionterm;
import fun.server.service.TPermissionTermService;
import org.springframework.stereotype.Service;

@Service(value = "tpermissiontermService")
public class TPermissionTermServiceImpl extends AbstractService<TPermissionterm> implements TPermissionTermService {}