package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IPackBookingService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.PackBookingDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.pack.PackDateDao;
import com.campusdual.cd2024bfs2g1.model.core.service.pack.PackDateService;
import com.campusdual.cd2024bfs2g1.model.core.service.pack.PackService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackBookingService")
public class PackBookingService implements IPackBookingService {

    private DefaultOntimizeDaoHelper daoHelper;

    private PackBookingDao packBookingDao;

    private ClientService clientService;

    private PackService packService;
    private PackDateService packDateService;


    @Autowired
    public PackBookingService(DefaultOntimizeDaoHelper daoHelper, PackBookingDao packBookingDao, ClientService clientService, PackService packService, PackDateService packDateService) {
        this.daoHelper = daoHelper;
        this.packBookingDao = packBookingDao;
        this.clientService = clientService;
        this.packService = packService;
        this.packDateService = packDateService;
    }

    @Override
    public EntityResult packBookingQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packBookingDao,keysValues, attributes);
    }

    @Override
    public EntityResult  packBookingInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

            //Create filter
            Map <String, Object> filtro = new HashMap<>();
            filtro.put(PackDao.PCK_ID,keysValues.get(PackDao.PCK_ID));

            //Create Set Values
            Map <String, Object> values = new HashMap<>();
            values.put(PackDateDao.PCS_ID,2);

            //Update
            packService.packUpdate(values, filtro);


            //Get Logged Client Id
            keysValues.put(ClientDao.CLIENT_ID, clientService.getClientId());


            return this.daoHelper.insert(this.packBookingDao, keysValues);


    }

    @Override
    public EntityResult  packBookingUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packBookingDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult  packBookingDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        EntityResult packDate =  this.packBookingQuery(keysValues, List.of("pd_id"));
        this.daoHelper.delete(this.packBookingDao, keysValues);

        Map<String, Object> mapaPackDelete = new HashMap<>();
        mapaPackDelete.put("pcs_id", 1);

        return this.packDateService.packDateUpdate( mapaPackDelete, packDate.getRecordValues(0));

    }

}
