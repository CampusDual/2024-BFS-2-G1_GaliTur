package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClientService {
    // CLIENT
    EntityResult clientQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult isClientQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult clientInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    EntityResult clientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult clientDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    // CUSTOM SQL
}
