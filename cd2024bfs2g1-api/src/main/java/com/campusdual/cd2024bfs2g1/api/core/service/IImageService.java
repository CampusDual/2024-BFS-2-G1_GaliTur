package com.campusdual.cd2024bfs2g1.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;

import java.util.List;
import java.util.Map;

public interface IImageService {

    public EntityResult imageQuery(Map<String, Object> keyMap, List<String> attrList);
    public EntityResult imageInsert(Map<String, Object> attrMap);
}
