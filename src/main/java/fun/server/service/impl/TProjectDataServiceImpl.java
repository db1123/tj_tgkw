package fun.server.service.impl;

import fun.server.model.TProject;
import fun.server.model.TProjectData;
import fun.server.service.TProjectDataService;
import fun.server.service.TProjectService;
import org.springframework.stereotype.Service;

@Service(value = "projectdataService")
public class TProjectDataServiceImpl extends AbstractService<TProjectData> implements TProjectDataService {}