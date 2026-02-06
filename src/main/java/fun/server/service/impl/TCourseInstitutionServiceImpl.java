package fun.server.service.impl;

import fun.server.model.TCourseInstitution;
import fun.server.service.TCourseInstitutionService;
import org.springframework.stereotype.Service;

@Service(value = "tcourseinstitutionService")
public class TCourseInstitutionServiceImpl extends AbstractService<TCourseInstitution> implements TCourseInstitutionService {}