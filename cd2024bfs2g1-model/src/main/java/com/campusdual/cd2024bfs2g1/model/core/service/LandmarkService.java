package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.ILandmarkService;
import com.campusdual.cd2024bfs2g1.model.core.dao.LandmarkDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Lazy
@Service("LandmarkService")
public class LandmarkService implements ILandmarkService {

    @Autowired
    private LandmarkDao landmarkDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(landmarkDao, keyMap, attrList);
    }

    @Override
    public EntityResult landmarkInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(landmarkDao, attrMap);
    }
}
