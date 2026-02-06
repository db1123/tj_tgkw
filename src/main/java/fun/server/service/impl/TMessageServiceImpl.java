package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TMessage;
import fun.server.service.TMessageService;

@Service(value = "messageService")
public class TMessageServiceImpl extends AbstractService<TMessage> implements TMessageService {}