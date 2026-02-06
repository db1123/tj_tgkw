package fun.server.service.impl;

import fun.server.model.TGrade;
import fun.server.service.TGradeService;
import org.springframework.stereotype.Service;

@Service(value = "tgradeService")
public class TGradeServiceImpl extends AbstractService<TGrade> implements TGradeService {}