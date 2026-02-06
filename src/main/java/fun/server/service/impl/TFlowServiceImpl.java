package fun.server.service.impl;

import fun.server.model.TFlow;
import fun.server.service.TFlowService;
import org.springframework.stereotype.Service;

@Service(value = "flowService")
public class TFlowServiceImpl extends AbstractService<TFlow> implements TFlowService {}