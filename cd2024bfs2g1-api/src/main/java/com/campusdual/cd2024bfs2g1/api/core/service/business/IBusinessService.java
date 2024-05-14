package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IBusinessService {


    EntityResult businessQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    EntityResult businessMerchantQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;


    EntityResult businessInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult businessUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult businessDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;


}

