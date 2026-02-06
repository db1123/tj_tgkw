package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TMenuType;
import fun.server.service.TMenuTypeService;

@Service(value = "menuTypeService")
public class TMenuTypeServiceImpl extends AbstractService<TMenuType> implements TMenuTypeService {}