package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IRestaurantService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.AgencyGuideDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.RestaurantDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("RestaurantService")
public class RestaurantService implements IRestaurantService {

    private final DefaultOntimizeDaoHelper daoHelper;
    private final RestaurantDao restaurantDao;


    @Autowired
    public RestaurantService(DefaultOntimizeDaoHelper daoHelper, RestaurantDao restaurantDao) {
        this.daoHelper = daoHelper;
        this.restaurantDao = restaurantDao;
    }

    @Override
    public EntityResult restaurantQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.restaurantDao,keysValues, attributes);
    }

    @Override
    public EntityResult restaurantInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.restaurantDao, keysValues);
    }

    @Override
    public EntityResult restaurantUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.restaurantDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult restaurantDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.restaurantDao, keysValues);
    }
}
