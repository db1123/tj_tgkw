package fun.server.service.impl;

import fun.server.model.TCoursePrerequisites;
import fun.server.service.TCoursePrerequisitesService;
import org.springframework.stereotype.Service;

@Service(value = "tcourseprerequisitesService")
public class TCoursePrerequisitesServiceImpl extends AbstractService<TCoursePrerequisites> implements TCoursePrerequisitesService {}