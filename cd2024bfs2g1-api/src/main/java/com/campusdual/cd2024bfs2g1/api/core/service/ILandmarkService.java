package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ILandmarkService {

    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList);

    EntityResult landmarkOfRouteQuery(Map<String, Object> keyMap, List<String> attrList);

    public EntityResult landmarkInsert(Map<String, Object> attrMap);

    EntityResult landmarkDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
