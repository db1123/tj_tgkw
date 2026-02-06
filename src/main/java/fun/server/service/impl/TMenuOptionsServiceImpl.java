package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TMenuOptions;
import fun.server.service.TMenuOptionsService;

@Service(value = "menuOptionsService")
public class TMenuOptionsServiceImpl extends AbstractService<TMenuOptions> implements TMenuOptionsService {}