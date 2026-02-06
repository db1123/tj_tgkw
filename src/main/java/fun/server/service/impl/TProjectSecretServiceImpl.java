package fun.server.service.impl;

import fun.server.model.TProjectPlan;
import fun.server.model.TProjectSecret;
import fun.server.service.TProjectPlanService;
import fun.server.service.TProjectSecretService;
import org.springframework.stereotype.Service;

@Service(value = "projectsecretService")
public class TProjectSecretServiceImpl extends AbstractService<TProjectSecret> implements TProjectSecretService {}