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

import java.util.*;


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
    public PackService(DefaultOntimizeDaoHelper daoHelper, PackDao packDao,ImageDao imageDao, PackDateService packDateService,
                       GuideCitiesDao cityDao, ImageService imageService, GuideCitiesService cityService,
                       ImagePackService imagePackService, ClientService clientService, ImagePackDao imagePackDao, PackDateDao packDateDao) {
        this.daoHelper = daoHelper;
        this.packDao = packDao;
        this.packDateDao = packDateDao;
        this.imageDao = imageDao;
        this.imagePackDao = imagePackDao;
        this.packDateService = packDateService;
        this.cityDao = cityDao;
        this.imageService = imageService;
        this.cityService = cityService;
        this.imagePackService = imagePackService;
        this.clientService = clientService;
    }

    @Override
    public EntityResult packQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        attrList.remove("comboDates");
        return this.daoHelper.query(this.packDao, keyMap, attrList);
    }

    @Override
    public EntityResult packImageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        if(keyMap.size()>0){
            Object key = keyMap.remove(PackDao.PCK_ID);
            keyMap.put("p."+PackDao.PCK_ID, key);
        }
        attrList.remove(PackDao.PCK_ID);
        attrList.add("p."+PackDao.PCK_ID);

        EntityResult EntityAux = this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_DETAILS_QUERY);
        return EntityAux;
    }

    @Override
    public AdvancedEntityResult packMultiPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, PackDao.PCK_IMG_PACK_DETAIL);
    }
    @Override
    public EntityResult packProvinceQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_ACOORDING_PROVINCE_QUERY);
    }

    @Override
    public AdvancedEntityResult packPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy) throws OntimizeJEERuntimeException {
        return null;
    }

    @Override
    public EntityResult packImageUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Map<String, Object> attrMapUpdate = new HashMap<>();
        //Meter la imagen
        EntityResult imagePackUpdate  = null;
        EntityResult imageInsert = this.daoHelper.insert(imageDao, attrMap);

        if(imageInsert.getCode()==EntityResult.OPERATION_SUCCESSFUL){
            attrMapUpdate.put(imagePackDao.IMG_ID,imageInsert.get("image_id"));
            keyMap.remove("pck_id");
            return imagePackUpdate = this.daoHelper.update(imagePackDao,attrMapUpdate,keyMap);
        }
        //Meter la relacion
        return imagePackUpdate;
    }

    @Override
    public EntityResult packDaysQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        int n = Integer.parseInt((String) keyMap.get(PackDao.PCK_ID));
        keyMap.put(PackDao.PCK_ID, n);
        EntityResult er = this.daoHelper.query(this.packDao, keyMap, attrList);
        List<Integer> lista = (List<Integer>) er.get(PackDao.PCK_DAYS);
        int days = lista.get(0);
        List<Map<String, Object>> dias = new ArrayList<>();
        for (int i = 1; i <= days; i++) {
            Map<String, Object> mapaDias = new HashMap<>();
            mapaDias.put("day", i);
            mapaDias.put("day_string", Integer.toString(i));
            dias.add(mapaDias);
        }

        List<List<Map<String, Object>>> lista_de_listas = new ArrayList<>();
        lista_de_listas.add(dias);
        er.put(PackDao.PCK_DAYS, lista_de_listas);
        return er;
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
    public EntityResult packDetailQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        if(keysValues.containsKey(PackDao.PCK_ID)){
            Object key = keysValues.remove("pck_id");
            keysValues.put("p.pck_id", key);
        }
        return this.daoHelper.query(this.packDao, keysValues, attributes, this.packDao.PCK_DETAIL);
    }

    @Override
    public EntityResult packAndBookingDetailQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        if(keysValues.containsKey(PackDao.PCK_ID)){
            Object key = keysValues.remove("pck_id");
            keysValues.put("p.pck_id", key);
        }
        attributes.remove(PackDao.PCK_ID);
        return this.daoHelper.query(this.packDao, keysValues, attributes, this.packDao.PCK_MULTI_QUERY);
    }
    @Override
    public EntityResult packCancelDetailQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        if(keysValues.containsKey(PackDao.PCK_ID)){
            Object key = keysValues.remove("pck_id");
            keysValues.put("p.pck_id", key);
        }
        EntityResult result = this.daoHelper.query(this.packDao, keysValues, attributes, this.packDao.PCK_CANCEL_DETAIL);
        return result;
    }

    @Override
    public AdvancedEntityResult allPacksPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, this.packDao.PCK_ALL_QUERY);
    }


    @Override
    public AdvancedEntityResult packClientPaginationQuery(Map<String, Object> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        keysValues.put(ClientDao.CLIENT_ID, clientService.getClientId());
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, this.packDao.PCK_MULTI_QUERY);
    }

    @Override
    @Secured(PermissionsProviderSecured.SECURED)
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {

        // For later use
        Object imgCode = attrMap.remove(ImageDao.ATTR_IMAGE_CODE);
        Date beginDate = (Date) attrMap.remove(PackDateDao.PD_DATE_BEGIN);
        Date endDate = (Date) attrMap.remove(PackDateDao.PD_DATE_END);

        // Insert into pack
        EntityResult erInsertPack = this.daoHelper.insert(this.packDao, attrMap);
        if (erInsertPack.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertPack;
        Integer packId = (Integer) erInsertPack.get(PackDao.PCK_ID);

        if (beginDate != null){
            // Insert into pack_date
            EntityResult erInsertPackDate = packDateService.packDateInsert(Map.of(
                    PackDateDao.PD_DATE_BEGIN, beginDate,
                    PackDateDao.PD_DATE_END, endDate,
                    PackDateDao.PCK_ID, packId
            ));
            if ((erInsertPackDate.getCode() != EntityResult.OPERATION_SUCCESSFUL)) return erInsertPackDate;
        }

        if (imgCode != null){
            // Insert into image
            EntityResult erInsertImage = imageService.imageInsert(Map.of(ImageDao.ATTR_IMAGE_CODE, imgCode));
            if (erInsertImage.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertImage;
            Object imgId = erInsertImage.get(ImageDao.ATTR_IMAGE_ID);

            // Insert into image_pack
            EntityResult erInsertImagePack = this.imagePackService.imagePackInsert(Map.of(
                    ImagePackDao.PCK_ID, packId,
                    ImagePackDao.IMG_ID, imgId
            ));
            if (erInsertImagePack.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertImagePack;
        }

        return erInsertPack;
    }

    @Override
    public EntityResult packUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        //Guardamos la base siempre
        if(attrMap.containsKey(PackDao.PCK_DATE_BEGIN) && attrMap.containsKey(PackDao.PCK_DATE_END)) {
            //Creamos el pack completo porque ya tiene fechas
            this.daoHelper.insert(packDateDao, attrMap);
            //Le quitamos las fechas porque no nos hacen falta
            attrMap.remove(PackDao.PCK_DATE_BEGIN);
            attrMap.remove(PackDao.PCK_DATE_END);
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

    @Override
    public EntityResult popularPacksQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_POPULARS_QUERY);
    }
}
