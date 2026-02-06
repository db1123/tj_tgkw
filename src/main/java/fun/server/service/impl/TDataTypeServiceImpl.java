package fun.server.service.impl;

import fun.server.model.TDataType;
import fun.server.service.TDataTypeService;
import org.springframework.stereotype.Service;

@Service(value = "datatypeService")
public class TDataTypeServiceImpl extends AbstractService<TDataType> implements TDataTypeService {}