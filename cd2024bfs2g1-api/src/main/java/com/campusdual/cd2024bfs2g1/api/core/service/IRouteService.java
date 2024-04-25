package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;

import java.util.List;
import java.util.Map;

public interface IRouteService {

    public EntityResult routeQuery(Map<String, Object> keyMap, List<String> attrList);
    public EntityResult routeInsert(Map<String, Object> attrMap);

}
