package fun.server.service.impl;

import fun.server.model.TCourseTarget;
import fun.server.service.TCourseTargetService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursetargetService")
public class TCourseTargetServiceImpl extends AbstractService<TCourseTarget> implements TCourseTargetService {}