package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IClientService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserDao;
import com.campusdual.cd2024bfs2g1.model.core.dao.UserRoleDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.services.user.UserInformation;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Lazy
@Service("ClientService")
public class ClientService implements IClientService {
    private final ClientDao clientDao;
    private final UserRoleDao userRoleDao;
    private final DefaultOntimizeDaoHelper daoHelper;
    private final UserAndRoleService userAndRoleService;

    @Autowired
    public ClientService(ClientDao clientDao, UserRoleDao userRoleDao, DefaultOntimizeDaoHelper daoHelper, UserAndRoleService userAndRoleService) {
        this.clientDao = clientDao;
        this.userRoleDao = userRoleDao;
        this.daoHelper = daoHelper;
        this.userAndRoleService = userAndRoleService;
    }

    @Override
    public EntityResult clientQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList);
    }

    @Override
    public EntityResult isClientQuery(Map<String, Object> keymap, List<String> attrList) {
        EntityResult er = this.daoHelper.query(this.clientDao, keymap, attrList);
        int clientid = getClientId();


        List<Object> dataArray = new ArrayList<>();

        dataArray.add(clientid);


        er.put("data", dataArray);
        return er;
    }

    @Override
    public EntityResult clientInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Object birthDate = attrMap.get(ClientDao.BIRTH_DATE);
        attrMap.remove(ClientDao.BIRTH_DATE);
        EntityResult userEntityResult = userAndRoleService.userInsert(attrMap);

        if (userEntityResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
            Map<String, Object> clientMap = (Map<String, Object>) userEntityResult;
            clientMap.put(ClientDao.BIRTH_DATE, birthDate);
            EntityResult clientEntityResult = this.daoHelper.insert(this.clientDao, clientMap);
            clientMap.remove(ClientDao.BIRTH_DATE);

            if (clientEntityResult.getCode() == EntityResult.OPERATION_WRONG) {
                this.userAndRoleService.userDelete(clientMap);
            } else if (clientEntityResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
                clientMap.put(UserRoleDao.ROL_ID, 3);
                this.daoHelper.insert(this.userRoleDao, clientMap);
            }
            return clientEntityResult;
        }
        return userEntityResult;
    }

    @Override
    public EntityResult clientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.clientDao, attrMap, keyMap);
    }

    @Override
    public EntityResult clientDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientDao, keyMap);
    }


    /**
     * Gets logged client ID
     * @return Client ID
     */
    public Integer getClientId() {
        //Gets client object
        Object client = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Gets logged user id
        int userId = (int) ((UserInformation) client).getOtherData().get(UserDao.USR_ID);

        List<String> qKeys = new ArrayList<String>();
        qKeys.add(ClientDao.CLIENT_ID);

        Map<String, Object> emptyMap = new HashMap<>();
        emptyMap.put("CL." + ClientDao.USR_ID, userId);


        EntityResult clientEr = clientQuery(emptyMap, qKeys);
        ArrayList<Integer> al = (ArrayList<Integer>) clientEr.get(ClientDao.CLIENT_ID);

        if(al == null){
            return -1;
        }else {
            return al.get(0);
        }

    }
}
