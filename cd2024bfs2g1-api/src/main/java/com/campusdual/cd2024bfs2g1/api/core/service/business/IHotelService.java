package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IHotelService {


    EntityResult hotelQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult hotelMultiQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult hotelInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelMultiUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;


}

