package fun.server.service.impl;

import fun.server.model.TSubdata;
import fun.server.model.TSubdataCheck;
import fun.server.service.TSubDataCheckService;
import fun.server.service.TSubDataService;
import org.springframework.stereotype.Service;

@Service(value = "subdatacheckService")
public class TSubDataCheckServiceImpl extends AbstractService<TSubdataCheck> implements TSubDataCheckService {}