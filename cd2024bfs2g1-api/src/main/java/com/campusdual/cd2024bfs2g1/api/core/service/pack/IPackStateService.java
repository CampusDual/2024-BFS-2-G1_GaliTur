package com.campusdual.cd2024bfs2g1.api.core.service.pack;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IPackStateService {

    EntityResult packStateQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult packStateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    EntityResult packStateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult packStateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
