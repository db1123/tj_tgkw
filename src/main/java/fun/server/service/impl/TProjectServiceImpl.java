package fun.server.service.impl;

import fun.server.mapper.TProjectMapper;
import fun.server.model.TProject;
import fun.server.model.customQuery.TProjectMaterialList;
import fun.server.model.customQuery.TProject_applyGroupID;
import fun.server.service.TProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "projectService")
public class TProjectServiceImpl extends AbstractService<TProject> implements TProjectService {

    private final TProjectMapper tProjectMapper;

    public TProjectServiceImpl(TProjectMapper tProjectMapper) {
        this.tProjectMapper = tProjectMapper;
    }


    @Override
    public List<TProject_applyGroupID> getGroupID() {
        return tProjectMapper.getGroupID();
    }

    @Override
    public List<TProjectMaterialList> getprojectMaterial(String search, Long FProjectID,int FType,int FSType) {
        return tProjectMapper.getprojectMaterial(search,FProjectID,FType,FSType);
    }


}