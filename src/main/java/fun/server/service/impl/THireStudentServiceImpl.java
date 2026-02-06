package fun.server.service.impl;

import fun.server.model.THireStudent;
import fun.server.service.THireStudentService;
import org.springframework.stereotype.Service;

@Service(value = "thirestudentService")
public class THireStudentServiceImpl extends AbstractService<THireStudent> implements THireStudentService {}