package fun.server.service.impl;

import fun.server.model.TCourseCategory;
import fun.server.service.TCourseCategoryService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursecategoryService")
public class TCourseCategoryServiceImpl extends AbstractService<TCourseCategory> implements TCourseCategoryService {}