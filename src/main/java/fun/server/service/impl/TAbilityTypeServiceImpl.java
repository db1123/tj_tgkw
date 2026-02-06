package fun.server.service.impl;

import fun.server.model.TAbilityType;
import fun.server.service.TAbilityTypeService;
import org.springframework.stereotype.Service;

@Service(value = "abilityleveltypeService")
public class TAbilityTypeServiceImpl extends AbstractService<TAbilityType> implements TAbilityTypeService {}