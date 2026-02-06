package fun.server.service.impl;

import fun.server.model.TPost;
import fun.server.service.TPostService;
import org.springframework.stereotype.Service;

@Service(value = "postService")
public class TPostServiceImpl extends AbstractService<TPost> implements TPostService {}