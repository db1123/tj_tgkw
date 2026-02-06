package fun.server.service.impl;

import fun.server.model.TDept;
import fun.server.service.TDeptService;
import org.springframework.stereotype.Service;

@Service(value = "deptService")
public class TDeptServiceImpl extends AbstractService<TDept> implements TDeptService {}