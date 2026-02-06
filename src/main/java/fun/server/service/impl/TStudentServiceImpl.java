package fun.server.service.impl;

import fun.server.model.TStudent;
import fun.server.service.TStudentService;
import org.springframework.stereotype.Service;

@Service(value = "studentService")
public class TStudentServiceImpl extends AbstractService<TStudent> implements TStudentService {}