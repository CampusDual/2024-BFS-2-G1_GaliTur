package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.campusdual.cd2024bfs2g1.api.core.service.IImage_routeService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ImageDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.Image_routeDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("Image_routeService")
public class Image_routeService  implements IImage_routeService {

    @Autowired
    private Image_routeDao image_routeDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult image_routeQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(image_routeDao, keyMap, attrList);
    }

    @Override
    public EntityResult image_routeInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(image_routeDao, attrMap);
    }
}