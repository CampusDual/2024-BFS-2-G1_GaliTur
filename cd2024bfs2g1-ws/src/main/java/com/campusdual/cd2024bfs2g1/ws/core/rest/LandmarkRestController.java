package com.campusdual.cd2024bfs2g1.ws.core.rest;


import com.campusdual.cd2024bfs2g1.api.core.service.ILandmarkService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landmarks")
public class LandmarkRestController extends ORestController<ILandmarkService> {

    @Autowired
    private ILandmarkService landmarkService;

    @Override
    public ILandmarkService getService() {
        return landmarkService;
    }
}
