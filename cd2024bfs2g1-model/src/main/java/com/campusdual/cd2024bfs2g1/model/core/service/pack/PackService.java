package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideCitiesDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.ImagePackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDateDao;
import com.campusdual.cd2024bfs2g1.model.core.service.ClientService;
import com.campusdual.cd2024bfs2g1.model.core.service.ImageService;
import com.campusdual.cd2024bfs2g1.model.core.service.business.GuideCitiesService;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackService")
public class PackService implements IPackService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackDao packDao;
    private final PackDateDao packDateDao;
    private final ImageDao imageDao;
    private final ImagePackDao imagePackDao;
    private final PackDateService packDateService;
    private final GuideCitiesDao cityDao;
    private final ImageService imageService;
    private final GuideCitiesService cityService;
    private final ImagePackService imagePackService;
    private final ClientService clientService;

    @Autowired
    public PackService(DefaultOntimizeDaoHelper daoHelper, PackDao packDao, ImageDao imageDao, GuideCitiesDao cityDao,
                       ImageService imageService, GuideCitiesService cityService, ImagePackService imagePackService, ClientService clientService,
                       ImagePackDao imagePackDao,PackDateDao packDateDao,PackDateService packDateService) {

        this.daoHelper = daoHelper;
        this.packDao = packDao;
        this.packDateService = packDateService;
        this.imageDao = imageDao;
        this.cityDao = cityDao;
        this.imageService = imageService;
        this.cityService = cityService;
        this.imagePackService = imagePackService;
        this.clientService = clientService;
        this.imagePackDao = imagePackDao;
        this.packDateDao = packDateDao;
    }

    @Override
    public EntityResult packQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList);
    }
    @Override
    public AdvancedEntityResult packPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, this.packDao.PCK_IMG_PACK_DETAIL);
    }
    @Override
    public EntityResult packImageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        if(keyMap.size()>0){
            Object key = keyMap.remove("pck_id");
            keyMap.put("p.pck_id", key);
        }
        attrList.remove("pck_id");
        attrList.add("p.pck_id");

        EntityResult EntityAux = this.daoHelper.query(this.packDao, keyMap, attrList, "packsDetails");
        return EntityAux;
    }
  
    @Override
    public EntityResult packProvinceQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_ACOORDING_PROVINCE_QUERY);
    }

    @Override
    public EntityResult packImageUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Map<String, Object> attrMapUpdate = new HashMap<>();
        //Meter la imagen
        EntityResult imagePackUpdate  = null;
        EntityResult imageInsert = this.daoHelper.insert(imageDao, attrMap);

//        if(imageInsert.getCode()==EntityResult.OPERATION_SUCCESSFUL){
            attrMapUpdate.put(imagePackDao.IMG_ID,imageInsert.get("image_id"));
            keyMap.remove("pck_id");
            return imagePackUpdate = this.daoHelper.update(imagePackDao,attrMapUpdate,keyMap);
//        }
        //Meter la relacion

    }

    /**
     * Lists set of packs purchased by a client (logged user)
     * @param keysValues filter (client id)
     * @param attributes query columns
     * @return query data
     * @throws OntimizeJEERuntimeException
     */
    @Override
    public EntityResult packClientQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        keysValues.put(ClientDao.CLIENT_ID, clientService.getClientId());
        return this.daoHelper.query(this.packDao, keysValues, attributes, this.packDao.PCK_MULTI_QUERY);
    }

    @Override
    public EntityResult allPacksQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keysValues, attributes, this.packDao.PCK_ALL_QUERY);
    }

    @Override
    @Secured(PermissionsProviderSecured.SECURED)
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {

        Object imgCode = attrMap.get(ImageDao.ATTR_IMAGE_CODE);
        attrMap.remove(ImageDao.ATTR_IMAGE_CODE);
        EntityResult erInsertImage = null;
        if (imgCode != null) {
            erInsertImage = imageService.imageInsert(Map.of(ImageDao.ATTR_IMAGE_CODE, imgCode));
            if (erInsertImage.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertImage;
        }

        EntityResult erInsertPack = this.daoHelper.insert(this.packDao, attrMap);
        if (erInsertPack.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertPack;

        Date beginDate = (Date) attrMap.remove(PackDateDao.PD_DATE_BEGIN);
        Date endDate = (Date) attrMap.remove(PackDateDao.PD_DATE_END);
        Map<String, Object> packDate = new HashMap<>();
        packDate.put(PackDateDao.PD_DATE_BEGIN, beginDate);
        packDate.put(PackDateDao.PD_DATE_END, endDate);
        packDate.put(PackDateDao.PCK_ID, erInsertPack.get(PackDao.PCK_ID));
        EntityResult erInsertPackDate = packDateService.packDateInsert(packDate);
        if (erInsertPackDate.getCode() != EntityResult.OPERATION_SUCCESSFUL) return  erInsertPackDate;

        Object packId = erInsertPack.get(PackDao.PCK_ID);
        Object imgId = erInsertImage == null ? ImageDao.DEFAULT_IMG_ID : erInsertImage.get(ImageDao.ATTR_IMAGE_ID);
        return this.imagePackService.imagePackInsert(Map.of(ImagePackDao.PCK_ID, packId, ImagePackDao.IMG_ID, imgId));
    }

    @Override
    public EntityResult packUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        //Guardamos la base siempre
        if(attrMap.containsKey("pck_date_begin") && attrMap.containsKey("pck_date_end")) {
            //Creamos el pack completo porque ya tiene fechas
            this.daoHelper.insert(packDateDao, attrMap);
            //Le quitamos las fechas porque no nos hacen falta
            attrMap.remove("pck_date_begin");
            attrMap.remove("pck_date_end");
        }
        return this.daoHelper.update(this.packDao, attrMap, keyMap);

    }

    @Override
    public EntityResult packDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packDao, keyMap);
    }

    @Override
    public EntityResult newestQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_NEWEST_QUERY);
    }
}
