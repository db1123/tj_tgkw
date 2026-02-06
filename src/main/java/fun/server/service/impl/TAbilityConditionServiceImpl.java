package fun.server.service.impl;

import fun.server.model.TAbilityCondition;
import fun.server.service.TAbilityConditionService;
import org.springframework.stereotype.Service;

@Service(value = "abilityconditionService")
public class TAbilityConditionServiceImpl extends AbstractService<TAbilityCondition> implements TAbilityConditionService {}