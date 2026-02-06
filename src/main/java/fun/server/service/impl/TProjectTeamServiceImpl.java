package fun.server.service.impl;

import fun.server.model.TProjectSecret;
import fun.server.model.TProjectTeam;
import fun.server.service.TProjectSecretService;
import fun.server.service.TProjectTeamService;
import org.springframework.stereotype.Service;

@Service(value = "projectteamService")
public class TProjectTeamServiceImpl extends AbstractService<TProjectTeam> implements TProjectTeamService {}