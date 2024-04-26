package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;

import java.util.List;
import java.util.Map;

public interface IImage_routeService {

    public EntityResult image_routeQuery(Map<String, Object> keyMap, List<String> attrList);
    public EntityResult image_routeInsert(Map<String, Object> attrMap);
}
