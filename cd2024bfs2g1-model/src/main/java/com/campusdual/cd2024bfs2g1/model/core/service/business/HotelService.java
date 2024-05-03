package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("HotelService")
public class HotelService implements IHotelService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private HotelDao hotelDao;

    @Override
    public EntityResult hotelQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.hotelDao,keysValues, attributes);
    }

    @Override
    public EntityResult hotelInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.hotelDao, keysValues);
    }

    @Override
    public EntityResult hotelUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.hotelDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult hotelDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.hotelDao, keysValues);
    }
}
