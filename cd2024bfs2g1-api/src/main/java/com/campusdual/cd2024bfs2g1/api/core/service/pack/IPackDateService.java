package com.campusdual.cd2024bfs2g1.api.core.service.pack;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IPackDateService {
    EntityResult packDateQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    EntityResult packDateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException;
    EntityResult packDateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    EntityResult packDateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
