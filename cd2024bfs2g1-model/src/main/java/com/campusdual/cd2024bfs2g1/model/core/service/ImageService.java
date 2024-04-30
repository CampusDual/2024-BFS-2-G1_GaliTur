package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.Image_routeDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;


@Lazy
@Service("ImageService")
public class ImageService implements IImageService {

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult imageInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(imageDao, attrMap);
    }
}
