package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.ILandmarkService;
import com.campusdual.cd2024bfs2g1.model.core.dao.*;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.text.Keymap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Lazy
@Service("LandmarkService")
public class LandmarkService implements ILandmarkService {

    private LandmarkDao landmarkDao;
    private ImageDao imageDao;
    private ImageLandmarkDao imageLandmarkDao;
    private DefaultOntimizeDaoHelper daoHelper;
    private RouteAndLandmarkService routeAndLandmarkService;
    private RouteLandmarkDao routeLandmarkDao;

    @Autowired
    public LandmarkService(LandmarkDao landmarkDao, ImageDao imageDao, ImageLandmarkDao imageLandmarkDao, DefaultOntimizeDaoHelper daoHelper, RouteAndLandmarkService routeAndLandmarkService, RouteLandmarkDao routeLandmarkDao) {
        this.landmarkDao = landmarkDao;
        this.imageDao = imageDao;
        this.imageLandmarkDao = imageLandmarkDao;
        this.daoHelper = daoHelper;
        this.routeAndLandmarkService = routeAndLandmarkService;
        this.routeLandmarkDao = routeLandmarkDao;
    }

    @Override
    public EntityResult landmarkQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(landmarkDao, keyMap, attrList, LandmarkDao.QUERY_LANDMARK_NAME);
    }

    @Override
    public EntityResult landmarkOfRouteQuery(Map<String, Object> keyMap, List<String> attrList) {
        if(keyMap.containsKey(routeLandmarkDao.ATTR_ROUTE_ID)){
            Object route_id = keyMap.remove(routeLandmarkDao.ATTR_ROUTE_ID);
            keyMap.put("r."+routeLandmarkDao.ATTR_ROUTE_ID,Integer.parseInt(route_id+""));
        }
        EntityResult aux = this.daoHelper.query(landmarkDao, keyMap, attrList, LandmarkDao.QUERY_LANDMARKS);
        return aux;
    }

    @Override
    public EntityResult landmarkInsert(Map<String, Object> attrMap) {
        EntityResult insertLandmarkId = this.daoHelper.insert(landmarkDao, attrMap);
        Map<String,Object> landmarkRouteMapAttr=new HashMap();
        if (attrMap.get("inputIdRoute")!=null) {
            landmarkRouteMapAttr.put(RouteDao.ATTR_ID,attrMap.get("inputIdRoute"));
            landmarkRouteMapAttr.put(LandmarkDao.ATTR_ID, insertLandmarkId.get(LandmarkDao.ATTR_ID));
        }
        return this.daoHelper.insert(routeLandmarkDao, landmarkRouteMapAttr);
    }

    @Override
    public EntityResult landmarkDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        EntityResult landmarkDeleteResult = null;
        List<String> attrList = new ArrayList<>();
        attrList.add(routeLandmarkDao.ATTR_LANDMARK_ID);
        //Buscamos el landmark para borrar
        EntityResult landmarkDeleteObject = this.daoHelper.query(routeLandmarkDao, keyMap,attrList);
        //Borramos la entrada de la tabla intermedia
        EntityResult landmarkRouteDeleteResult = this.daoHelper.delete(routeLandmarkDao, keyMap);
        if(landmarkRouteDeleteResult.getCode()==EntityResult.OPERATION_SUCCESSFUL){
            //Borramos el landmark
           return deleteLandmarkAux(((ArrayList)landmarkDeleteObject.get(landmarkDao.ATTR_ID)).get(0));
        }
        return landmarkDeleteResult;
    }

    public EntityResult deleteLandmarkAux(Object landmark_id){
        Map<String,Object> landmarkDeleteKey = new HashMap<>();
        landmarkDeleteKey.put(landmarkDao.ATTR_ID,landmark_id);
        return this.daoHelper.delete(landmarkDao, landmarkDeleteKey);
    }
}
