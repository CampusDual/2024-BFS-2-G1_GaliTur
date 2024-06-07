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
    public EntityResult hotelDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.hotelDao, keysValues);
    }
}
