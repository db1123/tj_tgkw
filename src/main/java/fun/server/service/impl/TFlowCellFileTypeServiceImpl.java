package fun.server.service.impl;

import org.springframework.stereotype.Service;

import fun.server.model.TFlowCellFileType;
import fun.server.service.TFlowCellFileTypeService;

@Service(value = "flowCellFileTypeService")
public class TFlowCellFileTypeServiceImpl extends AbstractService<TFlowCellFileType> implements TFlowCellFileTypeService {}