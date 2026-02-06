package fun.server.service.impl;

import fun.server.model.THire;
import fun.server.service.THireService;
import org.springframework.stereotype.Service;

@Service(value = "thireService")
public class THireServiceImpl extends AbstractService<THire> implements THireService {}