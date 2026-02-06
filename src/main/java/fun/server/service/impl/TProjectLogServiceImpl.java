package fun.server.service.impl;

import fun.server.model.TProjectData;
import fun.server.model.TProjectLog;
import fun.server.service.TProjectDataService;
import fun.server.service.TProjectLogService;
import org.springframework.stereotype.Service;

@Service(value = "projectlogService")
public class TProjectLogServiceImpl extends AbstractService<TProjectLog> implements TProjectLogService {}