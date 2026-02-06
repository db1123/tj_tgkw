package fun.server.service;

import fun.server.model.TProject;
import fun.server.model.customQuery.TProjectMaterialList;
import fun.server.model.customQuery.TProject_applyGroupID;

import java.util.List;

public interface TProjectService extends BaseService<TProject> {
    List<TProject_applyGroupID> getGroupID();

    List<TProjectMaterialList> getprojectMaterial(String search,Long FProjectID,int FType,int FSType);
}