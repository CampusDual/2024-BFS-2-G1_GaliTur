package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackRatingService;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackRatingDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
@Lazy
@Service("PackRatingService")
public class PackRatingService implements IPackRatingService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackRatingDao packRatingDao;

    @Autowired
    public PackRatingService(DefaultOntimizeDaoHelper daoHelper, PackRatingDao packRatingDao) {
        this.daoHelper = daoHelper;
        this.packRatingDao = packRatingDao;
    }

    @Override
    public EntityResult packRatingQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packRatingDao, keyMap, attrList);
    }

    @Override
    public EntityResult packRatingInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {
        return this.daoHelper.insert(this.packRatingDao, attrMap);
    }

    @Override
    public EntityResult packRatingUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packRatingDao, attrMap,keyMap);
    }

    @Override
    public EntityResult packRatingDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packRatingDao, keyMap);
    }
}
