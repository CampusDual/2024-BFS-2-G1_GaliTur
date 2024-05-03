package com.campusdual.cd2024bfs2g1.model.core.service;

import com.campusdual.cd2024bfs2g1.api.core.service.IClientService;
import com.campusdual.cd2024bfs2g1.model.core.dao.ClientDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import liquibase.pro.packaged.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Lazy
@Service("ClientService")
public class ClientService implements IClientService {
    private final ClientDao clientDao;
    private final DefaultOntimizeDaoHelper daoHelper;
    private final UserAndRoleService userAndRoleService;

    @Autowired
    public ClientService(ClientDao clientDao, DefaultOntimizeDaoHelper daoHelper, UserAndRoleService userAndRoleService) {
        this.clientDao = clientDao;
        this.daoHelper = daoHelper;
        this.userAndRoleService = userAndRoleService;
    }

    @Override
    public EntityResult clientQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList);
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
            if (clientEntityResult.getCode() == EntityResult.OPERATION_WRONG) {
                clientMap.remove(ClientDao.BIRTH_DATE);
                this.userAndRoleService.userDelete(clientMap);
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
}
