package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.campusdual.cd2024bfs2g1.api.core.util.Utils;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.ImagePackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.campusdual.cd2024bfs2g1.model.core.service.ImageService;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Lazy
@Service("PackService")
public class PackService implements IPackService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackDao packDao;
    private final ImageService imageService;
    private final ImagePackService imagePackService;

    @Autowired
    public PackService(DefaultOntimizeDaoHelper daoHelper, PackDao packDao, ImageService imageService, ImagePackService imagePackService) {
        this.daoHelper = daoHelper;
        this.packDao = packDao;
        this.imageService = imageService;
        this.imagePackService = imagePackService;
    }

    @Override
    public EntityResult packQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList);
    }

    @Override
    @Secured(PermissionsProviderSecured.SECURED)
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException, ParseException {
        Object imgCode = attrMap.get(ImageDao.ATTR_IMAGE_CODE);
        attrMap.remove(ImageDao.ATTR_IMAGE_CODE);

        Map<String, Object> dates = (Map<String, Object>) attrMap.get("dates");
        attrMap.remove("dates");
        attrMap.put(PackDao.PCK_DATE_BEGIN, Utils.iso8601Format.parse((String) dates.get("startDate")));
        attrMap.put(PackDao.PCK_DATE_END, Utils.iso8601Format.parse((String) dates.get("endDate")));

        EntityResult erInsertPack = this.daoHelper.insert(this.packDao, attrMap);
        if (erInsertPack.getCode() != EntityResult.OPERATION_SUCCESSFUL || imgCode == null) return erInsertPack;

        EntityResult erInsertImage = imageService.imageInsert(Map.of(ImageDao.ATTR_IMAGE_CODE, imgCode));
        if (erInsertImage.getCode() != EntityResult.OPERATION_SUCCESSFUL) return erInsertImage;

        Object packId = erInsertPack.get(PackDao.PCK_ID);
        Object imgId = erInsertImage.get(ImageDao.ATTR_IMAGE_ID);
        return this.imagePackService.imagePackInsert(Map.of(ImagePackDao.PCK_ID, packId, ImagePackDao.IMG_ID, imgId));
    }

    @Override
    public EntityResult packUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packDao, attrMap, keyMap);
    }

    @Override
    public EntityResult packDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packDao, keyMap);
    }
}
