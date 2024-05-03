package com.campusdual.cd2024bfs2g1.ws.core.rest;

import com.campusdual.cd2024bfs2g1.api.core.service.IClientService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController extends ORestController<IClientService> {

    @Autowired
    private IClientService clientService;

    @Override
    public IClientService getService() {
        return clientService;
    }
}
