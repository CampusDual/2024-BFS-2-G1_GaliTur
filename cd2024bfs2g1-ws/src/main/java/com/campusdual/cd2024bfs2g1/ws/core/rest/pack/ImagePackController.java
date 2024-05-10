package com.campusdual.cd2024bfs2g1.ws.core.rest.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IImagePackService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagePacks")
public class ImagePackController extends ORestController<IImagePackService> {
    @Autowired
    private IImagePackService imagePackService;
    @Override
    public IImagePackService getService() {
        return imagePackService;
    }
}
