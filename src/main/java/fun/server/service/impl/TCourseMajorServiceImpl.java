package fun.server.service.impl;

import fun.server.model.TCourseMajor;
import fun.server.service.TCourseMajorService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursemajorService")
public class TCourseMajorServiceImpl extends AbstractService<TCourseMajor> implements TCourseMajorService {}