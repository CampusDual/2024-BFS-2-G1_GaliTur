package com.campusdual.cd2024bfs2g1.model.core.service.business;

import com.campusdual.cd2024bfs2g1.api.core.service.business.IHotelService;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelRoomsDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.business.HotelServicesDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("HotelService")
public class HotelService implements IHotelService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelServicesDao hotelServicesDao;

    @Autowired
    private HotelRoomsDao hotelRoomsDao;

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @Autowired
    private HotelServicesService hotelServicesService;

    @Override
    public EntityResult hotelQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.hotelDao,keysValues, attributes);
    }


    @Override
    public EntityResult hotelMultiQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {

        attributes.remove("roomTypeSingle");
        attributes.remove("priceSingleRoom");
        attributes.remove("roomTypeDouble");
        attributes.remove("priceDoubleRoom");
        attributes.remove("roomTypeTriple");
        attributes.remove("priceTripleRoom");
        attributes.remove("toggleWifi");
        attributes.remove("toggleParking");
        attributes.remove("togglePool");
        attributes.remove("toggleBreakfast");
        attributes.remove("toggleLunch");
        attributes.remove("toggleDinner");


        EntityResult er = this.daoHelper.query(this.hotelDao,keysValues, attributes);


        ArrayList <Integer> hotelIdArr = (ArrayList<Integer>) er.get("htl_id");
        int hotelId = hotelIdArr.get(0);


        keysValues.put("htl_id",hotelId);
        keysValues.remove("bsn_id");
        attributes.add("srv_type");
        attributes.remove("bsn_id");

        EntityResult erServ = this.daoHelper.query(this.hotelServicesDao,keysValues, attributes);

        ArrayList <String> serviceList = (ArrayList<String>) erServ.get("srv_type");


        ArrayList <Boolean> bool = new ArrayList<>();
        bool.add(true);

        for(String service : serviceList){



            switch (service){
                case "Wifi":
                    er.put("toggleWifi",bool);
                    break;
                case "Parking":
                    er.put("toggleParking",bool);
                    break;
                case "Pool":
                    er.put("togglePool",bool);
                    break;
                case "Breakfast":
                    er.put("toggleBreakfast",bool);
                    break;
                case "Lunch":
                    er.put("toggleLunch",bool);
                    break;
                case "Dinner":
                    er.put("toggleDinner",bool);
                    break;
                default:
                    break;


            }

        }

        keysValues.put("htl_id",hotelId);
        keysValues.remove("bsn_id");
        attributes.add("rm_type");
        attributes.add("rm_cost");
        attributes.remove("srv_type");
        attributes.remove("bsn_id");


        EntityResult erRooms = this.daoHelper.query(this.hotelRoomsDao,keysValues, attributes);

        ArrayList <String> roomTypeList = (ArrayList<String>) erRooms.get("rm_type");
        ArrayList <BigDecimal> roomCostList = (ArrayList<BigDecimal>) erRooms.get("rm_cost");

        for(int i = 0; i<roomTypeList.size(); i++){
           String roomType = roomTypeList.get(i);
           BigDecimal rCost = roomCostList.get(i);
           double roomCost = rCost.doubleValue();
           ArrayList <Double> arrayCoste = new ArrayList<>();
           arrayCoste.add(roomCost);

            switch (roomType){
                case "Single":
                    er.put("roomTypeSingle",bool);
                    er.put("priceSingleRoom", arrayCoste);
                    break;
                case "Double":
                    er.put("roomTypeDouble",bool);
                    er.put("priceDoubleRoom", arrayCoste);
                    break;
                case "Triple":
                    er.put("roomTypeTriple",bool);
                    er.put("priceTripleRoom", arrayCoste);
                    break;

            }

        }


        return er;



    }

    @Override
    public EntityResult hotelInsert(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.hotelDao, keysValues);
    }

    @Override
    public EntityResult hotelUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.hotelDao, attributesValues, keysValues);
    }

    @Override
    public EntityResult hotelMultiUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {

        List <String> attributesRooms = new ArrayList<>();
        attributesRooms.add("rm_id");
        attributesRooms.add("rm_type");
        attributesRooms.add("rm_cost");




        Map<String, Object> mapaRooms = keysValues;
        mapaRooms.remove("bsn_id");

        EntityResult roomsEr =  this.daoHelper.query(this.hotelRoomsDao,mapaRooms, attributesRooms);

        List <Integer> idsRooms = (List<Integer>) roomsEr.get("rm_id");
        ArrayList <String> roomTypeList = (ArrayList<String>) roomsEr.get("rm_type");
        ArrayList <BigDecimal> roomCostList = (ArrayList<BigDecimal>) roomsEr.get("rm_cost");

        for(int i = 0; i<roomTypeList.size(); i++){
            int idRoom = idsRooms.get(i);
            String roomType = roomTypeList.get(i);

            Map <String, Object> mapSingleRoom = new HashMap<>();
            Map <String, Object> values = new HashMap<>();


            switch (roomType){
                case "Single":
                    if( attributesValues.containsKey("priceSingleRoom") && attributesValues.get("priceSingleRoom").toString().equals("")){
                        mapSingleRoom.put("rm_id",idRoom);
                        this.daoHelper.delete(this.hotelRoomsDao, mapSingleRoom);
                    }else if(attributesValues.containsKey("priceSingleRoom")){
                        Float pricelist = (Float) attributesValues.get("priceSingleRoom");


                        mapSingleRoom.put("rm_id",idRoom);
                        values.put("rm_cost",pricelist);
                        this.daoHelper.update(this.hotelRoomsDao, values, mapSingleRoom);

                    }

                    break;
                case "Double":
                    if( attributesValues.containsKey("priceDoubleRoom") && attributesValues.get("priceDoubleRoom").toString().equals("")){
                        mapSingleRoom.put("rm_id",idRoom);
                        this.daoHelper.delete(this.hotelRoomsDao, mapSingleRoom);
                    }else if(attributesValues.containsKey("priceDoubleRoom")){

                        Float pricelist = (Float) attributesValues.get("priceDoubleRoom");


                        mapSingleRoom.put("rm_id",idRoom);
                        values.put("rm_cost",pricelist);
                        this.daoHelper.update(this.hotelRoomsDao, values, mapSingleRoom);

                    }
                    break;
                case "Triple":
                    if( attributesValues.containsKey("priceTripleRoom") && attributesValues.get("priceTripleRoom").toString().equals("")){
                        mapSingleRoom.put("rm_id",idRoom);
                        this.daoHelper.delete(this.hotelRoomsDao, mapSingleRoom);
                    }else if(attributesValues.containsKey("priceTripleRoom")){

                        Float pricelist = (Float) attributesValues.get("priceTripleRoom");


                        mapSingleRoom.put("rm_id",idRoom);
                        values.put("rm_cost",pricelist);
                        this.daoHelper.update(this.hotelRoomsDao, values, mapSingleRoom);

                    }
                    break;

            }

        }

        if(attributesValues.containsKey("roomTypeSingle")){

            Map <String,Object> mapaHotel = new HashMap<>();
            Float price = (Float) attributesValues.get("priceSingleRoom");
            mapaHotel.put("rm_type","Single");
            mapaHotel.put("rm_cost",price);

            this.daoHelper.insert(this.hotelRoomsDao, mapaHotel);

        }

        if(attributesValues.containsKey("roomTypeDouble")){

            Map <String,Object> mapaHotel = new HashMap<>();
            Float price = (Float) attributesValues.get("priceDoubleRoom");
            mapaHotel.put("rm_type","Double");
            mapaHotel.put("rm_cost",price);

            this.daoHelper.insert(this.hotelRoomsDao, mapaHotel);

        }

        if(attributesValues.containsKey("roomTypeTriple")){

            Map <String,Object> mapaHotel = new HashMap<>();
            Float price = (Float) attributesValues.get("priceTripleRoom");
            mapaHotel.put("rm_type","Triple");
            mapaHotel.put("rm_cost",price);

            this.daoHelper.insert(this.hotelRoomsDao, mapaHotel);

        }

        /*
        if(attributesValues.containsKey("toggleWifi")){

            if(attributesValues.get("toggleWifi")){

            }

            Map <String,Object> mapaHotel = new HashMap<>();

            mapaHotel.put("srv_type","Wifi");
            mapaHotel.put("srv_cost",0.0);

            this.daoHelper.insert(this.hotelServicesDao, mapaHotel);

        }

        if(attributesValues.containsKey("toggleParking")){

            Map <String,Object> mapaHotel = new HashMap<>();

            mapaHotel.put("srv_type","Parking");
            mapaHotel.put("srv_cost",0.0);

            this.daoHelper.insert(this.hotelServicesDao, mapaHotel);
        }

        if(attributesValues.containsKey("togglePool")){

            Map <String,Object> mapaHotel = new HashMap<>();

            mapaHotel.put("srv_type","Pool");
            mapaHotel.put("srv_cost",0.0);

            this.daoHelper.insert(this.hotelServicesDao, mapaHotel);
        }

        if(attributesValues.containsKey("toggleBreakfast")){

            Map <String,Object> mapaHotel = new HashMap<>();

            mapaHotel.put("srv_type","Breakfast");
            mapaHotel.put("srv_cost",0.0);

            this.daoHelper.insert(this.hotelServicesDao, mapaHotel);
        }

        if(attributesValues.containsKey("toggleBreakfast")){

            Map <String,Object> mapaHotel = new HashMap<>();

            mapaHotel.put("srv_type","Breakfast");
            mapaHotel.put("srv_cost",0.0);

            this.daoHelper.insert(this.hotelServicesDao, mapaHotel);
        }
        */








        EntityResult er = this.daoHelper.update(this.hotelDao, attributesValues, keysValues);

        return er;
    }

    @Override
    public EntityResult hotelDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.hotelDao, keysValues);
    }

    /**
     * Process Services and Room hotel data
     *
     * @param dataMap Map with values
     */
    private void hotelServicesRoomsProcessor(Map<String, Object> dataMap) {
        if ((Boolean) dataMap.get("toggleWifi")) {
            insertHotelAttributes(dataMap, "Wifi");
        }else{

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
}
