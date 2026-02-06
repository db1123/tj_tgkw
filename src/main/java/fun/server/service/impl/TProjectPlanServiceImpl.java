package fun.server.service.impl;

import fun.server.model.TProjectData;
import fun.server.model.TProjectPlan;
import fun.server.service.TProjectDataService;
import fun.server.service.TProjectPlanService;
import org.springframework.stereotype.Service;

@Service(value = "projectplanService")
public class TProjectPlanServiceImpl extends AbstractService<TProjectPlan> implements TProjectPlanService {}