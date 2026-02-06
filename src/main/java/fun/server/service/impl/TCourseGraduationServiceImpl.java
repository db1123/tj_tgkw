package fun.server.service.impl;

import fun.server.model.TCourseGraduation;
import fun.server.service.TCourseGraduationService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursegraduationService")
public class TCourseGraduationServiceImpl extends AbstractService<TCourseGraduation> implements TCourseGraduationService {}