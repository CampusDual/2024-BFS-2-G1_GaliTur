package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IImageService {
    public EntityResult imageInsert(Map<String, Object> attrMap);
    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
}
