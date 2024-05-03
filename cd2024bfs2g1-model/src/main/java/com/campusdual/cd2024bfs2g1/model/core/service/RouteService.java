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

import java.util.*;

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
        //Map<String,Object> imageMapAttr=new HashMap();

        EntityResult route_id_entity = this.daoHelper.insert(routeDao, attrMap);
//        if (route_id_entity.getCode()!=EntityResult.OPERATION_WRONG) {
//            imageMapAttr.put("img_code",attrMap.get("images"));
//            EntityResult image_id_entity = this.daoHelper.insert(imageDao, imageMapAttr);
//           if (image_id_entity.getCode()!=EntityResult.OPERATION_WRONG) {
//              insertImageAux(route_id_entity.get("route_id"),image_id_entity.get("image_id"));
//           }
//        }
        return route_id_entity;
    }

    public EntityResult insertImageAux(Object id_route,Object id_image) {
        Map<String,Object> image_RouteMapAttr=new HashMap();
        image_RouteMapAttr.put("route_id", id_route);
        image_RouteMapAttr.put("image_id", id_image);
        return this.daoHelper.insert(image_routeDao, image_RouteMapAttr);
    }

    @Override
    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(landmarkDao, keyMap, attrList, landmarkDao.QUERY_LANDMARK_NAME);
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