package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IGuideZoneService {


    EntityResult guideZoneQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult guideZoneInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult guideZoneUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult guideZoneDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;


}

