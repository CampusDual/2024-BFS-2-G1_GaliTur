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


        List<List<Map<String,Object>>> listaDelistas = new ArrayList<>();
        List<Map<String, Object>> listaMapas = new ArrayList<>();

        for (String language : languagesStr) {
            Map <String,Object> mapa = new HashMap<>();

            switch (language) {
                case "SPANISH":
                    mapa.put("gui_l_id",1);
                    mapa.put("gui_l_name","SPANISH");
                    break;
                case "GALICIAN":
                    mapa.put("gui_l_id",2);
                    mapa.put("gui_l_name","GALICIAN");
                    break;
                case "ENGLISH":
                    mapa.put("gui_l_id",3);
                    mapa.put("gui_l_name","ENGLISH");
                    break;
                case "GERMAN":
                    mapa.put("gui_l_id",4);
                    mapa.put("gui_l_name","GERMAN");
                    break;
                case "PORTUGUESE":
                    mapa.put("gui_l_id",5);
                    mapa.put("gui_l_name","PORTUGUESE");
                    break;
                case "FRENCH":
                    mapa.put("gui_l_id",6);
                    mapa.put("gui_l_name","FRENCH");
                    break;
                case "RUSSIAN":
                    mapa.put("gui_l_id",7);
                    mapa.put("gui_l_name","RUSSIAN");
                    break;
                case "ITALIAN":
                    mapa.put("gui_l_id",8);
                    mapa.put("gui_l_name","ITALIAN");
                    break;
                case "BASQUE":
                    mapa.put("gui_l_id",9);
                    mapa.put("gui_l_name","BASQUE");
                    break;
                case "CATALAN":
                    mapa.put("gui_l_id",10);
                    mapa.put("gui_l_name","CATALAN");
                    break;
                case "POLISH":
                    mapa.put("gui_l_id",11);
                    mapa.put("gui_l_name","POLISH");
                    break;
                case "UKRAINIAN":
                    mapa.put("gui_l_id",12);
                    mapa.put("gui_l_name","UKRAINIAN");
                    break;
                case "DUTCH":
                    mapa.put("gui_l_id",13);
                    mapa.put("gui_l_name","DUTCH");
                    break;
                case "CHINESE":
                    mapa.put("gui_l_id",14);
                    mapa.put("gui_l_name","CHINESE");
                    break;
                case "ARABIC":
                    mapa.put("gui_l_id",15);
                    mapa.put("gui_l_name","ARABIC");
                    break;
            }
            listaMapas.add(mapa);
        }
        listaDelistas.add(listaMapas);

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
