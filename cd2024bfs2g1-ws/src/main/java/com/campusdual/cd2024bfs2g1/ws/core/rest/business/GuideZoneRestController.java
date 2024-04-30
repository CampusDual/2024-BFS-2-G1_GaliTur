package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideLanguageService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guideZones")
public class GuideZoneRestController extends ORestController<IGuideZoneService> {
    @Autowired
    private IGuideZoneService iGuideZoneService;


    @Override
    public IGuideZoneService getService() {
        return this.iGuideZoneService;
    }
}
