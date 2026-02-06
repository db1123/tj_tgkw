package fun.server.service.impl;

import fun.server.model.TCourseStandardS;
import fun.server.service.TCourseStandardSService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursestandardsService")
public class TCourseStandardSServiceImpl extends AbstractService<TCourseStandardS> implements TCourseStandardSService {}