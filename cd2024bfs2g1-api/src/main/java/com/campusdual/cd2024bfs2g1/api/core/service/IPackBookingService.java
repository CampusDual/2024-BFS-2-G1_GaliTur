package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IPackBookingService {


    EntityResult packBookingQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult packBookingInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult packBookingUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult packBookingDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;
    EntityResult topMostPacksQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;
    EntityResult topLeastPacksQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult packbookingClientUserDatePackPaginationQuery(Map<String, Object> keysValues, List<String> attributes, int recordNumber, int startIndex, List<?> orderBy) throws OntimizeJEERuntimeException;
    EntityResult packBookingDatePackQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;
}

