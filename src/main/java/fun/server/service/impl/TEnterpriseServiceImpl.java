package fun.server.service.impl;

import fun.server.model.TEnterprise;
import fun.server.service.TEnterpriseService;
import org.springframework.stereotype.Service;

@Service(value = "tenterpriseService")
public class TEnterpriseServiceImpl extends AbstractService<TEnterprise> implements TEnterpriseService {}