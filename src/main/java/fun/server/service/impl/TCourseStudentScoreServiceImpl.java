package fun.server.service.impl;

import fun.server.model.TCourseStudentScore;
import fun.server.service.TCourseStudentScoreService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursestudentscoreService")
public class TCourseStudentScoreServiceImpl extends AbstractService<TCourseStudentScore> implements TCourseStudentScoreService {}