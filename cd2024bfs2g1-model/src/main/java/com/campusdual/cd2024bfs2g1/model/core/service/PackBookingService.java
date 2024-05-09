package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IPackBookingService;
import com.campusdual.cd2024bfs2g1.api.core.service.business.IGuideZoneService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.MerchantDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.PackBookingDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.GuideZoneDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("PackBookingService")
public class PackBookingService implements IPackBookingService {

    private DefaultOntimizeDaoHelper daoHelper;

    private PackBookingDao packBookingDao;

    private ClientService clientService;

    @Autowired
    public PackBookingService(DefaultOntimizeDaoHelper daoHelper, PackBookingDao packBookingDao, ClientService clientService) {
        this.daoHelper = daoHelper;
        this.packBookingDao = packBookingDao;
        this.clientService = clientService;
    }

    @Override
    public EntityResult packBookingQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.packBookingDao,keysValues, attributes);
    }

    @Override
    public EntityResult  packBookingInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        keysValues.put(ClientDao.CLIENT_ID, getClientId());


        return this.daoHelper.insert(this.packBookingDao, keysValues);
    }

    @Override
    public EntityResult  packBookingUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.packBookingDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult  packBookingDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.packBookingDao, keysValues);
    }

    private int getClientId() {
        Object client = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int userId = (int) ((UserInformation) client).getOtherData().get(UserDao.USR_ID);


        List<String> qKeys = new ArrayList<String>();
        qKeys.add(ClientDao.CLIENT_ID);

        Map<String, Object> emptyMap = new HashMap<>();
        emptyMap.put("CL." + ClientDao.USR_ID, userId);


        EntityResult clientEr = clientService.clientQuery(emptyMap, qKeys);
        ArrayList<Integer> al = (ArrayList<Integer>) clientEr.get(ClientDao.CLIENT_ID);
        return al.get(0);
    }
}
