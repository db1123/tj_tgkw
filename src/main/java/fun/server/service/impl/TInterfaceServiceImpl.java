package fun.server.service.impl;

import fun.server.model.TInterface;
import fun.server.service.TInterfaceService;
import org.springframework.stereotype.Service;

@Service(value = "interfaceService")
public class TInterfaceServiceImpl extends AbstractService<TInterface> implements TInterfaceService {}