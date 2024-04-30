package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideLanguageService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideLanguageDao;
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
@Service("GuideLanguageService")
public class GuideLanguageService implements IGuideLanguageService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private GuideLanguageDao guideLanguageDao;

    @Override
    public EntityResult guideLanguageQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.guideLanguageDao,keysValues, attributes);
    }

    @Override
    public EntityResult  guideLanguageInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.guideLanguageDao, keysValues);
    }

    @Override
    public EntityResult  guideLanguageUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.guideLanguageDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult  guideLanguageDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.guideLanguageDao, keysValues);
    }
}
