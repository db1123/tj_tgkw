package fun.server.service.impl;

import fun.server.model.TSysno;
import fun.server.service.TSysNoService;
import org.springframework.stereotype.Service;

@Service(value = "tsysnoService")
public class TSysNoServiceImpl extends AbstractService<TSysno> implements TSysNoService {}