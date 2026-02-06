package fun.server.service.impl;

import fun.server.model.TInterfaceType;
import fun.server.service.TInterfaceTypeService;
import org.springframework.stereotype.Service;

@Service(value = "interfacetypeService")
public class TInterfaceTypeServiceImpl extends AbstractService<TInterfaceType> implements TInterfaceTypeService {}