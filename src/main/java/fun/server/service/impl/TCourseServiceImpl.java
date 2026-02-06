package fun.server.service.impl;

import fun.server.model.TCourse;
import fun.server.service.TCourseService;
import org.springframework.stereotype.Service;

@Service(value = "tcourseService")
public class TCourseServiceImpl extends AbstractService<TCourse> implements TCourseService {}