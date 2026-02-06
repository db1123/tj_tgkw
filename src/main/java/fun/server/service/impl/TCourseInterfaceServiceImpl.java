package fun.server.service.impl;

import fun.server.model.TCourseInterface;
import fun.server.service.TCourseInterfaceService;
import org.springframework.stereotype.Service;

@Service(value = "tcourseinterfaceService")
public class TCourseInterfaceServiceImpl extends AbstractService<TCourseInterface> implements TCourseInterfaceService {}