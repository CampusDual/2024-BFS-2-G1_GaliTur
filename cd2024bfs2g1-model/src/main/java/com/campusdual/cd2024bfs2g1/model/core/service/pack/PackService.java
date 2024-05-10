package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideCitiesDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.ImagePackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.campusdual.cd2024bfs2g1.model.core.service.ImageService;
import com.campusdual.cd2024bfs2g1.model.core.service.business.GuideCitiesService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.event.ObjectChangeListener;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackService")
public class PackService implements IPackService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final PackDao packDao;
    private final ImageDao imageDao;
    private final GuideCitiesDao cityDao;
    private final ImageService imageService;
    private final GuideCitiesService cityService;
    private final ImagePackService imagePackService;

    @Autowired
    public PackService(DefaultOntimizeDaoHelper daoHelper, PackDao packDao, ImageDao imageDao, GuideCitiesDao cityDao,
                       ImageService imageService, GuideCitiesService cityService, ImagePackService imagePackService) {
        this.daoHelper = daoHelper;
        this.packDao = packDao;
        this.imageDao = imageDao;
        this.cityDao = cityDao;
        this.imageService = imageService;
        this.cityService = cityService;
        this.imagePackService = imagePackService;

    }

    @Override
    public EntityResult packQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packDao, keyMap, attrList);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public EntityResult packInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {

//        Object imgCode = attrMap.get(ImageDao.ATTR_IMAGE_CODE);
//        attrMap.remove(ImageDao.ATTR_IMAGE_CODE);
//        EntityResult erInsertImage = imageService.imageInsert(Map.of(ImageDao.ATTR_IMAGE_CODE, imgCode));
//        if (erInsertImage.getCode() != EntityResult.OPERATION_SUCCESSFUL)
//            return erInsertImage;
//
//        Integer cityIds[] = (Integer[]) attrMap.get("comboCity");
//        EntityResult erInsertPack
//
//        EntityResult erInsertPack = this.daoHelper.insert(this.packDao, attrMap);
        return null; // TODO
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
}
