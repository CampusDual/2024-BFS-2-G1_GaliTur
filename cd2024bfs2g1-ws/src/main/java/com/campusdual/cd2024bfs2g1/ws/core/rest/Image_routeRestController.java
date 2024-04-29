package com.campusdual.cd2024bfs2g1.ws.core.rest;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.campusdual.cd2024bfs2g1.api.core.service.IImage_routeService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes3")
public class Image_routeRestController extends ORestController<IImage_routeService> {

    @Autowired
    private IImage_routeService image_routeService;

    @Override
    public IImage_routeService getService() {
        return this.image_routeService;
    }
}
