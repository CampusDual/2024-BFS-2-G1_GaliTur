package com.campusdual.cd2024bfs2g1.ws.core.rest.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IBusinessPackService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessPacks")
public class BusinessPackController extends ORestController<IBusinessPackService> {

    @Autowired
    private IBusinessPackService iBusinessPackService;
    @Override
    public IBusinessPackService getService() {
        return iBusinessPackService;
    }
}
