package com.campusdual.cd2024bfs2g1.ws.core.rest.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackDateService;
import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packDates")
public class PackServiceController extends ORestController<IPackDateService> {
    @Autowired
    private IPackDateService packDateService;

    @Override
    public IPackDateService getService() {
        return packDateService;
    }
}
