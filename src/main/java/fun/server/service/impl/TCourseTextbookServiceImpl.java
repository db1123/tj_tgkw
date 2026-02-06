package fun.server.service.impl;

import fun.server.model.TCourseTextbook;
import fun.server.service.TCourseTextbookService;
import org.springframework.stereotype.Service;

@Service(value = "tcoursetextbookService")
public class TCourseTextbookServiceImpl extends AbstractService<TCourseTextbook> implements TCourseTextbookService {}