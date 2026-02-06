package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TLogAction;
import fun.server.service.TLogActionService;

@Service(value = "logActionService")
public class TLogActionServiceImpl extends AbstractService<TLogAction> implements TLogActionService {}