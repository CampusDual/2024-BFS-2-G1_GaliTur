package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.campusdual.cd2024bfs2g1.api.core.util.Utils;
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
    private final PackDateService packDateService;
    private final GuideCitiesDao cityDao;
    private final ImageService imageService;
    private final GuideCitiesService cityService;
    private final ImagePackService imagePackService;
    private final ClientService clientService;

    @Autowired
    public PackService(DefaultOntimizeDaoHelper daoHelper, PackDao packDao, PackDateService packDateService, GuideCitiesDao cityDao, ImageService imageService, GuideCitiesService cityService, ImagePackService imagePackService, ClientService clientService) {
        this.daoHelper = daoHelper;
        this.packDao = packDao;
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
        return this.daoHelper.query(this.packDao, keyMap, attrList);
    }

    @Override
    public EntityResult packImageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        Object key = keyMap.remove("pck_id");
        keyMap.put("p.pck_id", key);
        attrList.remove("pck_id");
        attrList.add("p.pck_id");
        return this.daoHelper.query(this.packDao, keyMap, attrList, "packsDetails");
    }

    @Override
    public EntityResult packProvinceQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList, PackDao.PCK_ACOORDING_PROVINCE_QUERY);
    }

    /**
     * Lists set of packs purchased by a client (logged user)
     *
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
    public AdvancedEntityResult allPacksPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, this.packDao.PCK_ALL_QUERY);
    }

    @Override
    public AdvancedEntityResult packClientPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.packDao, keysValues, attributes, recordNumber, startIndex, orderBy, this.packDao.PCK_ALL_QUERY);
    }

    @Override
    @Secured(PermissionsProviderSecured.SECURED)
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {

        EntityResult erInsertImage = insertImage(attrMap);
        if (erInsertImage != null && erInsertImage.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertImage;

        EntityResult erInsertPack = this.daoHelper.insert(this.packDao, attrMap);
        if (erInsertPack.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertPack;
        Integer packId = (Integer) erInsertPack.get(PackDao.PCK_ID);

        EntityResult erInsertPackDate = insertPackDate(attrMap, packId);
        if ((erInsertPackDate != null && erInsertPackDate.getCode() != EntityResult.OPERATION_SUCCESSFUL) || erInsertImage == null) return erInsertPackDate;

        Object imgId = erInsertImage.get(ImageDao.ATTR_IMAGE_ID);
        return this.imagePackService.imagePackInsert(Map.of(
                ImagePackDao.PCK_ID, packId,
                ImagePackDao.IMG_ID, imgId
        ));
    }

    private EntityResult insertPackDate(Map<String, Object> attrMap, Integer packId) throws ParseException {
        Date beginDate = (Date) attrMap.remove(PackDateDao.PD_DATE_BEGIN);
        Date endDate = (Date) attrMap.remove(PackDateDao.PD_DATE_END);
        if (beginDate == null) return null;

        Map<String, Object> packDate = Map.of(
                PackDateDao.PD_DATE_BEGIN, beginDate,
                PackDateDao.PD_DATE_END, endDate,
                PackDateDao.PCK_ID, packId
        );
        return packDateService.packDateInsert(packDate);
    }

    private EntityResult insertImage(Map<String, Object> attrMap) {
        Object imgCode = attrMap.remove(ImageDao.ATTR_IMAGE_CODE);
        return imgCode == null ? null : imageService.imageInsert(Map.of(ImageDao.ATTR_IMAGE_CODE, imgCode));
    }

    @Override
    public EntityResult packUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
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
