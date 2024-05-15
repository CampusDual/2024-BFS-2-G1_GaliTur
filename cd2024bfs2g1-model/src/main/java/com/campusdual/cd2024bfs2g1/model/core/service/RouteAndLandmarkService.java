package com.campusdual.cd2024bfs2g1.model.core.service;


import com.campusdual.cd2024bfs2g1.api.core.service.business.IRouteAndLandmarkService;
import com.campusdual.cd2024bfs2g1.model.core.dao.LandmarkDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteLandmarkDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.security.SecurityTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("RouteAndLandmarkService")
public class RouteAndLandmarkService implements IRouteAndLandmarkService {

    private RouteDao routeDao;
    private LandmarkDao landmarkDao;
    private RouteLandmarkDao routeLandmarkDao;
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    public RouteAndLandmarkService(RouteDao routeDao, DefaultOntimizeDaoHelper daoHelper, RouteLandmarkDao routeLandmarkDao, LandmarkDao landmarkDao) {
        this.routeDao = routeDao;
        this.daoHelper = daoHelper;
        this.routeLandmarkDao = routeLandmarkDao;
        this.landmarkDao = landmarkDao;
    }

    @Override
    public EntityResult landmarksForRouteQuery(final Map<?, ?> keysValues, final List<?> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.routeLandmarkDao, keysValues, attributes, RouteLandmarkDao.QUERY_ROUTE_LANDMARK_NAME);
    }

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult landmarksForRouteUpdate(final Map<?, ?> attributesValues, final Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        try {
            if (!(Boolean) attributesValues.get(RouteLandmarkDao.ATTR_ACTIVED)) {
                // insert
                final Map<String, Object> valuesToInsert = new HashMap<>();
                valuesToInsert.put(RouteLandmarkDao.ATTR_ROUTE_ID, keysValues.get(RouteLandmarkDao.ATTR_ROUTE_ID));
                valuesToInsert.put(RouteLandmarkDao.ATTR_LANDMARK_ID, keysValues.get(RouteLandmarkDao.ATTR_LANDMARK_ID));
                return this.daoHelper.insert(this.routeLandmarkDao, valuesToInsert);
            } else if (keysValues.get(RouteLandmarkDao.ATTR_ROUTE_LANDMARK_ID) != null) {
                // delete
                final Map<String, Object> valuesToDelete = new HashMap<>();
                valuesToDelete.put(RouteLandmarkDao.ATTR_ROUTE_LANDMARK_ID, keysValues.get(RouteLandmarkDao.ATTR_ROUTE_LANDMARK_ID));
                return this.daoHelper.delete(this.routeLandmarkDao, valuesToDelete);
            }
            return new EntityResultMapImpl();
        } finally {
            this.invalidateSecurityManager();
        }
    }

    private void invalidateSecurityManager() {
        SecurityTools.invalidateSecurityManager(this.daoHelper.getApplicationContext());
    }

    @Override
    public EntityResult routelandmarkInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.routeLandmarkDao, attrMap);
    }


}
