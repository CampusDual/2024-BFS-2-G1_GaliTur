package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IRouteService;
import com.campusdual.cd2024bfs2g1.model.core.dao.*;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.RoutePackDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Lazy
@Service("RouteService")
public class RouteService  implements IRouteService {


    private RouteDao routeDao;
    private LandmarkDao landmarkDao;
    private ImageDao imageDao;
    private ImageRouteDao image_routeDao;
    private DefaultOntimizeDaoHelper daoHelper;
    private LandmarkService landmarkService;
    private RouteLandmarkDao routeLandmarkDao;
    private RoutePackDao routePackDao;

    @Autowired
    public RouteService(RouteDao routeDao, LandmarkDao landmarkDao,
                        ImageDao imageDao, ImageRouteDao image_routeDao,
                        DefaultOntimizeDaoHelper daoHelper,LandmarkService landmarkService,
                        RouteLandmarkDao routeLandmarkDao,RoutePackDao routePackDao) {
        this.routeDao = routeDao;
        this.landmarkDao = landmarkDao;
        this.imageDao = imageDao;
        this.image_routeDao = image_routeDao;
        this.daoHelper = daoHelper;
        this.landmarkService = landmarkService;
        this.routeLandmarkDao = routeLandmarkDao;
        this.routePackDao = routePackDao;
    }

    @Override
    public EntityResult routeQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(routeDao, keyMap, attrList);
    }

    @Override
    public EntityResult routeImageQuery(Map<String, Object> keyMap, List<String> attrList) {
       attrList.remove("route_id");
        return this.daoHelper.query(routeDao, keyMap, attrList, "multi");
    }

    @Override
    public EntityResult packImageForEditQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        if(keyMap.size()>0){
            Object key = keyMap.remove(RouteDao.ATTR_ID);
            keyMap.put("r."+RouteDao.ATTR_ID, key);
        }
        attrList.remove(RouteDao.ATTR_ID);
        attrList.add("r."+RouteDao.ATTR_ID);

        EntityResult EntityAux = this.daoHelper.query(this.routeDao, keyMap, attrList, routeDao.QUERY_ALL_ROUTES);
        return EntityAux;
    }

    @Override
    public EntityResult routeInsert(Map<String, Object> attrMap) {
        UserInformation userInformation =(UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userInformation.getAuthorities().stream().anyMatch(c->c.toString().equals("manager"))){
            Map<String,Object> imageMapAttr=new HashMap();
            EntityResult route_id_entity = this.daoHelper.insert(routeDao, attrMap);
            if (attrMap.get("images")!=null) {
                imageMapAttr.put(ImageDao.ATTR_IMAGE_CODE,attrMap.get("images"));
                EntityResult image_id_entity = this.daoHelper.insert(imageDao, imageMapAttr);
                insertImageAux(route_id_entity.get(RouteDao.ATTR_ID),image_id_entity.get(ImageDao.ATTR_IMAGE_ID));
            }
            return  route_id_entity;
        }else{
            EntityResultMapImpl entity = new EntityResultMapImpl(EntityResult.OPERATION_WRONG,1,"NO_GESTOR");
            return entity;
        }
    }

    @Override
    public boolean UserRolQuery(Map<String, Object> attrMap){
        UserInformation userInformation =(UserInformation) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userInformation.getAuthorities().stream().anyMatch(c->c.toString().equals("manager"))){
            return true;
        }else{
            return false;
        }
    }

    public EntityResult insertImageAux(Object id_route,Object id_image) {
        Map<String,Object> image_RouteMapAttr=new HashMap();
        image_RouteMapAttr.put(RouteDao.ATTR_ID, id_route);
        image_RouteMapAttr.put(ImageDao.ATTR_IMAGE_ID, id_image);
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

    @Override
    public AdvancedEntityResult routePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.routeDao, keysValues, attributes, recordNumber, startIndex, orderBy, RouteDao.QUERY_ROUTE_IMAGE);
    }

    @Override
    public AdvancedEntityResult routeNotDraftPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy) throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.routeDao, keysValues, attributes, recordNumber, startIndex, orderBy, RouteDao.QUERY_ROUTE_NOT_DRAFT);
    }

    @Override
    public EntityResult routesOfPackQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(routeDao, keyMap, attrList, routeDao.QUERY_ROUTES_OF_PACK);
    }

    @Override
    public EntityResult routeUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(routeDao, attrMap, keyMap);
    }

    @Override
    public EntityResult routeDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        EntityResult finalResponse = null;
        List<String> attrList = new ArrayList<>();
        //ImagePart
        attrList.add(image_routeDao.ATTR_IMAGE_ROUTE_ID);
        attrList.add(image_routeDao.ATTR_IMAGE_ID);
        EntityResult actualImageRoute = null;
        actualImageRoute = this.daoHelper.query(image_routeDao, keyMap, attrList);
        if(!actualImageRoute.isEmpty()){
            EntityResult responseDeleteImageRoute = deleteImageRouteAux(((ArrayList)actualImageRoute.get(image_routeDao.ATTR_IMAGE_ROUTE_ID)).get(0));
            EntityResult responseDeleteImage = deleteImageAux(((ArrayList)actualImageRoute
                    .get(image_routeDao.ATTR_IMAGE_ID)).get(0));
        }

        attrList.clear();
        //Landkmark part
        attrList.add(landmarkDao.ATTR_ID);
        attrList.add(routeLandmarkDao.ATTR_ROUTE_LANDMARK_ID);
        EntityResult routeLandmark = null;
        routeLandmark = this.daoHelper.query(routeLandmarkDao, keyMap, attrList);


        if(!routeLandmark.isEmpty()){
            for (Object iter : ((ArrayList)routeLandmark.get(routeLandmarkDao.ATTR_ROUTE_LANDMARK_ID))) {
                Map landmarkDeleteMap = new HashMap<String,Object>();
                landmarkDeleteMap.put(routeLandmarkDao.ATTR_ROUTE_LANDMARK_ID,
                        iter);
                landmarkService.landmarkDelete(landmarkDeleteMap);
            }

        }
        //Pack part
        attrList.clear();
        //Landkmark part
        attrList.add(routePackDao.ROUTE_PACK_ID);
        EntityResult routePack = null;
        routePack = this.daoHelper.query(routePackDao, keyMap, attrList);


        if(!routePack.isEmpty()){
            for (Object iter : ((ArrayList)routePack.get(routePackDao.ROUTE_PACK_ID))) {
                Map routePackDeleteMap = new HashMap<String,Object>();
                routePackDeleteMap.put("route_pack_id",
                        iter);
                this.daoHelper.delete(routePackDao, routePackDeleteMap);
            }

        }
        //Route delete
        return finalResponse = this.daoHelper.delete(routeDao, keyMap);
    }


    private EntityResult deleteImageRouteAux(Object imageRouteObject){
        Map<String,Object> imageRouteDeleteKey = new HashMap<>();
        imageRouteDeleteKey.put(image_routeDao.ATTR_IMAGE_ROUTE_ID,imageRouteObject);
        return this.daoHelper.delete(image_routeDao, imageRouteDeleteKey);
    }
    private EntityResult deleteImageAux(Object imageObject){
        Map<String,Object> imageDeleteKey = new HashMap<>();
        imageDeleteKey.put(imageDao.ATTR_IMAGE_ID,imageObject);
        return this.daoHelper.delete(imageDao, imageDeleteKey);
    }

    @Override
    public EntityResult searchPacksQuery(Map<String, Object> keyMap, List<String> attrList) {
        if(keyMap.containsKey("route_id")){
            Object route_idAux = keyMap.remove("route_id");
            keyMap.put("r."+routeDao.ATTR_ID,route_idAux);
        }
        EntityResult auxObject = this.daoHelper.query(routeDao, keyMap, attrList,routeDao.QUERY_SEARCH_PACKS);
        return auxObject;
    }
}
