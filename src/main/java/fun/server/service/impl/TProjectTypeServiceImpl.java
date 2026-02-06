package fun.server.service.impl;

import fun.server.model.TProjectTeam;
import fun.server.model.TProjectType;
import fun.server.service.TProjectTeamService;
import fun.server.service.TProjectTypeService;
import org.springframework.stereotype.Service;

@Service(value = "projecttypeService")
public class TProjectTypeServiceImpl extends AbstractService<TProjectType> implements TProjectTypeService {}