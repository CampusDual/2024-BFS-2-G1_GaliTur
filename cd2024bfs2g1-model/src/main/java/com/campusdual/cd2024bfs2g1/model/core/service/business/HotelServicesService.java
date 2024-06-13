package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelServicesService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelServicesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("HotelServicesService")
public class HotelServicesService implements IHotelServicesService {

    private final DefaultOntimizeDaoHelper daoHelper;

    private final HotelServicesDao hotelServicesDao;

    @Autowired
    public HotelServicesService(DefaultOntimizeDaoHelper daoHelper, HotelServicesDao hotelServicesDao) {
        this.daoHelper = daoHelper;
        this.hotelServicesDao = hotelServicesDao;
    }

    @Override
    public EntityResult hotelServicesQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.hotelServicesDao,keysValues, attributes);
    }

    @Override
    public EntityResult hotelServicesInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.hotelServicesDao, keysValues);
    }

    @Override
    public EntityResult hotelServicesUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.hotelServicesDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult hotelServicesDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.hotelServicesDao, keysValues);
    }
}
