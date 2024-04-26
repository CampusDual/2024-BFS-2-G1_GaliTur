package com.campusdual.cd2024bfs2g1.ws.core.rest;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.campusdual.cd2024bfs2g1.api.core.service.IRouteService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
public class ImageRestController extends ORestController<IImageService> {

    @Autowired
    private IImageService imageService;

    @Override
    public IImageService getService() {
        return this.imageService;
    }
}
