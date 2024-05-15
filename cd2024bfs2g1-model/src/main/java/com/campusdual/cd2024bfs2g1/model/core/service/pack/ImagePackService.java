package com.campusdual.cd2024bfs2g1.model.core.service.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IImagePackService;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.ImagePackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Lazy
@Service("ImagePackService")
public class ImagePackService implements IImagePackService {
    private final DefaultOntimizeDaoHelper daoHelper;
    private final ImagePackDao imagePackDao;

    @Autowired
    public ImagePackService(DefaultOntimizeDaoHelper daoHelper, ImagePackDao imagePackDao) {
        this.daoHelper = daoHelper;
        this.imagePackDao = imagePackDao;
    }
    @Override
    public EntityResult imagePackQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.imagePackDao, keyMap, attrList, ImagePackDao.IMAGE_FOR_PACK);
    }

    @Override
    public EntityResult imagePackInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.imagePackDao, attrMap);
    }

    @Override
    public EntityResult imagePackUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.imagePackDao, attrMap, keyMap);
    }

    @Override
    public EntityResult imagePackDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.imagePackDao, keyMap);
    }
}
