package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.AgencyGuideDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.BusinessDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("AgencyGuideService")
public class AgencyGuideService implements IAgencyGuideService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private AgencyGuideDao agencyGuideDao;

    @Autowired
    private BusinessDao businessDao;

    @Override
    public EntityResult agencyGuideQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.agencyGuideDao,keysValues, attributes);
    }

    @Override
    public EntityResult agencyGuideInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.agencyGuideDao, keysValues);
    }

    @Override
    public EntityResult agencyGuideUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.agencyGuideDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult agencyGuideDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.agencyGuideDao, keysValues);
    }
}
