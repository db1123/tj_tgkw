package fun.server.mapper;

import fun.server.model.TProject;
import fun.server.model.customQuery.TProjectMaterialList;
import fun.server.model.customQuery.TProject_applyGroupID;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TProjectMapper  extends Mapper<TProject> {
    List<TProject_applyGroupID> getGroupID();

    List<TProjectMaterialList> getprojectMaterial(
            @Param("search") String search,
            @Param("FProjectID") Long FProjectID,
            @Param("FType") int FType,
            @Param("FSType") int FSType
    );
}