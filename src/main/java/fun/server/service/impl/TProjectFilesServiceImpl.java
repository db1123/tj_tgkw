package fun.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fun.server.mapper.TProjectFilesMapper;
import fun.server.model.TProjectFiles;
import fun.server.model.customQuery.TProjectFilesList;
import fun.server.model.customQuery.projectFile.projectFileNumCs;
import fun.server.service.TProjectFilesService;
import org.springframework.stereotype.Service;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "projectfilesService")
public class TProjectFilesServiceImpl extends AbstractService<TProjectFiles> implements TProjectFilesService {


    private final TProjectFilesMapper tProjectFilesMapper;

    public TProjectFilesServiceImpl(TProjectFilesMapper tProjectFilesMapper) {
        this.tProjectFilesMapper = tProjectFilesMapper;
    }


    @Override
    public PageInfo<TProjectFilesList> getprojectfilesls(int isall, Long FFileID, int offset, int limit) {
        PageHelper.offsetPage(offset, limit, true);
        return  new PageInfo<>(tProjectFilesMapper.getprojectfilesls(isall,FFileID));
    }

    @Override
    public Integer getFProjectFilesNum(projectFileNumCs projectFileNumCs) {
        return tProjectFilesMapper.getFProjectFilesNum(projectFileNumCs);
    }

}