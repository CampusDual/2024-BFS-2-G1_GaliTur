package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IAgencyGuideService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.AgencyGuideDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.BusinessDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("AgencyGuideService")
public class AgencyGuideService implements IAgencyGuideService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private AgencyGuideDao agencyGuideDao;

    @Autowired
    private BusinessDao businessDao;

    @Override
    public EntityResult agencyGuideQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.agencyGuideDao,keysValues, attributes);
    }

    @Override
    public EntityResult agencyGuideInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        Map<String,Object> agencyMap = new HashMap<>(keysValues);
        agencyMap.remove("merchant_id");
        agencyMap.remove("bsn_name");
        agencyMap.remove("bsn_type");
        agencyMap.remove("bsn_description");
        agencyMap.remove("bsn_cif");
        agencyMap.remove("bsn_address");
        agencyMap.remove("bsn_phone");
        agencyMap.remove("bsn_email");
        agencyMap.remove("bsn_photos");
        agencyMap.remove("bsn_website");
        agencyMap.remove("bsn_schedule");

        EntityResult er = this.daoHelper.insert(this.businessDao, keysValues);

        agencyMap.put("bsn_id",(int) er.get("bsn_id"));


        return this.daoHelper.insert(this.agencyGuideDao, agencyMap);
    }

    @Override
    public EntityResult agencyGuideUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        Map<String,Object> agencyMap = new HashMap<>(attributesValues);
        agencyMap.remove("merchant_id");
        agencyMap.remove("bsn_name");
        agencyMap.remove("bsn_type");
        agencyMap.remove("bsn_description");
        agencyMap.remove("bsn_cif");
        agencyMap.remove("bsn_address");
        agencyMap.remove("bsn_phone");
        agencyMap.remove("bsn_email");
        agencyMap.remove("bsn_photos");
        agencyMap.remove("bsn_website");
        agencyMap.remove("bsn_schedule");

        List <String> qKeys = new ArrayList<String>();
        qKeys.add("B.bsn_id");
        EntityResult erAgencyGuide = this.daoHelper.query(this.agencyGuideDao,keysValues, qKeys);
        Map<String,Object> mapBsn = new HashMap<>();

        ArrayList<Integer> al = (ArrayList<Integer>) erAgencyGuide.get("bsn_id");
        int id = al.get(0);

        mapBsn.put("bsn_id", id);

        this.daoHelper.update(this.businessDao, attributesValues, mapBsn);

        return this.daoHelper.update(this.agencyGuideDao, agencyMap, keysValues);
    }

    @Override
    public EntityResult agencyGuideDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        List <String> qKeys = new ArrayList<String>();
        qKeys.add("B.bsn_id");
        EntityResult erAgencyGuide = this.daoHelper.query(this.agencyGuideDao,keysValues, qKeys);
        Map<String,Object> mapBsn = new HashMap<>();

        ArrayList<Integer> al = (ArrayList<Integer>) erAgencyGuide.get("bsn_id");
        int id = al.get(0);

        mapBsn.put("bsn_id", id);

        this.daoHelper.delete(this.agencyGuideDao, keysValues);


        return this.daoHelper.delete(this.businessDao, mapBsn);
    }
}
