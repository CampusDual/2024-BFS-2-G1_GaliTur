package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IBusinessService {


    EntityResult businessQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

    AdvancedEntityResult businessDownDatePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy);

    EntityResult businessMerchantQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;


    EntityResult businessInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;



    EntityResult businessDownDateDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult businessUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    EntityResult businessDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

    AdvancedEntityResult businessPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy);

    AdvancedEntityResult businessMerchantPaginationQuery(Map<String, Object> keysValues, List<String> attributes, int recordNumber, int startIndex, List<?> orderBy);

    EntityResult businessOfPackQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;


}

