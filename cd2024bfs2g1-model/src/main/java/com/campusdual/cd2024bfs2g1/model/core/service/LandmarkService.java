package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.ILandmarkService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.LandmarkDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteLandmarkDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Lazy
@Service("LandmarkService")
public class LandmarkService implements ILandmarkService {

    @Autowired
    private LandmarkDao landmarkDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private ImageDao image_landmarkDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private RouteAndLandmarkService routeAndLandmarkService;
    @Autowired
    private RouteLandmarkDao routeLandmarkDao;

    @Override
    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(landmarkDao, keyMap, attrList);
    }

    @Override
    public EntityResult landmarkInsert(Map<String, Object> attrMap) {
        EntityResult insertLandmarkId = this.daoHelper.insert(landmarkDao, attrMap);
        Map<String,Object> landmarkRouteMapAttr=new HashMap();
//        Map<String,Object> imageMapAttr=new HashMap();
//        if (attrMap.get("images")!=null) {
//            imageMapAttr.put(ImageDao.ATTR_IMAGE_CODE,attrMap.get("images"));
//            EntityResult image_id_entity = this.daoHelper.insert(imageDao, imageMapAttr);
//            insertImageAux(insertLandmarkId.get(LandmarkDao.ATTR_ID),image_id_entity.get(ImageDao.ATTR_IMAGE_ID));
//        }
        if (attrMap.get("inputIdRoute")!=null) {
            landmarkRouteMapAttr.put(RouteDao.ATTR_ID,attrMap.get("inputIdRoute"));
            landmarkRouteMapAttr.put(LandmarkDao.ATTR_ID, insertLandmarkId.get(LandmarkDao.ATTR_ID));
        }
        return this.daoHelper.insert(routeLandmarkDao, landmarkRouteMapAttr);
    }

//    public EntityResult insertImageAux(Object id_landmark,Object id_image) {
//        Map<String,Object> image_LandmarkMapAttr=new HashMap();
//        image_LandmarkMapAttr.put(LandmarkDao.ATTR_ID, id_landmark);
//        image_LandmarkMapAttr.put(ImageDao.ATTR_IMAGE_ID, id_image);
//        return this.daoHelper.insert(image_landmarkDao, image_LandmarkMapAttr);
//    }
}
