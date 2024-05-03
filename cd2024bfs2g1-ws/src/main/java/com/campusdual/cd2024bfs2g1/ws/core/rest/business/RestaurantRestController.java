package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IRestaurantService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController extends ORestController<IRestaurantService> {
    @Autowired
    private IRestaurantService iRestaurantService;


    @Override
    public IRestaurantService getService() {
        return this.iRestaurantService;
    }
}
