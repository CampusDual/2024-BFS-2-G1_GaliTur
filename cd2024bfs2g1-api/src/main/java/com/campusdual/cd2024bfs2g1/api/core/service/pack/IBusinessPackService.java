package com.campusdual.cd2024bfs2g1.api.core.service.pack;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IBusinessPackService {

    EntityResult businessPackQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;


    EntityResult packBusinessQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException;

    EntityResult businessPackInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    EntityResult businessPackUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult businessPackDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
