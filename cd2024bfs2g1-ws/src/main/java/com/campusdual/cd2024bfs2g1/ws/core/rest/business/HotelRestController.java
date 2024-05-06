package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelRestController extends ORestController<IHotelService> {
    @Autowired
    private IHotelService iHotelService;


    @Override
    public IHotelService getService() {
        return this.iHotelService;
    }
}
