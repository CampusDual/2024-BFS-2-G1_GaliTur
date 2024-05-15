package com.campusdual.cd2024bfs2g1.api.core.service.business;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Map;

public interface IRouteAndLandmarkService {

    EntityResult landmarksForRouteQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

    @Secured({ PermissionsProviderSecured.SECURED })
    EntityResult landmarksForRouteUpdate(Map<?, ?> attributesValues, Map<?, ?> keysValues) throws OntimizeJEERuntimeException;

    EntityResult routelandmarkInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
}
