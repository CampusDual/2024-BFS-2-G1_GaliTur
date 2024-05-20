package com.campusdual.cd2024bfs2g1.ws.core.rest;

import com.campusdual.cd2024bfs2g1.api.core.service.IPackBookingService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packBookings")
public class PackBookingRestController extends ORestController<IPackBookingService> {
    @Autowired
    private IPackBookingService iPackBookingService;


    @Override
    public IPackBookingService getService() {
        return this.iPackBookingService;
    }
}
