package fun.server.service.impl;

import fun.server.model.TSemester;
import fun.server.service.TSemesterService;
import org.springframework.stereotype.Service;

@Service(value = "tSemesterService")
public class TSemesterServiceImpl extends AbstractService<TSemester> implements TSemesterService {}