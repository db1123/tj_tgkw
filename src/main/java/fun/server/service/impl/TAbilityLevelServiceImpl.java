package fun.server.service.impl;

import fun.server.model.TAbilityLevel;
import fun.server.service.TAbilityLevelService;
import org.springframework.stereotype.Service;

@Service(value = "abilitylevelService")
public class TAbilityLevelServiceImpl extends AbstractService<TAbilityLevel> implements TAbilityLevelService {}