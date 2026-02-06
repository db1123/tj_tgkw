package fun.server.service.impl;

import fun.server.model.TClassroom;
import fun.server.service.TClassroomService;
import org.springframework.stereotype.Service;

@Service(value = "tClassroomService")
public class TClassroomServiceImpl extends AbstractService<TClassroom> implements TClassroomService {}