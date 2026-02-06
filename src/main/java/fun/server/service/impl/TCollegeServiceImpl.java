package fun.server.service.impl;

import fun.server.model.TCollege;
import fun.server.service.TCollegeService;
import org.springframework.stereotype.Service;

@Service(value = "tcollegeService")
public class TCollegeServiceImpl extends AbstractService<TCollege> implements TCollegeService {}