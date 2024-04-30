package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideLanguageService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guideLanguages")
public class GuideLanguageRestController extends ORestController<IGuideLanguageService> {
    @Autowired
    private IGuideLanguageService iGuideLanguageService;


    @Override
    public IGuideLanguageService getService() {
        return this.iGuideLanguageService;
    }
}
