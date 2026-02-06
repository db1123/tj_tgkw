package fun.server.service.impl;

import fun.server.model.TCourseResourcefile;
import fun.server.service.TCourseResourcefileService;
import org.springframework.stereotype.Service;

@Service(value = "tcourseresourcefileService")
public class TCourseResourcefileServiceImpl extends AbstractService<TCourseResourcefile> implements TCourseResourcefileService {}