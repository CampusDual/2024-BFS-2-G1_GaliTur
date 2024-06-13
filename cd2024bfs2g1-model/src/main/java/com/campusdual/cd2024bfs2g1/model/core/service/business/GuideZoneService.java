package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideLanguageService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideLanguageDao;
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
@Service("GuideZoneService")
public class GuideZoneService implements IGuideZoneService {

    private final DefaultOntimizeDaoHelper daoHelper;

    private final GuideZoneDao guideZoneDao;

    @Autowired
    public GuideZoneService(DefaultOntimizeDaoHelper daoHelper, GuideZoneDao guideZoneDao) {
        this.daoHelper = daoHelper;
        this.guideZoneDao = guideZoneDao;
    }

    @Override
    public EntityResult guideZoneQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.guideZoneDao,keysValues, attributes);
    }

    @Override
    public EntityResult  guideZoneInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.guideZoneDao, keysValues);
    }

    @Override
    public EntityResult  guideZoneUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.guideZoneDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult  guideZoneDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.guideZoneDao, keysValues);
    }
}
