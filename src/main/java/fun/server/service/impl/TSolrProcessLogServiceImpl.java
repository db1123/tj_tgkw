package fun.server.service.impl;

import fun.server.model.TSolrProcessLog;
import fun.server.service.TSolrProcessLogService;
import org.springframework.stereotype.Service;

@Service(value = "tsolrprocesslogService")
public class TSolrProcessLogServiceImpl extends AbstractService<TSolrProcessLog> implements TSolrProcessLogService {}