package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IRouteService;
import com.campusdual.cd2024bfs2g1.model.core.dao.RouteDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("RouteService")
public class RouteService  implements IRouteService {

    @Autowired
    private RouteDao routeDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Override
    public EntityResult routeQuery(Map<String, Object> keyMap, List<String> attrList) {
        return this.daoHelper.query(routeDao, keyMap, attrList);
    }

    @Override
    public EntityResult routeInsert(Map<String, Object> attrMap) {
        return this.daoHelper.insert(routeDao, attrMap);
    }
}
