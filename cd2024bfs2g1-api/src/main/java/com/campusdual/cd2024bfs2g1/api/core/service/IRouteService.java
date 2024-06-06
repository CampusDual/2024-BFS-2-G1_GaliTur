package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;

import java.util.List;
import java.util.Map;

public interface IRouteService {

    public EntityResult routeQuery(Map<String, Object> keyMap, List<String> attrList);

    EntityResult routeImageQuery(Map<String, Object> keyMap, List<String> attrList);

    public EntityResult routeInsert(Map<String, Object> attrMap) ;

    public boolean UserRolQuery(Map<String, Object> attrMap);

    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList);

    public EntityResult image_routeQuery(Map<String, Object> keyMap, List<String> attrList);
    public EntityResult image_routeInsert(Map<String, Object> attrMap);

    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList);
    public EntityResult imageInsert(Map<String, Object> attrMap);

    AdvancedEntityResult routePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy);

    EntityResult routesOfPackQuery(Map<String, Object> keyMap, List<String> attrList);

    EntityResult routeAccordingDifficultyQuery(Map<String, Object> keyMap, List<String> attrList);
}
