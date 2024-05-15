package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Lazy
@Service("ImageService")
public class ImageService implements IImageService {

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.imageDao,keyMap,attrList);
    }
    @Override
    public EntityResult imageInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(imageDao, attrMap);
    }

    @Override
    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.imageDao,keyMap,attrList);
    }

}
