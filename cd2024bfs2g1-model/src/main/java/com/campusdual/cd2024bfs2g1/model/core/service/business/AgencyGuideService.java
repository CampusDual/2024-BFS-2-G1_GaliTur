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
        return this.daoHelper.query(this.agencyGuideDao, keysValues, attributes);
    }

    @Override
    public EntityResult agencyGuideEditQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {

        attributes.remove("comboLanguages");
        attributes.remove("comboZone");
        attributes.remove("comboCity");
        attributes.add("gui_language");
        attributes.add("gui_zone");
        attributes.add("gui_city");



        EntityResult er = this.daoHelper.query(this.agencyGuideDao, keysValues, attributes);

        ArrayList<String> languagesStr = (ArrayList<String>) er.get("gui_language");


            String[] array = languagesStr.get(0).split(", ");
            for(String s : array){
                languagesStr.add(s);
            }
            languagesStr.remove(0);


        List<Integer> listaCodes = new ArrayList<>();

        for (String language : languagesStr) {

            switch (language) {
                case "SPANISH":
                    listaCodes.add(1);
                    break;
                case "GALICIAN":
                    listaCodes.add(2);

                    break;
                case "ENGLISH":
                    listaCodes.add(3);

                    break;
                case "GERMAN":
                    listaCodes.add(4);

                    break;
                case "PORTUGUESE":
                    listaCodes.add(5);
                    break;
                case "FRENCH":
                    listaCodes.add(6);
                    break;
                case "RUSSIAN":
                    listaCodes.add(7);
                    break;
                case "ITALIAN":
                    listaCodes.add(8);
                    break;
                case "BASQUE":
                    listaCodes.add(9);
                    break;
                case "CATALAN":
                    listaCodes.add(10);
                    break;
                case "POLISH":
                    listaCodes.add(11);
                    break;
                case "UKRAINIAN":
                    listaCodes.add(12);
                    break;
                case "DUTCH":
                    listaCodes.add(13);
                    break;
                case "CHINESE":
                    listaCodes.add(14);
                    break;
                case "ARABIC":
                    listaCodes.add(15);
                    break;
            }
        }

        List<List<Integer>> listaDelistas = new ArrayList<>();
        listaDelistas.add(listaCodes);

        er.put("comboLanguages",listaDelistas);






        return er;


    }


    @Override
    public EntityResult agencyGuideInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.agencyGuideDao, keysValues);
    }

    @Override
    public EntityResult agencyGuideUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.agencyGuideDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult agencyGuideDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.agencyGuideDao, keysValues);
    }
}
