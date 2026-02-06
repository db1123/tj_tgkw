package fun.server.service.impl;

import fun.server.mapper.TAbilityMapper;
import fun.server.model.TAbility;
import fun.server.model.customQuery.ability.abilityCS;
import fun.server.model.customQuery.ability.abilityData;
import fun.server.service.TAbilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "abilityService")
public class TAbilityServiceImpl extends AbstractService<TAbility> implements TAbilityService {

    private final TAbilityMapper tAbilityMapper;

    public TAbilityServiceImpl(TAbilityMapper tAbilityMapper) {
        this.tAbilityMapper = tAbilityMapper;
    }


    @Override
    public List<abilityData> getabilityselectList(abilityCS abilityCS) {
        return tAbilityMapper.getabilityselectList(abilityCS);
    }


}