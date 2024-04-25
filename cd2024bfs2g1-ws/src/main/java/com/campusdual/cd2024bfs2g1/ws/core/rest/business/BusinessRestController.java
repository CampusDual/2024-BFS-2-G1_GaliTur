package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IBusinessService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businesses")
public class BusinessRestController extends ORestController<IBusinessService> {
    @Autowired
    private IBusinessService iBusinessService;


    @Override
    public IBusinessService getService() {
        return this.iBusinessService;
    }
}
