package fun.server.mapper;

import fun.server.model.TPost;
import fun.server.model.TPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TPostMapper extends Mapper<TPost> {}