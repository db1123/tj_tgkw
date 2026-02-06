package fun.server.service.impl;

import fun.server.model.TAbilityTree;
import fun.server.service.TAbilityTreeService;
import org.springframework.stereotype.Service;

@Service(value = "abilitytreeService")
public class TAbilityTreeServiceImpl extends AbstractService<TAbilityTree> implements TAbilityTreeService {}