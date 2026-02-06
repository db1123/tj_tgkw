package fun.server.service;

import fun.server.model.TAbility;
import fun.server.model.customQuery.ability.abilityCS;
import fun.server.model.customQuery.ability.abilityData;

import java.util.List;

public interface TAbilityService extends BaseService<TAbility> {

    List<abilityData> getabilityselectList(abilityCS abilityCS);


}