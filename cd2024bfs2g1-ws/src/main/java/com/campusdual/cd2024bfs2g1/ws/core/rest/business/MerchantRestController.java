package com.campusdual.cd2024bfs2g1.ws.core.rest.business;


import com.campusdual.cd2024bfs2g1.api.core.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontimize.jee.server.rest.ORestController;

@RestController
@RequestMapping("/merchants")
public class MerchantRestController extends ORestController<IMerchantService> {

    @Autowired
    private IMerchantService merchantService;

    @Override
    public IMerchantService getService() {
        return this.merchantService;
    }


}