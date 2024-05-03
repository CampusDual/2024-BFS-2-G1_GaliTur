package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IBusinessService;
import com.campusdual.cd2024bfs2g1.model.core.dao.MerchantDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.*;
import com.campusdual.cd2024bfs2g1.model.core.service.MerchantService;
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
    public EntityResult businessInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {


        keysValues.put(MerchantDao.MERCHANT_ID, getMerchantId());

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

    private static Map guideAgencyDataProcessor(Map<String, Object> dataMap) {
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
            }


            concatCities += cityName + ", ";
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
            }


            concatLanguages += languageName + ", ";
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
     * Returns merchant id from logged user
     *
     * @return merchant_id
     */
    private int getMerchantId() {
        Object merchant = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int userId = (int) ((UserInformation) merchant).getOtherData().get(UserDao.USR_ID);


        List<String> qKeys = new ArrayList<String>();
        qKeys.add(MerchantDao.MERCHANT_ID);

        Map<String, Object> emptyMap = new HashMap<>();
        emptyMap.put("M." + UserDao.USR_ID, userId);


        EntityResult merchantEr = merchantService.merchantQuery(emptyMap, qKeys);
        ArrayList<Integer> al = (ArrayList<Integer>) merchantEr.get(MerchantDao.MERCHANT_ID);
        return al.get(0);
    }


    @Override
    public EntityResult businessUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.businessDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult businessDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.businessDao, keysValues);
    }
}
