package com.campusdual.cd2024bfs2g1.ws.core.rest.pack;

import com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackRatingService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packRatings")
public class PackRatingController extends ORestController<IPackRatingService> {

    @Autowired
    private IPackRatingService packRatingService;

    @Override
    public IPackRatingService getService() {
        return packRatingService;
    }
}
