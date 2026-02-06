package fun.server.mapper;

import fun.server.model.TAbility;
import fun.server.model.customQuery.ability.abilityCS;
import fun.server.model.customQuery.ability.abilityData;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TAbilityMapper extends Mapper<TAbility> {

    List<abilityData> getabilityselectList(abilityCS abilityCS);


}