package fun.server.service.impl;

import fun.server.model.TProjectPlan;
import fun.server.model.TProjectPlanBglog;
import fun.server.service.TProjectPlanBGLogService;
import fun.server.service.TProjectPlanService;
import org.springframework.stereotype.Service;

@Service(value = "projectplanbglogService")
public class TProjectPlanBGLogServiceImpl extends AbstractService<TProjectPlanBglog> implements TProjectPlanBGLogService {}