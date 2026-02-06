package fun.server.service.impl;

import fun.server.model.TInterfaceAi;
import fun.server.service.TInterfaceAiService;
import org.springframework.stereotype.Service;

@Service(value = "interfaceAiService")
public class TInterfaceAiServiceImpl extends AbstractService<TInterfaceAi> implements TInterfaceAiService {}