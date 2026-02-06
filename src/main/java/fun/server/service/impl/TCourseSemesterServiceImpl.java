package fun.server.service.impl;

import fun.server.model.TCourseSemester;
import fun.server.service.TCourseSemesterService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursesemesterService")
public class TCourseSemesterServiceImpl extends AbstractService<TCourseSemester> implements TCourseSemesterService {}