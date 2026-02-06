package fun.server.service.impl;

import fun.server.model.TCity;
import fun.server.service.TCityService;
import org.springframework.stereotype.Service;

@Service(value = "cityService")
public class TCityServiceImpl extends AbstractService<TCity> implements TCityService {}