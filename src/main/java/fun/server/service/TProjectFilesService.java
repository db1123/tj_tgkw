package fun.server.service;

import com.github.pagehelper.PageInfo;
import fun.server.model.TProjectFiles;
import fun.server.model.customQuery.TProjectFilesList;
import fun.server.model.customQuery.projectFile.projectFileNumCs;

public interface TProjectFilesService extends BaseService<TProjectFiles> {
    PageInfo<TProjectFilesList> getprojectfilesls(int isall,Long FFileID, int offset, int limit);

    Integer getFProjectFilesNum(projectFileNumCs projectFileNumCs);
}