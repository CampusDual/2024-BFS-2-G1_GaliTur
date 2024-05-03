package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideCitiesService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guideCities")
public class GuideCitiesRestController extends ORestController<IGuideCitiesService> {
    @Autowired
    private IGuideCitiesService iGuideCitiesService;


    @Override
    public IGuideCitiesService getService() {
        return this.iGuideCitiesService;
    }
}
