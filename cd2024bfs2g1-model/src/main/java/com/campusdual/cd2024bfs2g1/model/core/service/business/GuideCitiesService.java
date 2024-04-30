package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideCitiesService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideCitiesDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideZoneDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("GuideCitiesService")
public class GuideCitiesService implements IGuideCitiesService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private GuideCitiesDao guideCitiesDao;

    @Override
    public EntityResult guideCitiesQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.guideCitiesDao,keysValues, attributes);
    }

    @Override
    public EntityResult  guideCitiesInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.guideCitiesDao, keysValues);
    }

    @Override
    public EntityResult  guideCitiesUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.guideCitiesDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult  guideCitiesDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.guideCitiesDao, keysValues);
    }
}
