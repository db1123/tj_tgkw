package fun.server.service.impl;

import fun.server.model.TProjectData;
import fun.server.model.TProjectLevel;
import fun.server.service.TProjectDataService;
import fun.server.service.TProjectLevelService;
import org.springframework.stereotype.Service;

@Service(value = "projectlevelService")
public class TProjectLevelServiceImpl extends AbstractService<TProjectLevel> implements TProjectLevelService {}