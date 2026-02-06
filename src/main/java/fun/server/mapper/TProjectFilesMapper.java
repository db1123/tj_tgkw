package fun.server.mapper;

import fun.server.model.TProjectFiles;
import fun.server.model.customQuery.TProjectFilesList;
import fun.server.model.customQuery.projectFile.projectFileNumCs;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TProjectFilesMapper extends Mapper<TProjectFiles> {
    List<TProjectFilesList> getprojectfilesls(
            @Param("isall") int isall,
            @Param("FFileID") Long FFileID
    );


    Integer getFProjectFilesNum(projectFileNumCs projectFileNumCs);
}