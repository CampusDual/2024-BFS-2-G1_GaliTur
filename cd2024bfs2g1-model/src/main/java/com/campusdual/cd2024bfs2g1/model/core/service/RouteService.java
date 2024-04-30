package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IRouteService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.Image_routeDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.LandmarkDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("RouteService")
public class RouteService  implements IRouteService {

    @Autowired
    private RouteDao routeDao;
    @Autowired
    private LandmarkDao landmarkDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private Image_routeDao image_routeDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult routeQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(routeDao, keyMap, attrList);

    }

    @Override
    public EntityResult routeInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(routeDao, attrMap);
    }

    @Override
    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(landmarkDao, keyMap, attrList);
    }


    @Override
    public EntityResult image_routeQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(image_routeDao, keyMap, attrList);
    }

    @Override
    public EntityResult image_routeInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(image_routeDao, attrMap);
    }

    @Override
    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(imageDao, keyMap, attrList, imageDao.QUERY_IMAGE_CODE);
    }

    @Override
    public EntityResult imageInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(imageDao, attrMap);
    }
}
