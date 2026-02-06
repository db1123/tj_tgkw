package fun.server.service.impl;

import fun.server.model.TTeacher;
import fun.server.service.TTeacherService;
import org.springframework.stereotype.Service;

@Service(value = "tTeacherService")
public class TTeacherServiceImpl extends AbstractService<TTeacher> implements TTeacherService {}