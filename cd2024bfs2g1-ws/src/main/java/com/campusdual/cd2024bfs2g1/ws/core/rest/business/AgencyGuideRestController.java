package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agencyGuides")
public class AgencyGuideRestController extends ORestController<IAgencyGuideService> {
    @Autowired
    private IAgencyGuideService iAgencyGuideService;


    @Override
    public IAgencyGuideService getService() {
        return this.iAgencyGuideService;
    }
}
