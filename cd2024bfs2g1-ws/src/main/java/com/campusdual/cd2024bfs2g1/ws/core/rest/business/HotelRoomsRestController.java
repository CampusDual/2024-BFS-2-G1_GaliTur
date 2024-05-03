package com.campusdual.cd2024bfs2g1.ws.core.rest.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelRoomsService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotelRooms")
public class HotelRoomsRestController extends ORestController<IHotelRoomsService> {
    @Autowired
    private IHotelRoomsService iHotelRoomsService;


    @Override
    public IHotelRoomsService getService() {
        return this.iHotelRoomsService;
    }
}
