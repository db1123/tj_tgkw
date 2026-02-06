package fun.server.service.impl;

import fun.server.model.TStudentGradehistory;
import fun.server.service.TStudentGradehistoryService;
import org.springframework.stereotype.Service;

@Service(value = "studentgradehistoryService")
public class TStudentGradehistoryServiceImpl extends AbstractService<TStudentGradehistory> implements TStudentGradehistoryService {}