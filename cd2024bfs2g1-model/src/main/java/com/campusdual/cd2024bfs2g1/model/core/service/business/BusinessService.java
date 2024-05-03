package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IBusinessService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.AgencyGuideDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.BusinessDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelServicesDao;
import com.campusdual.cd2024bfs2g1.model.core.service.MerchantService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("BusinessService")
public class BusinessService implements IBusinessService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private AgencyGuideService agencyGuideService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @Autowired
    private HotelServicesService hotelServicesService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public EntityResult businessQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.businessDao,keysValues, attributes);
    }

    @Override
    public EntityResult businessInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {


        Object merchant = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //int userId = ((UserInformation) merchant).otherData.get("usr_id");

        int userId = (int) ((UserInformation) merchant).getOtherData().get("usr_id");


        List <String> qKeys = new ArrayList<String>();
        qKeys.add("merchant_id");

        Map<String,Object> emptyMap = new HashMap<>();
        emptyMap.put("M.usr_id",userId);


        EntityResult merchantEr = merchantService.merchantQuery(emptyMap,qKeys);
        ArrayList<Integer> al = (ArrayList<Integer>) merchantEr.get("merchant_id");
        int idMerchant = al.get(0);

        keysValues.put("merchant_id",idMerchant);

        Map<String,Object> dataMap = new HashMap<>(keysValues);
        dataMap.remove("merchant_id");
        dataMap.remove("bsn_name");
        dataMap.remove("bsn_type");
        dataMap.remove("bsn_description");
        dataMap.remove("bsn_cif");
        dataMap.remove("bsn_address");
        dataMap.remove("bsn_phone");
        dataMap.remove("bsn_email");
        dataMap.remove("bsn_photos");
        dataMap.remove("bsn_website");
        dataMap.remove("bsn_schedule");

        String businessType = getBusinessType(keysValues);
        keysValues.replace("bsn_type",businessType);

        if(!keysValues.containsKey("bsn_website")){
            keysValues.put("bsn_website","Sin especificar");
        }

        EntityResult er = this.daoHelper.insert(this.businessDao, keysValues);

        int id = (int) er.get("bsn_id");

        dataMap.put("bsn_id",id);


        if(dataMap.containsKey("comboZone")){

            switch ((int)dataMap.get("comboZone")){
                case 1:
                    dataMap.remove("comboZone");
                    dataMap.put("gui_zone","CORUNA");
                    break;
                case 2:
                    dataMap.remove("comboZone");
                    dataMap.put("gui_zone","LUGO");
                    break;
                case 3:
                    dataMap.remove("comboZone");
                    dataMap.put("gui_zone","OURENSE");
                    break;
                case 4:
                    dataMap.remove("comboZone");
                    dataMap.put("gui_zone","PONTEVEDRA");
                    break;
            }

            ArrayList <Integer> cityValues = (ArrayList<Integer>) dataMap.get("comboCity");
            String concatCities = "";
            String cityName = "";

            for(Integer num: cityValues){
                switch (num){
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



                concatCities += cityName+", ";
            }



            dataMap.remove("comboCity");
            concatCities = concatCities.substring(0, concatCities.length()-2);
            dataMap.put("gui_city",concatCities);




            ArrayList <Integer> languagesValues = (ArrayList<Integer>) dataMap.get("comboLanguages");
            String concatLanguages = "";
            String languageName = "";

            for(Integer num: languagesValues){
                switch (num){
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



                concatLanguages += languageName+", ";
            }



            dataMap.remove("comboLanguages");
            concatLanguages = concatLanguages.substring(0, concatLanguages.length()-2);
            dataMap.put("gui_language",concatLanguages);





            return agencyGuideService.agencyGuideInsert(dataMap);

        }else if(dataMap.containsKey("rest_menu")){


            restaurantService.restaurantInsert(dataMap);


        }else if(dataMap.containsKey("toggleWifi")){



            EntityResult hotelEr = hotelService.hotelInsert(dataMap);

            int idHotel = (int) hotelEr.get("htl_id");

            dataMap.put("htl_id",idHotel);


            if((Boolean)dataMap.get("toggleWifi")){
                dataMap.put("srv_type","Wifi");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("toggleParking")){
                dataMap.put("srv_type","Parking");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("togglePool")){
                dataMap.put("srv_type","Pool");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("toggleBreakfast")){
                dataMap.put("srv_type","Breakfast");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("toggleLunch")){
                dataMap.put("srv_type","Lunch");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("toggleDinner")){
                dataMap.put("srv_type","Dinner");
                dataMap.put("srv_cost",0.0);
                hotelServicesService.hotelServicesInsert(dataMap);
            }

            if((Boolean)dataMap.get("roomTypeSingle")){
                dataMap.put("rm_type","Single");
                dataMap.put("rm_cost",dataMap.get("priceSingleRoom"));
                hotelRoomsService.hotelRoomsInsert(dataMap);
            }

            if((Boolean)dataMap.get("roomTypeDouble")){
                dataMap.put("rm_type","Double");
                dataMap.put("rm_cost",dataMap.get("priceDoubleRoom"));
                hotelRoomsService.hotelRoomsInsert(dataMap);
            }

            if((Boolean)dataMap.get("roomTypeTriple")){
                dataMap.put("rm_type","Triple");
                dataMap.put("rm_cost",dataMap.get("priceTripleRoom"));
                hotelRoomsService.hotelRoomsInsert(dataMap);
            }




        }


        return er;


    }

    private String getBusinessType(Map<String, Object> keysValues) {

        int typeNum = (int) keysValues.get("bsn_type");

        if(typeNum==1){
            return "Restaurant";
        }else if (typeNum==2){
            return "Lodging";
        } else if (typeNum==3) {
            return "AgencyGuide";
        }


        return null;
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
