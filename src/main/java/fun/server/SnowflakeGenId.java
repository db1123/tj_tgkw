package fun.server;

import fun.tools.SnowflakeIdWorker;
import tk.mybatis.mapper.genid.GenId;

public class SnowflakeGenId implements GenId<Long> {

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

    @Override
    public Long genId(String table, String column) {
        return idWorker.nextId();
    }
}