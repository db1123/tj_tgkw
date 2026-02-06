package fun.server.service.impl;

import fun.server.model.TProjectType;
import fun.server.model.TSubdata;
import fun.server.service.TProjectTypeService;
import fun.server.service.TSubDataService;
import org.springframework.stereotype.Service;

@Service(value = "subdataService")
public class TSubDataServiceImpl extends AbstractService<TSubdata> implements TSubDataService {}