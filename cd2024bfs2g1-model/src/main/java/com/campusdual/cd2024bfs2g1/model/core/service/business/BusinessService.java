package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IBusinessService;
import com.campusdual.cd2024bfs2g1.model.core.dao.MerchantDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.*;
import com.campusdual.cd2024bfs2g1.model.core.service.MerchantService;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("BusinessService")
public class BusinessService implements IBusinessService {

    private final DefaultOntimizeDaoHelper daoHelper;
    private final BusinessDao businessDao;
    private final AgencyGuideService agencyGuideService;
    private final HotelService hotelService;
    private final HotelRoomsService hotelRoomsService;
    private final HotelServicesService hotelServicesService;
    private final RestaurantService restaurantService;
    private final MerchantService merchantService;

    @Autowired
    public BusinessService(DefaultOntimizeDaoHelper daoHelper, BusinessDao businessDao, AgencyGuideService agencyGuideService, HotelService hotelService, HotelRoomsService hotelRoomsService, HotelServicesService hotelServicesService, RestaurantService restaurantService, MerchantService merchantService) {
        this.daoHelper = daoHelper;
        this.businessDao = businessDao;
        this.agencyGuideService = agencyGuideService;
        this.hotelService = hotelService;
        this.hotelRoomsService = hotelRoomsService;
        this.hotelServicesService = hotelServicesService;
        this.restaurantService = restaurantService;
        this.merchantService = merchantService;
    }

    @Override
    public EntityResult businessQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {




        return this.daoHelper.query(this.businessDao, keysValues, attributes);
    }



    @Override
    public EntityResult businessRestaurantQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        attributes.remove("rest_menu");
        attributes.remove("gui_language");
        attributes.remove("gui_zone");
        attributes.remove("gui_city");
        EntityResult er = this.daoHelper.query(this.businessDao, keysValues, attributes);
        ArrayList <String> business = (ArrayList<String>) er.get(BusinessDao.TYPE);
        String businessType = business.get(0);
        if(businessType.equals("Restaurant")){
            attributes.add("rest_menu");
            return this.daoHelper.query(this.businessDao, keysValues, attributes, "restaurantBusinesses");

        }else if(businessType.equals("Lodging")){
            return this.daoHelper.query(this.businessDao, keysValues, attributes, "lodgingBusinesses");
        }else if(businessType.equals("AgencyGuide")){
            attributes.add("gui_language");
            attributes.add("gui_zone");
            attributes.add("gui_city");
            return this.daoHelper.query(this.businessDao, keysValues, attributes, "agencyBusinesses");

        }

        return null;
    }

    @Override
    public EntityResult businessRestaurantUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.businessDao, attributesValues, keysValues);
    }


    @Override
    public AdvancedEntityResult businessDownDatePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy) {
        return this.daoHelper.paginationQuery(this.businessDao, keysValues, attributes, recordNumber, startIndex, orderBy, "downDate");
    }

    @Override
    public EntityResult businessRestaurantInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.businessDao, keysValues);
    }

    @Override
    public EntityResult businessMerchantQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        keysValues.put(MerchantDao.MERCHANT_ID, merchantService.getMerchantId());



        return this.daoHelper.query(this.businessDao, keysValues, attributes);
    }



    @Override
    public EntityResult businessInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {


        keysValues.put(MerchantDao.MERCHANT_ID, merchantService.getMerchantId());

        Map<String, Object> dataMap = new HashMap<>(keysValues);

        String businessType = getBusinessType(keysValues);
        keysValues.replace(BusinessDao.TYPE, businessType);

        keysValues = setWebsitePlaceholder(keysValues);

        EntityResult er = this.daoHelper.insert(this.businessDao, keysValues);

        int id = (int) er.get(BusinessDao.BSN_ID);

        dataMap.put(BusinessDao.BSN_ID, id);


        if (dataMap.containsKey("comboZone")) {

            return agencyGuideService.agencyGuideInsert(guideAgencyDataProcessor(dataMap));

        } else if (dataMap.containsKey(RestaurantDao.MENU)) {

            restaurantService.restaurantInsert(dataMap);

        } else if (dataMap.containsKey("toggleWifi")) {


            EntityResult hotelEr = hotelService.hotelInsert(dataMap);

            int idHotel = (int) hotelEr.get(HotelDao.HTL_ID);

            dataMap.put(HotelDao.HTL_ID, idHotel);


            hotelServicesRoomsProcessor(dataMap);


        }


        return er;


    }

    /**
     * Process Services and Room hotel data
     *
     * @param dataMap Map with values
     */
    private void hotelServicesRoomsProcessor(Map<String, Object> dataMap) {
        if ((Boolean) dataMap.get("toggleWifi")) {
            insertHotelAttributes(dataMap, "Wifi");
        }

        if ((Boolean) dataMap.get("toggleParking")) {
            insertHotelAttributes(dataMap, "Parking");
        }

        if ((Boolean) dataMap.get("togglePool")) {
            insertHotelAttributes(dataMap, "Pool");
        }

        if ((Boolean) dataMap.get("toggleBreakfast")) {
            insertHotelAttributes(dataMap, "Breakfast");
        }

        if ((Boolean) dataMap.get("toggleLunch")) {
            insertHotelAttributes(dataMap, "Lunch");
        }

        if ((Boolean) dataMap.get("toggleDinner")) {
            insertHotelAttributes(dataMap, "Dinner");
        }

        if ((Boolean) dataMap.get("roomTypeSingle")) {
            insertHotelRooms(dataMap, "Single", "priceSingleRoom");
        }

        if ((Boolean) dataMap.get("roomTypeDouble")) {
            insertHotelRooms(dataMap, "Double", "priceDoubleRoom");
        }

        if ((Boolean) dataMap.get("roomTypeTriple")) {
            insertHotelRooms(dataMap, "Triple", "priceTripleRoom");
        }
    }

    private void insertHotelRooms(Map<String, Object> dataMap, String roomType, String roomPrice) {
        dataMap.put(HotelRoomsDao.TYPE, roomType);
        dataMap.put(HotelRoomsDao.COST, dataMap.get(roomPrice));
        hotelRoomsService.hotelRoomsInsert(dataMap);
    }

    private void insertHotelAttributes(Map<String, Object> dataMap, String serviceType) {
        dataMap.put(HotelServicesDao.TYPE, serviceType);
        dataMap.put(HotelServicesDao.COST, 0.0);
        hotelServicesService.hotelServicesInsert(dataMap);
    }

    public static Map guideAgencyDataProcessor(Map<String, Object> dataMap) {
        switch ((int) dataMap.get("comboZone")) {
            case 1:
                dataMap.remove("comboZone");
                dataMap.put(AgencyGuideDao.ZONE, "CORUNA");
                break;
            case 2:
                dataMap.remove("comboZone");
                dataMap.put(AgencyGuideDao.ZONE, "LUGO");
                break;
            case 3:
                dataMap.remove("comboZone");
                dataMap.put(AgencyGuideDao.ZONE, "OURENSE");
                break;
            case 4:
                dataMap.remove("comboZone");
                dataMap.put(AgencyGuideDao.ZONE, "PONTEVEDRA");
                break;
        }

        ArrayList<Integer> cityValues = (ArrayList<Integer>) dataMap.get("comboCity");
        String concatCities = "";
        String cityName = "";

        for (Integer num : cityValues) {
            switch (num) {
                case 1:
                    cityName = "CORUNA";
                    break;
                case 2:
                    cityName = "FERROL";
                    break;
                case 3:
                    cityName = "SANTIAGO";
                    break;
                case 4:
                    cityName = "LUGO";
                    break;
                case 5:
                    cityName = "MONFORTE";
                    break;
                case 6:
                    cityName = "VIVEIRO";
                    break;
                case 7:
                    cityName = "OURENSE";
                    break;
                case 8:
                    cityName = "VERIN";
                    break;
                case 9:
                    cityName = "XINZO";
                    break;
                case 10:
                    cityName = "PONTEVEDRA";
                    break;
                case 11:
                    cityName = "VIGO";
                    break;
                case 12:
                    cityName = "MARIN";
                    break;
                case 13:
                    cityName = "ABEGONDO";
                    break;
                case 14:
                    cityName = "AMES";
                    break;
                case 15:
                    cityName = "ARANGA";
                    break;
                case 16:
                    cityName = "ARES";
                    break;
                case 17:
                    cityName = "ARTEIXO";
                    break;
                case 18:
                    cityName = "ARZUA";
                    break;
                case 19:
                    cityName = "A BANA";
                    break;
                case 20:
                    cityName = "BERGONDO";
                    break;
                case 21:
                    cityName = "BETANZOS";
                    break;
                case 22:
                    cityName = "BOIMORTO";
                    break;
                case 23:
                    cityName = "BOIRO";
                    break;
                case 24:
                    cityName = "BOQUEIXON";
                    break;
                case 25:
                    cityName = "BRION";
                    break;
                case 26:
                    cityName = "CABANA DE BERGANTINOS";
                    break;
                case 27:
                    cityName = "CABANAS";
                    break;
                case 28:
                    cityName = "CAMARINAS";
                    break;
                case 29:
                    cityName = "CAMBRE";
                    break;
                case 30:
                    cityName = "A CAPELA";
                    break;
                case 31:
                    cityName = "CARBALLO";
                    break;
                case 32:
                    cityName = "CARINO";
                    break;
                case 33:
                    cityName = "CARNOTA";
                    break;
                case 34:
                    cityName = "CARRAL";
                    break;
                case 35:
                    cityName = "CEDEIRA";
                    break;
                case 36:
                    cityName = "CEE";
                    break;
                case 37:
                    cityName = "CERCEDA";
                    break;
                case 38:
                    cityName = "CERDIDO";
                    break;
                case 39:
                    cityName = "COIROS";
                    break;
                case 40:
                    cityName = "CORCUBION";
                    break;
                case 41:
                    cityName = "CORISTANCO";
                    break;
                case 42:
                    cityName = "CULLEREDO";
                    break;
                case 43:
                    cityName = "CURTIS";
                    break;
                case 44:
                    cityName = "DODRO";
                    break;
                case 45:
                    cityName = "DUMBRIA";
                    break;
                case 46:
                    cityName = "FENE";
                    break;
                case 47:
                    cityName = "FISTERRA";
                    break;
                case 48:
                    cityName = "FRADES";
                    break;
                case 49:
                    cityName = "IRIXOA";
                    break;
                case 50:
                    cityName = "A LARACHA";
                    break;
                case 51:
                    cityName = "LAXE";
                    break;
                case 52:
                    cityName = "LOUSAME";
                    break;
                case 53:
                    cityName = "MALPICA DE BERGANTINOS";
                    break;
                case 54:
                    cityName = "MANON";
                    break;
                case 55:
                    cityName = "MAZARICOS";
                    break;
                case 56:
                    cityName = "MELIDE";
                    break;
                case 57:
                    cityName = "MESIA";
                    break;
                case 58:
                    cityName = "MINO";
                    break;
                case 59:
                    cityName = "MOECHE";
                    break;
                case 60:
                    cityName = "MONFERO";
                    break;
                case 61:
                    cityName = "MUGARDOS";
                    break;
                case 62:
                    cityName = "MUROS";
                    break;
                case 63:
                    cityName = "MUXIA";
                    break;
                case 64:
                    cityName = "NARON";
                    break;
                case 65:
                    cityName = "NEDA";
                    break;
                case 66:
                    cityName = "NEGREIRA";
                    break;
                case 67:
                    cityName = "NOIA";
                    break;
                case 68:
                    cityName = "OLEIROS";
                    break;
                case 69:
                    cityName = "ORDES";
                    break;
                case 70:
                    cityName = "OROSO";
                    break;
                case 71:
                    cityName = "ORTIGUEIRA";
                    break;
                case 72:
                    cityName = "OUTES";
                    break;
                case 73:
                    cityName = "OZA-CESURAS";
                    break;
                case 74:
                    cityName = "PADERNE";
                    break;
                case 75:
                    cityName = "PADRON";
                    break;
                case 76:
                    cityName = "O PINO";
                    break;
                case 77:
                    cityName = "A POBRA DO CARAMINAL";
                    break;
                case 78:
                    cityName = "PONTECESO";
                    break;
                case 79:
                    cityName = "PONTEDEUME";
                    break;
                case 80:
                    cityName = "AS PONTES DE GARCIA RODRIGUEZ";
                    break;
                case 81:
                    cityName = "PORTO DO SON";
                    break;
                case 82:
                    cityName = "RIANXO";
                    break;
                case 83:
                    cityName = "RIBEIRA";
                    break;
                case 84:
                    cityName = "ROIS";
                    break;
                case 85:
                    cityName = "SADA";
                    break;
                case 86:
                    cityName = "SAN SADURNINO";
                    break;
                case 87:
                    cityName = "SANTA COMBA";
                    break;
                case 88:
                    cityName = "SANTISO";
                    break;
                case 89:
                    cityName = "SOBRADO";
                    break;
                case 90:
                    cityName = "AS SOMOZAS";
                    break;
                case 91:
                    cityName = "TEO";
                    break;
                case 92:
                    cityName = "TOQUES";
                    break;
                case 93:
                    cityName = "TORDOIA";
                    break;
                case 94:
                    cityName = "TOURO";
                    break;
                case 95:
                    cityName = "TRAZO";
                    break;
                case 96:
                    cityName = "VAL DO DUBRA";
                    break;
                case 97:
                    cityName = "VALDOVINO";
                    break;
                case 98:
                    cityName = "VEDRA";
                    break;
                case 99:
                    cityName = "VILARMAIOR";
                    break;
                case 100:
                    cityName = "VILASANTAR";
                    break;
                case 101:
                    cityName = "VIMIANZO";
                    break;
                case 102:
                    cityName = "ZAS";
                    break;
                case 103:
                    cityName = "ABADIN";
                    break;
                case 104:
                    cityName = "ALFOZ";
                    break;
                case 105:
                    cityName = "ANTAS DE ULLA";
                    break;
                case 106:
                    cityName = "BALEIRA";
                    break;
                case 107:
                    cityName = "BARALLA";
                    break;
                case 108:
                    cityName = "BARREIROS";
                    break;
                case 109:
                    cityName = "BECERREA";
                    break;
                case 110:
                    cityName = "BEGONTE";
                    break;
                case 111:
                    cityName = "BOVEDA";
                    break;
                case 112:
                    cityName = "BURELA";
                    break;
                case 113:
                    cityName = "CARBALLEDO";
                    break;
                case 114:
                    cityName = "CASTRO DE REI";
                    break;
                case 115:
                    cityName = "CASTROVERDE";
                    break;
                case 116:
                    cityName = "CERVANTES";
                    break;
                case 117:
                    cityName = "CERVO";
                    break;
                case 118:
                    cityName = "CHANTADA";
                    break;
                case 119:
                    cityName = "O CORGO";
                    break;
                case 120:
                    cityName = "COSPEITO";
                    break;
                case 121:
                    cityName = "FOLGOSO DO COUREL";
                    break;
                case 122:
                    cityName = "A FONSAGRADA";
                    break;
                case 123:
                    cityName = "FOZ";
                    break;
                case 124:
                    cityName = "FRIOL";
                    break;
                case 125:
                    cityName = "GUITIRIZ";
                    break;
                case 126:
                    cityName = "GUNTIN";
                    break;
                case 127:
                    cityName = "O INCIO";
                    break;
                case 128:
                    cityName = "LANCARA";
                    break;
                case 129:
                    cityName = "LOURENZA";
                    break;
                case 130:
                    cityName = "MEIRA";
                    break;
                case 131:
                    cityName = "MONDONEDO";
                    break;
                case 132:
                    cityName = "MONTERROSO";
                    break;
                case 133:
                    cityName = "MURAS";
                    break;
                case 134:
                    cityName = "NAVIA DE SUARNA";
                    break;
                case 135:
                    cityName = "NEGUEIRA DE MUNIZ";
                    break;
                case 136:
                    cityName = "AS NOGAIS";
                    break;
                case 137:
                    cityName = "OUROL";
                    break;
                case 138:
                    cityName = "OUTEIRO DE REI";
                    break;
                case 139:
                    cityName = "PALAS DE REI";
                    break;
                case 140:
                    cityName = "PANTON";
                    break;
                case 141:
                    cityName = "PARADELA";
                    break;
                case 142:
                    cityName = "O PARAMO";
                    break;
                case 143:
                    cityName = "A PASTORIZA";
                    break;
                case 144:
                    cityName = "PEDRAFITA DO CEBREIRO";
                    break;
                case 145:
                    cityName = "POL";
                    break;
                case 146:
                    cityName = "A POBRA DO BROLLON";
                    break;
                case 147:
                    cityName = "A PONTENOVA";
                    break;
                case 148:
                    cityName = "PORTOMARIN";
                    break;
                case 149:
                    cityName = "QUIROGA";
                    break;
                case 150:
                    cityName = "RABADE";
                    break;
                case 151:
                    cityName = "RIBADEO";
                    break;
                case 152:
                    cityName = "RIBAS DE SIL";
                    break;
                case 153:
                    cityName = "RIBEIRA DE PIQUIN";
                    break;
                case 154:
                    cityName = "RIOTORTO";
                    break;
                case 155:
                    cityName = "SAMOS";
                    break;
                case 156:
                    cityName = "SARRIA";
                    break;
                case 157:
                    cityName = "O SAVINAO";
                    break;
                case 158:
                    cityName = "SOBER";
                    break;
                case 159:
                    cityName = "TABOADA";
                    break;
                case 160:
                    cityName = "TRABADA";
                    break;
                case 161:
                    cityName = "TRICASTELA";
                    break;
                case 162:
                    cityName = "O VALADOURO";
                    break;
                case 163:
                    cityName = "O VICEDO";
                    break;
                case 164:
                    cityName = "VILALBA";
                    break;
                case 165:
                    cityName = "XERMADE";
                    break;
                case 166:
                    cityName = "XOVE";
                    break;
                case 167:
                    cityName = "ALLARIZ";
                    break;
                case 168:
                    cityName = "AMOEIRO";
                    break;
                case 169:
                    cityName = "A ARNOIA";
                    break;
                case 170:
                    cityName = "AVION";
                    break;
                case 171:
                    cityName = "BALTAR";
                    break;
                case 172:
                    cityName = "BANDE";
                    break;
                case 173:
                    cityName = "BANOS DE MOLGAS";
                    break;
                case 174:
                    cityName = "BARBADAS";
                    break;
                case 175:
                    cityName = "O BARCO DE VALDEORRAS";
                    break;
                case 176:
                    cityName = "BEADE";
                    break;
                case 177:
                    cityName = "BEARIZ";
                    break;
                case 178:
                    cityName = "OS BLANCOS";
                    break;
                case 179:
                    cityName = "BOBORAS";
                    break;
                case 180:
                    cityName = "A BOLA";
                    break;
                case 181:
                    cityName = "O BOLO";
                    break;
                case 182:
                    cityName = "CALVOS DE RANDIN";
                    break;
                case 183:
                    cityName = "CARBALLEDA DE AVIA";
                    break;
                case 184:
                    cityName = "CARBALLEDA DE VALDEORRAS";
                    break;
                case 185:
                    cityName = "O CARBALLINO";
                    break;
                case 186:
                    cityName = "CARTELLE";
                    break;
                case 187:
                    cityName = "CASTRELO DE MINO";
                    break;
                case 188:
                    cityName = "CASTRELO DO VAL";
                    break;
                case 189:
                    cityName = "CASTRO CALDELAS";
                    break;
                case 190:
                    cityName = "CELANOVA";
                    break;
                case 191:
                    cityName = "CENLLE";
                    break;
                case 192:
                    cityName = "CHANDREXA DE QUEIXA";
                    break;
                case 193:
                    cityName = "COLES";
                    break;
                case 194:
                    cityName = "CORTEGADA";
                    break;
                case 195:
                    cityName = "CUALEDRO";
                    break;
                case 196:
                    cityName = "ENTRIMO";
                    break;
                case 197:
                    cityName = "ESGOS";
                    break;
                case 198:
                    cityName = "GOMESENDE";
                    break;
                case 199:
                    cityName = "A GUDINA";
                    break;
                case 200:
                    cityName = "O IRIXO";
                    break;
                case 201:
                    cityName = "LAROUCO";
                    break;
                case 202:
                    cityName = "LAZA";
                    break;
                case 203:
                    cityName = "LEIRO";
                    break;
                case 204:
                    cityName = "LOBEIRA";
                    break;
                case 205:
                    cityName = "LOBIOS";
                    break;
                case 206:
                    cityName = "MACEDA";
                    break;
                case 207:
                    cityName = "MANZANEDA";
                    break;
                case 208:
                    cityName = "MASIDE";
                    break;
                case 209:
                    cityName = "MELON";
                    break;
                case 210:
                    cityName = "A MERCA";
                    break;
                case 211:
                    cityName = "A MEZQUITA";
                    break;
                case 212:
                    cityName = "MONTEDERRAMO";
                    break;
                case 213:
                    cityName = "MONTERREI";
                    break;
                case 214:
                    cityName = "MUINOS";
                    break;
                case 215:
                    cityName = "NOGUEIRA DE RAMUIN";
                    break;
                case 216:
                    cityName = "OIMBRA";
                    break;
                case 217:
                    cityName = "PADERNE DE ALLARIZ";
                    break;
                case 218:
                    cityName = "PADRENDA";
                    break;
                case 219:
                    cityName = "PARADA DE SIL";
                    break;
                case 220:
                    cityName = "O PEREIRO DE AGUIAR";
                    break;
                case 221:
                    cityName = "A PEROXA";
                    break;
                case 222:
                    cityName = "PETIN";
                    break;
                case 223:
                    cityName = "PINOR";
                    break;
                case 224:
                    cityName = "A POBRA DE TRIVES";
                    break;
                case 225:
                    cityName = "PONTEDEVA";
                    break;
                case 226:
                    cityName = "PORQUEIRA";
                    break;
                case 227:
                    cityName = "PUNXIN";
                    break;
                case 228:
                    cityName = "QUINTELA DE LEIRADO";
                    break;
                case 229:
                    cityName = "RAIRIZ DE VEIGA";
                    break;
                case 230:
                    cityName = "RAMIRAS";
                    break;
                case 231:
                    cityName = "RIBADAVIA";
                    break;
                case 232:
                    cityName = "RIOS";
                    break;
                case 233:
                    cityName = "A RUA";
                    break;
                case 234:
                    cityName = "RUBIA";
                    break;
                case 235:
                    cityName = "SAN AMARO";
                    break;
                case 236:
                    cityName = "SAN CIBRAO DAS VINAS";
                    break;
                case 237:
                    cityName = "SAN CRISTOVO DE CEA";
                    break;
                case 238:
                    cityName = "SAN XOAN DE RIO";
                    break;
                case 239:
                    cityName = "SANDIAS";
                    break;
                case 240:
                    cityName = "SARREAUS";
                    break;
                case 241:
                    cityName = "TABOADELA";
                    break;
                case 242:
                    cityName = "A TEIXEIRA";
                    break;
                case 243:
                    cityName = "TOEN";
                    break;
                case 244:
                    cityName = "TRASMIRAS";
                    break;
                case 245:
                    cityName = "A VEIGA";
                    break;
                case 246:
                    cityName = "VEREA";
                    break;
                case 247:
                    cityName = "VIANA DO BOLO";
                    break;
                case 248:
                    cityName = "VILAMARIN";
                    break;
                case 249:
                    cityName = "VILAMARTIN DE VALDEORRAS";
                    break;
                case 250:
                    cityName = "VILAR DE BARRIO";
                    break;
                case 251:
                    cityName = "VILAR DE SANTOS";
                    break;
                case 252:
                    cityName = "VILARDEVOS";
                    break;
                case 253:
                    cityName = "VILARINO DE CONSO";
                    break;
                case 254:
                    cityName = "XUNQUEIRA DE AMBIA";
                    break;
                case 255:
                    cityName = "XUNQUEIRA DE ESPADANEDO";
                    break;
                case 256:
                    cityName = "AGOLADA";
                    break;
                case 257:
                    cityName = "ARBO";
                    break;
                case 258:
                    cityName = "BAIONA";
                    break;
                case 259:
                    cityName = "BARRO";
                    break;
                case 260:
                    cityName = "BUEU";
                    break;
                case 261:
                    cityName = "CALDAS DE REIS";
                    break;
                case 262:
                    cityName = "CAMBADOS";
                    break;
                case 263:
                    cityName = "CAMPO LAMEIRO";
                    break;
                case 264:
                    cityName = "CANGAS";
                    break;
                case 265:
                    cityName = "A CANIZA";
                    break;
                case 266:
                    cityName = "CATOIRA";
                    break;
                case 267:
                    cityName = "CERDEDO-COTOBADE";
                    break;
                case 268:
                    cityName = "COVELO";
                    break;
                case 269:
                    cityName = "CRECENTE";
                    break;
                case 270:
                    cityName = "CUNTIS";
                    break;
                case 271:
                    cityName = "DOZON";
                    break;
                case 272:
                    cityName = "A ESTRADA";
                    break;
                case 273:
                    cityName = "FORCAREI";
                    break;
                case 274:
                    cityName = "FORNELOS DE MONTES";
                    break;
                case 275:
                    cityName = "GONDOMAR";
                    break;
                case 276:
                    cityName = "O GROVE";
                    break;
                case 277:
                    cityName = "A GUARDA";
                    break;
                case 278:
                    cityName = "A ILLA DE AROUSA";
                    break;
                case 279:
                    cityName = "LALIN";
                    break;
                case 280:
                    cityName = "A LAMA";
                    break;
                case 281:
                    cityName = "MEANO";
                    break;
                case 282:
                    cityName = "MEIS";
                    break;
                case 283:
                    cityName = "MOANA";
                    break;
                case 284:
                    cityName = "MONDARIZ";
                    break;
                case 285:
                    cityName = "MONDARIZ-BALNEARIO";
                    break;
                case 286:
                    cityName = "MORANA";
                    break;
                case 287:
                    cityName = "MOS";
                    break;
                case 288:
                    cityName = "AS NEVES";
                    break;
                case 289:
                    cityName = "NIGRAN";
                    break;
                case 290:
                    cityName = "OIA";
                    break;
                case 291:
                    cityName = "PAZOS DE BORBEN";
                    break;
                case 292:
                    cityName = "POIO";
                    break;
                case 293:
                    cityName = "PONTE CALDELAS";
                    break;
                case 294:
                    cityName = "PONTEAREAS";
                    break;
                case 295:
                    cityName = "PONTECESURES";
                    break;
                case 296:
                    cityName = "O PORRINO";
                    break;
                case 297:
                    cityName = "PORTAS";
                    break;
                case 298:
                    cityName = "REDONDELA";
                    break;
                case 299:
                    cityName = "RIBADUMIA";
                    break;
                case 300:
                    cityName = "RODEIRO";
                    break;
                case 301:
                    cityName = "O ROSAL";
                    break;
                case 302:
                    cityName = "SALCEDA DE CASELAS";
                    break;
                case 303:
                    cityName = "SALVATERRA DE MINO";
                    break;
                case 304:
                    cityName = "SANXENXO";
                    break;
                case 305:
                    cityName = "SILLEDA";
                    break;
                case 306:
                    cityName = "SOUTOMAIOR";
                    break;
                case 307:
                    cityName = "TOMINO";
                    break;
                case 308:
                    cityName = "TUI";
                    break;
                case 309:
                    cityName = "VALGA";
                    break;
                case 310:
                    cityName = "VILA DE CRUCES";
                    break;
                case 311:
                    cityName = "VILABOA";
                    break;
                case 312:
                    cityName = "VILAGARCIA DE AROUSA";
                    break;
                case 313:
                    cityName = "VILANOVA DE AROUSA";
                    break;
            }

            concatCities += cityName + ", ";
        }

        if (concatCities.endsWith(",")) {
            concatCities = concatCities.substring(0, concatCities.length() - 1);
        }


        dataMap.remove("comboCity");
        concatCities = concatCities.substring(0, concatCities.length() - 2);
        dataMap.put(AgencyGuideDao.CITY, concatCities);


        ArrayList<Integer> languagesValues = (ArrayList<Integer>) dataMap.get("comboLanguages");
        String concatLanguages = "";
        String languageName = "";

        for (Integer num : languagesValues) {
            switch (num) {
                case 1:
                    languageName = "SPANISH";
                    break;
                case 2:
                    languageName = "GALICIAN";
                    break;
                case 3:
                    languageName = "ENGLISH";
                    break;
                case 4:
                    languageName = "GERMAN";
                    break;
                case 5:
                    languageName = "PORTUGUESE";
                    break;
                case 6:
                    languageName = "FRENCH";
                    break;
                case 7:
                    languageName = "RUSSIAN";
                    break;
                case 8:
                    languageName = "ITALIAN";
                    break;
                case 9:
                    languageName = "BASQUE";
                    break;
                case 10:
                    languageName = "CATALAN";
                    break;
                case 11:
                    languageName = "POLISH";
                    break;
                case 12:
                    languageName = "UKRAINIAN";
                    break;
                case 13:
                    languageName = "DUTCH";
                    break;
                case 14:
                    languageName = "CHINESE";
                    break;
                case 15:
                    languageName = "ARABIC";
                    break;
            }

            concatLanguages += languageName + ", ";
        }

        if (concatLanguages.endsWith(",")) {
            concatLanguages = concatLanguages.substring(0, concatLanguages.length() - 1);
        }


        dataMap.remove("comboLanguages");
        concatLanguages = concatLanguages.substring(0, concatLanguages.length() - 2);
        dataMap.put(AgencyGuideDao.LANGUAGE, concatLanguages);

        return dataMap;
    }

    private static Map<String, Object> setWebsitePlaceholder(Map<String, Object> keysValues) {
        if (!keysValues.containsKey(BusinessDao.WEBSITE) || keysValues.get(BusinessDao.WEBSITE).equals("")) {
            keysValues.put(BusinessDao.WEBSITE, "Sin especificar");
        }
        return keysValues;
    }

    private String getBusinessType(Map<String, Object> keysValues) {

        int typeNum = (int) keysValues.get(BusinessDao.TYPE);

        if (typeNum == 1) {
            return "Restaurant";
        } else if (typeNum == 2) {
            return "Lodging";
        } else if (typeNum == 3) {
            return "AgencyGuide";
        }


        return null;
    }


    /**
     * Insert DOWN DATE in Business
     * @param keysValues
     * @return
     * @throws OntimizeJEERuntimeException
     */
    @Override
    public EntityResult businessDownDateDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        Map<String, Object> attributesValues = new HashMap<>();

        LocalDate ld = LocalDate.now();

        attributesValues.put(BusinessDao.DOWN_DATE, ld);

        return this.daoHelper.update(this.businessDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult businessUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.businessDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult businessDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.businessDao, keysValues);
    }

    @Override
    public AdvancedEntityResult businessPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy) {
        return this.daoHelper.paginationQuery(this.businessDao, keysValues, attributes, recordNumber, startIndex, orderBy, BusinessDao.QUERY_ALL_BUSINESSES);
    }

    @Override
    public AdvancedEntityResult businessMerchantPaginationQuery(Map<String, Object> keysValues, List<String> attributes, int recordNumber, int startIndex, List<?> orderBy) {
        keysValues.put(MerchantDao.MERCHANT_ID, merchantService.getMerchantId());

        return this.daoHelper.paginationQuery(this.businessDao, keysValues, attributes, recordNumber, startIndex, orderBy, "downDate");
    }

    @Override
    public EntityResult businessOfPackQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.businessDao, keysValues, attributes, BusinessDao.QUERY_BUSINESS_OF_PACK);
    }

}
