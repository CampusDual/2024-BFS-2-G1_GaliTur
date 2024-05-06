package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IHotelRoomsService {


    EntityResult hotelRoomsQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult hotelRoomsInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelRoomsUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelRoomsDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;


}

