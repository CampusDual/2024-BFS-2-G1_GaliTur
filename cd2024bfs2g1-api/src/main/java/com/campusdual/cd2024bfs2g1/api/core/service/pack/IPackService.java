package com.campusdual.cd2024bfs2g1.api.core.service.pack;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IPackService {

    EntityResult packQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult packImageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult packProvinceQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult packDetailQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException;

    EntityResult packDaysQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException;



    EntityResult packClientQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult allPacksQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult packUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult packDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    EntityResult newestQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    AdvancedEntityResult allPacksPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy);

}
