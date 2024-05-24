package com.campusdual.cd2024bfs2g1.ws.core.rest.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IRoutePackService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routePacks")
public class RoutePackController extends ORestController<IRoutePackService> {

    @Autowired
    private IRoutePackService routePackService;
    @Override
    public IRoutePackService getService() {
        return routePackService;
    }
}
