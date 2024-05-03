package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IHotelServicesService {


    EntityResult hotelServicesQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult hotelServicesInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelServicesUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult hotelServicesDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;


}

