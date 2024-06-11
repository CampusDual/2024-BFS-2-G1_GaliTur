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
    public EntityResult agencyGuideEditProvinceQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {

        attributes.remove("comboLanguages");
        attributes.remove("comboZone");
        attributes.remove("comboCity");
        attributes.add("gui_language");
        attributes.add("gui_zone");
        attributes.add("gui_city");

        EntityResult er = this.daoHelper.query(this.agencyGuideDao, keysValues, attributes);

        ArrayList<String> zoneStr = (ArrayList<String>) er.get("gui_zone");

        List<Integer> listaCodes = new ArrayList<>();

        for (String language : zoneStr) {

            switch (language) {
                case "CORUNA":
                    listaCodes.add(1);
                    break;
                case "LUGO":
                    listaCodes.add(2);
                    break;
                case "OURENSE":
                    listaCodes.add(3);
                    break;
                case "PONTEVEDRA":
                    listaCodes.add(4);
                    break;
            }
        }

        List<List<Integer>> listaDelistas = new ArrayList<>();
        listaDelistas.add(listaCodes);

        er.put("comboZone",listaDelistas);

        return er;
    }

    @Override
    public EntityResult agencyGuideEditCityQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {

        attributes.remove("comboLanguages");
        attributes.remove("comboZone");
        attributes.remove("comboCity");
        attributes.add("gui_language");
        attributes.add("gui_zone");
        attributes.add("gui_city");



        EntityResult er = this.daoHelper.query(this.agencyGuideDao, keysValues, attributes);

        ArrayList<String> citiesStr = (ArrayList<String>) er.get("gui_city");


        String[] array = citiesStr.get(0).split(", ");
        for(String s : array){
            citiesStr.add(s);
        }
        citiesStr.remove(0);


        List<Integer> listaCodesMunicipaly = new ArrayList<>();

        for (String language : citiesStr) {
            switch (language) {
                case "CORUNA":
                    listaCodesMunicipaly.add(1);
                    break;
                case "FERROL":
                    listaCodesMunicipaly.add(2);
                    break;
                case "SANTIAGO":
                    listaCodesMunicipaly.add(3);
                    break;
                case "LUGO":
                    listaCodesMunicipaly.add(4);
                    break;
                case "MONFORTE":
                    listaCodesMunicipaly.add(5);
                    break;
                case "VIVEIRO":
                    listaCodesMunicipaly.add(6);
                    break;
                case "OURENSE":
                    listaCodesMunicipaly.add(7);
                    break;
                case "VERIN":
                    listaCodesMunicipaly.add(8);
                    break;
                case "XINZO":
                    listaCodesMunicipaly.add(9);
                    break;
                case "PONTEVEDRA":
                    listaCodesMunicipaly.add(10);
                    break;
                case "VIGO":
                    listaCodesMunicipaly.add(11);
                    break;
                case "MARIN":
                    listaCodesMunicipaly.add(12);
                    break;
                case "ABEGONDO":
                    listaCodesMunicipaly.add(13);
                    break;
                case "AMES":
                    listaCodesMunicipaly.add(14);
                    break;
                case "ARANGA":
                    listaCodesMunicipaly.add(15);
                    break;
                case "ARES":
                    listaCodesMunicipaly.add(16);
                    break;
                case "ARTEIXO":
                    listaCodesMunicipaly.add(17);
                    break;
                case "ARZUA":
                    listaCodesMunicipaly.add(18);
                    break;
                case "A BANA":
                    listaCodesMunicipaly.add(19);
                    break;
                case "BERGONDO":
                    listaCodesMunicipaly.add(20);
                    break;
                case "BETANZOS":
                    listaCodesMunicipaly.add(21);
                    break;
                case "BOIMORTO":
                    listaCodesMunicipaly.add(22);
                    break;
                case "BOIRO":
                    listaCodesMunicipaly.add(23);
                    break;
                case "BOQUEIXON":
                    listaCodesMunicipaly.add(24);
                    break;
                case "BRION":
                    listaCodesMunicipaly.add(25);
                    break;
                case "CABANA DE BERGANTINOS":
                    listaCodesMunicipaly.add(26);
                    break;
                case "CABANAS":
                    listaCodesMunicipaly.add(27);
                    break;
                case "CAMARINAS":
                    listaCodesMunicipaly.add(28);
                    break;
                case "CAMBRE":
                    listaCodesMunicipaly.add(29);
                    break;
                case "A CAPELA":
                    listaCodesMunicipaly.add(30);
                    break;
                case "CARBALLO":
                    listaCodesMunicipaly.add(31);
                    break;
                case "CARINO":
                    listaCodesMunicipaly.add(32);
                    break;
                case "CARNOTA":
                    listaCodesMunicipaly.add(33);
                    break;
                case "CARRAL":
                    listaCodesMunicipaly.add(34);
                    break;
                case "CEDEIRA":
                    listaCodesMunicipaly.add(35);
                    break;
                case "CEE":
                    listaCodesMunicipaly.add(36);
                    break;
                case "CERCEDA":
                    listaCodesMunicipaly.add(37);
                    break;
                case "CERDIDO":
                    listaCodesMunicipaly.add(38);
                    break;
                case "COIROS":
                    listaCodesMunicipaly.add(39);
                    break;
                case "CORCUBION":
                    listaCodesMunicipaly.add(40);
                    break;
                case "CORISTANCO":
                    listaCodesMunicipaly.add(41);
                    break;
                case "CULLEREDO":
                    listaCodesMunicipaly.add(42);
                    break;
                case "CURTIS":
                    listaCodesMunicipaly.add(43);
                    break;
                case "DODRO":
                    listaCodesMunicipaly.add(44);
                    break;
                case "DUMBRIA":
                    listaCodesMunicipaly.add(45);
                    break;
                case "FENE":
                    listaCodesMunicipaly.add(46);
                    break;
                case "FISTERRA":
                    listaCodesMunicipaly.add(47);
                    break;
                case "FRADES":
                    listaCodesMunicipaly.add(48);
                    break;
                case "IRIXOA":
                    listaCodesMunicipaly.add(49);
                    break;
                case "A LARACHA":
                    listaCodesMunicipaly.add(50);
                    break;
                case "LAXE":
                    listaCodesMunicipaly.add(51);
                    break;
                case "LOUSAME":
                    listaCodesMunicipaly.add(52);
                    break;
                case "MALPICA DE BERGANTINOS":
                    listaCodesMunicipaly.add(53);
                    break;
                case "MANON":
                    listaCodesMunicipaly.add(54);
                    break;
                case "MAZARICOS":
                    listaCodesMunicipaly.add(55);
                    break;
                case "MELIDE":
                    listaCodesMunicipaly.add(56);
                    break;
                case "MESIA":
                    listaCodesMunicipaly.add(57);
                    break;
                case "MINO":
                    listaCodesMunicipaly.add(58);
                    break;
                case "MOECHE":
                    listaCodesMunicipaly.add(59);
                    break;
                case "MONFERO":
                    listaCodesMunicipaly.add(60);
                    break;
                case "MUGARDOS":
                    listaCodesMunicipaly.add(61);
                    break;
                case "MUROS":
                    listaCodesMunicipaly.add(62);
                    break;
                case "MUXIA":
                    listaCodesMunicipaly.add(63);
                    break;
                case "NARON":
                    listaCodesMunicipaly.add(64);
                    break;
                case "NEDA":
                    listaCodesMunicipaly.add(65);
                    break;
                case "NEGREIRA":
                    listaCodesMunicipaly.add(66);
                    break;
                case "NOIA":
                    listaCodesMunicipaly.add(67);
                    break;
                case "OLEIROS":
                    listaCodesMunicipaly.add(68);
                    break;
                case "ORDES":
                    listaCodesMunicipaly.add(69);
                    break;
                case "OROSO":
                    listaCodesMunicipaly.add(70);
                    break;
                case "ORTIGUEIRA":
                    listaCodesMunicipaly.add(71);
                    break;
                case "OUTES":
                    listaCodesMunicipaly.add(72);
                    break;
                case "OZA-CESURAS":
                    listaCodesMunicipaly.add(73);
                    break;
                case "PADERNE":
                    listaCodesMunicipaly.add(74);
                    break;
                case "PADRON":
                    listaCodesMunicipaly.add(75);
                    break;
                case "O PINO":
                    listaCodesMunicipaly.add(76);
                    break;
                case "A POBRA DO CARAMINAL":
                    listaCodesMunicipaly.add(77);
                    break;
                case "PONTECESO":
                    listaCodesMunicipaly.add(78);
                    break;
                case "PONTEDEUME":
                    listaCodesMunicipaly.add(79);
                    break;
                case "AS PONTES DE GARCIA RODRIGUEZ":
                    listaCodesMunicipaly.add(80);
                    break;
                case "PORTO DO SON":
                    listaCodesMunicipaly.add(81);
                    break;
                case "RIANXO":
                    listaCodesMunicipaly.add(82);
                    break;
                case "RIBEIRA":
                    listaCodesMunicipaly.add(83);
                    break;
                case "ROIS":
                    listaCodesMunicipaly.add(84);
                    break;
                case "SADA":
                    listaCodesMunicipaly.add(85);
                    break;
                case "SAN SADURNINO":
                    listaCodesMunicipaly.add(86);
                    break;
                case "SANTA COMBA":
                    listaCodesMunicipaly.add(87);
                    break;
                case "SANTISO":
                    listaCodesMunicipaly.add(88);
                    break;
                case "SOBRADO":
                    listaCodesMunicipaly.add(89);
                    break;
                case "AS SOMOZAS":
                    listaCodesMunicipaly.add(90);
                    break;
                case "TEO":
                    listaCodesMunicipaly.add(91);
                    break;
                case "TOQUES":
                    listaCodesMunicipaly.add(92);
                    break;
                case "TORDOIA":
                    listaCodesMunicipaly.add(93);
                    break;
                case "TOURO":
                    listaCodesMunicipaly.add(94);
                    break;
                case "TRAZO":
                    listaCodesMunicipaly.add(95);
                    break;
                case "VAL DO DUBRA":
                    listaCodesMunicipaly.add(96);
                    break;
                case "VALDOVINO":
                    listaCodesMunicipaly.add(97);
                    break;
                case "VEDRA":
                    listaCodesMunicipaly.add(98);
                    break;
                case "VILARMAIOR":
                    listaCodesMunicipaly.add(99);
                    break;
                case "VILASANTAR":
                    listaCodesMunicipaly.add(100);
                    break;
                case "VIMIANZO":
                    listaCodesMunicipaly.add(101);
                    break;
                case "ZAS":
                    listaCodesMunicipaly.add(102);
                    break;
                case "ABADIN":
                    listaCodesMunicipaly.add(103);
                    break;
                case "ALFOZ":
                    listaCodesMunicipaly.add(104);
                    break;
                case "ANTAS DE ULLA":
                    listaCodesMunicipaly.add(105);
                    break;
                case "BALEIRA":
                    listaCodesMunicipaly.add(106);
                    break;
                case "BARALLA":
                    listaCodesMunicipaly.add(107);
                    break;
                case "BARREIROS":
                    listaCodesMunicipaly.add(108);
                    break;
                case "BECERREA":
                    listaCodesMunicipaly.add(109);
                    break;
                case "BEGONTE":
                    listaCodesMunicipaly.add(110);
                    break;
                case "BOVEDA":
                    listaCodesMunicipaly.add(111);
                    break;
                case "BURELA":
                    listaCodesMunicipaly.add(112);
                    break;
                case "CARBALLEDO":
                    listaCodesMunicipaly.add(113);
                    break;
                case "CASTRO DE REI":
                    listaCodesMunicipaly.add(114);
                    break;
                case "CASTROVERDE":
                    listaCodesMunicipaly.add(115);
                    break;
                case "CERVANTES":
                    listaCodesMunicipaly.add(116);
                    break;
                case "CERVO":
                    listaCodesMunicipaly.add(117);
                    break;
                case "CHANTADA":
                    listaCodesMunicipaly.add(118);
                    break;
                case "O CORGO":
                    listaCodesMunicipaly.add(119);
                    break;
                case "COSPEITO":
                    listaCodesMunicipaly.add(120);
                    break;
                case "FOLGOSO DO COUREL":
                    listaCodesMunicipaly.add(121);
                    break;
                case "A FONSAGRADA":
                    listaCodesMunicipaly.add(122);
                    break;
                case "FOZ":
                    listaCodesMunicipaly.add(123);
                    break;
                case "FRIOL":
                    listaCodesMunicipaly.add(124);
                    break;
                case "GUITIRIZ":
                    listaCodesMunicipaly.add(125);
                    break;
                case "GUNTIN":
                    listaCodesMunicipaly.add(126);
                    break;
                case "O INCIO":
                    listaCodesMunicipaly.add(127);
                    break;
                case "LANCARA":
                    listaCodesMunicipaly.add(128);
                    break;
                case "LOURENZA":
                    listaCodesMunicipaly.add(129);
                    break;
                case "MEIRA":
                    listaCodesMunicipaly.add(130);
                    break;
                case "MONDONEDO":
                    listaCodesMunicipaly.add(131);
                    break;
                case "MONTERROSO":
                    listaCodesMunicipaly.add(132);
                    break;
                case "MURAS":
                    listaCodesMunicipaly.add(133);
                    break;
                case "NAVIA DE SUARNA":
                    listaCodesMunicipaly.add(134);
                    break;
                case "NEGUEIRA DE MUNIZ":
                    listaCodesMunicipaly.add(135);
                    break;
                case "AS NOGAIS":
                    listaCodesMunicipaly.add(136);
                    break;
                case "OUROL":
                    listaCodesMunicipaly.add(137);
                    break;
                case "OUTEIRO DE REI":
                    listaCodesMunicipaly.add(138);
                    break;
                case "PALAS DE REI":
                    listaCodesMunicipaly.add(139);
                    break;
                case "PANTON":
                    listaCodesMunicipaly.add(140);
                    break;
                case "PARADELA":
                    listaCodesMunicipaly.add(141);
                    break;
                case "O PARAMO":
                    listaCodesMunicipaly.add(142);
                    break;
                case "A PASTORIZA":
                    listaCodesMunicipaly.add(143);
                    break;
                case "PEDRAFITA DO CEBREIRO":
                    listaCodesMunicipaly.add(144);
                    break;
                case "POL":
                    listaCodesMunicipaly.add(145);
                    break;
                case "A POBRA DO BROLLON":
                    listaCodesMunicipaly.add(146);
                    break;
                case "A PONTENOVA":
                    listaCodesMunicipaly.add(147);
                    break;
                case "PORTOMARIN":
                    listaCodesMunicipaly.add(148);
                    break;
                case "QUIROGA":
                    listaCodesMunicipaly.add(149);
                    break;
                case "RABADE":
                    listaCodesMunicipaly.add(150);
                    break;
                case "RIBADEO":
                    listaCodesMunicipaly.add(151);
                    break;
                case "RIBAS DE SIL":
                    listaCodesMunicipaly.add(152);
                    break;
                case "RIBEIRA DE PIQUIN":
                    listaCodesMunicipaly.add(153);
                    break;
                case "RIOTORTO":
                    listaCodesMunicipaly.add(154);
                    break;
                case "SAMOS":
                    listaCodesMunicipaly.add(155);
                    break;
                case "SARRIA":
                    listaCodesMunicipaly.add(156);
                    break;
                case "O SAVINAO":
                    listaCodesMunicipaly.add(157);
                    break;
                case "SOBER":
                    listaCodesMunicipaly.add(158);
                    break;
                case "TABOADA":
                    listaCodesMunicipaly.add(159);
                    break;
                case "TRABADA":
                    listaCodesMunicipaly.add(160);
                    break;
                case "TRICASTELA":
                    listaCodesMunicipaly.add(161);
                    break;
                case "O VALADOURO":
                    listaCodesMunicipaly.add(162);
                    break;
                case "O VICEDO":
                    listaCodesMunicipaly.add(163);
                    break;
                case "VILALBA":
                    listaCodesMunicipaly.add(164);
                    break;
                case "XERMADE":
                    listaCodesMunicipaly.add(165);
                    break;
                case "XOVE":
                    listaCodesMunicipaly.add(166);
                    break;
                case "ALLARIZ":
                    listaCodesMunicipaly.add(167);
                    break;
                case "AMOEIRO":
                    listaCodesMunicipaly.add(168);
                    break;
                case "A ARNOIA":
                    listaCodesMunicipaly.add(169);
                    break;
                case "AVION":
                    listaCodesMunicipaly.add(170);
                    break;
                case "BALTAR":
                    listaCodesMunicipaly.add(171);
                    break;
                case "BANDE":
                    listaCodesMunicipaly.add(172);
                    break;
                case "BANOS DE MOLGAS":
                    listaCodesMunicipaly.add(173);
                    break;
                case "BARBADAS":
                    listaCodesMunicipaly.add(174);
                    break;
                case "O BARCO DE VALDEORRAS":
                    listaCodesMunicipaly.add(175);
                    break;
                case "BEADE":
                    listaCodesMunicipaly.add(176);
                    break;
                case "BEARIZ":
                    listaCodesMunicipaly.add(177);
                    break;
                case "OS BLANCOS":
                    listaCodesMunicipaly.add(178);
                    break;
                case "BOBORAS":
                    listaCodesMunicipaly.add(179);
                    break;
                case "A BOLA":
                    listaCodesMunicipaly.add(180);
                    break;
                case "O BOLO":
                    listaCodesMunicipaly.add(181);
                    break;
                case "CALVOS DE RANDIN":
                    listaCodesMunicipaly.add(182);
                    break;
                case "CARBALLEDA DE AVIA":
                    listaCodesMunicipaly.add(183);
                    break;
                case "CARBALLEDA DE VALDEORRAS":
                    listaCodesMunicipaly.add(184);
                    break;
                case "O CARBALLINO":
                    listaCodesMunicipaly.add(185);
                    break;
                case "CARTELLE":
                    listaCodesMunicipaly.add(186);
                    break;
                case "CASTRELO DE MINO":
                    listaCodesMunicipaly.add(187);
                    break;
                case "CASTRELO DO VAL":
                    listaCodesMunicipaly.add(188);
                    break;
                case "CASTRO CALDELAS":
                    listaCodesMunicipaly.add(189);
                    break;
                case "CELANOVA":
                    listaCodesMunicipaly.add(190);
                    break;
                case "CENLLE":
                    listaCodesMunicipaly.add(191);
                    break;
                case "CHANDREXA DE QUEIXA":
                    listaCodesMunicipaly.add(192);
                    break;
                case "COLES":
                    listaCodesMunicipaly.add(193);
                    break;
                case "CORTEGADA":
                    listaCodesMunicipaly.add(194);
                    break;
                case "CUALEDRO":
                    listaCodesMunicipaly.add(195);
                    break;
                case "ENTRIMO":
                    listaCodesMunicipaly.add(196);
                    break;
                case "ESGOS":
                    listaCodesMunicipaly.add(197);
                    break;
                case "GOMESENDE":
                    listaCodesMunicipaly.add(198);
                    break;
                case "A GUDINA":
                    listaCodesMunicipaly.add(199);
                    break;
                case "O IRIXO":
                    listaCodesMunicipaly.add(200);
                    break;
                case "LAROUCO":
                    listaCodesMunicipaly.add(201);
                    break;
                case "LAZA":
                    listaCodesMunicipaly.add(202);
                    break;
                case "LEIRO":
                    listaCodesMunicipaly.add(203);
                    break;
                case "LOBEIRA":
                    listaCodesMunicipaly.add(204);
                    break;
                case "LOBIOS":
                    listaCodesMunicipaly.add(205);
                    break;
                case "MACEDA":
                    listaCodesMunicipaly.add(206);
                    break;
                case "MANZANEDA":
                    listaCodesMunicipaly.add(207);
                    break;
                case "MASIDE":
                    listaCodesMunicipaly.add(208);
                    break;
                case "MELON":
                    listaCodesMunicipaly.add(209);
                    break;
                case "A MERCA":
                    listaCodesMunicipaly.add(210);
                    break;
                case "A MEZQUITA":
                    listaCodesMunicipaly.add(211);
                    break;
                case "MONTEDERRAMO":
                    listaCodesMunicipaly.add(212);
                    break;
                case "MONTERREI":
                    listaCodesMunicipaly.add(213);
                    break;
                case "MUINOS":
                    listaCodesMunicipaly.add(214);
                    break;
                case "NOGUEIRA DE RAMUIN":
                    listaCodesMunicipaly.add(215);
                    break;
                case "OIMBRA":
                    listaCodesMunicipaly.add(216);
                    break;
                case "PADERNE DE ALLARIZ":
                    listaCodesMunicipaly.add(217);
                    break;
                case "PADRENDA":
                    listaCodesMunicipaly.add(218);
                    break;
                case "PARADA DE SIL":
                    listaCodesMunicipaly.add(219);
                    break;
                case "O PEREIRO DE AGUIAR":
                    listaCodesMunicipaly.add(220);
                    break;
                case "A PEROXA":
                    listaCodesMunicipaly.add(221);
                    break;
                case "PETIN":
                    listaCodesMunicipaly.add(222);
                    break;
                case "PINOR":
                    listaCodesMunicipaly.add(223);
                    break;
                case "A POBRA DE TRIVES":
                    listaCodesMunicipaly.add(224);
                    break;
                case "PONTEDEVA":
                    listaCodesMunicipaly.add(225);
                    break;
                case "PORQUEIRA":
                    listaCodesMunicipaly.add(226);
                    break;
                case "PUNXIN":
                    listaCodesMunicipaly.add(227);
                    break;
                case "QUINTELA DE LEIRADO":
                    listaCodesMunicipaly.add(228);
                    break;
                case "RAIRIZ DE VEIGA":
                    listaCodesMunicipaly.add(229);
                    break;
                case "RAMIRAS":
                    listaCodesMunicipaly.add(230);
                    break;
                case "RIBADAVIA":
                    listaCodesMunicipaly.add(231);
                    break;
                case "RIOS":
                    listaCodesMunicipaly.add(232);
                    break;
                case "A RUA":
                    listaCodesMunicipaly.add(233);
                    break;
                case "RUBIA":
                    listaCodesMunicipaly.add(234);
                    break;
                case "SAN AMARO":
                    listaCodesMunicipaly.add(235);
                    break;
                case "SAN CIBRAO DAS VINAS":
                    listaCodesMunicipaly.add(236);
                    break;
                case "SAN CRISTOVO DE CEA":
                    listaCodesMunicipaly.add(237);
                    break;
                case "SAN XOAN DE RIO":
                    listaCodesMunicipaly.add(238);
                    break;
                case "SANDIAS":
                    listaCodesMunicipaly.add(239);
                    break;
                case "SARREAUS":
                    listaCodesMunicipaly.add(240);
                    break;
                case "TABOADELA":
                    listaCodesMunicipaly.add(241);
                    break;
                case "A TEIXEIRA":
                    listaCodesMunicipaly.add(242);
                    break;
                case "TOEN":
                    listaCodesMunicipaly.add(243);
                    break;
                case "TRASMIRAS":
                    listaCodesMunicipaly.add(244);
                    break;
                case "A VEIGA":
                    listaCodesMunicipaly.add(245);
                    break;
                case "VEREA":
                    listaCodesMunicipaly.add(246);
                    break;
                case "VIANA DO BOLO":
                    listaCodesMunicipaly.add(247);
                    break;
                case "VILAMARIN":
                    listaCodesMunicipaly.add(248);
                    break;
                case "VILAMARTIN DE VALDEORRAS":
                    listaCodesMunicipaly.add(249);
                    break;
                case "VILAR DE BARRIO":
                    listaCodesMunicipaly.add(250);
                    break;
                case "VILAR DE SANTOS":
                    listaCodesMunicipaly.add(251);
                    break;
                case "VILARDEVOS":
                    listaCodesMunicipaly.add(252);
                    break;
                case "VILARINO DE CONSO":
                    listaCodesMunicipaly.add(253);
                    break;
                case "XUNQUEIRA DE AMBIA":
                    listaCodesMunicipaly.add(254);
                    break;
                case "XUNQUEIRA DE ESPADANEDO":
                    listaCodesMunicipaly.add(255);
                    break;
                case "AGOLADA":
                    listaCodesMunicipaly.add(256);
                    break;
                case "ARBO":
                    listaCodesMunicipaly.add(257);
                    break;
                case "BAIONA":
                    listaCodesMunicipaly.add(258);
                    break;
                case "BARRO":
                    listaCodesMunicipaly.add(259);
                    break;
                case "BUEU":
                    listaCodesMunicipaly.add(260);
                    break;
                case "CALDAS DE REIS":
                    listaCodesMunicipaly.add(261);
                    break;
                case "CAMBADOS":
                    listaCodesMunicipaly.add(262);
                    break;
                case "CAMPO LAMEIRO":
                    listaCodesMunicipaly.add(263);
                    break;
                case "CANGAS":
                    listaCodesMunicipaly.add(264);
                    break;
                case "A CANIZA":
                    listaCodesMunicipaly.add(265);
                    break;
                case "CATOIRA":
                    listaCodesMunicipaly.add(266);
                    break;
                case "CERDEDO-COTOBADE":
                    listaCodesMunicipaly.add(267);
                    break;
                case "COVELO":
                    listaCodesMunicipaly.add(268);
                    break;
                case "CRECENTE":
                    listaCodesMunicipaly.add(269);
                    break;
                case "CUNTIS":
                    listaCodesMunicipaly.add(270);
                    break;
                case "DOZON":
                    listaCodesMunicipaly.add(271);
                    break;
                case "A ESTRADA":
                    listaCodesMunicipaly.add(272);
                    break;
                case "FORCAREI":
                    listaCodesMunicipaly.add(273);
                    break;
                case "FORNELOS DE MONTES":
                    listaCodesMunicipaly.add(274);
                    break;
                case "GONDOMAR":
                    listaCodesMunicipaly.add(275);
                    break;
                case "O GROVE":
                    listaCodesMunicipaly.add(276);
                    break;
                case "A GUARDA":
                    listaCodesMunicipaly.add(277);
                    break;
                case "A ILLA DE AROUSA":
                    listaCodesMunicipaly.add(278);
                    break;
                case "LALIN":
                    listaCodesMunicipaly.add(279);
                    break;
                case "A LAMA":
                    listaCodesMunicipaly.add(280);
                    break;
                case "MEANO":
                    listaCodesMunicipaly.add(281);
                    break;
                case "MEIS":
                    listaCodesMunicipaly.add(282);
                    break;
                case "MOANA":
                    listaCodesMunicipaly.add(283);
                    break;
                case "MONDARIZ":
                    listaCodesMunicipaly.add(284);
                    break;
                case "MONDARIZ-BALNEARIO":
                    listaCodesMunicipaly.add(285);
                    break;
                case "MORANA":
                    listaCodesMunicipaly.add(286);
                    break;
                case "MOS":
                    listaCodesMunicipaly.add(287);
                    break;
                case "AS NEVES":
                    listaCodesMunicipaly.add(288);
                    break;
                case "NIGRAN":
                    listaCodesMunicipaly.add(289);
                    break;
                case "OIA":
                    listaCodesMunicipaly.add(290);
                    break;
                case "PAZOS DE BORBEN":
                    listaCodesMunicipaly.add(291);
                    break;
                case "POIO":
                    listaCodesMunicipaly.add(292);
                    break;
                case "PONTE CALDELAS":
                    listaCodesMunicipaly.add(293);
                    break;
                case "PONTEAREAS":
                    listaCodesMunicipaly.add(294);
                    break;
                case "PONTECESURES":
                    listaCodesMunicipaly.add(295);
                    break;
                case "O PORRINO":
                    listaCodesMunicipaly.add(296);
                    break;
                case "PORTAS":
                    listaCodesMunicipaly.add(297);
                    break;
                case "REDONDELA":
                    listaCodesMunicipaly.add(298);
                    break;
                case "RIBADUMIA":
                    listaCodesMunicipaly.add(299);
                    break;
                case "RODEIRO":
                    listaCodesMunicipaly.add(300);
                    break;
                case "O ROSAL":
                    listaCodesMunicipaly.add(301);
                    break;
                case "SALCEDA DE CASELAS":
                    listaCodesMunicipaly.add(302);
                    break;
                case "SALVATERRA DE MINO":
                    listaCodesMunicipaly.add(303);
                    break;
                case "SANXENXO":
                    listaCodesMunicipaly.add(304);
                    break;
                case "SILLEDA":
                    listaCodesMunicipaly.add(305);
                    break;
                case "SOUTOMAIOR":
                    listaCodesMunicipaly.add(306);
                    break;
                case "TOMINO":
                    listaCodesMunicipaly.add(307);
                    break;
                case "TUI":
                    listaCodesMunicipaly.add(308);
                    break;
                case "VALGA":
                    listaCodesMunicipaly.add(309);
                    break;
                case "VILA DE CRUCES":
                    listaCodesMunicipaly.add(310);
                    break;
                case "VILABOA":
                    listaCodesMunicipaly.add(311);
                    break;
                case "VILAGARCIA DE AROUSA":
                    listaCodesMunicipaly.add(312);
                    break;
                case "VILANOVA DE AROUSA":
                    listaCodesMunicipaly.add(313);
                    break;
            }
        }

        List<List<Integer>> listaDelistas = new ArrayList<>();
        listaDelistas.add(listaCodesMunicipaly);

        er.put("comboCity",listaDelistas);






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
    public EntityResult agencyGuideEditUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        return this.daoHelper.update(this.agencyGuideDao, BusinessService.guideAgencyDataProcessor(attributesValues), keysValues);
    }

    @Override
    public EntityResult agencyGuideDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.agencyGuideDao, keysValues);
    }
}
