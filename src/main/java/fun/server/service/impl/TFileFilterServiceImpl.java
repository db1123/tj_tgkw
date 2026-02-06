package fun.server.service.impl;

import fun.server.model.TFileFilter;
import fun.server.service.TFileFilterService;
import org.springframework.stereotype.Service;

@Service(value = "tfilefiltersService")
public class TFileFilterServiceImpl extends AbstractService<TFileFilter> implements TFileFilterService {}