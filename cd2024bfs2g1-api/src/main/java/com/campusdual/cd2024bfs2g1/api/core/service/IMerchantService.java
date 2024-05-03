package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IMerchantService {

    // MERCHANT
    EntityResult merchantQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult merchantInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    EntityResult merchantUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult merchantDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    // CUSTOM SQL
}