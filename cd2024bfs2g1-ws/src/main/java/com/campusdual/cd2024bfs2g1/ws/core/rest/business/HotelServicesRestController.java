package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelRoomsService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelServicesService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotelServices")
public class HotelServicesRestController extends ORestController<IHotelServicesService> {
    @Autowired
    private IHotelServicesService iHotelServicesService;


    @Override
    public IHotelServicesService getService() {
        return this.iHotelServicesService;
    }
}
